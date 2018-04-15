package game;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Menu extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.setPrefSize(200, 200);
		root.setSpacing(20);
		root.setPadding(new Insets(10));
		primaryStage.setScene(new Scene(root));
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.show();
		primaryStage.setTitle("MyGame");

		
		TextField name = new TextField();
		Button score = new Button("Wyniki");
		Button play = new Button("Graj");
		root.getChildren().add(name);
		root.getChildren().add(play);
		root.getChildren().add(score);
		
		play.setOnMouseClicked(new EventHandler<MouseEvent>() {
		GameFX playStart = new GameFX();
			@Override
			public void handle(MouseEvent event) {

				playStart.start(new Stage());
				playStart.setName(name.getText());
				primaryStage.close();
			}
		});
		
		score.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			System.out.println("oknow w budowie");

		}
	});
		
		
	}

}
