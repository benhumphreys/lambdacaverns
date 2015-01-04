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

import lambdacaverns.common.Position;
import lambdacaverns.logic.CombatLogic;
import lambdacaverns.logic.PatrolLogic;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Tile;

/**
 * Provides the basic logic for a combative patrolling NPC.
 * NPC classes extending this will patrol within some patrol range, attacking
 * enemies within attack range. It does not pursue enemies.
 */
public class AbstractPatrollingNPC extends AttackableEntity {
    final static int ATTACK_RANGE = 1; // TODO: Move this to config
    final static int PATROL_RANGE = 5; // TODO: Move this to config
    
    private final PatrolLogic patrol;
    private final int damagePerTurn;

    public AbstractPatrollingNPC(String name,
            Position pos,
            Tile tile,
            int initialHealth,
            int initialArmour,
            int damagePerTurn,
            Faction faction) {
        super(name, tile, pos, initialHealth, initialArmour, faction);
        this.damagePerTurn = damagePerTurn;
        patrol = new PatrolLogic(pos, PATROL_RANGE);
    }

    @Override
    public void tick(World w) {
        AttackableEntity e = findTarget(w, ATTACK_RANGE);
        if (e != null) {
            CombatLogic.combat(w, this, e);
        } else {
            setPosition(patrol.nextPosition(w, getPosition()));
        }
    }

    @Override
    public int getDamagePerTurn() {
        return damagePerTurn;
    }
    
    /**
     * Iterates over all entities, and returns the first enemy within "range"
     * steps. An enemy is defined by the faction system.
     * 
     * @return an enemy within "range" steps, or null if none are within range
     */
    private AttackableEntity findTarget(World w, int range) {
        for (AbstractEntity e : w.getEntities()) {
            if (!(e instanceof AttackableEntity)) continue;
            AttackableEntity ae = (AttackableEntity) e;
            
            if (getPosition().distanceTo(ae.getPosition()) <= range
                    && getFaction().isHostile(ae.getFaction())) {
                return ae;
            }
        }
        return null;
    }
}
