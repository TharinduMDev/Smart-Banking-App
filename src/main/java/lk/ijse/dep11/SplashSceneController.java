package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashSceneController {
    public AnchorPane rootSplash;
    public ImageView imgIcon;
    public Label lblLoading;

    public void initialize() throws IOException, InterruptedException {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(1000), event -> {
            lblLoading.setText("Application is being initialized...");
        });

        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(2000), event -> {
            lblLoading.setText("Setting up Tools...");
        });

        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(3000), event -> {
            lblLoading.setText("Setting up UI...");
        });

        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(4000), event -> {

            try {
                AnchorPane Mainroot = FXMLLoader.load(getClass().getResource("/view/MainScene.fxml"));
                Scene MainScene = new Scene(Mainroot);

                Stage MainStage = new Stage();
                MainStage.setScene(MainScene);
                MainStage.setTitle("Welcome to Smart Banking");
                MainStage.setResizable(false);
                MainStage.centerOnScreen();
                MainStage.show();
                lblLoading.getScene().getWindow().hide();
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
        timeline.playFromStart();
    }
}
