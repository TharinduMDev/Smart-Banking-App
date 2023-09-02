package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class CreateSceneController {

    public Label lblSuccessful;
    @FXML
    private Button btnAddAccount;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane rootCreateAccount;

    @FXML
    private TextField txtAccNum;

    @FXML
    private TextField txtInitialDeposit;

    @FXML
    private TextField txtName;

    public ArrayList<String[]> store1;

    public void initialize(){

        txtAccNum.setDisable(true);
        txtName.setDisable(true);
        txtInitialDeposit.setDisable(true);
        btnSave.setDisable(true);
        lblSuccessful.setVisible(false);
        btnAddAccount.requestFocus();

    }

    @FXML
    void btnAddAccountOnAction(ActionEvent event) {
        lblSuccessful.setVisible(false);
        txtAccNum.setText(String.format("SBA-%04d",store1.size()+1));
        txtName.setDisable(false);
        txtInitialDeposit.setDisable(false);
        btnSave.setDisable(false);
        txtName.requestFocus();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        MainSceneController mainSceneController = fxmlLoader.getController();
        mainSceneController.initData(store1);

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

        Stage primaryStage = (Stage) rootCreateAccount.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String name = txtName.getText().strip().toUpperCase();
        String initialDeposit = txtInitialDeposit.getText().strip();
        if(nameValidation(name)){
            return;
        }
        else if (depositvalidation(initialDeposit)){
            return;
        }
        else {
            String[] newCustomer = new String[3];
            newCustomer[0] = txtAccNum.getText();
            newCustomer[1] = name;
            newCustomer [2] = initialDeposit;

            store1.add(newCustomer);
            lblSuccessful.setVisible(true);
            txtAccNum.clear();
            txtAccNum.promptTextProperty();
            txtAccNum.setDisable(true);
            txtName.clear();
            txtName.promptTextProperty();
            txtName.setDisable(true);
            txtInitialDeposit.clear();
            txtInitialDeposit.promptTextProperty();
            txtInitialDeposit.setDisable(true);
            btnSave.setDisable(true);

        }


    }

    public boolean nameValidation(String name){
        if(name.isEmpty()){
            String title = "Name Error";
            String header = "Invalid Input";
            String content = "Name can't be empty!";
            error(title,header,content);
            txtName.selectAll();
            txtName.requestFocus();
            return true;

        } else  {
            for (int i = 0; i < name.length(); i++) {
                if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                    String title = "Name Error";
                    String header = "Invalid Input";
                    String content = "Name can only contains Uppercase,lowercase and spaces!";
                    error(title,header,content);
                    txtName.selectAll();
                    txtName.requestFocus();
                    return true;
                }
            }

        }
        return false;
    }

    public boolean depositvalidation(String input){
        if (input.isEmpty()){
            String title = "Diposit Amount Error";
            String header = "Invalid Input";
            String content = "Deposit amount can't be empty!";
            error(title,header,content);
            txtInitialDeposit.selectAll();
            txtInitialDeposit.requestFocus();
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))){
                String title = "Diposit Amount Error";
                String header = "Invalid Input";
                String content = "Deposit amount should be a number!";
                error(title,header,content);
                txtInitialDeposit.selectAll();
                txtInitialDeposit.requestFocus();
                return true;
            }
        }
        if (Integer.valueOf(input) < 5000){
            String title = "Diposit Amount Error";
            String header = "Less amount";
            String content = "Minimum initial deposit amount is Rs.5000.00!";
            error(title,header,content);
            txtInitialDeposit.selectAll();
            txtInitialDeposit.requestFocus();
            return true;
        }
        return false;
    }

    public static void error(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void initData(ArrayList<String[]> data) {
        if (data == null){
            store1 = new ArrayList<String[]>();
        }else store1 = data;
    }

}
