import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC3{
    public static void main (String[] args) throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }
        catch (ClassNotFoundException e){

            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_users",
                    "root",
                    "param1234"
            );
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate("insert into employees values(4,'Robert','Data Scientist',30000.00)");
            // will return 1 if insertion(DML) happened or 0.
            if(rowsAffected>0){
                System.out.println("Insertion Successfull : "+rowsAffected+ " rows affected");
            }
            else{
                System.out.println("Insertion Failed");
            }
            stmt.close();
            con.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
