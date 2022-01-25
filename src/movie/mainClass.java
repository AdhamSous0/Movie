/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movie;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import static java.lang.Class.forName;
import java.util.Date;  
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class mainClass {
     
          PrintStream  kk=null;
          private Connection con=null;
          Statement      stat=null;
          Statement      stat2=null;
          ResultSet      rs=null;
          ResultSet      rs1=null;
          ResultSet      rs12=null;
          PreparedStatement prep=null;
          Scanner in= new Scanner (System.in);
          private Date data1;
            
 Connection getCon() throws Exception
      {
    
          String URL= "jdbc:ucanaccess://database//MovieDb.accdb";
          
          Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
         
          return DriverManager.getConnection(URL);
          
      }
 public mainClass()throws  Exception 
     {
         con=getCon();
    stat=con.createStatement();
     }
 public  ResultSet rs(){
           
           return  rs;
           
           }
 public  Statement Sta(){
          return stat ;
           
           
           }                 
 public   Connection cone(){
          return  con;
           
           }
 public  String Search(String id) throws Exception{
           int a=Integer.parseInt(id);
             PreparedStatement  P= con.prepareStatement("SELECT * FROM Table1 WHERE id LIKE ?");
        P.setString(1, id);
       ResultSet  rs= P.executeQuery();
       if(rs.next()==false)
           return "This id does not exist";
           else    
        return  "name : "+rs.getString(2)+"\n"+"data"+rs.getString(3)+"\n"+"rating :"+rs.getString(4)+"\n"+"time :"+rs.getString(5)+"Category :"+rs.getString(6)+"\n" ;
      }
 public void print()throws  Exception{
                          ResultSet  rs= stat.executeQuery("select * from Table1");
                while(rs.next())
        {
      
System.out.println(   rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
      
           
         
                   
                   
        }
             
                   }
 public String  rentVideo(String id,String idcus,String name,String Dateofrental,String  Dateofreturn)throws Exception{
                   String s=Search(id);
                   if(s.equals("This id does not exist")){
                       return "This id does not exist ";
                  }
  
             int a=Integer.parseInt(id);
             int b=Integer.parseInt(idcus);
         
           
            
         PreparedStatement  Pr= con.prepareStatement("insert into Table2 values (?,?,?,?,?);");
        Pr.setString(1, id);
       Pr.setString(2, name);
        Pr.setString(3, idcus);
        Pr.setString(4, Dateofrental);
        Pr.setString(5, Dateofreturn);
     Pr.executeUpdate();
        return "complet";
     }                  
 public String ReturnVideo(String id) throws Exception{
 
if(Search(id)=="This id does not exist"){
    
return "not found this video";
}
    
             PreparedStatement  P= con.prepareStatement("SELECT * FROM Table2 WHERE id LIKE ?");
        P.setString(1, id);
       ResultSet  rs= P.executeQuery();
       if(rs.next()==false)
           return "nat found";
              
       else{
                       PreparedStatement prep =
                 con.prepareStatement("delete from Table2  where id=?;");
      prep.setString(1, id);
       prep.executeUpdate(); 
 
       
       
       
       
       }
           
    
return "a";
}
 public void print2()throws  Exception{
              ResultSet  rs= stat.executeQuery("select * from Table2");
                while(rs.next())
        {
      
       System.out.println(rs.getInt(1)+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
     }
          
   }
 public void Add(String  id,String name,String data,String rating,String lingth,String Category )throws  Exception{
 
     
     PreparedStatement  P= con.prepareStatement("insert into Table1 values (?,?,?,?,?,?);");
   int a=Integer.parseInt(id);
        P.setString(1, id);
        P.setString(2, name);
        P.setString(3, data);
        P.setString(4, rating);
        P.setString(5, lingth);
         P.setString(6, Category ); 
        P.executeUpdate(); 

  } 
 public void delete(String id)throws  Exception{
                   PreparedStatement prep =
                 con.prepareStatement("delete from Table1  where id=?;");
         prep.setString(1,id);
         prep.executeUpdate(); 
 
          
          } 
 public String SearchForCustomer(String id)throws  Exception{
           PreparedStatement  P= con.prepareStatement("SELECT * FROM Table2 WHERE idcus LIKE ?");
       int a=Integer.parseInt(id);
        P.setInt(1, a);
       ResultSet  rs= P.executeQuery();
         String s="";
       if(rs.next()==true)
    return    s="id coustmor "+rs.getInt(3)+"\n id movie"+rs.getInt(1)+"\n name Coustmor "+rs.getString(2)+"\n Date of rental "+rs.getString(4)+"\n Date of return "+rs.getString(5);
       else
  
 return "This id does not exist";     
        } 
 public void serc(int a) throws Exception{
    
    
    }
 public void export (String s)throws  Exception {
    

                 Scanner in= null;
        PrintStream  kk=null;
        
  
        File  y= new File(s);
  
        if (y.exists()==false){
            
            System.out.println("file in not found");
         }
        else{
            
            try {
               
                kk= new PrintStream(s);
                
               
                 System.setOut(kk);
                ResultSet  rs= stat.executeQuery("select * from Table1");
                     
                    
                while(rs.next()){
int b=rs.getInt(1);
             System.out.println("id movie: "+ b +"\t Movie Name: "+ rs.getString(2)+" \t Date released: "+rs.getString(3)+
                        "\t  Rated: "+rs.getString(4)+"\t length: "+rs.getString(5)+"\t Category: "+rs.getString(6));
                 
                }
                
                kk.flush();
               kk.close();
                       kk=null;
            } catch (FileNotFoundException ex) {
              
            }
            finally{
                if (in!=null)
                    in.close();
                
                 if (kk!=null)
                    kk.close();
            }
            
            
            
        }
        
        
        
        
    }
 public String Import() throws Exception{
Scanner in= null;
    
        
        File  x= new File("import/import.txt");

  
        if (x.exists()==false){
            
            System.out.println("file in not found");
      
        }
        else{
            
           
                in= new Scanner(x);
          
      
               String  id;
                String name="";
                String Datereleased="";
                String Rated="";
                String length="";
                String Category="";
           
               //    in.nextLine();
                    
                  try {
                while(in.hasNext())
                {
            
                 id=in.next();
                 name=in.next();
                    Datereleased=in.next();
                     Rated=in.next();
                      length=in.next();
                       Category=in.next();
                             if(Search(id)!="This id does not exist"){
                             return id;
                             
                             } 
                       if(Search(id)=="This id does not exist" && id!=null && name !=null&& Datereleased != null
                               && Rated != null && length !=null && Category!=null  ){
                          
                    Add(id,name,Datereleased,Rated,length,Category);
                        }
                       else{
                              in.close();
                               in=null;
        
                          return "false";
                       }
                       
                    
                 }
                
          
                in.close();
                in=null;
         
            } catch (NoSuchElementException ex) {
                in.close();
                 in=null;
                
                 return "false";
            }
            finally{
                if (in!=null)
                    in.close();
                
            }
          
            
        }
  return "true";
}
    /**
     * @param args the command line arguments
     */
          
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
     menu a=new menu();
a.setVisible(true);

    }

};
