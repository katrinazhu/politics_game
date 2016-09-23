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
 * Start screen class to control behavior of the first screen.
 * 
 * @author Katrina Zhu
 */
public class StartScreen {
	public static final String TITLE = "Clinton Crush!!!";
    private Group root1;    
    private Scene scene1;
    private LevelTwo levTwo;
    private Scene skipScene;
    private ImageView myAmericanFlag;
    private Scene nextScene;
    private LevelOne levOne;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    boolean six_was_read = false;

    /**
     * Returns name of the game.
     */
	public String getTitle () {
        return TITLE;
    }
	
    /**
     * Initializes objects and behavior in the game.
     */	
	public Scene init (int width, int height, Stage myStage) {
		levOne = new LevelOne();
		levTwo = new LevelTwo();
		root1 = new Group();
    	scene1 = new Scene(root1, width, height);
    	Image america = new Image(getClass().getClassLoader().getResourceAsStream("americanflag.jpeg"));
        myAmericanFlag = new ImageView(america);
        myAmericanFlag.setX(-50);
        myAmericanFlag.setY(0);
        myAmericanFlag.setFitWidth(1400);
        myAmericanFlag.setPreserveRatio(true);
        root1.getChildren().add(myAmericanFlag);        
        // respond to input
        scene1.setOnKeyPressed(e -> handleKeyInput(e.getCode(), myStage));
        scene1.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY(), myStage));
        return scene1;
    }
	
    /**
     * If you press 69, automatically jump to level 2.
     */
	private void handleKeyInput (KeyCode code, Stage myStage) {
		switch(code){
		case DIGIT6:
			six_was_read = true;
			break;
		case DIGIT9:
			if(six_was_read){
				skipScene=levTwo.init(1200,  1200, myStage);
				myStage.setScene(skipScene);
				Timeline animation = new Timeline();
		        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
		        e -> levTwo.step(SECOND_DELAY, myStage, animation));
		        animation.setCycleCount(Timeline.INDEFINITE);
		        animation.getKeyFrames().add(frame);
		        animation.play();     
			}
			break;
		default:
			six_was_read=false;
			break;
		}
    }
	
    /**
     * Sends user to level 1 after clicking the mouse.
     */
	private void handleMouseInput (double x, double y, Stage myStage) {    	      
		Timeline animation = new Timeline();
		nextScene=levOne.init(1200, 1200, myStage);
        myStage.setScene(nextScene);        
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
        e -> levOne.step(SECOND_DELAY, myStage, animation));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();        
    }

}
