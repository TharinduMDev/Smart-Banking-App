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

public class CheckBalanceSceneController {
    public AnchorPane rootCheckBalance;
    public Label lblName1;
    public Label lblCurrentAccBalance1;
    public TextField txtEnterAccNum;
    public Button btnEnterAccNum;
    public Label lblName;
    public Label lblCurrentAccBalance;

    public Button btnBack;

    public static ArrayList<String[]> store5;

    public static int existIndex ;

    public void initialize(){
        lblName.setDisable(true);
        lblCurrentAccBalance.setDisable(true);
        txtEnterAccNum.requestFocus();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        MainSceneController mainSceneController = fxmlLoader.getController();
        mainSceneController.initData(store5);

        Scene MainScene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(MainScene);
        stage1.setTitle("Welcome to Smart Banking");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) rootCheckBalance.getScene().getWindow();
        primaryStage.close();
    }

    public void btnEnterAccNumOnAction(ActionEvent actionEvent) {
        String accNum = txtEnterAccNum.getText().strip();
        if (accNumValidation(accNum)){
            txtEnterAccNum.selectAll();
            return;
        }
        lblName.setText(store5.get(existIndex)[1]);
//        lblName.setVisible(true);
        lblName.setDisable(false);
//        lblCurentAccBalance1.setVisible(true);
        lblCurrentAccBalance.setText(String.format("Rs.%.2f",Double.valueOf(store5.get(existIndex)[2])));
        lblCurrentAccBalance.setDisable(false);
//        lblDepositAmount1.setVisible(true);
//        txtDeposiAmount.setVisible(true);
//        btnEnterDepositAmount.setVisible(true);
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

        if(store5 == null){
            return true;
        }
        for (int i = 0 ; i < store5.size() ; i++) {
            if (store5.get(i)[0].equals(intput)){
                existIndex = i;
                return false;
            }

        }
        return true;
    }

    public void initData(ArrayList<String[]> data) {

        if(data == null){
            store5 = new ArrayList<String[]>();
        }store5 = data;
    }
}
