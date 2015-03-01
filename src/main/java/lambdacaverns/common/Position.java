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
package lambdacaverns.common;

/**
 * Encapsulates an immutable row/column (x/y) position on the map
 */
public class Position {
    private final int _row;
    private final int _col;

    public Position(int row, int col) {
        _row = row;
        _col = col;
    }

    /**
     * @return the row
     */
    public int row() {
        return _row;
    }

    /**
     * @return the column
     */
    public int col() {
        return _col;
    }

    /**
     * Returns the distance between this position and another.
     *
     * @param other the other position to calculate the distance to
     * @return the distance between "this" position and the "other" position
     */
    public int distanceTo(Position other) {
        if (this.equals(other)) return 0;

        int diffrow = Math.abs(row() - other.row());
        int diffcol = Math.abs(col() - other.col());
        return (int) Math.floor(Math.sqrt((diffrow * diffrow) + (diffcol * diffcol)));
    }

    /**
     * Positions are equal if they have the same row and column
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return _row == other._row && _col == other._col;
    }

    @Override
    public int hashCode() {
        return _row + (31 * _col);
    }

    @Override
    public String toString() {
        return "(" + row() + ", " + col() + ")";
    }
}
