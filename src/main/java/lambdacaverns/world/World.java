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
package lambdacaverns.world;

import lambdacaverns.Constants;
import lambdacaverns.common.Actions;
import lambdacaverns.common.Position;
import lambdacaverns.world.entities.AbstractEntity;
import lambdacaverns.world.entities.AttackableEntity;
import lambdacaverns.world.entities.IAttackable;
import lambdacaverns.world.entities.Player;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.Tile;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Encapsulates the state of the game world.
 */
public class World {
    private final Map map;
    private final Random random;
    private final Player player;
    private final Set<AbstractEntity> entities;
    private final Messages messages;

    /**
     * Constructor
     *
     * @param map the map upon which the world is built
     * @param rnd source of randomness for the world
     */
    World(Map map, Random rnd) {
        entities = new HashSet<AbstractEntity>();
        messages = new Messages(Constants.TEXTAREA_HEIGHT);
        player = new Player(new Position(0, 0));
        addEntity(player);
        this.map = map;
        this.random = rnd;
    }

    /**
     * Progress the state of the world by one tick.
     * The player gets one requested action per tick.
     *
     * @param action the action the player wishes to perform
     */
    public void tick(Actions action) {
        Player ply = getPlayer();
        if (ply.getHealth() == 0) {
            getMessages().add("You can't do anything, you are dead!");
            return;
        }

        // Run tick for player
        ply.playerTick(this, action);

        // Run tick for all non-player entities
        for (AbstractEntity e : entities) {

            // Ensure dead entities don't get to do anything
            if (e instanceof AttackableEntity) {
                AttackableEntity ae = (AttackableEntity) e;
                if (ae.getHealth() == 0) {
                    continue;
                }
            }

            e.tick(this);
        }

        if (ply.getHealth() == 0) {
            getMessages().add("YOU HAVE DIED!");
        }

        reapDeadEntities();
    }

    /**
     * Indicate of the tile is open. A tile is open if:
     * <ul>
     * <li> the position is within the map; and
     * <li> the map tile is of type Tile.OPEN; and
     * <li> the player is not located on the specified tile; and
     * <li> a non-player entity is not present on the specified tile.
     * </ul>
     *
     * @param p the position to check
     * @return true if the position is open, otherwise false
     */
    public boolean isOpen(Position p) {
        if (!getMap().withinMap(p)) return false;
        if (getMap().getTile(p) != Tile.OPEN) return false;
        for (AbstractEntity e : entities) {
            if (p.equals(e.getPosition())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Indicate of the tile is attackable. A tile is attackable if:
     * <ul>
     * <li> the position is within the map; and
     * <li> the map tile is of type Tile.OPEN; and
     * <li> an attackable entity (including the player) is on the tile
     * </ul>
     *
     * @param p the position to check
     * @return true if the position is open, otherwise false
     */
    public boolean isAttackable(Position p) {
        if (!getMap().withinMap(p)) return false;

        if (getMap().getTile(p) != Tile.OPEN)
            return false;

        // Does an attackable non-player entity exist?
        for (AbstractEntity e : entities) {
            if (p.equals(e.getPosition()))
                return e instanceof IAttackable;
        }

        return false;
    }

    /**
     * @return the world map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @return the player entity object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the Messages object associated with the world.
     * The messages object is use to log messages to the screen.
     *
     * @return the messages object
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * Returns a set of entities (including the player) in the world.
     * Note, this is not a copy so modifications made to entities in this
     * set are applied to the actual world.
     *
     * @return a set of entities
     */
    public Set<AbstractEntity> getEntities() {
        return entities;
    }

    /**
     * Adds an entity to the world
     *
     * @param e the entity to add
     */
    public void addEntity(AbstractEntity e) {
        entities.add(e);
    }

    /**
     * Returns the global random number generator (RNG). This is world-scope
     * (global) for two primary reasons. Firstly, being able to inject a RNG
     * at a single location makes unit testing easier. Secondly, if multiple
     * entities have separate pseudo-RNG objects, it is possible for the
     * same number sequence to be returned, resulting in a number of objects
     * (e.g. NPCs) having identical behaviour.
     *
     * @return the global random number generator
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Gets an entity at the position given by "p".
     *
     * @param p the position at which to get an entity.
     * @return the entity at position "p", or null if no entity exists at
     * the specified position.
     */
    public AbstractEntity getEntityAtPos(Position p) {
        if (!getMap().withinMap(p)) throw new IndexOutOfBoundsException();

        for (AbstractEntity e : entities) {
            if (p.equals(e.getPosition())) {
                return e;
            }
        }

        return null;
    }

    /**
     * Iterates through the entities set and removes all entities
     * which have a health value of zero.
     */
    private void reapDeadEntities() {
        Iterator<AbstractEntity> it = entities.iterator();
        while (it.hasNext()) {
            final AbstractEntity e = it.next();
            if (e instanceof IAttackable) {
                final IAttackable ae = (IAttackable) e;
                if (ae.getHealth() == 0 && ae != getPlayer()) {
                    it.remove();
                }
            }
        }
    }
}
