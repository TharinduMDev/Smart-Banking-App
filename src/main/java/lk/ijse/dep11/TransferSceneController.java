package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class TransferSceneController {

    public AnchorPane rootTransfer;
    public Label lblFromName1;
    public Label lblCurrentFromAccBalance1;
    public TextField txtFromAccNum;
    public Button btnEnterFromAccNum;
    public TextField txtTransferAmount;
    public Button btnEnterTransferAmount;
    public Label lblFromName;
    public Label lblCurrentFromAccBalance;
    public Label lblTransferAmount1;
    public Label lblFromNewBalance1;
    public Label lblFromNewBalance;
    public TextField txtToAccNum;
    public Label lblCurrentToAccBalance1;
    public Label lblToName1;
    public Label lblToAccNum;
    public Label lblCurrentToAccBalance;
    public Label lblToName;
    public Button btnEnterToAccNum;
    public Label lblToNewBalance1;
    public Label lblToNewBalance;
    public Button btnTransferAgain;
    public Button btnBack;

    public int fromAccExistIndex;
    public int toAccExistIndex;

    public static int existIndex;

    public static ArrayList<String[]> store4 = new ArrayList<>();

    public void initialize(){
        lblFromName.setVisible(false);
        lblFromName1.setVisible(false);
        lblCurrentFromAccBalance.setVisible(false);
        lblCurrentFromAccBalance1.setVisible(false);
        lblToAccNum.setVisible(false);
        txtToAccNum.setVisible(false);
        btnEnterToAccNum.setVisible(false);
        lblToName1.setVisible(false);
        lblToName.setVisible(false);
        lblCurrentToAccBalance1.setVisible(false);
        lblCurrentToAccBalance.setVisible(false);
        lblTransferAmount1.setVisible(false);
        txtTransferAmount.setVisible(false);
        btnEnterTransferAmount.setVisible(false);
        lblFromNewBalance1.setVisible(false);
        lblFromNewBalance.setVisible(false);
        lblToNewBalance.setVisible(false);
        lblToNewBalance1.setVisible(false);
        txtFromAccNum.requestFocus();

    }

    public void btnEnterFromAccNumOnAction(ActionEvent actionEvent) {
        String accFromNum = txtFromAccNum.getText().strip();
        if (accNumValidation(accFromNum)){
            txtFromAccNum.selectAll();
            return;
        }
        fromAccExistIndex = existIndex;
        lblFromName.setText(store4.get(fromAccExistIndex)[1]);
        lblFromName1.setVisible(true);
        lblFromName.setVisible(true);
        lblCurrentFromAccBalance1.setVisible(true);
        lblCurrentFromAccBalance.setText("Rs."+ store4.get(fromAccExistIndex)[2]);
        lblCurrentFromAccBalance.setVisible(true);
        lblToAccNum.setVisible(true);
        txtToAccNum.setVisible(true);
        btnEnterToAccNum.setVisible(true);

    }

    public void btnEnterToAccNumOnAction(ActionEvent actionEvent) {
        String accToNum = txtToAccNum.getText().strip();
        if (accNumValidation(accToNum)){
            txtToAccNum.selectAll();
            txtToAccNum.requestFocus();
            return;
        }
        toAccExistIndex  = existIndex;
        if (fromAccExistIndex == toAccExistIndex) {
            CreateSceneController.error("Input Error","Invalid Input","From Account and to Account numbers are same!");
            txtToAccNum.selectAll();
            txtToAccNum.requestFocus();
            return;
        }
        lblToName.setText(store4.get(toAccExistIndex)[1]);
        lblToName1.setVisible(true);
        lblToName.setVisible(true);
        lblCurrentToAccBalance1.setVisible(true);
        lblCurrentToAccBalance.setText("Rs."+ store4.get(toAccExistIndex)[2]);
        lblCurrentToAccBalance.setVisible(true);
        lblTransferAmount1.setVisible(true);
        txtTransferAmount.setVisible(true);
        btnEnterTransferAmount.setVisible(true);
    }

    public void btnEnterTransferAmountOnAction(ActionEvent actionEvent) {
        String transferAmount = txtTransferAmount.getText().strip();
        if(transferValidation(transferAmount)){
            txtTransferAmount.requestFocus();
            return;
        }
        else if ((Double.valueOf(store4.get(fromAccExistIndex)[2]) - Double.valueOf(transferAmount) * 1.02) < 1000){
            CreateSceneController.error("Input Error","Less Amount","Insufficient amount in From account.Minimum balance after transaction should be Rs.1000.00 !");
            txtTransferAmount.selectAll();
            txtTransferAmount.requestFocus();
            return;
        }else {
            store4.get(toAccExistIndex)[2] = String.valueOf(Double.valueOf(store4.get(toAccExistIndex)[2]) + Double.valueOf(transferAmount));
            store4.get(fromAccExistIndex)[2] = String.valueOf(Double.valueOf(store4.get(fromAccExistIndex)[2]) - Double.valueOf(transferAmount)*1.02);

            lblFromNewBalance1.setVisible(true);
            lblFromNewBalance.setVisible(true);
            lblFromNewBalance.setText("Rs:" + store4.get(fromAccExistIndex)[2]);
            lblToNewBalance1.setVisible(true);
            lblToNewBalance.setVisible(true);
            lblToNewBalance.setText("Rs:" + store4.get(toAccExistIndex)[2]);
            btnTransferAgain.setVisible(true);
        }
    }

    public void btnTransferAgainOnAction(ActionEvent actionEvent) {

        txtToAccNum.clear();
        txtFromAccNum.clear();
        txtTransferAmount.clear();

        lblFromName.setVisible(false);
        lblFromName1.setVisible(false);
        lblCurrentFromAccBalance.setVisible(false);
        lblCurrentFromAccBalance1.setVisible(false);
        lblToAccNum.setVisible(false);
        txtToAccNum.setVisible(false);
        btnEnterToAccNum.setVisible(false);
        lblToName1.setVisible(false);
        lblToName.setVisible(false);
        lblCurrentToAccBalance1.setVisible(false);
        lblCurrentToAccBalance.setVisible(false);
        lblTransferAmount1.setVisible(false);
        txtTransferAmount.setVisible(false);
        btnEnterTransferAmount.setVisible(false);
        lblFromNewBalance1.setVisible(false);
        lblFromNewBalance.setVisible(false);
        lblToNewBalance.setVisible(false);
        lblToNewBalance1.setVisible(false);
        txtFromAccNum.requestFocus();

        //initialize();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        MainSceneController mainSceneController = fxmlLoader.getController();
        mainSceneController.initData(store4);

        Scene creatNewAccountScene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(creatNewAccountScene);
        stage1.setTitle("Welcome to Smart Banking");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) rootTransfer.getScene().getWindow();
        primaryStage.close();
    }

    public static boolean accNumValidation(String input){
        if (input.isEmpty()){
            CreateSceneController.error("Input Error","Invalid Input","Account number can't be empty!");
            return  true;
        }if (!input.startsWith("SBA-") || input.length()!= 8) {
            CreateSceneController.error("Input Error","Invalid Input","Account number should follow format \"SBA-xxxx\" !");
            return  true;
        }else {
            for (int i = 4; i < input.length(); i++) {
                if(!Character.isDigit(input.charAt(i))){
                    CreateSceneController.error("Input Error","Invalid Input","Account number should follow format \"SBA-xxxx\" !");
                    return  true;
                }
            }
        }
        if (accNumExist(input)){
            CreateSceneController.error("Input Error","Invalid Input","Account number doesn't exist!");
            return  true;
        }
        return false;
    }

    public static boolean accNumExist(String intput){

        if(store4 == null){
            return true;
        }
        for (int i=0 ; i < store4.size() ; i++) {
            if (store4.get(i)[0].equals(intput)){
                existIndex = i;

                return false;
            }

        }
        return true;
    }

    public boolean transferValidation(String input){
        if (input.isEmpty()){
            String title = "Transfer Amount Error";
            String header = "Invalid Input";
            String content = "Transfer amount can't be empty!";
            CreateSceneController.error(title,header,content);
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))){
                String title = "Transfer Amount Error";
                String header = "Invalid Input";
                String content = "Transfer amount should be a number!";
                CreateSceneController.error(title,header,content);
                return true;
            }
        }
        if (Integer.valueOf(input) < 100){
            String title = "Transfer Amount Error";
            String header = "Less amount";
            String content = "Minimum initial Transfer amount is Rs.100.00!";
            CreateSceneController.error(title,header,content);
            return true;
        }
        return false;
    }

    public void initData(ArrayList<String[]> data) {
        if (data == null){
            store4 = new ArrayList<String[]>();
        }
        store4 = data;
    }
}
