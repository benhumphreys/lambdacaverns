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

import lambdacaverns.Constants;
import lambdacaverns.common.Actions;
import lambdacaverns.common.Glyphs;
import lambdacaverns.common.Position;
import lambdacaverns.world.entities.Player;
import lambdacaverns.world.map.Map;

/**
 * Encapsulates the state of the game world.
 */
public class World {
    private Map map;
    private Player player;
    private Messages messages;

    public World() {
        messages = new Messages(Constants.TEXTAREA_HEIGHT);
    }

    public void init(Map map, Player player) {
        this.player = player;
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
                
        if (!getMap().withinMap(newPos)
                || getMap().getTile(newPos.row(),
                        newPos.col()).getGlyph() != Glyphs.OPEN) {
            getMessages().add("Cannot move there");
        } else {
            av.setPosition(newPos);
        }
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
}
