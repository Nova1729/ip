package chatbot.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import chatbot.Friday;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Friday chatbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/images2.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChatbot(Friday chatbot) {
        this.chatbot = chatbot;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chatbot.getResponse(input);
        if (!input.isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBotDialog(response, botImage)
            );
            userInput.clear();
        }
    }
}






