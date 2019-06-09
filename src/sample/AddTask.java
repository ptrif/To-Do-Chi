package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class AddTask {

    public static int userId;

    @FXML
    private Button viewButton;

    @FXML
    private ImageView addTaskButton;

    @FXML
    private Text noTaskForTodayText;

    @FXML
    private AnchorPane rootAncPane;


    @FXML
    void initialize() {
        viewButton.setOnAction(event -> showToDoList());

        addTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            System.out.println("Add Clicked!");

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2f), addTaskButton);
            FadeTransition textTransition = new FadeTransition(Duration.seconds(2f), noTaskForTodayText);

            addTaskButton.setOpacity(0);
            noTaskForTodayText.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            textTransition.setFromValue(1f);
            textTransition.setToValue(0f);
            textTransition.setCycleCount(1);
            textTransition.setAutoReverse(false);
            textTransition.play();

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/actualAddingTask.fxml"));
                AddTask.userId = getUserId();

                FadeTransition rootTransition = new FadeTransition(Duration.seconds(1), formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAncPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public void setUserId(int userId) {
        AddTask.userId = userId;
        System.out.println("User Id is " + AddTask.userId);
    }

    public int getUserId() {
        return userId;
    }

    public void showToDoList (){
        addTaskButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/ToDoList.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.showAndWait();
    }

}
