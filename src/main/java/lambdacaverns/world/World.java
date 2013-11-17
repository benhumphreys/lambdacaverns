package lambdacaverns.world;

import lambdacaverns.Constants;
import lambdacaverns.common.Position;

/**
 * Encapsulates the state of the game world.
 */
public class World {
    private Map map;
    private Avatar avatar;
    private Messages messages;
    
    public World() {
        avatar = new Avatar(new Position(25, 25));
        messages = new Messages(Constants.TEXTAREA_HEIGHT);
    }
    
    public void init(Map map) {
        this.map = map;
    }
    
    public void tick() {
        
    }
    
    public Map getMap() {
        return map;
    }
    
    public Avatar getAvatar() {
        return avatar;
    }
    
    public Messages getMessages() {
        return messages;
    }
}
