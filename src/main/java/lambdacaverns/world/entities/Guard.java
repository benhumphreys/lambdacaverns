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

import lambdacaverns.Constants;
import lambdacaverns.common.Position;
import lambdacaverns.world.map.Tile;

/**
 * Encapsulates an NPC Guard
 */
public class Guard extends AbstractPatrollingNPC {

    public Guard(Position pos) {
        super("Guard", pos, Tile.GUARD, Constants.GUARD_MAX_HEALTH,
                Constants.GUARD_ARMOUR, Constants.GUARD_MAX_DAMAGE,
                Faction.FRIENDLY);
    }
}