package ShortApp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {
	private Stage stage;
	private Scene root;
	private Controller controller;


	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.setMinHeight(CONSTANTS.HEIGHT);
		stage.setMaxWidth(CONSTANTS.WIDTH);
		stage.getIcons().add(new Image("icon/icon.ico"));
		TextField commandInput = new TextField();
		commandInput.setMinSize(CONSTANTS.WIDTH, 70);
		commandInput.setMaxSize(CONSTANTS.WIDTH, 70);
		commandInput.setBackground(new Background(new BackgroundFill(Color.hsb(220, 0.51, 0.34), CornerRadii.EMPTY, Insets.EMPTY)));
		commandInput.setLayoutX(0);
		commandInput.setLayoutY(0);
		commandInput.setStyle("-fx-text-inner-color: white;");
		commandInput.setFont(Font.loadFont(getClass().getResource("/font/LexendDeca-Regular.ttf").toExternalForm(), 40));
		commandInput.setPadding(new Insets(5, 10, 5, 10));
		commandInput.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				controller.handelInput(commandInput.getText());
				commandInput.setText("");
			}
		});


		Text commandOutput = new Text();
		commandOutput.minWidth(CONSTANTS.WIDTH);
		commandOutput.minHeight(CONSTANTS.HEIGHT - commandInput.getMinHeight());
		commandOutput.setFill(Color.WHITE);
		commandOutput.setLayoutX(0);
		commandOutput.setLayoutY(0);
		commandOutput.setFont(Font.loadFont(getClass().getResource("/font/LexendDeca-Regular.ttf").toExternalForm(), 30));
		commandOutput.setWrappingWidth(CONSTANTS.WIDTH - 50);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setStyle("-fx-background: #3E5480; -fx-border-color: #2B6FAB;");
		scrollPane.setPadding(new Insets(20, 20, 20, 20));
		scrollPane.setMinSize(CONSTANTS.WIDTH, CONSTANTS.HEIGHT - commandInput.getMinHeight());
		scrollPane.setMaxSize(CONSTANTS.WIDTH, CONSTANTS.HEIGHT - commandInput.getMinHeight());
		scrollPane.setLayoutX(0);
		scrollPane.setLayoutY(commandInput.getMinHeight());
		scrollPane.setBorder(Border.EMPTY);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setContent(commandOutput);

		controller = new Controller(commandOutput, stage);

		AnchorPane pane = new AnchorPane();
		pane.getChildren().addAll(commandInput, scrollPane);

		root = new Scene(pane);
		stage.setScene(root);
		stage.show();

	}
}
