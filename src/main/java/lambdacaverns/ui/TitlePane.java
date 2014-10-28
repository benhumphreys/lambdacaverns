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
