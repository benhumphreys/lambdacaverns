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
package lambdacaverns.world;

import lambdacaverns.common.Actions;
import lambdacaverns.common.Position;
import lambdacaverns.world.entities.AbstractEntity;
import lambdacaverns.world.entities.IAttackable;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.Tile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WorldTest {
    /** Class under test */
    private World instance;

    @Before
    public void setUp() throws Exception {
        instance = WorldGen.generate(new Random());
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    /**
     * This is the main world test, ensuring the world maintains basic
     * consistency for 1000 ticks.
     */
    @Test
    public final void testOneThousandTicks() {
        final int N_ITERATIONS = 1000;
        for (int i = 0; i < N_ITERATIONS; ++i) {
            instance.tick(Actions.MOVE_UP);
            validateWorld(instance);
        }
    }

    /**
     * Performs basic consistency checks on the world
     */
    private void validateWorld(World w) {
        assertNotNull(w.getPlayer());
        assertNotNull(w.getEntities());
        assertNotNull(w.getMap());
        assertNotNull(w.getRandom());
        
        // Ensure all entities are within map bounds
        for (AbstractEntity e : w.getEntities()) {
            assertWithinMapBounds(w.getMap(), e);
            assertTrue(w.getMap().getTile(e.getPosition()) == Tile.OPEN);
        }
        
        // Ensure all attackable entities have health 0 (died this turn,
        // or greater
        for (AbstractEntity e : w.getEntities()) {
            if (e instanceof IAttackable) {
                IAttackable ia = (IAttackable) e;
                assertTrue(ia.getHealth() >= 0);
            }
        }
    }
    
    /**
     * Confirms the entity is within the map bounds.
     */
    private void assertWithinMapBounds(Map map, AbstractEntity e) {
        assertNotNull(map);
        assertNotNull(e);
        
        Position pos = e.getPosition();
        assertTrue(pos.row() >= 0);
        assertTrue(pos.col() >= 0);
        assertTrue(pos.row() < map.nrows());
        assertTrue(pos.col() < map.ncols());
    }
}
