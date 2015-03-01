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
package lambdacaverns.logic;

import lambdacaverns.world.Messages;
import lambdacaverns.world.World;
import lambdacaverns.world.entities.AttackableEntity;

/**
 * Encapsulates the logic of combat.
 */
public class CombatLogic {

    /**
     * Calculate and carry out combat action between two entities, the
     * attacker and the defender.
     * <p/>
     * Pre-conditions:
     * <ul>
     * <li> The attacker is alive (health > 0)
     * <li> The defender is alive (health > 0)
     * </ul>
     * <p/>
     * Post-conditions:
     * <ul>
     * <li> Any damage calculated is applied to the defender
     * <li> If the player was involved, a message is printed describing
     * the attack
     * </ul>
     *
     * @param w        the current world
     * @param attacker the entity that is attacking
     * @param defender the entity that is the subject of the attack
     */
    public static void combat(World w, AttackableEntity attacker,
                              AttackableEntity defender) {

        // Determine the maximum damage the attacker can do to the
        // defender
        int dmg = Math.max(0,
                attacker.getDamagePerTurn() - defender.getArmour());
        dmg = Math.min(dmg, defender.getHealth());

        // Randomise between 0 and max damage
        dmg = w.getRandom().nextInt(dmg + 1);

        // Apply the damage
        defender.damage(dmg);

        // Report the event via messages if the player is involved
        Messages msg = w.getMessages();
        if (attacker == w.getPlayer()) {
            if (dmg > 0) {
                msg.add("You hit an " + defender.getName() + " for " + dmg
                        + " damage");
            } else {
                msg.add("You swing at an " + defender.getName() + " and miss");
            }
            if (defender.getHealth() == 0) {
                msg.add("You kill an " + defender.getName());
            }
        } else if (defender == w.getPlayer()) {
            if (dmg > 0) {
                msg.add("An " + attacker.getName() + " hits you for " + dmg
                        + " damage");
            } else {
                msg.add("An " + attacker.getName() + " swings at you and misses");
            }
        } else {
            // If the player is not involved in combat, don't write any
            // message - But it can be useful to put debugging here to
            // see NPC vs NPC combat
        }
    }
}
