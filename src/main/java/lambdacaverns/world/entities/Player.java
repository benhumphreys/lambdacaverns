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

/**
 * Encapsulates the player entity.
 */
public class Player implements IEntity {
    private Position position;
    private int health = 100;
    private int maxHealth = 100;
    private int armour = 100;
    private int gold = 0;

    public Player(Position pos) {
        this.position = pos;
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
    
    @Override
    public void setPosition(Position pos) {
        this.position = pos;
    }
    
    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @param maxHealth the maxHealth to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * @return the armour
     */
    public int getArmour() {
        return armour;
    }

    /**
     * @param armour the armour to set
     */
    public void setArmour(int armour) {
        this.armour = armour;
    }
    
    /**
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * @param gold the gold to set
     */
    public void setGold(int gold) {
        this.gold = gold;
    }
}
