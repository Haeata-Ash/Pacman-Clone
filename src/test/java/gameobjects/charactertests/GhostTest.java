package test.java.gameobjects.charactertests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.AssertionError;

import processing.core.PApplet;
import java.util.ArrayList;
import applet.App;
import game.Game;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;
import gameobjects.characters.ghosts.Ghost;
import gameobjects.characters.ghosts.Ambusher;
import sprites.GhostSprite;
import gameobjects.cells.Fruit;

public class GhostTest {
  Ghost ghostTest;

  @BeforeEach
  public void construct() {
    ghostTest = new Ambusher(14, 20, 1, null);
  }


  @AfterEach
  public void destroy() {
    ghostTest = null;
  }

  @Test
  public void isAliveAndKillTest() {
    assertTrue(ghostTest.isAlive());

    ghostTest.kill();
    assertFalse(ghostTest.isAlive());
  }

  @Test
  public void setCurrentModeTest() {
    Game game = new Game("BasicTestMap.json");
    Ghost ghost = new Ambusher(14, 20, 1, game);
    ghost.frightenGhost();
    int i = 0;
    while (i < 3*65) {
      ghost.setCurrentMode();
      i++;
    }
    assertFalse(ghost.isFrightened());
  }

  @Test
  public void frightenGhostTest() {
    Game game = new Game("BasicTestMap.json");
    Ghost ghost = new Ambusher(14, 20, 1, game);
    ghost.frightenGhost();
    int i = 0;
    while (i < 3*59) {
      ghost.setCurrentMode();
      i++;
    }

    ghost.frightenGhost();
    assertTrue(ghost.isFrightened());
    while (i < 3*59) {
      ghost.setCurrentMode();
      i++;
    }

    assertTrue(ghost.isFrightened());
  }


  @Test
  public void nextDirectionTest() {

    ArrayList<Integer> dirs = new ArrayList<Integer>();
    dirs.add(37);
    dirs.add(39);
    dirs.add(40);
    for (int i = 0; i < 100; i++) {
      int nextDir = ghostTest.nextDirection(dirs, true);
      assertTrue(nextDir <= 40);
      assertTrue(nextDir >=37 );
    }


    Game game = new Game("ShortestPathTest1.json");
    ArrayList<Integer> dirs2 = new ArrayList<Integer>();
    dirs.add(37);
    dirs.add(39);
    dirs.add(38);
    game.tick();
    int shortestDir = game.ghosts.get(0).nextDirection(dirs, false);
    assertEquals(shortestDir, 38);
  }

  @Test
  public void possibleDirectionsTest() {
    Game game = new Game("2ValidDirectionsGhost.json");
    ArrayList<Integer> validDirs = new ArrayList<Integer>();
    validDirs.add(37);

    assertTrue(game.ghosts.get(0).possibleDirections().containsAll(validDirs));
    assertTrue(game.ghosts.get(0).possibleDirections().size() == validDirs.size());

    game = new Game("3ValidDirectionsGhost.json");
    validDirs = new ArrayList<Integer>();
    validDirs.add(37);
    validDirs.add(40);
    validDirs.add(38);

    assertTrue(game.ghosts.get(0).possibleDirections().containsAll(validDirs));
    assertTrue(game.ghosts.get(0).possibleDirections().size() == validDirs.size());

    game = new Game("OnlyReverseValidGhost.json");
    validDirs = new ArrayList<Integer>();
    validDirs.add(39);

    assertTrue(game.ghosts.get(0).possibleDirections().containsAll(validDirs));
    assertTrue(game.ghosts.get(0).possibleDirections().size() == validDirs.size());
  }

  @Test
  public void testDrawDebugLine() {
    App app = new App();
    PApplet.runSketch(new String[] {"applet.App"}, app);
    app.setup();

    Game game = new Game();
    for (Ghost ghost: game.ghosts) {
      ghost.debug = true;
    }
    try{
      game.ghosts.get(0).draw(app);
    } catch (NullPointerException e) {
      throw new AssertionError();
    }

  }

  @Test
  public void ghostSpriteTest() {
    App app = new App();
    PApplet.runSketch(new String[] {"applet.App"}, app);
    app.setup();
    assertNotNull(GhostSprite.Whim.update(false, true, false));
    assertNotNull(GhostSprite.Whim.update(true, true, false));
    assertNull(GhostSprite.Whim.update(false, true, true));
    assertNull(GhostSprite.Whim.update(true, true, true));
    assertNull(GhostSprite.Whim.update(false, false, true));
    assertNull(GhostSprite.Whim.update(false, false, false));
  }

}
