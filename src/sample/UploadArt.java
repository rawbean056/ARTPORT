package sample;


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class UploadArt implements Initializable{
    public Button UploadButton;
    public TextField ArtImage;
    public TextField Title;
    public TextField Price;
    public TextField Artist;
    public ComboBox Category;
    public ImageView Art;
    private File file;
    private FileChooser fc;
    FileInputStream fis;
    public Stage stage;
    public java.awt.Button BrowsingButton;
    public javafx.scene.control.TextField SelectedFilePath;
    private Desktop desktop = Desktop.getDesktop();
    public Image image;
    public ImageView SelectedImage;
    ObservableList<String> CategoryList = FXCollections.observableArrayList("Landscape","Potrait","Sketch","Water Color","Oil Paint");
    public String selectedCategory;

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
            ArtImage.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString(), 1024, 720, true, true);
            Art.setImage(image);


        }
    }
    @FXML
    public void UploadData(ActionEvent event) throws IOException{
        // Create a variable for the connection string.
        selectedCategory = (String) Category.getValue();
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=ArtPort;user=sa;password=0456";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();)
        {


            String SQL = "INSERT INTO ARTWORK VALUES ('"+Title.getText()+"','"+Price.getText()+"','"+Artist.getText()+"','"+selectedCategory+"',(SELECT * FROM OPENROWSET(BULK N'"+ArtImage.getText()+"', SINGLE_BLOB) as T2))";
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductPage.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println("doesnt Work!");
        }



        Parent p = loader.getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Category.setValue("Landscape");
        Category.setItems(CategoryList);
    }
}
