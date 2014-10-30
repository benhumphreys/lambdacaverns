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
package lambdacaverns.ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import lambdacaverns.common.Position;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Tile;

/**
 * The screen pane that displays the sidebar. The sidebar displays players
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
        String health = "Health: " + w.getPlayer().getHealth()
                + "/" + w.getPlayer().getMaxHealth();
        
        screen().putString(col, row, health, Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        
        screen().putString(col, row, "Gold:   " + w.getPlayer().getGold(),
                Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        
        screen().putString(col, row, "Armour: " + w.getPlayer().getArmour(),
                Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        
        // Tiles
        row += 3;
        screen().putString(col, row, "---- Tiles ----", Terminal.Color.WHITE,
                Terminal.Color.BLACK);
        row++;
        
        for (Tile t : Tile.values()) {
            if (t.getLabel().equals("")) continue;
            
            screen().putString(col, row, t.getGlyph() + " - " + t.getLabel(),
                    t.getColour(), Terminal.Color.BLACK);
            row++;
        }
        
        // Keys
        row += 2;
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
