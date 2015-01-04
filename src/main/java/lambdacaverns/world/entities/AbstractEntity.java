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
package lambdacaverns.world.entities;

import lambdacaverns.common.Position;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Tile;

/**
 * An interface for, and common implementation for all entities.
 * Provides the implementation for basic attributes:
 * <ul>
 * <li> name (immutable)
 * <li> tile glyph (immutable)
 * <li> position (mutable)
 * </ul>
 */
public abstract class AbstractEntity {
    private final String name;
    private final Tile tile;
    private Position pos;
    
    public AbstractEntity(String name, Tile tile, Position pos) {
        this.name = name;
        this.pos = pos;
        this.tile = tile;
    }
    
    /**
     * @return the name of the entity 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the location of the entity to the position specified.
     * @param pos   the new location for the entity.
     */
    public void setPosition(Position pos) {
        this.pos = pos;
    }
    
    /**
     * @return the current location of the entity.
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * @return the tile type that represents this entity.
     */
    public Tile getTile() {
        return tile;
    }
    
    /**
     * @param w the current state of the world
     */
    abstract public void tick(World w);
}
