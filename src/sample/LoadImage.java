package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.BreakIterator;

public class LoadImage {
    public ImageView LoadedImage;
    ResultSet rs = null;
    public void ClickedShowImage (){
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=ArtPort;user=sa;password=0456";

        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL = "Select SelectedImage from myimages where Filepath = 'C:\\XYZ.png'";
            rs =stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            if (rs.next()) {
                Blob blob = rs.getBlob("SelectedImage");
                byte [] data = blob.getBytes( 1, ( int ) blob.length() );

                Image img = new Image(new ByteArrayInputStream(data));
                LoadedImage.setImage(img);
            }


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
