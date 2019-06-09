package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.mods.User;

public class SignUp {

    @FXML
    private CheckBox signUpMale;

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
        signUpStartButton.setOnAction(event -> createUser());
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
}
