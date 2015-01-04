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

import lambdacaverns.Constants;
import lambdacaverns.common.Actions;
import lambdacaverns.common.Position;
import lambdacaverns.logic.CombatLogic;
import lambdacaverns.world.World;
import lambdacaverns.world.map.Tile;

/**
 * Encapsulates the player entity.
 */
public class Player extends AttackableEntity {

    /** The maximum health of the player */
    private int maxHealth = Constants.PLAYER_STARTING_MAX_HEALTH;

    /** The current number of gold coins carried by the player */
    private int gold = Constants.PLAYER_STARTING_GOLD;

    /** Constructor
     * 
     * @param pos the starting position for the player.
     */
    public Player(Position pos) {
        super("You", Tile.PLAYER, pos,
                Constants.PLAYER_STARTING_MAX_HEALTH,
                Constants.PLAYER_STARTING_ARMOUR_VALUE,
                Faction.FRIENDLY);
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

    /**
     * Player tick is handled by playerTick(World, Action), so this
     * doesn't do anything, however this is called each tick.
     */
    @Override
    public void tick(World w) {

    }

    /**
     * Player tick is different than NPE tick, as the player takes input in the
     * for of actions.
     * 
     * The player can perform at most a single action per tick.
     * 
     * @param w         the current world
     * @param action    an action to perform this tick
     */
    public void playerTick(World w, Actions action) {
        int row = getPosition().row();
        int col = getPosition().col();

        Position newPos = null;

        switch (action) {
        case MOVE_UP:
            newPos = new Position(row - 1, col);
            break;
        case MOVE_DOWN:
            newPos = new Position(row + 1, col);
            break;
        case MOVE_LEFT:
            newPos = new Position(row, col - 1);
            break;
        case MOVE_RIGHT:
            newPos = new Position(row, col + 1);
            break;
        default:
            w.getMessages().add("Unknown Key");
            return;
        }

        if (w.isOpen(newPos)) {
            setPosition(newPos);
        } else {
            if (w.isAttackable(newPos)) {
                AttackableEntity e = (AttackableEntity) w.getEntityAtPos(newPos);
                CombatLogic.combat(w, w.getPlayer(), e);
                if (e.getHealth() == 0) {
                    setPosition(newPos);
                }
            } else {
                w.getMessages().add("Cannot move there");
            }
        }
    }

    @Override
    public int getDamagePerTurn() {
        return Constants.PLAYER_MAX_DAMAGE;
    }
}
