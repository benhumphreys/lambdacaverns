package lambdacaverns.world;

import lambdacaverns.common.Position;

/**
 * Encapsulates the avatar.
 */
public class Avatar {
    private Position position;
    private int health = 100;
    private int maxHealth = 100;
    private int armour = 100;
    private int gold = 0;

    public Avatar(Position pos) {
        this.position = pos;
    }
    
    public Position getPosition() {
        return position;
    }
    
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
