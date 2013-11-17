package lambdacaverns.ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import lambdacaverns.common.Glyphs;
import lambdacaverns.common.Position;
import lambdacaverns.world.Map;
import lambdacaverns.world.Tile;
import lambdacaverns.world.World;

/**
 * The screen pane that displays the Map.
 */
public class MapPane extends Pane {

    public MapPane(Screen s, int height, int width, Position corner) {
        super(s, height, width, corner);
    }

    @Override
    void draw(World w) {
        drawBorders();
        drawMap(w.getMap());
        drawAvatar(w);
    }
    
    private void drawBorders() {
        StringBuffer hline = new StringBuffer();
        hline.append("+");
        for (int i = 0; i < width() - 2; ++i) {
            hline.append("-");
        }
        hline.append("+");

        final int tlcRow = corner().row();
        final int tlcCol = corner().col();

        // Draw top
        screen().putString(tlcCol, tlcRow, hline.toString(),
                Terminal.Color.WHITE, Terminal.Color.BLACK);

        // Draw bottom
        screen().putString(tlcCol, tlcRow + height() - 1, hline.toString(),
                Terminal.Color.WHITE, Terminal.Color.BLACK);

        // Draw left and right map border
        for (int row = tlcRow + 1; row < (tlcRow + height() - 1); row++) {
            screen().putString(tlcCol, row, "|", Terminal.Color.WHITE,
                    Terminal.Color.BLACK);
            screen().putString(tlcCol + width() - 1, row, "|",
                    Terminal.Color.WHITE, Terminal.Color.BLACK);
        }
    }

    private void drawMap(Map m) {
        int firstrow = corner().row() + 1;
        int firstcol = corner().col() + 1;
        int lastrow = height() - 3;
        int lastcol = width() - 3;

        for (int row = 0; row <= lastrow; ++row) {
            for (int col = 0; col <= lastcol; ++col) {
                Tile t = m.getTile(row, col);

                screen().putString(firstcol + col, firstrow + row,
                        Character.toString(t.getGlyph()),
                        Terminal.Color.WHITE,
                        Terminal.Color.BLACK);
            }
        }
    }
    
    private void drawAvatar(World w) {
        Map m = w.getMap();
        Position avpos = w.getAvatar().getPosition();
        
        // Preconditions
        assert (avpos.row() >= 0);
        assert (avpos.row() < m.nrows());
        assert (avpos.col() >= 0);
        assert (avpos.col() < m.ncols());
        
        screen().putString(avpos.col(),
                avpos.row(),
                Character.toString(Glyphs.AVATAR),
                Terminal.Color.YELLOW,
                Terminal.Color.BLACK,
                ScreenCharacterStyle.Bold);
        
        screen().setCursorPosition(avpos.col(), avpos.row());
    }
}
