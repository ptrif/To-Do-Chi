package sample;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.mods.Task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoListController {

    public static int userId;


    @FXML
    private AnchorPane rootAncPane;

    @FXML
    private ListView<Task> tasksList;

    @FXML
    private Button closeSesameButton;

    @FXML
    private Button addTaskButton2do;

    private ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
        addTaskButton2do.setOnAction(event -> {
            showAddTask();
        });

        tasks = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUserId(AddTask.userId);

        while (resultSet.next()) {
            System.out.println("user tasks: " + resultSet.getString("task"));

            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            tasks.addAll(task);
        }

        tasksList.setItems(tasks);
        tasksList.setCellFactory(CellController -> new CellController());


    }


    //think about log out button


    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        ToDoListController.userId = userId;
    }

    public void showAddTask(){
        tasksList.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/actualAddingTask.fxml"));
        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
