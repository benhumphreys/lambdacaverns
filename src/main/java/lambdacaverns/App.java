package lambdacaverns;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import lambdacaverns.ui.UserInterface;
import lambdacaverns.world.MapGen;
import lambdacaverns.world.World;
import lambdacaverns.common.Actions;

/**
 * Caverns of Lambda Application
 */
public class App {
    private Map<Character, Actions> actionMap = new HashMap<Character, Actions>();
    
    public App() {
        initActionMap();
    }
    
    private void initActionMap() {
        actionMap.put('u', Actions.MOVE_UP);
        actionMap.put('d', Actions.MOVE_DOWN);
        actionMap.put('l', Actions.MOVE_LEFT);
        actionMap.put('r', Actions.MOVE_RIGHT);
    }
    
    /**
     * Displays a welcome page.
     * @param s the screen to write the welcome page too
     */
    private static void displayTitle(Screen s) {
        final String welcome1 = "Welcome to " + Constants.GAME_TITLE;
        final String welcome2 = " Press any key to start...";

        // Find top right corner and put welcome message
        int xsize = s.getTerminalSize().getColumns();
        int ysize = s.getTerminalSize().getRows();
        int ypos = (ysize / 2) - 2;
        int xpos = (xsize - welcome1.length()) / 2;

        s.putString(xpos, ypos, welcome1, Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        s.putString(xpos, ypos + 1, welcome2, Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        s.refresh();

        // Wait for any key press
        getKeyBlocking(s);
    }

    /**
     * Creates a new/initialised/ready-to-play world object
     * @return the world!
     */
    private static World createWorld() {
        World w = new World();
        w.init(MapGen.generate(Constants.MAP_HEIGHT, Constants.MAP_WIDTH));
        w.getMessages().add("Welcome to " + Constants.GAME_TITLE);

        return w;
    }
    
    /**
     * Main game loop, exits when q/Q is pressed
     * @param w the world
     * @param s the screen to draw on
     */
    private void gameLoop(World w, Screen s) {
        UserInterface ui = new UserInterface(s);
        ui.draw(w);

        while (true) {
            Key k = getKeyBlocking(s);
            Character c = Character.toLowerCase(k.getCharacter());
            if (c == 'q')
                break;

            w.tick(actionMap.get(c));
            ui.draw(w);
        }
    }

    /**
     * Blocks waiting on a keypress
     * @param s the Lanterna screen instance from which to read the key input
     * @return the key pressed.
     */
    private static Key getKeyBlocking(Screen s) {
        Key k = null;
        while (k == null) {
            k = s.getTerminal().readInput();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
        return k;
    }
    
    void run() {
        // Initialise
        Screen s = TerminalFacade.createScreen(new SwingTerminal(
                Constants.SCREEN_NCOLS, Constants.SCREEN_NROWS));
        s.startScreen();
        displayTitle(s);
        
        World w = createWorld();
        
        // Run
        gameLoop(w, s);
        
        // Cleanup
        s.stopScreen();
    }

    /**
     * Main - It all starts here!
     * @param args command line arguments
     */
    public static void main(String[] args) {
        App a = new App();
        a.run();
    }
}
