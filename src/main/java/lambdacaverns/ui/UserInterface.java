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
package lambdacaverns.ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

import lambdacaverns.Constants;
import lambdacaverns.common.Position;
import lambdacaverns.world.World;

public class UserInterface implements Terminal.ResizeListener {
    private Screen screen;

    private TitlePane titlePane;
    private MapPane mapPane;
    private TextPane textPane;
    private SidebarPane sidebarPane;

    public UserInterface(Screen s) {
        this.screen = s;
        s.clear();
        s.refresh();
        initPanes();
        
        s.getTerminal().addResizeListener(this);
    }

    public void draw(World w) {
        screen.clear();
        titlePane.draw(w);
        mapPane.draw(w);
        textPane.draw(w);
        sidebarPane.draw(w);
        
        screen.refresh();
    }

    private void initPanes() {
        final int ncols = screen.getTerminalSize().getColumns();
        final int nrows = screen.getTerminalSize().getRows();

        // Setup Title Pane
        titlePane = new TitlePane(screen, nrows, ncols 
                - Constants.PADDING_LEFT - Constants.PADDING_RIGHT,
                new Position(Constants.PADDING_TOP, Constants.PADDING_LEFT));

        // Setup Map Pane
        int mapHeight = nrows - Constants.PADDING_TOP - Constants.TITLE_HEIGHT
                - Constants.PADDING_TITLE_MAP - Constants.PADDING_MAP_TEXT
                - Constants.TEXTAREA_HEIGHT - Constants.PADDING_BOTTOM;

        int mapWidth = ncols - Constants.PADDING_LEFT
                - Constants.PADDING_MAP_SIDEBAR - Constants.SIDEBAR_WIDTH
                - Constants.PADDING_RIGHT;
        
        Position mapTlc = new Position(Constants.PADDING_TOP + Constants.TITLE_HEIGHT
                + Constants.PADDING_TITLE_MAP - 1,
                Constants.PADDING_LEFT);
        
        mapPane = new MapPane(screen, mapHeight, mapWidth, mapTlc);

        
        // Setup Text Pane
        Position textAreaTlc = new Position(
                mapTlc.row() + mapPane.height() + Constants.PADDING_MAP_TEXT,
                Constants.PADDING_LEFT);
        
        textPane = new TextPane(screen, Constants.TEXTAREA_HEIGHT,
                mapPane.width(), textAreaTlc);

        // Setup Sidebar Pane        
        Position sidebarTlc = new Position(
                Constants.PADDING_TOP + Constants.TITLE_HEIGHT + Constants.PADDING_TITLE_MAP,
                ncols - 1 - Constants.SIDEBAR_WIDTH);
        
        sidebarPane = new SidebarPane(screen, mapHeight,Constants.SIDEBAR_WIDTH,
                sidebarTlc);
    }

    public void onResized(TerminalSize termSize) {
        // TODO: Handle this correctly
        screen.refresh();
    }
}
