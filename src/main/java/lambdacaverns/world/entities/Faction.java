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

/**
 * Factions govern hostility between entities, both NPC & player.
 */
public enum Faction {
    FRIENDLY,
    ORC;

    /**
     * Allows determination as to the hostility of two factions.
     *
     * @param other the faction to consider for hostility.
     * @return true if this faction "other" is hostile to "this" faction
     */
    public boolean isHostile(Faction other) {
        return other != this;
    }
}
