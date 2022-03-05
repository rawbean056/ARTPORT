package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class Controller {
    public Button SubmitButton;
    public TextField NameField;
    public TextField EmailField;
    public TextField ContactField;
    public TextField DOB;
    public TextField Password;
    public TextField Retyped;

    public Controller() throws IOException {
    }

    public void SubmitData(ActionEvent event){
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=ArtPort;user=sa;password=0456";

        try (Connection con = DriverManager.getConnection(connectionUrl);
             Statement stmt = con.createStatement();)
        {


                String SQL = "INSERT INTO MEMBER VALUES ('"+NameField.getText()+"','"+EmailField.getText()+"','"+ContactField.getText()+"','"+DOB.getText()+"','"+Password.getText()+"','"+Retyped.getText()+"')";
                if (Password.getText().equals(Retyped.getText()))
                {
                    stmt.executeQuery(SQL);
                }
                else
                {
                    System.out.println(Password.getText());
                    System.out.println(Retyped.getText());
                }



            // Iterate through the data in the result set and display it.
            /* while (rs.next()) {
                 System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
             }*/
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        Parent menuView = null;
        try {
            menuView = FXMLLoader.load(getClass().getResource("image.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene menuViewScene = new Scene(menuView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuViewScene);
        window.show();
    }

}
