import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String url = "jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password ="param1234";
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_users",
                    "root",
                    "param1234"
            );
            System.out.println("connection established !!");
            System.out.println(connection);
        }
        catch (Exception e){
            System.out.println("connection failed" + e.getMessage());
        }
    }
}