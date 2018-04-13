package game;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameFX extends Application {
	ArrayList<Rectangle> enemys = new ArrayList<>();
	Rectangle enemy = new Rectangle(40, 40, Color.RED); 
	Pane root = new Pane();
    private AnimationTimer timer;
    int y=0;
    
    
    public GameFX () {
        timer = new AnimationTimer() {
            @Override
            public void handle(long w) {
                move();
            }
        };
        timer.start();
    }
    
    public void enemy() {
		enemy.setTranslateX(Math.random()*600);
		enemy.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("dziala");
				
				timer.stop();
				enemy.setTranslateY(-100);
				
			}
		});
		root.getChildren().add(enemy);
    }
    
    
	public void move () {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enemy.setTranslateY(y+=10);
		if(enemy.getTranslateY()==500) {
			System.out.println("koniec");
		}

 
	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(root));
		root.setPrefSize(800, 600);
		
		enemy();


		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
