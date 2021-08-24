package test.java.gameobjects.celltests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.AssertionError;
import game.Game;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;
import gameobjects.characters.ghosts.Ghost;
import gameobjects.characters.ghosts.Ambusher;
import gameobjects.cells.Cell;
import gameobjects.cells.Soda;

public class SodaTest {
  Soda cell;

  @BeforeEach
  public void construct() {
    cell = new Soda(0, 0);
  }


  @AfterEach
  public void destroy() {
    cell = null;
  }

  @Test
  public void constructorTest() {
    assertNotNull(cell);
  }

  @Test
  public void canStepTest() {
    assertTrue(cell.canStep());
  }

  @Test
  public void stepTest() {
    Game game = new Game("BasicTestMap.json");
    cell.step(game.waka);
    for (Ghost ghost: game.ghosts) {
      assertTrue(ghost.isInvis());
    }

    game = new Game("BasicTestMap.json");
    cell.step(game.waka);
    for (Ghost ghost: game.ghosts) {
      assertFalse(ghost.isInvis());
    }
  }
}
