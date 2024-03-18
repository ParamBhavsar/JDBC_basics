import java.sql.*;
import java.util.Scanner;
public class JDBC5 {
    public static void main(String[] args) throws ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password = "param1234";
        String query = "SELECT * FROM employees WHERE e_name=? AND e_job_title=?;";
        String query2 = "INSERT INTO employees VALUES (?,?,?,?);";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,"Jack");
            ps.setString(2,"Senior Manager");
            ResultSet rs = ps.executeQuery();

            PreparedStatement ps2= con.prepareStatement(query2);
            ps2.setInt(1,5);
            ps2.setString(2,"mickey");
            ps2.setString(3,"Cashier");
            ps2.setDouble(4,3000);
            int result = ps2.executeUpdate();
            if(result>0) System.out.println("Insertion Successfully "+result+" rows affected");
            else System.out.println("Insertion failed");

            while (rs.next()){
                int id = rs.getInt("e_id");
                String name = rs.getString("e_name");
                String job = rs.getString("e_job_title");
                double salary = rs.getDouble("salary");
                System.out.println("e_id: "+id);
                System.out.println("e_name: "+name);
                System.out.println("e_job: "+job);
                System.out.println("e_salary: "+salary);
            }
            ps.close();
            ps2.close();
            rs.close();
            con.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
