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
import javafx.stage.Stage;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OrderPage implements Initializable {
    int userId;
    int artId;
    public Button back;
    public Label Name,Email,ordereditem,price;
    public Button show;
    ResultSet rs = null;
    String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=ArtPort;user=sa;password=0456";
    public void orderDetails (){
        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL = "INSERT INTO ORDERTAB VALUES ('"+userId+"','"+artId+"',GETDATE())";
            stmt.executeQuery(SQL);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void backtoProduct (ActionEvent event) throws IOException {
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
    public void fetchIDorder (int id){
        this.artId = id;
        System.out.println("art id in order page "+artId);

    }
    public void fetchuserIdorder (int id){
        this.userId = id;
        System.out.println("user id in order page  "+userId);
    }
    public void setUserName (String name){
        Name.setText(name);
    }
    public void setUserEmail (String email){
        Email.setText(email);
    }
    public void setOrdereditem (String title){
        ordereditem.setText(title);
    }
    public void setOrderPrice (String Price){
        price.setText(Price);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
