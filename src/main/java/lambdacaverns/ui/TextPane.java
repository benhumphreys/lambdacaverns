package lambdacaverns.ui;

import java.util.LinkedList;
import java.util.Vector;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import lambdacaverns.common.Position;
import lambdacaverns.world.World;

/**
 * The screen pane that displays the text area. This pane outputs the various
 * text descriptions that help the player, for example a description of their
 * surroundings, etc.
 */
public class TextPane extends Pane {

    public TextPane(Screen s, int height, int width, Position corner) {
        super(s, height, width, corner);
    }
    
    @Override
    void draw(World w) {               
        Vector<String> text = wrapLines(w.getMessages().getTextList(),
                width(), height());
        
        int row = corner().row();
        for (String s: text) {
            assert (s.length() < width());
            
            screen().putString(corner().col(), row, s,
                    Terminal.Color.WHITE, Terminal.Color.BLACK);
            row++;
        }
    }
    
    /**
     * Given  a list of strings, wrap and trim them to fit within the bound
     * specified. The bounds here relate to the height and width of the pane.
     * 
     * @param lst       the input string list. The oldest strings are at
     *                  position 'first', and the most recent ones then
     *                  are at 'last'.
     * @param maxLen    the maximum length of any given string. Strings longer
     *                  than this must be wrapped. This should be greater 
     *                  than 0.
     * @param maxHeight the maximum height (number of rows). If all strings
     *                  in the input lst have length <= maxLen then the
     *                  vector returned is simply the last 'maxHeight' strings
     *                  however in the case some strings are too long, they get
     *                  wrapped. This should be greater than 0. 
     * @return          a vector of strings. Each string is guaranteed to have
     *                  length less than maxLen, and the vector will be of length
     *                  <= maxHeight.
     */
    static private Vector<String> wrapLines(LinkedList<String> lst,
                                            int maxLen, int maxHeight) {
        // Pre-conditions
        assert lst != null;
        assert maxLen > 0;
        assert maxHeight > 0;
        
        Vector<String> v = new Vector<String>();
        for (String s: lst) {
            if (s.length() < maxLen) {
                v.add(s);
            } else {
                v.addAll(v.size(), wrapString(s, maxLen));
            }
        }
        
        while (v.size() > maxHeight) v.remove(0);
        
        // Post-conditions
        assert v.size() <= maxHeight;
        for (String s : v) assert s.length() <= maxLen;
        
        return v;
    }
    
    /**
     * Given a string of some length, wrap it such that no line is larger than
     * the maxLen parameters.
     * 
     * This method will try to break the line at a space, rather than mid-word.
     * 
     * @param s the string to be wrapped (if needed)
     * @param maxLen the maximum characters in any line.
     * @return  a vector containing the wrapped lines. If the input string 's'
     *          had length <= maxLen then the vector will be of size 1. Note that
     *          the beginning of the string is at vector index 0;
     */
    static private Vector<String> wrapString(String s, int maxLen) {
        s = s.trim();
        Vector<String> v = new Vector<String>();
        int beginIndex = 0;
        while (beginIndex < s.length()) {
            int subStrLen = Math.min(maxLen, s.length() - beginIndex);
            int endIndex = subStrLen + beginIndex;
            
            // TODO: Fix this hack - This is simply trying to find the best position
            // to break the string by moving backwards and looking for a space. Some
            // strings may break it.
            if (subStrLen == maxLen) {
                int lastSpace = s.lastIndexOf(' ', endIndex);
                if (lastSpace > (beginIndex + 1)) endIndex = lastSpace;
            }
            
            v.add(s.substring(beginIndex, endIndex).trim());
            beginIndex = endIndex;
        }
        return v;
    }
}
