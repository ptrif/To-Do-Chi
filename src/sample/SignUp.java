package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.mods.User;

import java.io.IOException;

public class SignUp {

    @FXML
    private CheckBox signUpFemale;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpStartButton;

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpFullName;


    @FXML
    void initialize() {
        signUpStartButton.setOnAction(event -> {
            createUser();
            takeUserBack();

        });
    }

    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = signUpFullName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();
        String gender;

        if (signUpFemale.isSelected()) {
            gender = "Female";
        } else {
            gender = "Male";
        }

        User user = new User(name, userName, password, gender);

        databaseHandler.signUpUser(user);
        System.out.println("You got the key!");
    }

    private void closeButtonAction(){
        Stage stage = (Stage) signUpStartButton.getScene().getWindow();
        stage.close();
    }

    private void takeUserBack() {
        //signUpStartButton.getScene().getWindow().hide();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/login.fxml"));

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
