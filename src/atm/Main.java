package atm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {
    Button btn1,btn2;
    Label lb1,lb2;

    Stage stage;
    Scene scene1;

    public static double totalWithdrawn;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage=primaryStage;
        btn1 = new Button("Login");
        btn2 = new Button("&lt");
        lb1=new Label("Account Number");
        lb2=new Label("PIN");
        //btn1.setOnAction(e->handleButtonAction(e));

        stage.setTitle("ATM GUI");
        scene1 = new Scene(root, 980, 940);
        scene1.getStylesheets().add(getClass().getResource("atm.css").toExternalForm());
        stage.setScene(scene1);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
