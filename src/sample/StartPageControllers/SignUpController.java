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
 * controller class for sign up page
 * handles the sign up action for
 * creating a new user
 *
 * @author Mohammad Hosein Aref
 * @version 1.0
 *
 */
public class SignUpController {

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField PasswordCheck;

    /**
     * performs the action of signing up
     *
     * shows an error in text field of username
     * if any of the circumstances are not met
     * saves users to file
     *
     * @param event the "sign up" key pressing
     * @throws IOException show error path
     * @throws ClassNotFoundException shows error path
     */
    @FXML
    void signUp(ActionEvent event) throws IOException, ClassNotFoundException {
        if (UserName.getText().isEmpty() || Password.getText().isEmpty() || PasswordCheck.getText().isEmpty()){
            clearAll();
            UserName.setText("Please complete all Fields");
        }
        else {
            if (Password.getText().equals(PasswordCheck.getText())) {
                boolean found = false;
                File file = new File("./Saves/Users.xml");
                if (file.exists()) {
                    try (Scanner in = new Scanner(new FileInputStream(file))) {
                        while (in.hasNextLine()) {
                            User user = new User(in.nextLine(),in.nextLine());
                            if (user.getUserName().equals(UserName.getText())) {
                                clearAll();
                                UserName.setText("This user name is already in use!");
                                found = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    file.createNewFile();
                }
                if (!found) {
                    try (PrintWriter out = new PrintWriter(new FileOutputStream(file,true))){
                        User user = new User(UserName.getText(),Password.getText());
                        out.println(user.getUserName());
                        out.println(user.getPassword());
                        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                        Main.startGame(user);
                    }
                }
            } else {
                clearAll();
                Password.setPromptText("Password and password check are not the same!");
                PasswordCheck.setPromptText("Password and password check are not the same!");
            }
        }
    }

    /**
     * clears whole fields of page
     *
     */
    private void clearAll(){
        UserName.setText("");
        PasswordCheck.setText("");
        Password.setText("");
    }

}
