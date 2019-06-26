package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTask {

    @FXML
    private Button viewButton;

    @FXML
    private Text noTaskForTodayText;

    @FXML
    private AnchorPane rootAncPane;

    @FXML
    private Button addTaskButtonB;

    @FXML
    void initialize() {
        viewButton.setOnAction(event -> showToDoList());

        addTaskButtonB.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            System.out.println("CLiCKED!");

            
            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/actualAddingTask.fxml"));
                Controller.userId = getUserId();

                rootAncPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    void setUserId(int userId) {
        Controller.userId = userId;
        System.out.println("User Id from Controller is: " + Controller.userId);
    }

    int getUserId() {
        return Controller.userId;
    }

    private void closeButtonAction(){
        Stage stage = (Stage) addTaskButtonB.getScene().getWindow();
        stage.close();
    }

    private void showToDoList(){
        //addTaskButtonB.getScene().getWindow().hide(); //убрать окно
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
        stage.show();
        closeButtonAction();
    }
}
