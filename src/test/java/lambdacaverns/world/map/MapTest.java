/*
 * Caverns of Lambda - A Rogue-like
 * Copyright (C) 2015  Ben Humphreys
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

import lambdacaverns.common.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    private static final int NROWS = 1000;
    private static final int NCOLS = 1000;
    private static final Tile EXPECTED_INITIAL_TILE = Tile.OPEN;
    private Map instance;

    @Before
    public void setUp() throws Exception {
        instance = new Map(NROWS, NCOLS);
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Test
    public final void testConstructor() {
        // Ensure all tiles are set open
        for (int row = 0; row < NROWS; ++row) {
            for (int col = 0; col < NCOLS; ++col) {
                assertEquals(EXPECTED_INITIAL_TILE, instance.getTile(0, 0));
            }
        }
    }
    
    @Test
    public final void testNrows() {
        assertEquals(NROWS, instance.nrows());
    }

    @Test
    public final void testNcols() {
        assertEquals(NCOLS, instance.ncols());
    }

    @Test
    public final void testSetGetTileIntInt() {
        instance.setTile(0, 0, Tile.TREE);
        assertEquals(Tile.TREE, instance.getTile(0, 0));
        }

    @Test
    public final void testSetGetTilePosition() {
        final Position p = new Position(0, 0);
        instance.setTile(p, Tile.TREE);
        assertEquals(Tile.TREE, instance.getTile(p));
    }

    @Test
    public final void testWithinMap() {
        // Within
        for (int row = 0; row < NROWS; ++row) {
            for (int col = 0; col < NCOLS; ++col) {
                assertTrue(instance.withinMap(new Position(row, col)));
            }
        }
        
        // Above
        assertFalse(instance.withinMap(new Position(-1, 0)));
        assertFalse(instance.withinMap(new Position(Integer.MIN_VALUE, 0)));
        
        // Below
        assertFalse(instance.withinMap(new Position(NROWS, 0)));
        assertFalse(instance.withinMap(new Position(Integer.MAX_VALUE, 0)));
        
        // Left
        assertFalse(instance.withinMap(new Position(0, -1)));
        assertFalse(instance.withinMap(new Position(0, Integer.MIN_VALUE)));
        
        // Right
        assertFalse(instance.withinMap(new Position(0, NCOLS)));
        assertFalse(instance.withinMap(new Position(0, Integer.MAX_VALUE)));
    }
}
