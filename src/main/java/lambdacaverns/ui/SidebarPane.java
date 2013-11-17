package lambdacaverns.ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import lambdacaverns.common.Glyphs;
import lambdacaverns.common.Position;
import lambdacaverns.world.World;

/**
 * The screen pane that displays the sidebar. The sidebar displays avatar
 * stats, a list of key bindings, and a legend for the map.
 */
public class SidebarPane extends Pane {

    public SidebarPane(Screen s, int height, int width, Position corner) {
        super(s, height, width, corner);
    }

    @Override
    void draw(World w) {

        int row = corner().row();
        int col = corner().col();
        
        // Stats
        String health = "Health: " + w.getAvatar().getHealth()
                + "/" + w.getAvatar().getMaxHealth();
        
        screen().putString(col, row, health, Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        
        screen().putString(col, row, "Gold:   " + w.getAvatar().getGold(),
                Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        
        screen().putString(col, row, "Armour: " + w.getAvatar().getArmour(),
                Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        
        // Tiles
        row += 3;
        screen().putString(col, row, "---- Tiles ----", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        
        screen().putString(col, row, Glyphs.AVATAR + " - Avatar", Terminal.Color.YELLOW,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, Glyphs.WALL + " - Wall", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, Glyphs.WATER + " - Water", Terminal.Color.BLUE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, Glyphs.TREE + " - Trees", Terminal.Color.GREEN,
                Terminal.Color.BLACK);
        
        // Keys
        row += 3;
        screen().putString(col, row, "---- Keys  ----", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, "\u2190 - Left", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, "\u2192 - Right", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, "\u2193 - Up", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, "\u2191 - Down", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        screen().putString(col, row, "q - Quit", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
    }

}
