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
package lambdacaverns.world.entities;

import java.util.Random;

import lambdacaverns.common.Position;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.Tile;

public class Orc implements IEntity {
    private Position position;
    private static Random rng = new Random(System.currentTimeMillis());

    public Orc(Position pos) {
        this.position = pos;
    }

    @Override
    public void setPosition(Position pos) {
        this.position = pos;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Tile getTile() {
        return Tile.ORC;
    }

    @Override
    public void tick(World w) {
        position = randomStep(w);
    }

    // Randomly take a step
    private Position randomStep(World w) {
        final Map m = w.getMap();
        int row = rng.nextInt(3) - 1;
        int col = rng.nextInt(3) - 1;
        Position newpos = new Position(position.row() + row,
                position.col() + col);
        if (m.withinMap(newpos) && w.isOpen(newpos)) {
            return newpos;
        } else {
            return position;
        }
    }
}
