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

import lambdacaverns.common.Glyphs;

/**
 * A map tile.
 */
public class Tile {
    char glyph;
    
    public Tile() {
        this.glyph = Glyphs.OPEN;
    }
    
    public Tile(char glyph) {
        this.glyph = glyph;
    }
    
    public char getGlyph() {
        return glyph;
    }
    
    public void setGlyph(char g) {
        this.glyph = g;
    }
}