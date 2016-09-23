import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Class to control the behavior of the winning screen.
 * 
 * @author Katrina Zhu
 */
public class WinningScreen {
	public static final String TITLE = "You WIN!!";
    private Group root2;    
    private Scene myScene;
    private ImageView myWinningScreen;
    private StartScreen start;
    private Scene startScene;

    /**
     * Method to return title of the part of the game.
     */    
	public String getTitle () {
        return TITLE;
    }
	
    /**
     * Initializes the elements in the winning screen
     */  
	public Scene init (int width, int height, Stage myStage) {
		start=new StartScreen();
		root2 = new Group();
    	myScene = new Scene(root2, width, height);
    	Image lose = new Image(getClass().getClassLoader().getResourceAsStream("winninghillary.jpeg"));
        myWinningScreen = new ImageView(lose);
        myWinningScreen.setX(-180);
        myWinningScreen.setY(0);
        myWinningScreen.setFitWidth(1400);
        myWinningScreen.setPreserveRatio(true);
        root2.getChildren().add(myWinningScreen);
        startScene=start.init(1200, 1200, myStage);
        // respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY(), myStage));
        return myScene;
    }
	
	private void handleKeyInput (KeyCode code) {
        return;
    }
	
	private void handleMouseInput (double x, double y, Stage myStage) {    	
		myStage.setScene(startScene);
    }
}
