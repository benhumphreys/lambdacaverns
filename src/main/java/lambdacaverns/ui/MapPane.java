/*
 * Caverns of Lambda - A Rogue-like
 * Copyright (C) 2014-2015  Ben Humphreys
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
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import lambdacaverns.common.Position;
import lambdacaverns.world.World;
import lambdacaverns.world.entities.AbstractEntity;
import lambdacaverns.world.entities.Player;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.Tile;

/**
 * The screen pane that displays the Map.
 */
public class MapPane extends Pane {
    private final int firstScreenRow;
    private final int firstScreenCol;

    // Width of the map area (i.e. pane width minus borders)
    private final int mapWidth;

    // Height of the map area (i.e. pane height minus borders)
    private final int mapHeight;

    public MapPane(Screen s, int height, int width, Position corner) {
        super(s, height, width, corner);

        firstScreenRow = corner().row() + 1;
        firstScreenCol = corner().col() + 1;
        mapWidth = width() - 2;
        mapHeight = height() - 2;
    }

    @Override
    void draw(World w) {
        drawBorders();
        drawPane(w);
    }

    private void drawBorders() {
        StringBuilder hline = new StringBuilder();
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

    private void drawPane(World w) {
        final Map m = w.getMap();
        final Player ply = w.getPlayer();

        // Calculate the map position which will align with the top-left-corner
        // of the pane. This depends on the player position, try to keep the
        // player in the centre, however as the player approaches the edge of
        // the map stays stationary and the player moves.
        int mapTlcRow = ply.getPosition().row() - (mapHeight / 2);
        mapTlcRow = Math.max(0, mapTlcRow);
        mapTlcRow = Math.min(m.nrows() - mapHeight, mapTlcRow);

        int mapTlcCol = ply.getPosition().col() - (mapWidth / 2);
        mapTlcCol = Math.max(0, mapTlcCol);
        mapTlcCol = Math.min(m.ncols() - mapWidth, mapTlcCol);

        // Draw
        drawMap(mapTlcRow, mapTlcCol, m);
        drawEntities(mapTlcRow, mapTlcCol, w);
    }

    // Draws the map tiles to the map pane
    private void drawMap(int mapTlcRow, int mapTlcCol, Map m) {
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                Tile t = m.getTile(mapTlcRow + row, mapTlcCol + col);

                screen().putString(firstScreenCol + col, firstScreenRow + row,
                        Character.toString(t.getGlyph()), t.getColour(),
                        Terminal.Color.BLACK);
            }
        }
    }

    // Draw all non-player entities on to the map pane
    private void drawEntities(int mapTlcRow, int mapTlcCol,
                              World w) {
        for (AbstractEntity e : w.getEntities()) {
            Position pos = e.getPosition();

            // Does the entity fall within the visible part of the map?
            if (pos.row() >= mapTlcRow && pos.row() < mapTlcRow + mapHeight
                    && pos.col() >= mapTlcCol
                    && pos.col() < mapTlcCol + mapWidth) {
                drawEntity(mapTlcRow, mapTlcCol, e, e == w.getPlayer());
            }
        }
    }

    // Draw a single entity on the map pane
    private void drawEntity(int mapTlcRow, int mapTlcCol, AbstractEntity e,
                            boolean isPlayer) {
        Position pos = e.getPosition();

        // Calculate draw position
        int drawcol = firstScreenCol + (pos.col() - mapTlcCol);
        int drawrow = firstScreenRow + (pos.row() - mapTlcRow);

        Tile t = e.getTile();
        if (t.getBold()) {
            screen().putString(drawcol, drawrow,
                    Character.toString(t.getGlyph()), t.getColour(),
                    Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
        } else {
            screen().putString(drawcol, drawrow,
                    Character.toString(t.getGlyph()), t.getColour(),
                    Terminal.Color.BLACK);
        }

        if (isPlayer) {
            screen().setCursorPosition(drawcol, drawrow);
        }
    }
}
