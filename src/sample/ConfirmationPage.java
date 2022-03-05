package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationPage implements Initializable {
    int orderID;
    public Label Orderid;
    public void setorder (int id){
        orderID = id;
        Orderid.setText(String.valueOf(orderID));
    }
    @FXML
    public void gotoProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductPage.fxml"));
        try{
            loader.load();
        } catch (IOException ex){
            System.out.println("doesnt Work!");
        }

        Parent p = loader.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
