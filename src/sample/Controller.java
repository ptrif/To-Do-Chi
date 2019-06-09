package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Animations.ShakeItUp;
import sample.Database.DatabaseHandler;
import sample.mods.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

//login Controller
public class Controller {

    private int userId;

    @FXML
    private TextField loginUserName;

    @FXML
    private Button loginOpenSesameButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginGetKeyButton;


    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        loginOpenSesameButton.setOnAction(event -> {
            String loginUsr = loginUserName.getText().trim();
            String loginPwd = loginPassword.getText().trim();
            User user = new User();
            user.setUserName(loginUsr);
            user.setPassword(loginPwd);

            ResultSet userString = databaseHandler.getUser(user);
            int count = 0;
            try {
                while (userString.next()) {
                    count++;
                    userId = userString.getInt("userid");
                }

                if (count == 1) {
                    System.out.println("SESAME IS OPEN!");
                    showAddTaskPage();

                } else {
                    ShakeItUp userNameShaker = new ShakeItUp(loginUserName);
                    ShakeItUp passwordShaker = new ShakeItUp(loginPassword);
                    userNameShaker.shake();
                    passwordShaker.shake();

                    System.out.println("Wrong username or password!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

// takes users to sign-up page
        loginGetKeyButton.setOnAction(event -> {
            loginGetKeyButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/signUp.fxml"));

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

    }

    // Takes users to AddTask page
    private void showAddTaskPage() {
       loginGetKeyButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/addTask2.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddTask addTask = loader.getController();
        addTask.setUserId(userId);


        stage.showAndWait();
    }

}
