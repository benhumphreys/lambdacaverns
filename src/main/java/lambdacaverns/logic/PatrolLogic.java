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
package lambdacaverns.logic;

import java.util.Random;

import lambdacaverns.common.Position;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Map;

/**
 * Encapsulates the logic for a patrolling non-player character.
 * This object has the concept of a home, which is the centre point
 * of the patrol area and a maximum patrol distance which is
 * the radius of the patrol area.
 */
public class PatrolLogic {
    private final Position home;
    private final int patrolDistance;
    
    /**
     * Builds an instance that has no home and as such is a wanderer
     * without a specific patrol area.
     */
    public PatrolLogic() {
        home = null;
        patrolDistance = Integer.MAX_VALUE;
    }

    /**
     * Builds an instance with a specific home and patrol distance, thus
     * designating the patrol area.
     * 
     * @param home the centre point of the patrol area
     * @param patrolDistance the radius of the patrol area
     */
    public PatrolLogic(Position home, int patrolDistance) {
        this.home = home;
        this.patrolDistance = patrolDistance;
    }
    
    /**
     * Calculates the suggested next position for the NPC.
     * This method does not mutate the world, indeed it simply suggests
     * the next move for an NPC to make. It is up to the caller to actually
     * carry out the move.
     * 
     * @param w current state of the world
     * @param current the current position of the NPC
     * @return the suggested next position
     */
    public Position nextPosition(World w, Position current) {

        // Take a random step
        final Map m = w.getMap();
        Random rnd = w.getRandom();
        int row = rnd.nextInt(3) - 1;
        int col = rnd.nextInt(3) - 1;
        Position newpos = new Position(current.row() + row,
                current.col() + col);
        if (!m.withinMap(newpos) || !w.isOpen(newpos)) {
            return current;
        }
        
        // If the new position takes the entity outside of its patrol radius
        // don't take the step.
        if (home != null && newpos.distanceTo(home) >= patrolDistance) {
            return current;
        }
        
        return newpos;
    }
}
