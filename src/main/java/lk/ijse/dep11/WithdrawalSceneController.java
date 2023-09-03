package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.util.Duration;



public class WithdrawalSceneController {
    public AnchorPane rootWithdraw;
    public Label lblName1;
    public Label lblCurrentAccBalance1;
    public TextField txtEnterAccNum;
    public Button btnEnterAccNum;
    public Label lblName;
    public Label lblCurrentAccBalance;
    public Label lblNewBalance1;
    public Label lblNewBalance;
    public Button btnWithdraw;
    public Button btnBack;
    public TextField txtWithdrawAmount;
    public Button btnEnterWithdrawAmount;
    public Label lblWithdrawAmount1;

    public static ArrayList<String[]> store3;

    public static String[] existIndex;

    public void initialize(){
        lblName.setVisible(false);
        lblName1.setVisible(false);
        lblCurrentAccBalance1.setVisible(false);
        lblCurrentAccBalance.setVisible(false);
        lblWithdrawAmount1.setVisible(false);
        txtWithdrawAmount.setVisible(false);
        lblNewBalance1.setVisible(false);
        lblNewBalance.setVisible(false);
        btnWithdraw.setVisible(false);
        btnEnterWithdrawAmount.setVisible(false);
        txtEnterAccNum.requestFocus();

    }

    public void btnEnterAccNumOnAction(ActionEvent actionEvent) {
        String accNum = txtEnterAccNum.getText().strip();
        if (accNumValidation(accNum)){
            txtEnterAccNum.selectAll();
            return;
        }
        lblName.setText(existIndex[1]);
        lblName.setVisible(true);
        lblName1.setVisible(true);
        lblCurrentAccBalance1.setVisible(true);
        lblCurrentAccBalance.setText("Rs."+ existIndex[2]);
        lblCurrentAccBalance.setVisible(true);
        lblWithdrawAmount1.setVisible(true);
        txtWithdrawAmount.setVisible(true);
        btnEnterWithdrawAmount.setVisible(true);
    }


    public void btnWithdrawOnAction(ActionEvent actionEvent) {
        txtEnterAccNum.clear();
        txtWithdrawAmount.clear();
        lblName.setVisible(false);
        lblName1.setVisible(false);
        lblCurrentAccBalance1.setVisible(false);
        lblCurrentAccBalance.setVisible(false);
        lblWithdrawAmount1.setVisible(false);
        txtWithdrawAmount.setVisible(false);
        lblNewBalance1.setVisible(false);
        lblNewBalance.setVisible(false);
        btnWithdraw.setVisible(false);
        btnEnterWithdrawAmount.setVisible(false);
        txtEnterAccNum.requestFocus();
        initialize();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        MainSceneController mainSceneController = fxmlLoader.getController();
        mainSceneController.initData(store3);

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

        Stage primaryStage = (Stage) rootWithdraw.getScene().getWindow();
        primaryStage.close();
    }

    public void btnEnterWithdrawAmountOnAction(ActionEvent actionEvent) {
        String withdrawAmount = txtWithdrawAmount.getText().strip();
        if(WithdrawValidation(withdrawAmount)){
            txtWithdrawAmount.requestFocus();
            return;
        }
        if(Integer.valueOf(existIndex[2]) - Integer.valueOf(withdrawAmount) < 1000){
            CreateSceneController.error("Input Error", "insufficient Amount", "Minimum balance after withdrawal should be Rs.1000.00 !");
            txtWithdrawAmount.requestFocus();
            txtWithdrawAmount.selectAll();

        }else {
            existIndex[2] = String.valueOf(Integer.valueOf(existIndex[2]) - Integer.valueOf(withdrawAmount));
            lblNewBalance1.setVisible(true);
            lblNewBalance.setVisible(true);
            lblNewBalance.setText("Rs:" + existIndex[2]);
            btnWithdraw.setVisible(true);
        }
    }

    public boolean WithdrawValidation(String input){
        if (input.isEmpty()){
            String title = "Withdraw Amount Error";
            String header = "Invalid Input";
            String content = "Withdraw amount can't be empty!";
            CreateSceneController.error(title,header,content);
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))){
                String title = "Withdraw Amount Error";
                String header = "Invalid Input";
                String content = "Withdraw amount should be a number!";
                CreateSceneController.error(title,header,content);
                return true;
            }
        }
        if (Integer.valueOf(input) < 100){
            String title = "Withdraw Amount Error";
            String header = "Less amount";
            String content = "Withdraw initial deposit amount is Rs.100.00!";
            CreateSceneController.error(title,header,content);
            return true;
        }
        return false;
    }

    public static boolean accNumExist(String intput){

        if(store3 == null){
            return true;
        }
        for (String[] customer: store3) {
            if (customer[0].equals(intput)){
                existIndex = customer;
                return false;
            }

        }
        return true;
    }

    public static boolean accNumValidation(String input) {
        if (input.isEmpty()) {
            CreateSceneController.error("Input Error", "Invalid Input", "Account number can't be empty!");
            return true;
        }
        if (!input.startsWith("SBA-") || input.length() != 8) {
            CreateSceneController.error("Input Error", "Invalid Input", "Account number should follow format \"SBA-xxxx\" !");
            return true;
        } else {
            for (int i = 4; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    CreateSceneController.error("Input Error", "Invalid Input", "Account number should follow format \"SBA-xxxx\" !");
                    return true;
                }
            }
        }
        if (accNumExist(input)){
            CreateSceneController.error("Input Error","Invalid Input","Account number doesn't exist!");
            return  true;
        }
        return  false;
    }


    public void initData(ArrayList<String[]> data) {
        if(data == null){
            store3 = new ArrayList<String[]>();
        }
        store3 = data;
    }
}
