package lambdacaverns.world;

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
