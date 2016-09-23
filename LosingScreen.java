import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Class to control the behavior of the losing screen.
 * 
 * @author Katrina Zhu
 */
public class LosingScreen {
	public static final String TITLE = "You lose.";
    private Group root2;    
    private Scene scene2;
    private ImageView myLosingScreen;
    private StartScreen start;
    private Scene startScene;
    
    /**
     * Method to return title of the part of the game.
     */    
	public String getTitle () {
        return TITLE;
    }
	
    /**
     * Initializes the elements/behavior in the losing screen
     */  	
	public Scene init (int width, int height, Stage myStage) {
		start=new StartScreen();
		root2 = new Group();
    	scene2 = new Scene(root2, width, height);
    	Image lose = new Image(getClass().getClassLoader().getResourceAsStream("youlose.jpeg"));
        myLosingScreen = new ImageView(lose);
        myLosingScreen.setX(-50);
        myLosingScreen.setY(0);
        myLosingScreen.setFitWidth(1400);
        myLosingScreen.setPreserveRatio(true);
        root2.getChildren().add(myLosingScreen);
        startScene=start.init(1200, 1200, myStage);
        // respond to input
        scene2.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene2.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY(), myStage));
        return scene2;
    }
	
	private void handleKeyInput (KeyCode code) {
        return;
    }
	
	private void handleMouseInput (double x, double y, Stage myStage) {    	
		myStage.setScene(startScene);
    }

}
