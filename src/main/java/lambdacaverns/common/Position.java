package lambdacaverns.common;

/**
 * Encapsulates a row/column (x/y) position on the map.
 */
public class Position {
    private int _row;
    private int _col;
    
    public Position(int row, int col) {
        _row = row;
        _col = col;
    }
    
    public int row() {
        return _row;
    }
    
    public int col() {
        return _col;
    }
}
