import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Katrina Zhu
 */
public class Main extends Application {
    public static final int SIZE = 1200;
    public static final int FRAMES_PER_SECOND = 60;
    private StartScreen myStartScreen;

    /**
     * Set things up at the beginning, navigating to the start screen.
     */
    @Override
    public void start (Stage s) {
        myStartScreen = new StartScreen();
    	s.setTitle(myStartScreen.getTitle());
        Scene scene = myStartScreen.init(SIZE, SIZE, s);
        s.setScene(scene);
        s.show();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
