

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
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
                            String userName = in.nextLine();
                            String passWord = in.nextLine();
                            String points = in.nextLine();
                            ArrayList<String> deckStr = new ArrayList<>(8);
                            for (int i = 0 ; i < 8;i++){
                                deckStr.add(in.nextLine());
                            }
                            User user = new User(userName,passWord,points,User.genDeck(deckStr));
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
                    User user = new User(UserName.getText(),Password.getText(),"0",new ArrayList<>());
                    User.print(user);
                    Stage current = ((Stage)(((Button)event.getSource()).getScene().getWindow()));
                    Main.startGameMenu(user,current);
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
