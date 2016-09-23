import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

//import javafx.animation.KeyFrame;
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
 * Separate the game code from some of the boilerplate code.
 * 
 * @author Katrina Zhu
 */
public class Game {
    public static final String TITLE = "Clinton Crush!!!";
    public static final int KEY_INPUT_SPEED = 50;
    private static final int FALLING_SPEED = 130;
    public static final int FRAMES_PER_SECOND = 60;

    private Scene myScene;
    private ImageView myHillary;
    private ImageView myWhiteHouse;
    private int score;
    private Canvas canvas;
    private GraphicsContext gc;
    private Group root; 
    private String pointsText;
    private ArrayList<ImageView> myEmailList;
    private ArrayList<ImageView> myVoteList;
    private boolean emailExists;
    private boolean voteExists;
    //private StartScreen start;
    //private Scene scene1;
    private NextLevel level;
    private Scene nextLevelScene;
    private Scene losingScene;
    private LosingScreen losing;
    
    /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }

    /**
     * Create the game's scene
     */
    public Scene init (int width, int height, Stage myStage) {
    	//start = new StartScreen();
    	level = new NextLevel();
    	losing = new LosingScreen();
    	emailExists= false;
    	voteExists=false;
    	score=0;
        // create a scene graph to organize the scene
        root = new Group();
        // create a place to see the shapes
        myScene = new Scene(root, width, height, Color.WHITE);
        Image whiteHouse = new Image(getClass().getClassLoader().getResourceAsStream("whitehouse.jpeg"));
        myWhiteHouse = new ImageView(whiteHouse);
        myWhiteHouse.setX(-100);
        myWhiteHouse.setY(0);
        myWhiteHouse.setFitWidth(1400);
        myWhiteHouse.setPreserveRatio(true);
        root.getChildren().add(myWhiteHouse);
        // make some shapes and set their properties
        canvas = new Canvas(1200, 1200);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.DARKORCHID );
        gc.setStroke( Color.WHITE );
        gc.setLineWidth(1);
        updateScore();
        myEmailList=new ArrayList<ImageView>();
        myVoteList=new ArrayList<ImageView>();
        // x and y represent the top left corner, so center it        
        Image hillary = new Image(getClass().getClassLoader().getResourceAsStream("hillary.jpeg"));
        myHillary = new ImageView(hillary);
        // x and y represent the top left corner, so center it
        myHillary.setX(width / 2 - myHillary.getBoundsInLocal().getWidth() / 2);
        myHillary.setY(height-550);
        myHillary.setFitWidth(120);
        myHillary.setPreserveRatio(true);
        // order added to the group is the order in which they are drawn
        root.getChildren().add(myHillary);
        
        // respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        //scene1=start.init(1200, 1200, myStage);
        losingScene=losing.init(1200,  1200, myStage);
        nextLevelScene=level.init(1200, 1200, myStage);
        return myScene;
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
        myObject = new ImageView(object);
        // x and y represent the top left corner, so center it
        myObject.setX(width * Math.random());
        myObject.setY(0);
        myObject.setFitWidth(70);
        myObject.setPreserveRatio(true);
        root.getChildren().add(myObject);
        if (o.equals("email")){
            myEmailList.add(myObject);
        }
        else{
        	myVoteList.add(myObject);
        }
        //return myObject;
    }
    
    private void startFalling (int objectNumber, String objectType, double elapsedTime){
    	ImageView object;
    	if (objectType.equals("email")){
    			object=myEmailList.get(objectNumber);
    			object.setY(object.getY() + FALLING_SPEED * elapsedTime);
    	}
    	else if	(objectType.equals("vote")){
    			//myVotes=myVoteList.get(objectNumber);
    			object=myVoteList.get(objectNumber);
    			object.setY(object.getY() + FALLING_SPEED * elapsedTime);
    	}
    			
    }    

    /**
     * Change properties of shapes to animate them
     * 
     * Note, there are more sophisticated ways to animate shapes,
     * but these simple ways work too.
     */
    public void step (double elapsedTime, Stage myStage, Timeline animation) {
        int random = (int) (Math.random() * 500);
        if(random>480){
        	if(random%2==0){
        		newFallingObject(1200, "email");
        		emailExists=true;
        	}
        	else{
        		newFallingObject(1200, "vote");
        		voteExists = true;
        	}
        }
        // with images can only check bounding box
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
        if(score>=100){
        	animation.stop();
        	myStage.setScene(nextLevelScene);  
        }
    }
    
    private void changeScore(ImageView object, String objectType){
    	if (objectType.equals("email")){
    		if(root.getChildren().contains(object)){
            	score-=100;
                updateScore();
            }
        	root.getChildren().remove(object);
    	}
    	else if (objectType.equals("vote")){
    		if(root.getChildren().contains(object)){
        		score+=100;
            	updateScore();         
            }
        	root.getChildren().remove(object);
    	}
    }
    
    private void updateScore(){
    	pointsText="Points= " + score;
    	gc.clearRect(800, 0, 400, 100);
        gc.fillText( pointsText, 1000, 36 );
        gc.strokeText( pointsText, 1000, 36 ); 
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
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
            	//cheat code to get to last level
            default:
                // do nothing
        }
    }

    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
    	
        /*if (myBottomBlock.contains(x, y)) {
            myBottomBlock.setScaleX(myBottomBlock.getScaleX() * GROWTH_RATE);
            myBottomBlock.setScaleY(myBottomBlock.getScaleY() * GROWTH_RATE);
        }*/
    }
}
