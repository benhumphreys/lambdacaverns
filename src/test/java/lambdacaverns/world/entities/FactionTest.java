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
package lambdacaverns.world.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FactionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testIsHostile() {
        assertTrue(Faction.FRIENDLY.isHostile(Faction.ORC));
        assertTrue(Faction.ORC.isHostile(Faction.FRIENDLY));
        
        assertFalse(Faction.FRIENDLY.isHostile(Faction.FRIENDLY));
        assertFalse(Faction.ORC.isHostile(Faction.ORC));
    }
}
