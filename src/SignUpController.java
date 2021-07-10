import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;

public class SignUpController {

    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField PasswordCheck;

    @FXML
    void signUp(ActionEvent event) throws IOException, ClassNotFoundException {
        if (UserName.getText().isEmpty() || Password.getText().isEmpty() || PasswordCheck.getText().isEmpty()){
            clearAll();
            UserName.setText("Please complete all Fields");
        }
        else {
            if (Password.getText().equals(PasswordCheck.getText())) {
                boolean found = false;
                File file = new File("Users.txt");
                if (file.exists()) {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    while (true) {
                        try {
                            User user = (User) in.readObject();
                            if (user.getUserName().equals(UserName.getText())) {
                                clearAll();
                                UserName.setPromptText("This user name is already in use!");
                                found = true;
                                in.close();
                                break;
                            }
                        }catch (EOFException e){
                            break;
                        }
                    }
                }
                if (!found) {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Users.txt"));
                    out.writeObject(new User(UserName.getText(), Password.getText()));
                    JOptionPane.showMessageDialog(null, "Signed Up!");
                    out.close();
                }
            } else {
                clearAll();
                Password.setPromptText("Password and password check are not the same!");
                PasswordCheck.setPromptText("Password and password check are not the same!");
            }
        }
    }

    private void clearAll(){
        UserName.setText("");
        PasswordCheck.setText("");
        Password.setText("");
    }

}
