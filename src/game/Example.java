package game;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class Example extends Application {

    

    private Pane root;

    private List<Node> cars = new ArrayList<>();




    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        root.setPrefSize(800, 600);
        stage.setScene(new Scene(root));
        System.out.println("tworzy scene");
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long w) {
            		System.out.println("tworzy obiekt retange JESTEM W HANDLE");
                Rectangle rect = new Rectangle(40, 40, Color.RED);
                rect.setTranslateY(40); // ustawia po y 
                root.getChildren().add(rect);
                cars.add(rect);
                
                for (Node car : cars) {
                	System.out.println("jestem w forze ");
                    car.setTranslateX(car.getTranslateX() +  100); // poruszanie sie po x
                }
                
               
               try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        timer.start();

        System.out.println("opuszczam timer");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}