import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class JDBC4 {
    public static void main(String[] args){
        String url ="jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password = "param1234";
        String query = "DELETE from employees where e_id = 2;";
        String query2 = "UPDATE employees SET e_job_title ='Senior Manager',salary=75000 WHERE e_id=1; ";
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("connection established successfully...");
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            int rowsAffected2 = stmt.executeUpdate(query2);
            if(rowsAffected>0){
                System.out.println("DELETION successfull "+ rowsAffected +"row(s) affected");
            }
            else{
                System.out.println("DELETION failed");
            }
            if(rowsAffected2>0){
                System.out.println("UPDATION successfull "+ rowsAffected2 +"row(s) affected");
            }
            else{
                System.out.println("UPDATION failed");
            }
            stmt.close();
            con.close();
            System.out.println("connection closed...");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
