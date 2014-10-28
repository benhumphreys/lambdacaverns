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
package lambdacaverns.world;

import java.util.Random;

import lambdacaverns.Constants;
import lambdacaverns.common.Glyphs;
import lambdacaverns.common.Position;
import lambdacaverns.world.entities.Player;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.MapGen;

public class WorldGen {

    /**
     * Generates a a world, initialised with a map, the players avatar
     * and an assortment of NPCs.
     * @return an initialised world.
     */
    public static World generate() {
        World w = new World();
        Map m = MapGen.generate(Constants.MAP_HEIGHT, Constants.MAP_WIDTH);
        
        // Find an open map position for the player to start
        Position pos = findOpenPosition(m);
        
        w.init(m, new Player(pos));
        return w;
    }
    
    // Find a random open position on the map
    private static Position findOpenPosition(Map m) {
        Random rng = new Random(System.currentTimeMillis());
        Position pos = null;
        do {
            int row = rng.nextInt(m.nrows());
            int col = rng.nextInt(m.ncols());
            if (m.getTile(row, col).getGlyph() == Glyphs.OPEN) {
                pos = new Position(row, col);
            }
            
        } while (pos == null);
        
        return pos;
    }

}
