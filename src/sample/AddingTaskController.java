package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.mods.Task;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

public class AddingTaskController {

    private DatabaseHandler databaseHandler;
    private int userId;

    @FXML
    private Label taskAddedLabel;

    @FXML
    private Button viewToDosButton;

    @FXML
    private Button saveTaskButton;

    @FXML
    private TextField taskTextField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField taskTextFieldNew;

    @FXML
    private TextField descriptionFieldNew;

    @FXML
    private Button saveTaskButtonNew;

    @FXML
    private Button viewToDosButtonN;

    @FXML
    private Label taskAddedLabelN;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        saveTaskButton.setOnAction(event -> {

            Task task = new Task();
            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());      //java.sql.Timestamp

            String taskToAdd = taskTextField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskToAdd.equals("") || !taskDescription.equals("")) {

                System.out.println("User Id: " + AddTask.userId);

                task.setUserId(AddTask.userId);
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskToAdd);

                databaseHandler.loadUpTask(task);

                taskTextField.setText("");
                descriptionField.setText("");

                System.out.println("Task was added to To-Do list");
                taskAddedLabel.setVisible(true);
                viewToDosButton.setVisible(true);
                viewToDosButton.setOnAction(event1 -> {
                    //takes user to todoList itself
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
                });

            } else {
                System.out.println("Please, set the task and the description");
            }

        });
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println(this.userId);
    }

}
