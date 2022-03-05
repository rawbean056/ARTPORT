package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //here write the fxml file that you want to load as program
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("ArtPort");
        primaryStage.setScene(new Scene(root, 1840, 940));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
