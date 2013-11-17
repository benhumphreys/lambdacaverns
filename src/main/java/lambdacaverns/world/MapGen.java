package lambdacaverns.world;

import java.util.Date;
import java.util.Random;

import lambdacaverns.common.Glyphs;

/**
 * A basic cellular automata based map generator. The technique used is taken
 * from here:
 * http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels
 */
public class MapGen {
    
    /**
     * Randomly generate a map.
     * @param nrows height of the map to be generated
     * @param ncols width of the map to be generated.
     */
    public static Map generate(int nrows, int ncols) {
        Map m = new Map(nrows, ncols);
        randomise(m);
        
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
    private static void randomise(Map m) {
        final double PROBABILITY_WALL = 0.45;
        Random rand = new Random(new Date().getTime());
        Tile wall = new Tile(Glyphs.WALL);
        
        for (int row = 0; row < m.nrows(); row++) {
            for (int col = 0; col < m.ncols(); col++) {
                if (rand.nextDouble() < PROBABILITY_WALL) {
                    m.setTile(row,  col, wall);
                }
            }
        }
    }
    
    /**
     * Smooth the map
     * @param m
     * @param fillEmpty
     */
    private static void smooth(Map m, boolean fillEmpty) {
        Tile wall = new Tile(Glyphs.WALL);
        Tile open = new Tile(Glyphs.OPEN);
        
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
                   for (int j = col - 1; j <= col + 1; j++ ) {
                       if (i < 0 || j < 0 || i >= nrows || j >= ncols) {
                           wallcount++;
                       } else {
                           if (m.getTile(i, j).getGlyph() == Glyphs.WALL) {
                               wallcount++;
                           }
                       }
                   }
               }
               
               // Apply smoothing using cellular automata approach.
               if (wallcount > 4 || (fillEmpty && wallcount == 0)) {
                   m.setTile(row,  col, wall);
               } else {
                   m.setTile(row,  col, open);
               }
            }
        }
    }
}
