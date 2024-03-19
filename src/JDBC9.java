import java.sql.*;
import java.util.Scanner;

//batch processing with JDBC
//A job scheduler that allows you to schedule batch jobs to run at specific times or intervals.
public class JDBC9 {
    public static void main(String[] main) {
        String url = "jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password ="param1234";
        String query = "INSERT INTO EMPLOYEES VALUES(?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try{
                Connection con = DriverManager.getConnection(url,username,password);
                con.setAutoCommit(false);
                try{
                PreparedStatement ps = con.prepareStatement(query);
                Scanner sc = new Scanner(System.in);
                    while(true){
                    System.out.println("enter id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("enter name: ");
                    String name=sc.nextLine();
                    System.out.println("enter job title: ");
                    String job_title=sc.nextLine();
                    System.out.println("enter salary: ");
                    double salary=sc.nextDouble();
                    ps.setInt(1,id);
                    ps.setString(2,name);
                    ps.setString(3,job_title);
                    ps.setDouble(4,salary);
                    sc.nextLine(); // for proper input of next string
                    ps.addBatch(); //might throw:Sqlexception // Adds a set of parameters to this PreparedStatement object's batch of commands.
                        System.out.println("want to add more values? (Y/N):");
                        String decision = sc.nextLine();
                        if(decision.equalsIgnoreCase("N")){
                            break;
                        }
                    }
                    int[] batchResult = ps.executeBatch(); // Returns,an array of update counts containing one element for each command in the batch. The elements of the array are ordered according to the order in which commands were added to the batch.
                    //might throw BatchUpdateException,SqlException
                    con.commit();
                    ps.close();
                    System.out.println("BATCH executed successfully");
                }
                catch (BatchUpdateException e){
                    throw new RuntimeException(e);
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                con.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
