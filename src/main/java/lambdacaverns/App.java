/*
 * Caverns of Lambda - A Rogue-like
 * Copyright (C) 2014  Ben Humphreys
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */
package lambdacaverns;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import lambdacaverns.common.Actions;
import lambdacaverns.ui.UserInterface;
import lambdacaverns.world.World;
import lambdacaverns.world.WorldGen;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Caverns of Lambda Application
 */
public class App {
    private final Map<Character, Actions> actionMap = new HashMap<>();

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
     *
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
     *
     * @return the world!
     */
    private static World createWorld() {
        return WorldGen.generate(new Random(System.currentTimeMillis()));
    }

    /**
     * Main game loop, exits when q/Q is pressed
     *
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

            Actions act = actionMap.get(c);
            if (act == null) {
                continue;
            }
            w.tick(act);
            ui.draw(w);
        }
    }

    /**
     * Blocks waiting on a keypress
     *
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
                return null;
            }
        }
        return k;
    }

    private void run() {
        // Initialise
        Screen s = TerminalFacade.createScreen(new SwingTerminal(
                Constants.SCREEN_NCOLS, Constants.SCREEN_NROWS));
        s.startScreen();
        displayTitle(s);

        World w = createWorld();
        w.getMessages().add("Welcome to " + Constants.GAME_TITLE);

        // Run
        gameLoop(w, s);

        // Cleanup
        s.stopScreen();
    }

    /**
     * Main - It all starts here!
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        App a = new App();
        a.run();
    }
}
