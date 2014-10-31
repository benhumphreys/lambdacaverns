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

/**
 * An attackable entity base class
 * 
 * This abstract class provides the concepts of health and armour,
 * as is required to satisfy the IAttackable interface.
 */
public abstract class AttackableEntity implements IEntity, IAttackable {
    private int health;
    private int armour;

    public AttackableEntity(int initialHealth, int initialArmour) {
        health = initialHealth;
        armour = initialArmour;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void damage(int amount) {
        health = Math.max(0, health - amount);
    }

    @Override
    public void heal(int amount) {
        health += amount;
    }
    
    @Override
    public int getArmour() {
        return armour;
    }
    
    @Override
    public void setArmour(int armour) {
        this.armour = armour;
    }
}
