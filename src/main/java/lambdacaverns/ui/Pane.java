package lambdacaverns.ui;

import com.googlecode.lanterna.screen.Screen;

import lambdacaverns.common.Position;
import lambdacaverns.world.World;

/**
 * An abstract pane on the user interfaces. Encapsulates common functionality
 * such as height, width and position (represented as a row/col coordinate
 * for the top-left-corner of the pane).
 */
public abstract class Pane {
    Screen _screen;
    private int _height = 0;
    private int _width = 0;
    private Position _corner;
    
    public Pane(Screen s, int height, int width, Position corner) {
        this._screen = s;
        this._height = height;
        this._width = width;
        this._corner = corner;
    }
    
    /**
     * This class is implemented by the concrete Pane types.
     * @param w the current world state.
     */
    abstract void draw(World w);
    
    protected Screen screen() {
        return _screen;
    }
    
    /**
     * @return the height (in text rows) of the pane.
     */
    int height() {
        return _height;
    }
    
    /**
     * @return the width (in text columns) of the pane.
     */
    int width() {
        return _width;
    }
    
    /**
     * @return the top-left-corner Position of the pane.
     */
    Position corner() {
        return _corner;
    }
}
