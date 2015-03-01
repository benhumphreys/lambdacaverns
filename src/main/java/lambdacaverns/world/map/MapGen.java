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

import java.util.Random;

/**
 * A basic cellular automata based map generator. The technique used is taken
 * from here:
 * http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
 */
public class MapGen {

    /**
     * Randomly generate a map.
     *
     * @param nrows height of the map to be generated
     * @param ncols width of the map to be generated.
     */
    public static Map generate(int nrows, int ncols, Random rnd) {
        Map m = new Map(nrows, ncols);
        randomise(m, rnd);

        final int iterations = 5;
        for (int i = 0; i < iterations; i++) {
            if (i < iterations - 1) {
                smooth(m, true);
            } else {
                smooth(m, false);
            }
        }

        return m;
    }

    /**
     * Randomise the map uses a probability of 45% that a given tile
     * will be a wall.
     */
    private static void randomise(Map m, Random rnd) {
        final double PROBABILITY_WALL = 0.45;
        Tile wall = Tile.WALL;

        for (int row = 0; row < m.nrows(); row++) {
            for (int col = 0; col < m.ncols(); col++) {
                if (rnd.nextDouble() < PROBABILITY_WALL) {
                    m.setTile(row, col, wall);
                }
            }
        }
    }

    /**
     * Perform a single iteration of map smoothing
     *
     * @param m         the map to smooth
     * @param fillEmpty if all the neighbouring cells of a given map position
     *                  are empty, place a wall at the position.
     */
    private static void smooth(Map m, boolean fillEmpty) {
        Tile wall = Tile.WALL;
        Tile open = Tile.OPEN;

        final int nrows = m.nrows();
        final int ncols = m.ncols();

        for (int row = 0; row < nrows; row++) {
            for (int col = 0; col < ncols; col++) {
                int wallcount = 0;

                // Count the numbers of wall tiles surrounding the tile at
                // position row/col. Any tiles outside the bounds of the map
                // are counted as walls. This ensures more 'wall' at the map
                // edges.
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i < 0 || j < 0 || i >= nrows || j >= ncols) {
                            wallcount++;
                        } else {
                            if (m.getTile(i, j) == Tile.WALL) {
                                wallcount++;
                            }
                        }
                    }
                }

                // Apply smoothing using cellular automata approach.
                if (wallcount > 4 || (fillEmpty && wallcount == 0)) {
                    m.setTile(row, col, wall);
                } else {
                    m.setTile(row, col, open);
                }
            }
        }
    }
}
