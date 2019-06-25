package sample;

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
import sample.Database.DatabaseHandler;
import sample.mods.Task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoListController {

    @FXML
    private AnchorPane rootAncPane;

    @FXML
    private ListView<Task> tasksList; //позволяет создавать списки в javafx, создается либо пустым либо с наблюдаемыми списками

    @FXML
    private Button closeSesameButton;

    @FXML
    private Button addTaskButton2do;

    private ObservableList<Task> tasks; //наблюдаемый список


    @FXML
    void initialize() throws SQLException {

        addTaskButton2do.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Go to Adding Task");
            showAddTask();

        });

        tasks = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUserId(Controller.userId);

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

        closeSesameButton.setOnAction(event -> {
            //some kinda of log out button
            // rootAncPane.getScene().getWindow().hide();
            Stage stage = (Stage) rootAncPane.getScene().getWindow();
            stage.close();
        });


    }

    private void closeButtonAction(){
        Stage stage = (Stage) tasksList.getScene().getWindow();
        stage.close();
    }

    private void showAddTask(){
        //tasksList.getScene().getWindow().hide();
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
        closeButtonAction();

    }

}
