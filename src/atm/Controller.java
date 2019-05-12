package atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Controller {
    private static final String CURRENCY_SYMBOL = "\u0024"; //unicode for dollar sign
    DecimalFormat df = new DecimalFormat(CURRENCY_SYMBOL + "###,##0.00");
    public double withdrawn; //amount withdrawn in one day

    @FXML
    private Button signInBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button balanceBtn;
    @FXML
    private Button withdrawBtn;
    @FXML
    private Button depositBtn;
    @FXML
    private Button transferBtn;
    @FXML
    private Button withdraw1;
    @FXML
    private Button withdraw5;
    @FXML
    private Button withdraw10;
    @FXML
    private Button withdraw15;
    @FXML
    private Button withdraw100;
    @FXML
    private Button withdrawOtherBtn;
    @FXML
    private Button printNo;
    @FXML
    private Button printYes;
    @FXML
    private Button withdrawEnter;
    @FXML
    private TextField withdrawalAmount;


    @FXML
    public void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
        Stage stage;
        Parent root = null;
        if(event.getSource()==signInBtn){
            stage=(Stage) signInBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("home.fxml"));
        }
        else if(event.getSource()==logoutBtn){
            stage=(Stage) logoutBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        }
        else if(event.getSource()==balanceBtn){
            stage=(Stage) balanceBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("balance.fxml"));
        }
        else if(event.getSource()==withdrawBtn){
            stage=(Stage) withdrawBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("withdraw.fxml"));
        }
        else if(event.getSource()==depositBtn){
            stage=(Stage) depositBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("deposit.fxml"));
        }
        else if(event.getSource()==transferBtn){
            stage=(Stage) transferBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("transfer.fxml"));
        }

        else if(event.getSource()==withdraw1 ||
                event.getSource()==withdraw5 ||
                event.getSource()==withdraw10 ||
                event.getSource()==withdraw15 ||
                event.getSource()==withdrawEnter){

            if(event.getSource()==withdraw1){
                withdrawn = 1;
                stage = (Stage) withdraw1.getScene().getWindow();
            }
            else if(event.getSource()==withdraw5){
                withdrawn = 5;
                stage = (Stage) withdraw5.getScene().getWindow();
            }
            else if(event.getSource()==withdraw10){
                withdrawn = 10;
                stage = (Stage) withdraw10.getScene().getWindow();
            }
            else if(event.getSource()==withdraw15){
                withdrawn = 15;
                stage = (Stage) withdraw15.getScene().getWindow();
            }
            else{
                String s = withdrawalAmount.getText().substring(1);
                withdrawn = Double.parseDouble(s);
                stage = (Stage) withdrawEnter.getScene().getWindow();
            }

            if((Main.totalWithdrawn + withdrawn) <= 15) {
                Main.totalWithdrawn += withdrawn;
                root = FXMLLoader.load(getClass().getResource("withdrawReceipt.fxml"));
            }
            else{
                final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("withdrawDeny.fxml"));
                fxmlLoader.getNamespace().put("labelText", df.format(15.00 - Main.totalWithdrawn));
                root = fxmlLoader.load();
            }
            System.out.println("Withdraw amount: " + withdrawn);
            System.out.println("Total withdrawn: " + Main.totalWithdrawn);

        }

        else if(event.getSource()== withdrawOtherBtn){
            stage = (Stage) withdrawOtherBtn.getScene().getWindow();
            if((Main.totalWithdrawn) <= 15) {
                root = FXMLLoader.load(getClass().getResource("withdrawOther.fxml"));
            }
            else{
                root = FXMLLoader.load(getClass().getResource("withdrawDeny.fxml"));
            }
        }

        else if(event.getSource()== printNo ||
                event.getSource()== printYes){
            stage=(Stage) printNo.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("home.fxml"));
        }

        else if(event.getSource()==backBtn){
            stage=(Stage) backBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("home.fxml"));
        }
        else {
            stage=(Stage) logoutBtn.getScene().getWindow();
        }

        Scene scene = new Scene(root, 980, 940);
        stage.setScene(scene);
        stage.show();
    }

    ArrayList<String> amount = new ArrayList<>();
    @FXML
    public void processNumPad(javafx.event.ActionEvent event) {
        String s = ((Button) event.getSource()).getText();
        amount.add(s);
        withdrawalAmount.setText("$" + amount.toString().replaceAll("\\[|\\]", "").replaceAll(", ","\t") + ".00");

    }
}