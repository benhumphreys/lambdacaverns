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
import lambdacaverns.common.Position;
import lambdacaverns.world.World;

/**
 * An abstract pane on the user interfaces. Encapsulates common functionality
 * such as height, width and position (represented as a row/col coordinate
 * for the top-left-corner of the pane).
 */
public abstract class Pane {
    private final Screen _screen;
    private final int _height;
    private final int _width;
    private final Position _corner;

    public Pane(Screen s, int height, int width, Position corner) {
        this._screen = s;
        this._height = height;
        this._width = width;
        this._corner = corner;
    }

    /**
     * This class is implemented by the concrete Pane types.
     *
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
