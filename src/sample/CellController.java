package sample;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Database.DatabaseHandler;
import sample.mods.Task;

import java.io.IOException;
import java.sql.SQLException;

public class CellController extends JFXListCell<Task> {

    @FXML
    private Label dateLabel;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        if (fxmlLoader == null) {
            fxmlLoader = new FXMLLoader(getClass().getResource("/sample/cell.fxml"));
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            initialize();
            int taskId = myTask.getTaskId();

            taskLabel.setText(myTask.getTask());
            dateLabel.setText(myTask.getDatecreated().toString());
            descriptionLabel.setText(myTask.getDescription());

            deleteTaskButton.setOnAction(event -> {
                databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.deleteTask(Controller.userId, taskId);

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                getListView().getItems().remove(getItem());

            });

            setText(null);
            setGraphic(rootAnchorPane);
        }
    }
}
