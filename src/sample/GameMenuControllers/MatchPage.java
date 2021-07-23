package sample.GameMenuControllers;

import Classes.User;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * controller class for match page in main menu
 * contain methods and fields for corresponding fxml
 *
 * @author Mohammad hosein Aref
 * @version 1.0
 */

public class MatchPage implements GameMenController {
    private User user;

    /**
     * set users field
     *
     *
     * @param user  the logged in user
     */
    @Override
    public void setUser(User user) {
        this.user = user;
        File file = new File("./Saves/Games.xml");
        if (file.exists()) {
            try (Scanner in = new Scanner(new FileInputStream(file))) {
                while (in.hasNextLine()) {
                    String str = in.nextLine();
                    if (str.startsWith(user.getUserName())) {
                        Text text = new Text();
                        text.setText(str);
                        text.setStyle("-fx-background-color: Black;");
                        text.setStyle("-fx-font-family: Supercell-Magic;");
                        text.setStyle("-fx-font-size: 30");
                        vbox.getChildren().add(text);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private VBox vbox;
}
