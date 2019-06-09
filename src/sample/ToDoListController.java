package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.mods.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoListController {

    @FXML
    private ListView<Task> tasksList;

    @FXML
    private Button closeSesameButton;

    @FXML
    private Button saveTaskButtonList;

    @FXML
    private TextField descriptionFieldList;

    @FXML
    private TextField taskTextFieldList;

    private ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

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

    //think about saving tasks from ToDoList saver

    //think about refresher for List

    //think about log out button


}
