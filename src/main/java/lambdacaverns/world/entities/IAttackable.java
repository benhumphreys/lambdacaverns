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
 * This interface specifies the method that need to be implemented for any
 * entity that can participate in combat
 */
public interface IAttackable {
    
    /**
     * @return the current health of the entity
     */
    public int getHealth();
    
    /**
     * The maximum damage per turn this entity can inflict on
     * another entity.
     * @return returns the maximum damage per turn
     */
    public int getDamagePerTurn();
    
    /**
     * Do damage to an entity. That is, reduce the current health of the entity
     * by the specified amount.
     * @param ammount the amount of damage to do
     */
    public void damage(int amount);
    
    /**
     * Heal an entity. That is, increase the current health of the entity by
     * the specified amount.
     * @param amount the amount of healing to do
     */
    public void heal(int amount);
    
    /**
     * Returns the current armour rating, a non-zero integer.
     * @return the current armour rating
     */
    public int getArmour();
    
    /**
     * Sets the armour rating for this entity
     * @param value the new armour rating value for the entity
     * @throws IllegalArgumentException if the parameter "armour" is a
     *                                  negative value.
     */
    public void setArmour(int armour);
}