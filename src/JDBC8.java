import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//transaction handling
public class JDBC8 {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password ="param1234";
        String WithdrawQuery = "UPDATE my_accounts SET balance = balance - ? WHERE a_id = ?;";
        String DepositQuery = "UPDATE my_accounts SET balance = balance + ? WHERE a_id = ?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            con.setAutoCommit(false);
            try {
                PreparedStatement Withdraw_stmt = con.prepareStatement(WithdrawQuery);
                PreparedStatement Deposit_stmt = con.prepareStatement(DepositQuery);
                Withdraw_stmt.setDouble(1, 250.00);
                Withdraw_stmt.setString(2, "account123");
                Deposit_stmt.setDouble(1, 250.00);
                Deposit_stmt.setString(2, "account567");
                int rowsAffected = Withdraw_stmt.executeUpdate();
                int rowsAffected2 = Deposit_stmt.executeUpdate();
                if (rowsAffected > 0 && rowsAffected2 > 0) {
                    con.commit();
                    System.out.println("Transaction Successful");
                } else {
                    con.rollback();
                    System.out.println("Failed transaction");
                }
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
