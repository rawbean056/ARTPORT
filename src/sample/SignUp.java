package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;


public class SignUp implements Initializable{
    public Button SignUpButton;
    public TextField Name;
    public TextField Email;
    public TextField Contact;
    public RadioButton malebutton ;
    public RadioButton femalebutton;
    public RadioButton othersbutton;
    public TextField password;
    public TextField ConfirmPassword;
    public String genderselected;
    public Label warning;
    public ToggleGroup genderToggleGroup;
    ResultSet rs = null;
    int flag =0;

    public void RadioButtonSelected(){
        if(this.genderToggleGroup.getSelectedToggle().equals(this.malebutton))
            genderselected = "male";
        if(this.genderToggleGroup.getSelectedToggle().equals(this.femalebutton))
            genderselected = "female";
        if(this.genderToggleGroup.getSelectedToggle().equals(this.othersbutton))
            genderselected = "others";
    }



    @FXML
    public void SubmitData(ActionEvent event) throws IOException,SQLException {
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=ArtPort;user=sa;password=0456";

        try {
            warning.setText(" ");
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL1 = "SELECT * FROM MEMBER WHERE MemberEmail LIKE '"+Email.getText()+"'";
            rs = stmt.executeQuery(SQL1);
            if (rs.next()==false){
                String SQL = "INSERT INTO MEMBER VALUES ('"+Name.getText()+"','"+Email.getText()+"','"+Contact.getText()+"','"+genderselected+"','"+password.getText()+"')";
                if (password.getText().equals(ConfirmPassword.getText()))
                {int s=stmt.executeUpdate(SQL);
                    if (s>0)
                    this.gotoLogin(event);


                }
                else
                    warning.setText("The password didnt match!");
            }
            else
            {
                warning.setText("The Email is taken!");
            }



            // Iterate through the data in the result set and display it.
            /* while (rs.next()) {
                 System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
             }*/
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
        }

        }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        genderToggleGroup = new ToggleGroup();
        this.malebutton.setToggleGroup(genderToggleGroup);
        this.femalebutton.setToggleGroup(genderToggleGroup);
        this.othersbutton.setToggleGroup(genderToggleGroup);
    }
    @FXML
    public void gotoLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
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


}
