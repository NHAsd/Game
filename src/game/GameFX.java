package game;


import java.util.ArrayList;
import java.util.Random;

import com.sun.scenario.effect.impl.prism.PrImage;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameFX extends Application {
	private ArrayList<Rectangle> enemyList = new ArrayList<>();
	private Pane root;
	
    private AnimationTimer timer;
    Thread score;
    
    private Integer lives =3;
    private Integer myScore = 0;
    private String name;
    
    private double width;
    private double height;
    
	private Label showLives = new Label();
	private Label showScore = new Label();

	

	public void setName(String name) {
		this.name = name;
	}



	public Scene getScene() { // w parametrze wrzyucamy do root wrzucic tutaj Pane  wpierdolic przeciazony konstruora stage set scene new scene  root w h 
		root = new Pane();
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
		width = visualBounds.getWidth();
		height = visualBounds.getHeight();
		root.getChildren().add(showLives);
		root.getChildren().add(showScore);
		
		VBox hud = new VBox();
		hud.getChildren().add(showLives);
		hud.getChildren().add(showScore);
		showLives.setFont(Font.font(30));
		showScore.setFont(Font.font(30));
		hud.setSpacing(20);
		hud.setPadding(new Insets(20));
		root.getChildren().add(hud);
//		showLives.setTranslateY(height/100);
//		showScore.setTranslateY(2*height/100);
        return scene;
	}
	
	
	
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
    	Random generator = new Random();
    	Rectangle enemy = new Rectangle(40, 40, Color.RED); 
//		enemy.setTranslateX((int)(Math.random())*50);
    	enemy.setTranslateX(generator.nextDouble()*(width));

		if(Math.random()<0.003) { // wiecej cyz mniej enemy
			root.getChildren().add(enemy);
			enemyList.add(enemy);
		}
    }
    
    
	public void move () {
		enemy();
		for(Node e : enemyList) {
			e.setTranslateY(e.getTranslateY()+2); // predkosc enemy
			e.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					e.setTranslateY(-100);					
				}
			});

			if(e.getTranslateY()==height) {
				lives--;
				if (lives == 0) {
					timer.stop();
					Label endGame = new Label("GAME OVER SCORE: " + myScore.toString());

					root.getChildren().add(endGame);

					score.stop(); // wyejbac i interupt

					endGame.setTranslateX(1*width/4);
					endGame.setTranslateY(1*height/4);
					endGame.setFont(Font.font(50));
					ScoreMenager sc = sc.openFile();
					
					sc.getList().put(name, myScore);
					sc.saveFile();
				}
			}

			checkState();
		}

 
	}
	
	public void score() {
		score = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					for(int time = 0;lives != 0;time++) {
						Thread.sleep(100);
						myScore ++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				
			}
		});
		score.start();

	}
	
	public void checkState(){
		showLives.setText("zycia: "+lives.toString());
		showScore.setText("wynik: "+myScore.toString());
	}
	
	
	@Override
	public void start(Stage stage){
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(getScene());
		stage.setMaximized(true);
		stage.setMaximized(true);
		

		score();
		enemy();
		stage.show();
	}
	

}
