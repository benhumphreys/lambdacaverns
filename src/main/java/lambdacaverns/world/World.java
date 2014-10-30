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
package lambdacaverns.world;

import java.util.HashSet;
import java.util.Set;

import lambdacaverns.Constants;
import lambdacaverns.common.Actions;
import lambdacaverns.common.Position;
import lambdacaverns.world.entities.IEntity;
import lambdacaverns.world.entities.Player;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.map.Tile;

/**
 * Encapsulates the state of the game world.
 */
public class World {
    private Map map;
    private Player player;
    private Set<IEntity> entities;
    private Messages messages;

    public World(Map map) {
        entities = new HashSet<IEntity>();
        messages = new Messages(Constants.TEXTAREA_HEIGHT);
        player = new Player(new Position(0, 0));
        this.map = map;
    }

    public void tick(Actions action) {
        Player av = getPlayer();
        int row = av.getPosition().row();
        int col = av.getPosition().col();

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
            getMessages().add("Unknown Key");
            return;
        }

        if (!getMap().withinMap(newPos) || !isOpen(newPos)) {
            getMessages().add("Cannot move there");
        } else {
            av.setPosition(newPos);
        }

        // Run tick for all non-player entities
        for (IEntity e : entities) {
            e.tick(this);
        }
    }

    public boolean isOpen(Position p) {
        if (getMap().getTile(p.row(), p.col()) != Tile.OPEN)
            return false;
        if (p.equals(getPlayer().getPosition()))
            return false;
        for (IEntity e : entities) {
            if (p.equals(e.getPosition()))
                return false;
        }

        return true;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public Messages getMessages() {
        return messages;
    }

    public Set<IEntity> getEntities() {
        return entities;
    }

    public void addEntity(IEntity e) {
        entities.add(e);
    }
}
