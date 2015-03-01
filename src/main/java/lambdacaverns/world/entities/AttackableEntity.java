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

import lambdacaverns.common.Position;
import lambdacaverns.world.map.Tile;

/**
 * An attackable entity base class
 * <p/>
 * This abstract class provides the concepts of health and armour, as is
 * required to satisfy the IAttackable interface.
 */
public abstract class AttackableEntity extends AbstractEntity implements
        IAttackable {
    private int health;
    private int armour;
    private final Faction faction;

    public AttackableEntity(String name, Tile tile, Position pos,
                            int initialHealth, int initialArmour, Faction faction) {
        super(name, tile, pos);
        health = initialHealth;
        armour = initialArmour;
        this.faction = faction;
    }

    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Cause damage to this entity.
     * The entities health will be reduced by "amount", however health will
     * never be reduced below zero. The damage is applied directly, and does
     * not take into account armour rating. This must be taken account of
     * in external combat logic.
     *
     * @throws IllegalArgumentException if the parameter "amount" is less
     *                                  than zero
     */
    @Override
    public void damage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        health = Math.max(0, health - amount);
    }

    /**
     * Heal this entity.
     * The entities health will be increased by "amount", however will not
     * increase health beyond Integer.MAX_VALUE so the healing applied can
     * be less than "amount"
     *
     * @throws IllegalArgumentException if the parameter "amount" is less
     *                                  than zero
     */
    @Override
    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        if (Integer.MAX_VALUE - health <= amount) {
            health = Integer.MAX_VALUE;
        } else {
            health += amount;
        }
    }

    @Override
    public int getArmour() {
        return armour;
    }

    @Override
    public void setArmour(int armour) {
        this.armour = armour;
    }

    public Faction getFaction() {
        return faction;
    }
}
