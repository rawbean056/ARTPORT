package sample;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;


public class Login implements Initializable {
    public Button CreateNewButton;
    public TextField Email;
    public PasswordField password;
    public Button LogInButton;
    public Label userwarning;
    ResultSet rs1 = null;
    int userId;
    String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=ArtPort;user=sa;password=0456";
    @FXML
    public void Login(ActionEvent event) throws IOException, SQLException {
        try {
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL1 = "Select * from MEMBER where MemberEmail like '"+Email.getText()+"' AND MemberPassword like '"+password.getText()+"'";
            rs1 = stmt.executeQuery(SQL1);
            if (rs1.next()) {
                userId = rs1.getInt("MemberId");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ProductPage.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    System.out.println("doesnt Work!");
                }
                ProductPage UserTraversal = loader.getController();
                UserTraversal.fetchID(userId);


                Parent p = loader.getRoot();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(p));
                stage.show();
            }
            else
                System.out.println("No such user is found!");
                userwarning.setText("No Such User Is Found!");
            // Handle any errors that may have occurred.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void changeScreen(ActionEvent event) throws IOException {
        Parent menuView = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene menuViewScene = new Scene(menuView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
