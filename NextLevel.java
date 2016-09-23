import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Display the scene to transition from level one to level two.
 * 
 * @author Katrina Zhu
 */
public class NextLevel {
	public static final String TITLE = "On to the next level!!!";
    private Group root;    
    private Scene scene;
    private ImageView myNextLevel;
    private Scene nextScene;
    private LevelTwo levTwo;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

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
		levTwo = new LevelTwo();
		root = new Group();
    	scene = new Scene(root, width, height);
    	Image level = new Image(getClass().getClassLoader().getResourceAsStream("nextlevel.jpeg"));
        myNextLevel = new ImageView(level);
        myNextLevel.setX(-180);
        myNextLevel.setY(0);
        myNextLevel.setFitWidth(1400);
        myNextLevel.setPreserveRatio(true);
        root.getChildren().add(myNextLevel);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY(), myStage));
        return scene;
    }
	
	private void handleKeyInput (KeyCode code) {
        return;
    }
	
	private void handleMouseInput (double x, double y, Stage myStage) {    	
        nextScene=levTwo.init(1200, 1200, myStage);
        myStage.setScene(nextScene);
        Timeline animation = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
        e -> levTwo.step(SECOND_DELAY, myStage, animation));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();        
    }

}
