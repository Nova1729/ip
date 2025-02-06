package chatbot.GUI;

import chatbot.Friday;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private TextArea messageArea;
    private TextField userInput;
    private Button sendButton;

    @Override
    public void start(Stage stage) {
        // Layout
        VBox layout = new VBox();
        messageArea = new TextArea();
        messageArea.setEditable(false);
        ScrollPane scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        sendButton = new Button("Send");

        HBox inputBox = new HBox(userInput, sendButton);
        inputBox.setSpacing(10);

        layout.getChildren().addAll(scrollPane, inputBox);

        // Scene and Stage
        Scene scene = new Scene(layout, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Friday Chatbot");
        stage.show();

        // Button Action
        sendButton.setOnAction(e -> handleUserInput());
    }

    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            messageArea.appendText("You: " + input + "\n");
            userInput.clear();
            String response = getResponse(input); // Link to Friday logic
            messageArea.appendText("Friday: " + response + "\n");
        }
    }

    private String getResponse(String input) {
        Friday chatbot = new Friday("tasks.txt");
        return chatbot.getResponse(input);
    }

    public static void main(String[] args) {
        launch(args);
    }
}



