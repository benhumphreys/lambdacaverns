package lambdacaverns.world;

import static org.junit.Assert.*;

import java.util.Random;

import lambdacaverns.common.Actions;
import lambdacaverns.common.Position;
import lambdacaverns.world.map.Map;
import lambdacaverns.world.entities.IEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorldTest {
    /** Class under test */
    private World instance;

    @Before
    public void setUp() throws Exception {
        instance = WorldGen.generate(new Random());
    }

    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    /**
     * This is the main world test, ensuring the world maintains basic
     * consistency for 1000 ticks.
     */
    @Test
    public final void testOneThousandTicks() {
        final int N_ITERATIONS = 1000;
        for (int i = 0; i < N_ITERATIONS; ++i) {
            instance.tick(Actions.MOVE_UP);
            validateWorld(instance);
        }
    }

    /**
     * Performs basic consistency checks on the world
     */
    private void validateWorld(World w) {
        assertNotNull(w.getPlayer());
        assertNotNull(w.getEntities());
        assertNotNull(w.getMap());
        assertNotNull(w.getRandom());
        
        // Ensure player is within map bounds
        assertWithinMapBounds(w.getMap(), w.getPlayer());
        
        // Ensure all non-player entities are within map bounds
        for (IEntity e : w.getEntities()) {
            assertWithinMapBounds(w.getMap(), e);
        }
        
    }
    
    /**
     * Confirms the entity is within the map bounds.
     */
    private void assertWithinMapBounds(Map map, IEntity e) {
        assertNotNull(map);
        assertNotNull(e);
        
        Position pos = e.getPosition();
        assertTrue(pos.row() >= 0);
        assertTrue(pos.col() >= 0);
        assertTrue(pos.row() < map.nrows());
        assertTrue(pos.col() < map.ncols());
    }
}
