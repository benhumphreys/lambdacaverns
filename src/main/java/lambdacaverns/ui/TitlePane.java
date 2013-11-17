package lambdacaverns.ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import lambdacaverns.Constants;
import lambdacaverns.common.Position;
import lambdacaverns.world.World;

/**
 * The screen pane that displays the game title at the top of the screen.
 */
public class TitlePane extends Pane {

    public TitlePane(Screen s, int height, int width, Position corner) {
        super(s, height, width, corner);
    }

    @Override
    public void draw(World w) {
        int row = corner().row();
        int col = corner().col() + (width() - Constants.GAME_TITLE.length()) / 2;

        screen().putString(col, row, Constants.GAME_TITLE,
                Terminal.Color.WHITE, Terminal.Color.BLACK);
    }

}
