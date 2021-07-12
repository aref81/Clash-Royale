import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * controller class for log in page
 * handles the login action for singing in
 * an existing user
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class SignInController {

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Password;

    /**
     * performs the action of logging in
     *
     * shows errors in text fields of username
     * if any of the circumstances are not met
     * loadss users from the file
     *
     *
     * @param event the "log in" key pressing
     * @throws IOException show error path
     * @throws ClassNotFoundException show error path
     */
    @FXML
    void LogIn(ActionEvent event) throws IOException, ClassNotFoundException {
        if (UserName.getText().isEmpty()|| Password.getText().isEmpty()){
            clearAll();
            UserName.setText("Please complete all Fields");
        }
        else {
            boolean found = false;
            File file = new File("./Saves/Users.xml");
            if (file.exists()){
                try (Scanner in = new Scanner(new FileInputStream(file))) {
                    while (in.hasNextLine()) {
                        User user= new User(in.nextLine(),in.nextLine());
                        if (user.getUserName().equals(UserName.getText()) && Password.getText().equals(user.getPassword())) {
                            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                            Main.startGame(user);
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (!found){
                clearAll();
                UserName.setText("User name or password is incorrect!");
            }
        }
    }

    /**
     *
     * clears whole fields of page
     *
     */
    private void clearAll(){
        UserName.setText("");
        Password.setText("");
    }
}
