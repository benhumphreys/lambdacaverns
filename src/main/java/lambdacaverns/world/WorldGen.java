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
import lambdacaverns.common.Position;
import lambdacaverns.world.entities.Orc;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.MapGen;

/**
 * Provides functionality to build a "World" object
 */
public class WorldGen {

    /**
     * Generates a a world, initialised with a map, the players avatar
     * and an assortment of NPCs.
     * @return an initialised world.
     */
    public static World generate(Random rnd) {
        Map m = MapGen.generate(Constants.MAP_HEIGHT, Constants.MAP_WIDTH, rnd);
        World w = new World(m, rnd);
        
        // Find an open map position for the player to start
        Position pos = findOpenPosition(w);
        w.getPlayer().setPosition(pos);
        
        spawnOrcs(w, Constants.NPE_ORC_COUNT);
        return w;
    }
    
    /**
     * Find a random open position on the map.
     * 
     * This method will return a random open position on the map. The
     * map tile will be of type "OPEN" and no "entity" will be present
     * at that location.
     * 
     * @param w the world in which to find an open position.
     * @return the position of the open tile
     */
    private static Position findOpenPosition(World w) {
        Map m = w.getMap();
        Random rng = w.getRandom();
        Position pos = null;
        while (true) {
            int row = rng.nextInt(m.nrows());
            int col = rng.nextInt(m.ncols());
            pos = new Position(row, col);
            if (w.isOpen(pos)) {
                break;
            }
        }
        
        return pos;
    }

    /**
     * Spawn "n" Orcs at random location in the world "w".
     * @param w the world in which orcs will be added.
     */
    private static void spawnOrcs(World w, int n) {
        for (int i = 0; i < n; ++i) {
            w.addEntity(new Orc(findOpenPosition(w)));
        }
    }
}
