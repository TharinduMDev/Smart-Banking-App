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
import lk.ijse.dep11.CreateSceneController.*;



public class DepositSceneController {
    public AnchorPane rootDeposit;
    public Label lblEnterName;
    public Label lblCurentAccBalance1;
    public TextField txtEnterAccNum;
    public Button btnEnterAccNum;
    public Label lblName;
    public Label lblCurrentAccBalance;
    public Label lblDepositAmount1;
    public TextField txtDeposiAmount;
    public Label lblNewBalance1;
    public Label lblNewBalance;
    public Button btnDeposit;
    public Button btnBack;
    public Button btnEnterDepositAmount;

    public static ArrayList<String[]> store2;

    public static String[] existIndex ;

    public void initialize(){
        lblName.setVisible(false);
        lblEnterName.setVisible(false);
        lblCurentAccBalance1.setVisible(false);
        lblCurrentAccBalance.setVisible(false);
        lblDepositAmount1.setVisible(false);
        txtDeposiAmount.setVisible(false);
        lblNewBalance1.setVisible(false);
        lblNewBalance.setVisible(false);
        btnDeposit.setVisible(false);
        btnEnterDepositAmount.setVisible(false);
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
        lblEnterName.setVisible(true);
        lblCurentAccBalance1.setVisible(true);
        lblCurrentAccBalance.setText("Rs."+existIndex[2]);
        lblCurrentAccBalance.setVisible(true);
        lblDepositAmount1.setVisible(true);
        txtDeposiAmount.setVisible(true);
        btnEnterDepositAmount.setVisible(true);
    }

    public void btnEnterDepositAmountOnAction(ActionEvent actionEvent) {
        String depositAmount = txtDeposiAmount.getText().strip();
        if(depositvalidation(depositAmount)){
            txtDeposiAmount.requestFocus();
            return;
        }
        existIndex[2] = String.valueOf(Integer.valueOf(existIndex[2]) + Integer.valueOf(depositAmount));
        lblNewBalance1.setVisible(true);
        lblNewBalance.setVisible(true);
        lblNewBalance.setText("Rs:"+ existIndex[2]);
        btnDeposit.setVisible(true);

    }

    public void btnDepositOnAction(ActionEvent actionEvent) {
       // btnDeposit.setDisable(true);
        txtEnterAccNum.clear();
        txtDeposiAmount.clear();
        lblName.setVisible(false);
        lblEnterName.setVisible(false);
        lblCurentAccBalance1.setVisible(false);
        lblCurrentAccBalance.setVisible(false);
        lblDepositAmount1.setVisible(false);
        txtDeposiAmount.setVisible(false);
        lblNewBalance1.setVisible(false);
        lblNewBalance.setVisible(false);
        btnDeposit.setVisible(false);
        btnEnterDepositAmount.setVisible(false);
        txtEnterAccNum.requestFocus();
        initialize();

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        MainSceneController mainSceneController = fxmlLoader.getController();
        mainSceneController.initData(store2);

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

        Stage primaryStage = (Stage) rootDeposit.getScene().getWindow();
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

        if(store2 == null){
            return true;
        }
        for (String[] customer: store2) {
            if (customer[0].equals(intput)){
                existIndex = customer;
                return false;
            }

        }
        return true;
    }

    public boolean depositvalidation(String input){
        if (input.isEmpty()){
            String title = "Deposit Amount Error";
            String header = "Invalid Input";
            String content = "Deposit amount can't be empty!";
            CreateSceneController.error(title,header,content);
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))){
                String title = "Deposit Amount Error";
                String header = "Invalid Input";
                String content = "Deposit amount should be a number!";
                CreateSceneController.error(title,header,content);
                return true;
            }
        }
        if (Integer.valueOf(input) < 500){
            String title = "Deposit Amount Error";
            String header = "Less amount";
            String content = "Minimum initial deposit amount is Rs.500.00!";
            CreateSceneController.error(title,header,content);
            return true;
        }
        return false;
    }

    public void initData(ArrayList<String[]> data) {
        if(data == null){
            store2 = new ArrayList<String[]>();
        }
        store2 = data;
    }
}
