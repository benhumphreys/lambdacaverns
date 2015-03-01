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
package lambdacaverns.world.map;

import lambdacaverns.common.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the map. This is just a matrix of Tile objects.
 */
public class Map {
    private final List<Tile> data;
    private final int nrows;
    private final int ncols;

    public Map(int nrows, int ncols) {
        // Pre-conditions
        assert (nrows > 0);
        assert (ncols > 0);

        this.nrows = nrows;
        this.ncols = ncols;

        // Initialise data vector
        data = new ArrayList<Tile>(nrows * ncols);
        for (int i = 0; i < nrows * ncols; ++i) {
            data.add(Tile.OPEN);
        }
    }

    /**
     * @return the number of rows in the map
     */
    public int nrows() {
        return nrows;
    }

    /**
     * @return the number of columns in the map
     */
    public int ncols() {
        return ncols;
    }

    /**
     * Returns the tile at the given position.
     * This more primitive interface (relative to the one that takes a
     * Position) exists as an optimisation. Some code that iterates over
     * the whole map can be very large for huge maps.
     */
    public Tile getTile(int row, int col) {
        checkPosition(row, col);
        return data.get((row * ncols) + col);
    }

    /**
     * Returns the tile at the given position.
     */
    public Tile getTile(Position p) {
        return getTile(p.row(), p.col());
    }

    /**
     * Replaces the tile object at the specified position.
     */
    public void setTile(int row, int col, Tile t) {
        checkPosition(row, col);
        data.set(((row * ncols) + col), t);
    }

    /**
     * Replaces the tile object at the specified position.
     */
    public void setTile(Position p, Tile t) {
        setTile(p.row(), p.col(), t);
    }

    /**
     * Tests if a position is within the map bounds.
     *
     * @param pos the position to test
     * @return true if the position given by "pos" is within the map
     * bounds, otherwise false
     */
    public Boolean withinMap(Position pos) {
        return (pos.row() >= 0 && pos.row() < nrows
                && pos.col() >= 0 && pos.col() < ncols);
    }

    /**
     * Checks a map position (row/col) and throws an IndexOutOfBoundsException
     * if the position is not within the map bounds.
     */
    private void checkPosition(int row, int col) {
        if (row > nrows - 1) {
            throw new IndexOutOfBoundsException("Row: " + row
                    + " is out of bounds");
        }
        if (col > ncols - 1) {
            throw new IndexOutOfBoundsException("Column: " + col
                    + " is out of bounds");
        }
    }
}
