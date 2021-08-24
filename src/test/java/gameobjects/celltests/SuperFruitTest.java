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
import gameobjects.cells.Vertical;
import gameobjects.cells.Fruit;
import gameobjects.cells.SuperFruit;

public class SuperFruitTest {
  SuperFruit cell;

  @BeforeEach
  public void construct() {
    cell = new SuperFruit(0, 0);
  }


  @AfterEach
  public void destroy() {
    Fruit.resetTotalFruits();
    cell = null;
  }

  @Test
  public void constructorTest() {
    assertNotNull(cell);
  }

  @Test
  public void stepTest() {
    Game game = new Game("BasicTestMap.json");
    cell.step(game.waka);
    for (Ghost ghost: game.ghosts) {
      assertTrue(ghost.isFrightened());
    }

    game = new Game("BasicTestMap.json");
    cell.step(game.waka);
    for (Ghost ghost: game.ghosts) {
      assertFalse(ghost.isFrightened());
    }
  }
}
