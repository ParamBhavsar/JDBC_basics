import java.io.*;
import java.sql.*;

// to store the image to folder from the database
public class JDBC7 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password ="param1234";
        String folder_path ="F:\\image\\"; //note add \\ after image as we further concat to image.jpg
        String query ="SELECT * FROM image_table where img_id = ?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,1);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){ //rs.next() initially at before 1st row and we check if image exist, if yes then
                byte[] image_data = rs.getBytes("img_data"); // img_data in sql is of byte and is an array so we get that using byte[] arr
                String image_path = folder_path+"ExtractedImage.png";   //as we have image_path but we need to give the name of that image which we are storing at that folder so concat it
                OutputStream os = new FileOutputStream(image_path);     //
                os.write(image_data);                 //will write the data ,which is in byte[] arr as we retrived from db to outputstream instance os path
                System.out.println("Image inserted to folder");
            }
            else{
                System.out.println("Image not found");
            }
            ps.close();
            con.close();
        }
        catch (SQLException | FileNotFoundException e){
            e.printStackTrace();  // connection | FileInputStream throws
        } catch (IOException e) { //fi.available throws
            throw new RuntimeException(e);
        }

    }
}

