import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;

public class SignInController {

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Password;

    @FXML
    void LogIn(ActionEvent event) throws IOException, ClassNotFoundException {
        if (UserName.getText().isEmpty()|| Password.getText().isEmpty()){
            clearAll();
            UserName.setText("Please complete all Fields");
        }
        else {
            boolean found = false;
            File file = new File("Users.txt");
            if (file.exists()){
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                while (true){
                    try {
                        User user = (User) in.readObject();
                        if (user.getUserName().equals(UserName.getText()) && Password.getText().equals(user.getPassword())) {
                            JOptionPane.showMessageDialog(null, "Logged In!");
                            found = true;
                            break;
                        }
                    }catch (EOFException e){
                        break;
                    }
                }
                in.close();
            }
            if (!found){
                clearAll();
                UserName.setText("User name or password is incorrect!");
            }
        }
    }

    private void clearAll(){
        UserName.setText("");
        Password.setText("");
    }
}
