package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class DeleteAccountSceneController {
    public AnchorPane rootDeleteAccount;
    public Label lblName1;
    public TextField txtEnterAccNum;
    public Button btnEnterAccNum;
    public Label lblCurrentAccBalance1;
    public Label lblName;
    public Label lblCurrentAccBalance;
    public Button btnDelete;
    public Button btnBack;
    public Button btnDeleteAnother;
    private static ArrayList<String[]> store6;
    
    public static int existIndex;
    public Label lblStatus;

    public void initialize(){
        lblName.setDisable(true);
        lblCurrentAccBalance.setDisable(true);
        txtEnterAccNum.requestFocus();
        btnDelete.setDisable(true);
        btnDeleteAnother.setDisable(true);
        lblStatus.setVisible(false);
    }

    public void btnEnterAccNumOnAction(ActionEvent actionEvent) {
        String accNum = txtEnterAccNum.getText().strip();
        if (accNumValidation(accNum)){
            txtEnterAccNum.selectAll();
            return;
        }
        lblName.setText(store6.get(existIndex)[1]);
        lblName.setDisable(false);
        lblCurrentAccBalance.setText(String.format("Rs.%,.2f",Double.valueOf(store6.get(existIndex)[2])));
        lblCurrentAccBalance.setDisable(false);
        btnDelete.setDisable(false);
        btnDelete.requestFocus();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Account Delete Confirmation Alert");
        alert.setContentText("Are You Sure to Delete This Account");
        alert.setHeaderText("Delete Account");

        Optional<ButtonType> reply = alert.showAndWait();
        if(reply.get() == ButtonType.OK){

            lblStatus.setVisible(true);
            btnDeleteAnother.setDisable(false);
            String[] deletePosition = new String[1];
            store6.set(existIndex,deletePosition);
            btnDelete.setDisable(true);


        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        MainSceneController mainSceneController = fxmlLoader.getController();
        mainSceneController.initData(store6);

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

        Stage primaryStage = (Stage) rootDeleteAccount.getScene().getWindow();
        primaryStage.close();
    }

    public void btnDeleteAnotherOnAction(ActionEvent actionEvent) {
        txtEnterAccNum.clear();
        txtEnterAccNum.promptTextProperty();
        lblName.setText("Name");
        lblCurrentAccBalance.setText("Rs.000,000.00");
        initialize();
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
    public static boolean accNumExist(String input){

        if(store6 == null){
            return true;
        }
        for (int i = 0 ; i < store6.size() ; i++) {
            if(store6.get(i)[0] == null) continue;
            else if (store6.get(i)[0].equals(input)){
                existIndex = i;
                return false;
            }

        }
        return true;
    }


    public void initData(ArrayList<String[]> data) {
        if(data == null){
            store6 = new ArrayList<String[]>();
        }store6 = data;
    }
}
