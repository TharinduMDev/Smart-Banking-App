package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class MainSceneController {
    public AnchorPane root;
    public Button btnCreateAccount;
    public Button btnDeposits;
    public Button btnWithdrawals;
    public Button btnTransfer;
    public Button btnCheckBalance;
    public Button btnDeleteAccount;
    public Button btnExit;

    public static ArrayList<String[]> store;

    public  void initData(ArrayList<String[]> data){
        store = data;
    }


    public void btnCreateAccountOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CreateAccountScene.fxml"));
        AnchorPane rootCreatNewAccount = fxmlLoader.load();

        CreateSceneController createSceneController = fxmlLoader.getController();
        createSceneController.initData(store);

        Scene creatNewAccountScene = new Scene(rootCreatNewAccount);
        Stage stage1 = new Stage();
        stage1.setScene(creatNewAccountScene);
        stage1.setTitle("Smart Banking - Create new Account");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),rootCreatNewAccount);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.close();

    }

    public void btnDepositsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DepositScene.fxml"));
        AnchorPane rootDeppositScene = fxmlLoader.load();

        DepositSceneController depositSceneController = fxmlLoader.getController();
        depositSceneController.initData(store);

        Scene creatNewAccountScene = new Scene(rootDeppositScene);
        Stage stage1 = new Stage();
        stage1.setScene(creatNewAccountScene);
        stage1.setTitle("Smart Banking - Deposit");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),rootDeppositScene);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.close();
    }

    public void btnWithdrawalsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/WithdrawalScene.fxml"));
        AnchorPane rootWithdraw = fxmlLoader.load();

        WithdrawalSceneController withdrawalSceneController = fxmlLoader.getController();
        withdrawalSceneController.initData(store);

        Scene creatNewAccountScene = new Scene(rootWithdraw);
        Stage stage1 = new Stage();
        stage1.setScene(creatNewAccountScene);
        stage1.setTitle("Smart Banking - Withdraw");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),rootWithdraw);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.close();
    }

    public void btnTransferOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TransferScene.fxml"));
        AnchorPane rootWithdraw = fxmlLoader.load();

        TransferSceneController transferSceneController = fxmlLoader.getController();
        transferSceneController.initData(store);

        Scene creatNewAccountScene = new Scene(rootWithdraw);
        Stage stage1 = new Stage();
        stage1.setScene(creatNewAccountScene);
        stage1.setTitle("Smart Banking - Transfer");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),rootWithdraw);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.close();
    }

    public void btnCheckBalanceOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/CheckBalanceScene.fxml"));
        AnchorPane rootCheckBalance = fxmlLoader.load();

        CheckBalanceSceneController checkBalanceSceneController = fxmlLoader.getController();
        checkBalanceSceneController.initData(store);

        Scene checkBalanceScene = new Scene(rootCheckBalance);
        Stage stage1 = new Stage();
        stage1.setScene(checkBalanceScene);
        stage1.setTitle("Smart Banking - Check Balance");
        stage1.centerOnScreen();
        //stage1.setResizable(false);
        stage1.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),rootCheckBalance);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.playFromStart();

        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.close();
    }

    public void btnDeleteAccountOnAction(ActionEvent actionEvent) {
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
