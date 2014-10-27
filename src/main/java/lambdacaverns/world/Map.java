package lambdacaverns.world;

import java.util.Vector;

import lambdacaverns.common.Glyphs;
import lambdacaverns.common.Position;

/**
 * Encapsulates the map. This is just a matrix of Tile objects.
 */
public class Map {
    private Vector<Tile> data;
    int _nrows;
    int _ncols;

    public Map(int nrows, int ncols) {
        // Pre-conditions
        assert (nrows > 0);
        assert (ncols > 0);

        this._nrows = nrows;
        this._ncols = ncols;

        // Initialise data vector
        data = new Vector<Tile>(nrows * ncols);
        for (int i = 0; i < nrows * ncols; ++i) {
            data.add(new Tile(Glyphs.OPEN));
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

    public void setTile(int row, int col, Tile t) {
        checkPosition(row, col);
        data.set(((row * _ncols) + col), t);
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
