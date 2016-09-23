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
 * Runs level two of the game.
 * 
 * @author Katrina Zhu
 */
public class LevelTwo {
    public static final String TITLE = "Clinton Crush Level Two!!!";
    public static final int KEY_INPUT_SPEED = 50;
    public static final int FALLING_SPEED = 130;
    public static final int SHOOTING_SPEED = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int TRUMP_SPEED = 100;
    private boolean enterPressed;
    private Scene myScene;
    private ImageView myHillary;
    private ImageView myWhiteHouse;
    private int trump_health;
    private Canvas canvas;
    private GraphicsContext gc;
    private Group root; 
    private String pointsText;
    private ArrayList<ImageView> myHatList;
    private WinningScreen winning;
    private Scene winningScene;
    private Scene losingScene;
    private LosingScreen losing;
    private ImageView myTrump;
    private int constant_Trump_speed;
    private ImageView myBullet;
    private int trumpCount;
    private ImageView trumpImage2;
    private ImageView trumpImage3;
    private ImageView trumpImage4;
    private ImageView trumpImage5;
    
    /**
     * Returns title of level two of the game..
     */
    public String getTitle () {
        return TITLE;
    }

    /**
     * Initializes the objects in level two of the game.
     */
    public Scene init (int width, int height, Stage myStage) {
    	enterPressed=false;
    	trumpCount = 1;
    	constant_Trump_speed = 1;
    	trump_health=1000;
    	//initializes new scenes to transition to
    	winning = new WinningScreen();
    	losing = new LosingScreen();
        losingScene=losing.init(1200,  1200, myStage);
        winningScene=winning.init(1200, 1200, myStage);    	
        root = new Group();
        myScene = new Scene(root, width, height, Color.WHITE);
        //adding the White House as a background
        Image whiteHouse = new Image(getClass().getClassLoader().getResourceAsStream("whitehouse.jpeg"));
        myWhiteHouse = new ImageView(whiteHouse);
        myWhiteHouse.setX(-100);
        myWhiteHouse.setY(0);
        myWhiteHouse.setFitWidth(1400);
        myWhiteHouse.setPreserveRatio(true);
        root.getChildren().add(myWhiteHouse);
        //adding the Font on top of the White House
        canvas = new Canvas(1200, 1200);
        gc = canvas.getGraphicsContext2D();
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.DARKORCHID );
        gc.setStroke( Color.WHITE );
        gc.setLineWidth(1);
        updateHealth();
        myHatList=new ArrayList<ImageView>();
        //adding Hillary as a player
        Image hillary = new Image(getClass().getClassLoader().getResourceAsStream("hillary.jpeg"));
        myHillary = new ImageView(hillary);
        myHillary.setFitWidth(120);
        myHillary.setPreserveRatio(true);
        myHillary.setX(width / 2 - myHillary.getBoundsInLocal().getWidth() / 2);
        myHillary.setY(height-550);        
        root.getChildren().add(myHillary); 
        //adding multiple Trump faces as different players
        Image trump1 = new Image(getClass().getClassLoader().getResourceAsStream("trump1.png"));
        Image trump2 = new Image(getClass().getClassLoader().getResourceAsStream("trump2.png"));
        Image trump3 = new Image(getClass().getClassLoader().getResourceAsStream("trump3.png"));
        Image trump4 = new Image(getClass().getClassLoader().getResourceAsStream("trump4.png"));
        Image trump5 = new Image(getClass().getClassLoader().getResourceAsStream("trump5.png"));
        trumpImage2 = new ImageView(trump2);
        trumpImage3 = new ImageView(trump3);
        trumpImage4 = new ImageView(trump4);
        trumpImage5 = new ImageView(trump5);
        myTrump = new ImageView(trump1);
        myTrump.setFitWidth(150);
        myTrump.setPreserveRatio(true);
        myTrump.setX(width / 2 - myTrump.getBoundsInLocal().getWidth() / 2);
        myTrump.setY(10);        
        root.getChildren().add(myTrump);  
        root.getChildren().add(canvas);
        //adding bald eagles in as bullets
        Image object = new Image(getClass().getClassLoader().getResourceAsStream("baldeagle.png"));
    	myBullet = new ImageView(object);
        myBullet.setFitWidth(20);
        myBullet.setPreserveRatio(true);        
        // respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return myScene;
    }      

    /**
     * Runs the animation in level one of the game; causes the objects to move.
     */
    public void step (double elapsedTime, Stage myStage, Timeline animation) {
    	//cheat code to automatically win
    	if(enterPressed){
            animation.stop();
          	myStage.setScene(winningScene);
    	}
    	//shoots bullet and handles Trump/score transitions if he gets hit
    	if(root.getChildren().contains(myBullet)){
  	        shootBullet(myBullet, elapsedTime);
  	        if(myBullet.getBoundsInParent().intersects(myTrump.getBoundsInParent())){
  	        	double trumpLocation = myTrump.getX();
  	        	root.getChildren().remove(myTrump);
  	        	trumpCount++;
  	        	switch(trumpCount){
  	        	case 2: myTrump=trumpImage2;
  	        	break;
  	        	case 3: myTrump=trumpImage3;
  	        	break;
  	        	case 4: myTrump=trumpImage4;
  	        	break;
  	        	case 5: myTrump=trumpImage5;
  	        	break;
  	        	}
  	        	myTrump.setX(trumpLocation);
  	        	myTrump.setFitWidth(150);
  	        	myTrump.setPreserveRatio(true);
  	        	root.getChildren().add(myTrump);
  	        	root.getChildren().remove(myBullet);
  	        	changeScore();
  	        	if(trump_health<=0){
  	                animation.stop();
  	            	myStage.setScene(winningScene);
  	            }
  	        }
  	        if(myBullet.getY()<0){
  	        	root.getChildren().remove(myBullet);
  	        }
  	    }
    	//creates falling Trump hats
        int random = (int) (Math.random() * 500);
        if(random>475){
        	newFallingObject(1200);
        }
        //deals with hat falling, and hat/bullet interactions
	    for(int hat = 0; hat<myHatList.size(); hat++){
	        startFalling(hat, elapsedTime);   
	        ImageView specificHat = myHatList.get(hat);
	        if (specificHat.getBoundsInParent().intersects(myHillary.getBoundsInParent())) {
	        	animation.stop();
	            myStage.setScene(losingScene);
	        }
	        if(root.getChildren().contains(myBullet)){
	        	if(specificHat.getBoundsInParent().intersects(myBullet.getBoundsInParent())||specificHat.getY()>1200){
	        		root.getChildren().remove(myHatList.get(hat));
        			myHatList.remove(hat);
        			if(specificHat.getBoundsInParent().intersects(myBullet.getBoundsInParent()))
        				root.getChildren().remove(myBullet);
	        	}
	        }	        
	    }
	    //makes Trump move back and forth at the top of the screen
        myTrump.setX(myTrump.getX() + constant_Trump_speed*TRUMP_SPEED * elapsedTime);
        if(myTrump.getX()>myScene.getWidth() -myTrump.getBoundsInLocal().getWidth()|| myTrump.getX()<0){
            constant_Trump_speed *= -1;
        }
    }
    
    private void newFallingObject(int width){
    	Image object = new Image(getClass().getClassLoader().getResourceAsStream("trumphat.png"));
    	ImageView myObject;
        myObject = new ImageView(object);
        myObject.setX((width-70) * Math.random());
        myObject.setY(0);
        myObject.setFitWidth(70);
        myObject.setPreserveRatio(true);
        root.getChildren().add(myObject);
        myHatList.add(myObject);
    }
    
    private void startFalling (int objectNumber, double elapsedTime){
    	ImageView object=myHatList.get(objectNumber);
    	object.setY(object.getY() + FALLING_SPEED * elapsedTime);    			
    } 
    
    private void newBullet(){
    	myBullet.setX(myHillary.getX()+myHillary.getBoundsInLocal().getWidth() / 2);
        myBullet.setY(myHillary.getY());
        root.getChildren().add(myBullet);
    }
    
    private void shootBullet(ImageView bullet, double elapsedTime){
    	bullet.setY(bullet.getY() - SHOOTING_SPEED * elapsedTime);
    } 
    
    private void changeScore(){
            trump_health-=200;
            updateHealth(); 
    }   
    
    private void updateHealth(){
    	pointsText="Trump's Health: " + trump_health;
    	gc.clearRect(650, 0, 500, 200);
        gc.fillText( pointsText, 880, 36 );
        gc.strokeText( pointsText, 880, 36 ); 
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
            	if(!root.getChildren().contains(myBullet)){
            		newBullet();
            	}
            	break;
            case ENTER:
            	enterPressed=true;
            	break;
            default:
                return;
        }
    }
    
    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
    	return;
    }
}
