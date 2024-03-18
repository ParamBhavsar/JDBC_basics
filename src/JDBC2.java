import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC2 {
    public static  void main(String[] args) throws ClassNotFoundException
    {
        String url ="jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password = "param1234";
        String query = "select * from employees;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded sucessfully");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully...");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("e_id");
                String name = rs.getString("e_name");
                String job = rs.getString("e_job_title");
                double salary = rs.getDouble("salary");
                System.out.println();
                System.out.println("===========================");
                System.out.println("Employee id : "+id);
                System.out.println("Employee name : "+name);
                System.out.println("Employee job title : "+job);
                System.out.println("Employee salary : "+salary);
                System.out.println("===========================");
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Connection closed successfully...");
        }
        catch (Exception e){
            System.out.println("shit...");
            System.out.println(e.getMessage());
        }
    }
}
