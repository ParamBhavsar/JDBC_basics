import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

// to insert an image in database
public class JDBC6 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_users";
        String username = "root";
        String password = "param1234";
        String Image_path = "C:\\Users\\DELL\\Downloads\\wallpaperflare.com_wallpaper.jpg";
        String query = "Insert into image_table(img_data) values(?);";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            FileInputStream fi = new FileInputStream(Image_path);
            byte[] image_data =new byte[fi.available()];

            PreparedStatement ps = con.prepareStatement(query);
            ps.setBytes(1,image_data);
            int result = ps.executeUpdate();
            if(result>0) System.out.println("succesfully inserted");
            else System.out.println("insertion failed");

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
