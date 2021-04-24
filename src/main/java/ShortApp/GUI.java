package ShortApp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUI extends Application {
    private Stage stage;
    private Scene root;
    private Controller controller;


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        stage.setMinHeight(CONSTANTS.HEIGHT);
        stage.setMaxWidth(CONSTANTS.WIDTH);

        TextField commandInput = new TextField();
        commandInput.setMinSize(CONSTANTS.WIDTH, 70);
        commandInput.setBackground(new Background(new BackgroundFill(Color.hsb(220, 0.51, 0.34), CornerRadii.EMPTY, Insets.EMPTY)));
        commandInput.setLayoutX(0);
        commandInput.setLayoutY(0);
        commandInput.setStyle("-fx-text-inner-color: white;");
        commandInput.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 30));
        commandInput.setPadding(new Insets(5, 10, 5, 10));
        commandInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    controller.handelInput(commandInput.getText());
                    commandInput.setText("");
                }
            }
        });

        Label commandOutput = new Label();
        commandOutput.setMinSize(CONSTANTS.WIDTH, CONSTANTS.HEIGHT - commandInput.getMinHeight());
        commandOutput.setBackground(new Background(new BackgroundFill(Color.hsb(220, 0.51, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        commandOutput.setLayoutX(0);
        commandOutput.setLayoutY(commandInput.getMinHeight());
        commandOutput.setTextFill(Color.WHITE);
        commandOutput.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 20));
        commandOutput.setPadding(new Insets(5, 10, 5, 10));
        commandOutput.setWrapText(true);

        controller =  new Controller(commandOutput);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(commandInput, commandOutput);

        root = new Scene(pane);
        stage.setScene(root);
        stage.show();
    }
}
