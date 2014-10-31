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

import lambdacaverns.Constants;
import lambdacaverns.common.Position;
import lambdacaverns.logic.CombatLogic;
import lambdacaverns.logic.PatrolLogic;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Tile;

/**
 * Encapsulates an NPC Orc
 */
public class Orc extends AttackableEntity {
    private Position position;
    private PatrolLogic patrol;

    public Orc(Position pos) {
        super(Constants.ORC_MAX_HEALTH, Constants.ORC_ARMOUR);
        this.position = pos;
        patrol = new PatrolLogic(pos, 5);
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
        Player ply = w.getPlayer();
        if (getPosition().distanceTo(ply.getPosition()) <= 1) {
            CombatLogic.combat(w, this, ply);
        } else {
            position = patrol.nextPosition(w, getPosition());
        }
    }
    
    @Override
    public String getName() {
        return "Orc";
    }
    
    @Override
    public String toString() {
        return "[" + this.getClass().getName() + " " + position + "]";
    }

    @Override
    public int getDamagePerTurn() {
        return Constants.ORC_MAX_DAMAGE;
    }
}
