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
package lambdacaverns.world.map;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * A map tile.
 */
public enum Tile {
    OPEN('.', "", Terminal.Color.WHITE),
    PLAYER('@', "Player", Terminal.Color.YELLOW, true),
    WALL('#', "Wall", Terminal.Color.WHITE),
    WATER('~', "Water", Terminal.Color.BLUE),
    TREE('^', "Tree", Terminal.Color.GREEN),
    ORC('O', "Orc", Terminal.Color.RED);
    
    private char glyph;
    private String label;
    private Terminal.Color colour;
    private boolean bold;
    
    private Tile(char glyph, String label, Terminal.Color colour, boolean bold) {
        this.glyph = glyph;
        this.label = label;
        this.colour = colour;
        this.bold = bold;
    }
    
    private Tile(char glyph, String label, Terminal.Color colour) {
        this(glyph, label, colour, false);
    }
    
    public char getGlyph() {
        return glyph;
    }
    
    public String getLabel() {
        return label;
    }

    public Terminal.Color getColour() {
        return colour;
    }
    
    public boolean getBold() {
        return bold;
    }
}
