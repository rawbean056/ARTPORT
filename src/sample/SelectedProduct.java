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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;



public class SelectedProduct implements Initializable {
    int SelectedArtID;
    public Label selectedArtLabel;
    public Label titleLabel;
    public Button back;
    public Button order;
    public Label artistLabel;
    public Label categoryLabel;
    public Label priceLabel;
    public ImageView selectedImage;
    int userId;
    int selectedartID;
    String MemberName;
    String MemberEmail;

    int selectedint;
    ResultSet rs = null;
    String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=ArtPort;user=sa;password=0456";




    @FXML
    public void goToOrder (ActionEvent event) throws IOException {
        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL2 = "select * from MEMBER WHERE MemberId ="+userId;
            rs = stmt.executeQuery(SQL2);
            if (rs.next()){
                MemberName = rs.getString("MemberName");
                MemberEmail = rs.getString("MemberEmail");
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderPage.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println("doesnt Work!");
        }
        OrderPage orderpage = loader.getController();
        orderpage.fetchIDorder(SelectedArtID);
        orderpage.fetchuserIdorder(userId);
        orderpage.setOrdereditem(titleLabel.getText());
        orderpage.setOrderPrice(priceLabel.getText());
        orderpage.setUserEmail(MemberEmail);
        orderpage.setUserName(MemberName);

        Parent p = loader.getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }

    @FXML
    public void backtoproduct (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductPage.fxml"));
        try{
            loader.load();
        } catch (IOException ex){
            System.out.println("doesnt Work!");
        }
        Parent p = loader.getRoot();
        Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }
    public void fetchID (int ID){
        this.SelectedArtID = ID;
        System.out.println(SelectedArtID);
        selectedArtLabel.setText(String.valueOf(SelectedArtID));


    }
    public void fetchuserId(int userid){
        this.userId = userid;
        System.out.println("user id in selected product"+userid);
    }
    public void setArtist(String artist){
        artistLabel.setText(artist);
    }
    public void setArtCategory(String ArtCategory){
        categoryLabel.setText(ArtCategory);
    }
    public void setArtPrice (String ArtPrice){
        priceLabel.setText(ArtPrice);
    }
    public void setImgage (Image img){
        selectedImage.setImage(img);
    }
    public void setArtTitle (String title){
        titleLabel.setText(title);
    }
}
