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
package lambdacaverns.common;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {
    private Position p0 = new Position(0,0);
    private Position p1 = new Position(0,0);
    private Position p2 = new Position(1,0);
    private Position p3 = new Position(0,1);
    private Position p4 = new Position(1,1);
    private Position p5 = new Position(1,9);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testRow() {
        assertEquals(0, p1.row());
        assertEquals(1, p2.row());
    }

    @Test
    public final void testCol() {
        assertEquals(0, p1.col());
        assertEquals(1, p3.col());
    }

    @Test
    public final void testDistanceTo() {
        assertEquals(0, p0.distanceTo(p0));
        assertEquals(0, p0.distanceTo(p1));
        
        assertEquals(1, p1.distanceTo(p2));
        assertEquals(1, p1.distanceTo(p3));
        assertEquals(1, p1.distanceTo(p4));
        
        assertEquals(8, p4.distanceTo(p5));
        assertEquals(9, p0.distanceTo(p5));
    }

    @Test
    public final void testEqualsObject() {
        assertTrue(p0.equals(p0));
        assertTrue(p1.equals(p0));
        assertTrue(p0.equals(p1));
        
        assertFalse(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
    }

}
