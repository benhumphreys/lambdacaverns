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
package lambdacaverns;

public class Constants {

    /*********************************************************************
     *  GENERAL
     ********************************************************************/

    /**
     * Game title
     */
    public static final String GAME_TITLE = "Caverns of Lambda";

    /*********************************************************************
     *  MAP
     ********************************************************************/

    /**
     * Map Width (in text columns)
     */
    public static final int MAP_WIDTH = 200;

    /**
     * Map height (in text rows)
     */
    public static final int MAP_HEIGHT = 200;

    /*********************************************************************
     *  DIMENSIONS
     ********************************************************************/

    /**
     * Screen size (columns)
     */
    public static final int SCREEN_NCOLS = 140;

    /**
     * Screen size (rows)
     */
    public static final int SCREEN_NROWS = 40;

    /**
     * Text Area Height (width is variable)
     */
    public static final int TEXTAREA_HEIGHT = 6;

    /**
     * Side-bar Width (height is variable)
     */
    public static final int SIDEBAR_WIDTH = 17;

    /**
     * Title height (width is variable
     */
    public static final int TITLE_HEIGHT = 1;

    /*********************************************************************
     *  PADDING
     ********************************************************************/

    /**
     * Top padding
     */
    public static final int PADDING_TOP = 1;

    /**
     * Bottom padding
     */
    public static final int PADDING_BOTTOM = 1;

    /**
     * Left padding
     */
    public static final int PADDING_LEFT = 1;

    /**
     * Right padding
     */
    public static final int PADDING_RIGHT = 1;

    /**
     * Padding between the title and the map
     */
    public static final int PADDING_TITLE_MAP = 1;

    /**
     * Padding between the map and text area
     */
    public static final int PADDING_MAP_TEXT = 1;

    /**
     * Padding between the map and side-bar
     */
    public static final int PADDING_MAP_SIDEBAR = 2;

    /**
     * Padding between top and side-bar
     */
    public static final int PADDING_TOP_SIDEBAR = 1;

    /*********************************************************************
     * PLAYER
     ********************************************************************/

    /**
     * Initial value of max health for player
     */
    public static final int PLAYER_STARTING_MAX_HEALTH = 100;

    /**
     * Initial armour value of player
     */
    public static final int PLAYER_STARTING_ARMOUR_VALUE = 0;

    /**
     * Initial amount of gold for player
     */
    public static final int PLAYER_STARTING_GOLD = 0;

    /**
     * Damage per turn the player can do to another entity
     */
    public static final int PLAYER_MAX_DAMAGE = 4;

    /*********************************************************************
     * NON-PLAYER ENTITIES
     ********************************************************************/
    
    /* ORCS */

    /**
     * Number of Orcs at time of world creation
     */
    public static final int NPE_ORC_COUNT = 50;

    /**
     * Max health value for an Orc
     */
    public static final int ORC_MAX_HEALTH = 10;

    /**
     * Armour rating for an Orc
     */
    public static final int ORC_ARMOUR = 0;

    /**
     * Damage per turn on Orc can do to another entity
     */
    public static final int ORC_MAX_DAMAGE = 3;
    
    /* GUARDS */

    /**
     * Number of Guards at time of world creation
     */
    public static final int NPE_GUARD_COUNT = 20;

    /**
     * Max health value for an Guard
     */
    public static final int GUARD_MAX_HEALTH = 10;

    /**
     * Armour rating for an Guard
     */
    public static final int GUARD_ARMOUR = 1;

    /**
     * Damage per turn on Guard can do to another entity
     */
    public static final int GUARD_MAX_DAMAGE = 3;
}
