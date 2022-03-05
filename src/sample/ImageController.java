package sample;


import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.io.InputStream;


public class ImageController{
    private File file;
    private FileChooser fc;
    FileInputStream fis;
    public Stage stage;
    public Button BrowsingButton;
    public javafx.scene.control.TextField SelectedFilePath;
    private Desktop desktop = Desktop.getDesktop();
    public Image image;
    public ImageView SelectedImage;

    public void chooseMouseClicked (){
       //System.out.println("The controller works!!!");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BrowsingWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
        } catch (IOException e) {
            System.out.println("Cant Load New Window!");
        }

        /*ends*/
        fc = new FileChooser();
        //The following snippet determines what type of files should windows explorer look for
       fc.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Image File","*.png","*.jpg","*.gif")
       );
       file = fc.showOpenDialog(stage);
       if (file != null){
           SelectedFilePath.setText(file.getAbsolutePath());
           image = new Image(file.toURI().toString(), 400, 300, true, true);
           SelectedImage.setImage(image);


       }
    }
    public void UploadClicked (){

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=ArtPort;user=sa;password=0456";

        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL = "INSERT INTO myimages values ('"+SelectedFilePath.getText()+"', (SELECT * FROM OPENROWSET(BULK N'"+SelectedFilePath.getText()+"', SINGLE_BLOB) as T2))";
            stmt.executeQuery(SQL);








            // Iterate through the data in the result set and display it.
            /* while (rs.next()) {
                 System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
             }*/
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
        /*for showing a new window*/
       /* try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BrowsingWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cant Load New Window!");
        }

        /*ends*/
        /*fc = new FileChooser();
       fc.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Image File","*.png","*.jpg","*.gif")
       );
       file = fc.showOpenDialog(stage);
       if (file != null){
           try {
               desktop.open(file);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }*/

