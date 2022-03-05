package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ProductPage implements Initializable {
    public Button UploadArtworkButton;
    public Label ProductPrice1;
    public Label ProductTitle1;
    public ImageView ProductImage1;
    public Label ProductPrice2;
    public Label ProductTitle2;
    public ImageView ProductImage2;
    public Label ProductPrice3;
    public Label ProductTitle3;
    public ImageView ProductImage3;
    public Label ProductPrice4;
    public Label ProductTitle4;
    public ImageView ProductImage4;
    public Label ProductPrice5;
    public Label ProductTitle5;
    public ImageView ProductImage5;
    public Label ProductPrice6;
    public Label ProductTitle6;
    public ImageView ProductImage6;
    public ImageView Show;
    public AnchorPane anchorscroll;
    public Button next;
    public TextField keyword;
    public TextField minprice;
    public TextField maxprice;
    public Button select;
    public AnchorPane anchor12;

    String Artist1,Artist2,Artist3,ArtCategory1,ArtCategory2,ArtCategory3,Artist4,Artist5,Artist6,ArtCategory4,ArtCategory5,ArtCategory6;
    int ArtID1;
    int ArtID2;
    int ArtID3;
    int ArtID4;
    int ArtID5;
    int ArtID6;
    int SelectedID;
    int userId;
    int artCount = 1;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=ArtPort;user=sa;password=0456";
    String SQL2 = "select ROW_NUMBER() over(ORDER BY ArtTitle) as rownumber,ArtID,ArtTitle,ArtPrice,ArtImage,Artist,ArtCategory from ARTWORK";

    public void ClickedNext(){
        this.Reset();
        artCount = artCount+6;
        this.ClickedShowImage();
    }
    public void ClickedPrev (){
        if (artCount>0){
            this.Reset();
            artCount =artCount-6;
            this.ClickedShowImage();
        }
    }
    public void filterkeyword(){
        SQL2 = "select ROW_NUMBER() over(ORDER BY ArtTitle) as rownumber,ArtID,ArtTitle,ArtPrice,ArtImage,Artist,ArtCategory from ARTWORK WHERE ArtTitle LIKE '%"+keyword.getText()+"%'";
        this.Reset();
        this.ClickedShowImage();
    }
    public void filterprice(){
        SQL2 = "select ROW_NUMBER() over(ORDER BY ArtTitle) as rownumber,ArtID,ArtTitle,ArtPrice,ArtImage,Artist,ArtCategory from ARTWORK WHERE ArtPrice >="+Integer.parseInt(minprice.getText())+" AND ArtPrice<="+Integer.parseInt(maxprice.getText());
        this.Reset();
        this.ClickedShowImage();
    }
    public void Reset (){
       ProductPrice1.setText(" ");
         ProductTitle1.setText(" ");
       ProductImage1.setImage(null);
       ProductPrice2.setText(" ");
       ProductTitle2.setText(" ");
         ProductImage2.setImage(null);
        ProductPrice3.setText(" ");
        ProductTitle3.setText(" ");
        ProductImage3.setImage(null);
         ProductPrice4.setText(" ");
       ProductTitle4.setText(" ");
        ProductImage4.setImage(null);
         ProductPrice5.setText(" ");
        ProductTitle5.setText(" ");
        ProductImage5.setImage(null);
       ProductPrice6.setText(" ");
         ProductTitle6.setText(" ");
         ProductImage6.setImage(null);

    }
    @FXML
    public void ClickedAnchor11(ActionEvent event)throws Exception {
        SelectedID = ArtID1;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SelectedProductPage.fxml"));
        try{
            loader.load();
        } catch (IOException ex){
            System.out.println("doesnt Work!");
        }
        SelectedProduct selectedproduct =  loader.getController();
        selectedproduct.fetchID(SelectedID);
        selectedproduct.fetchuserId(userId);
        selectedproduct.setArtCategory(ArtCategory1);
        selectedproduct.setArtist(Artist1);
        selectedproduct.setArtPrice(ProductPrice1.getText());
        selectedproduct.setImgage(ProductImage1.getImage());
        selectedproduct.setArtTitle(ProductTitle1.getText());

        Parent p = loader.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }

    @FXML
    public void ClickedAnchor12(ActionEvent event)throws Exception {
        SelectedID = ArtID2;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SelectedProductPage.fxml"));
        try{
            loader.load();
        } catch (IOException ex){
            System.out.println("doesnt Work!");
        }
        SelectedProduct selectedproduct =  loader.getController();
        selectedproduct.fetchID(SelectedID);
        selectedproduct.fetchuserId(userId);
        selectedproduct.setArtCategory(ArtCategory2);
        selectedproduct.setArtist(Artist2);
        selectedproduct.setArtPrice(ProductPrice2.getText());
        selectedproduct.setImgage(ProductImage2.getImage());
        selectedproduct.setArtTitle(ProductTitle2.getText());

        Parent p = loader.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }
    @FXML
    public void ClickedAnchor13(ActionEvent event)throws Exception {
        SelectedID = ArtID3;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SelectedProductPage.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println("doesnt Work!");
        }
        SelectedProduct selectedproduct = loader.getController();
        selectedproduct.fetchID(SelectedID);
        selectedproduct.fetchuserId(userId);
        selectedproduct.setArtCategory(ArtCategory3);
        selectedproduct.setArtist(Artist3);
        selectedproduct.setArtPrice(ProductPrice3.getText());
        selectedproduct.setImgage(ProductImage3.getImage());
        selectedproduct.setArtTitle(ProductTitle3.getText());

        Parent p = loader.getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();

    }
    @FXML
    public void ClickedAnchor21(ActionEvent event)throws Exception {
        SelectedID = ArtID4;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SelectedProductPage.fxml"));
        try{
            loader.load();
        } catch (IOException ex){
            System.out.println("doesnt Work!");
        }
        SelectedProduct selectedproduct =  loader.getController();
        selectedproduct.fetchID(SelectedID);
        selectedproduct.fetchuserId(userId);
        selectedproduct.setArtCategory(ArtCategory4);
        selectedproduct.setArtist(Artist4);
        selectedproduct.setArtPrice(ProductPrice4.getText());
        selectedproduct.setImgage(ProductImage4.getImage());
        selectedproduct.setArtTitle(ProductTitle4.getText());

        Parent p = loader.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }

    @FXML
    public void ClickedAnchor22(ActionEvent event)throws Exception {
        SelectedID = ArtID5;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SelectedProductPage.fxml"));
        try{
            loader.load();
        } catch (IOException ex){
            System.out.println("doesnt Work!");
        }
        SelectedProduct selectedproduct =  loader.getController();
        selectedproduct.fetchID(SelectedID);
        selectedproduct.fetchuserId(userId);
        selectedproduct.setArtCategory(ArtCategory5);
        selectedproduct.setArtist(Artist5);
        selectedproduct.setArtPrice(ProductPrice5.getText());
        selectedproduct.setImgage(ProductImage5.getImage());
        selectedproduct.setArtTitle(ProductTitle5.getText());

        Parent p = loader.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }
    @FXML
    public void ClickedAnchor23(ActionEvent event)throws Exception {
        SelectedID = ArtID6;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SelectedProductPage.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println("doesnt Work!");
        }
        SelectedProduct selectedproduct = loader.getController();
        selectedproduct.fetchID(SelectedID);
        selectedproduct.fetchuserId(userId);
        selectedproduct.setArtCategory(ArtCategory6);
        selectedproduct.setArtist(Artist6);
        selectedproduct.setArtPrice(ProductPrice6.getText());
        selectedproduct.setImgage(ProductImage6.getImage());
        selectedproduct.setArtTitle(ProductTitle6.getText());

        Parent p = loader.getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();

    }




    public void Count () {
        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL1 = "Select COUNT(ArtID) as count from ARTWORK";
            rs1 = stmt.executeQuery(SQL1);
            if (rs1.next()) {
                int Arts_Number = rs1.getInt("count");
                System.out.println(Arts_Number);
            }
            // Handle any errors that may have occurred.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void ClickedShowImage () {
                // Create a variable for the connection string.


                try {
                    Connection con = DriverManager.getConnection(connectionUrl);
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery(SQL2);

                    // Iterate through the data in the result set and display it.
                    while (rs.next()) {
                        if (rs.getInt("rownumber")==artCount) {
                            Blob blob = rs.getBlob("ArtImage");
                            byte[] data = blob.getBytes(1, (int) blob.length());

                            Image img = new Image(new ByteArrayInputStream(data));
                            ProductImage1.setImage(img);
                            ProductPrice1.setText("Price : "+rs.getString("ArtPrice"));
                            ProductTitle1.setText(rs.getString("ArtTitle"));
                            Artist1 = rs.getString("Artist");
                            ArtCategory1 = rs.getString("ArtCategory");
                            ArtID1 = rs.getInt("ArtID");
                        }
                        if (rs.getInt("rownumber")==artCount+1) {
                            Blob blob = rs.getBlob("ArtImage");
                            byte[] data = blob.getBytes(1, (int) blob.length());

                            Image img = new Image(new ByteArrayInputStream(data));
                            ProductImage2.setImage(img);
                            ProductPrice2.setText("Price : "+rs.getString("ArtPrice"));
                            ProductTitle2.setText(rs.getString("ArtTitle"));
                            Artist2 = rs.getString("Artist");
                            ArtCategory2 = rs.getString("ArtCategory");
                            ArtID2 = rs.getInt("ArtID");
                        }
                        if (rs.getInt("rownumber")==artCount+2) {
                            Blob blob = rs.getBlob("ArtImage");
                            byte[] data = blob.getBytes(1, (int) blob.length());

                            Image img = new Image(new ByteArrayInputStream(data));
                            ProductImage3.setImage(img);
                            ProductPrice3.setText("Price : "+rs.getString("ArtPrice"));
                            ProductTitle3.setText(rs.getString("ArtTitle"));
                            Artist3 = rs.getString("Artist");
                            ArtCategory3 = rs.getString("ArtCategory");
                            ArtID3 = rs.getInt("ArtID");
                        }
                        if (rs.getInt("rownumber")==artCount+3) {
                            Blob blob = rs.getBlob("ArtImage");
                            byte[] data = blob.getBytes(1, (int) blob.length());

                            Image img = new Image(new ByteArrayInputStream(data));
                            ProductImage4.setImage(img);
                            ProductPrice4.setText("Price : "+rs.getString("ArtPrice"));
                            ProductTitle4.setText(rs.getString("ArtTitle"));
                            Artist4 = rs.getString("Artist");
                            ArtCategory4 = rs.getString("ArtCategory");
                            ArtID4 = rs.getInt("ArtID");
                        }
                        if (rs.getInt("rownumber")==artCount+4) {
                            Blob blob = rs.getBlob("ArtImage");
                            byte[] data = blob.getBytes(1, (int) blob.length());

                            Image img = new Image(new ByteArrayInputStream(data));
                            ProductImage5.setImage(img);
                            ProductPrice5.setText("Price : "+rs.getString("ArtPrice"));
                            ProductTitle5.setText(rs.getString("ArtTitle"));
                            Artist5 = rs.getString("Artist");
                            ArtCategory5 = rs.getString("ArtCategory");
                            ArtID5 = rs.getInt("ArtID");
                        }
                        if (rs.getInt("rownumber")==artCount+5) {
                            Blob blob = rs.getBlob("ArtImage");
                            byte[] data = blob.getBytes(1, (int) blob.length());

                            Image img = new Image(new ByteArrayInputStream(data));
                            ProductImage6.setImage(img);
                            ProductPrice6.setText("Price : "+rs.getString("ArtPrice"));
                            ProductTitle6.setText(rs.getString("ArtTitle"));
                            Artist6 = rs.getString("Artist");
                            ArtCategory6 = rs.getString("ArtCategory");
                            ArtID6 = rs.getInt("ArtID");
                        }


                    }


                }
                // Handle any errors that may have occurred.
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            @FXML
            public void changeScreenTOupload (ActionEvent event) throws IOException {
                Parent UploadView = FXMLLoader.load(getClass().getResource("UploadArt.fxml"));
                Scene UploadViewScene = new Scene(UploadView);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(UploadViewScene);
                window.show();
            }
    public void fetchID (int ID) {
        this.userId = ID;
        System.out.println("userid in product page"+userId);
    }
            @Override
            public void initialize (URL url, ResourceBundle rb){
                // TODO
                this.Count();
                this.ClickedShowImage();
            }


        }
