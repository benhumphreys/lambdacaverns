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

import java.util.Vector;

import lambdacaverns.common.Position;

/**
 * Encapsulates the map. This is just a matrix of Tile objects.
 */
public class Map {
    private Vector<Tile> data;
    private int _nrows;
    private int _ncols;

    public Map(int nrows, int ncols) {
        // Pre-conditions
        assert (nrows > 0);
        assert (ncols > 0);

        this._nrows = nrows;
        this._ncols = ncols;

        // Initialise data vector
        data = new Vector<Tile>(nrows * ncols);
        for (int i = 0; i < nrows * ncols; ++i) {
            data.add(Tile.OPEN);
        }
    }

    public int nrows() {
        return _nrows;
    }

    public int ncols() {
        return _ncols;
    }

    public Tile getTile(int row, int col) {
        checkPosition(row, col);
        return data.get((row * _ncols) + col);
    }
    
    public Tile getTile(Position p) {
        return getTile(p.row(), p.col());
    }

    public void setTile(int row, int col, Tile t) {
        checkPosition(row, col);
        data.set(((row * _ncols) + col), t);
    }
    
    public void setTile(Position p, Tile t) {
        setTile(p.row(), p.col(), t);
    }
    
    public Boolean withinMap(Position pos) {
        if (pos.row() < 0 || pos.row() > _nrows - 1
                || pos.col() < 0 || pos.col() > _ncols - 1) {
            return false;
        
        } else {
            return true;
        }
    }

    /**
     * Checks a map position (row/col) and throws an IndexOutOfBoundsException
     * if the position is not within the map bounds.
     */
    private void checkPosition(int row, int col) {
        if (row > _nrows - 1) {
            throw new IndexOutOfBoundsException("Row: " + row
                    + " is out of bounds");
        }
        if (col > _ncols - 1) {
            throw new IndexOutOfBoundsException("Column: " + col
                    + " is out of bounds");
        }
    }
}
