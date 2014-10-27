package lambdacaverns.world;

import lambdacaverns.Constants;
import lambdacaverns.common.Actions;
import lambdacaverns.common.Glyphs;
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

    public void tick(Actions action) {
        Avatar av = getAvatar();
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

    public Avatar getAvatar() {
        return avatar;
    }

    public Messages getMessages() {
        return messages;
    }
}
