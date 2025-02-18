package chatbot.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import chatbot.Friday;
import javafx.util.Duration;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/images2.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));

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
        if (!input.isEmpty()) {
            String response = chatbot.getResponse(input);

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBotDialog(response, botImage)
            );

            userInput.clear();

            // Check if the chatbot intends to exit
            if (chatbot.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1)); // 1-second delay
                delay.setOnFinished(event -> Platform.exit()); // Close the application after delay
                delay.play();
            }
        }
    }

}






