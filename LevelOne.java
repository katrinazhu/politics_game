// This entire file is part of my masterpiece.
// Katrina Zhu
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Refactored code deals with level one of the game.  Lots of helper methods allow the code to be very readable.
 * These added helper methods allowed for me to delete lots of repeated lines of code as well.
 * 
 * @author Katrina Zhu
 */
public class LevelOne {
    public static final String TITLE = "Clinton Crush!!!";
    public static final int KEY_INPUT_SPEED = 50;
    public static final int FALLING_SPEED = 130;
    public static final int FRAMES_PER_SECOND = 60;
    private Scene myScene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Group root; 
    private ImageView myHillary;
    private NextLevel level;
    private Scene nextLevelScene;
    private LosingScreen losing;
    private Scene losingScene;
    private ArrayList<ImageView> myEmailList;
    private ArrayList<ImageView> myVoteList;
    private boolean emailExists;
    private boolean voteExists;
    private boolean spaceWasPressed;
    private int score;
    private String pointsText;
    
    /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }

    /**
     * Initializes the objects in level one's setting.
     */
    public Scene init (int width, int height, Stage myStage) {
    	//initializing constants needed in rest of code
    	spaceWasPressed=false;
    	emailExists= false;
    	voteExists=false;
    	score=0;
        myEmailList=new ArrayList<ImageView>();
        myVoteList=new ArrayList<ImageView>();
        root = new Group();
        myScene = new Scene(root, width, height, Color.WHITE);
        Image whiteHouse = new Image(getClass().getClassLoader().getResourceAsStream("whitehouse.jpeg"));
        addImage(whiteHouse, -100, 0, 1400);
        Image hillary = new Image(getClass().getClassLoader().getResourceAsStream("hillary.jpeg"));
        myHillary=addImage(hillary, width/2 - 120/2, height-550, 120);
        initializeScore();
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode(), myStage));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
    	initializeNewLevels(myStage);
        return myScene;
    }
  
    /**
     * Runs the animation in level one of the game; causes the objects to move.
     */
    public void step (double elapsedTime, Stage myStage, Timeline animation) {
    	//cheat code; get full score if space is pressed
    	if(spaceWasPressed){
    		score=3000;
    	}
    	//creates randomly falling objects from the sky
        int randomFallingObjectGenerator = (int) (Math.random() * 500);
        if(randomFallingObjectGenerator>480){
        	if(randomFallingObjectGenerator%2==0){
        		newFallingObject(1200, "email");
        		emailExists=true;
        	}
        	else{
        		newFallingObject(1200, "vote");
        		voteExists = true;
        	}
        }
        //deals with Hillary/falling object intersections
        if (emailExists){
	        for(int email = 0; email<myEmailList.size(); email++){
	            startFalling(email, "email", elapsedTime);   
	            if (myEmailList.get(email).getBoundsInParent().intersects(myHillary.getBoundsInParent())) {
	            	animation.stop();
	                myStage.setScene(losingScene);
	            }
	        }
        }
        if(voteExists){
	        for(int vote = 0; vote<myVoteList.size(); vote++){
	    		startFalling(vote, "vote", elapsedTime);
	    		if (myVoteList.get(vote).getBoundsInParent().intersects(myHillary.getBoundsInParent())){
	            	changeScore(myVoteList.get(vote), "vote");
	    		}
	        }        
        }
        //Hillary winning and transitioning to the next scene
        if(score>=3000){
        	animation.stop();
        	myStage.setScene(nextLevelScene);  
        }
    }
    
    private void initializeNewLevels(Stage myStage){
    	level = new NextLevel();
    	losing = new LosingScreen();
        losingScene=losing.init(1200,  1200, myStage);
        nextLevelScene=level.init(1200, 1200, myStage);
    }
    
    private void initializeScore(){
        canvas = new Canvas(1200, 1200);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.DARKORCHID );
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        updateScore();
    }
    
    private ImageView addImage(Image image, double x, double y, int size){
    	ImageView myImage;
    	myImage=new ImageView(image);
    	myImage.setX(x);
        myImage.setY(y);
        myImage.setFitWidth(size);
        myImage.setPreserveRatio(true);
        root.getChildren().add(myImage);
        return myImage;
    }
    
    private void newFallingObject(int width, String o){
    	Image object;
    	if(o.equals("email")){
    		object = new Image(getClass().getClassLoader().getResourceAsStream("email.gif"));
    	}
    	else{
            object = new Image(getClass().getClassLoader().getResourceAsStream("vote.jpeg"));
    	}
    	ImageView myObject;
    	myObject=addImage(object, (width-70)*Math.random(), 0, 70);
        if (o.equals("email")){
            myEmailList.add(myObject);
        }
        else{
        	myVoteList.add(myObject);
        }
    }
    
    private void startFalling (int objectNumber, String objectType, double elapsedTime){
    	ImageView object;
    	if (objectType.equals("email")){
    			object=myEmailList.get(objectNumber);
    			object.setY(object.getY() + FALLING_SPEED * elapsedTime);
    	}
    	else if	(objectType.equals("vote")){
    			object=myVoteList.get(objectNumber);
    			object.setY(object.getY() + FALLING_SPEED * elapsedTime);
    	}   			
    }
    
    private void changeScore(ImageView object, String objectType){
    	if(root.getChildren().contains(object)){
        	score+=100;
            updateScore();         
        	root.getChildren().remove(object);
    	}
    }
    
    private void updateScore(){
    	pointsText="Points= " + score;
    	gc.clearRect(800, 0, 400, 100);
        gc.fillText( pointsText, 1000, 36 );
        gc.strokeText( pointsText, 1000, 36 ); 
    }

    /**
     * What to what when a key is pressed
     */
    private void handleKeyInput (KeyCode code, Stage myStage) {
        switch (code) {
            case RIGHT:
            	if(myHillary.getX()<myScene.getWidth()-myHillary.getBoundsInLocal().getWidth()-50){
            		myHillary.setX(myHillary.getX() + KEY_INPUT_SPEED);
            	}
                break;
            case LEFT:
            	if(myHillary.getX()>myHillary.getBoundsInLocal().getWidth() / 2){
            		myHillary.setX(myHillary.getX() - KEY_INPUT_SPEED);
            	}
                break;
            case SPACE:
            	spaceWasPressed=true;
		        break;
            default:
                return;
        }
    }

    /**
     * What to do when the mouse is clicked
     */
    private void handleMouseInput (double x, double y) {
    	return;
    }
}
