package test.java.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import game.Game;
import gameobjects.characters.waka.Waka;
import gameobjects.cells.Fruit;
import gameobjects.cells.Cell;
import java.util.ArrayList;
import gameobjects.characters.ghosts.Ghost;
import gameobjects.characters.ghosts.Ambusher;


public class GameTest extends Game {
  Game testGame = null;

  @BeforeEach
  public void construct() {
    testGame = new Game("BasicTestMap.json");
  }

  @AfterEach
  public void destroy() {
    testGame = null;
  }

  @Test
  public void constructionNoArg() {
    Game g = new Game();
    assertNotNull(g);
  }

  @Test
  public void constructionOverload() {
    assertNotNull(testGame);
  }

  @Test
  public void getAChaserLocationTest() {
    //positive case
    assertArrayEquals(testGame.getAChaserLocation(), new int[] {240, 16});
  }

  @Test
  public void getAChaserLocationNegativeTest() {
    //negative case
    Game noChaser = new Game("NoChaserConfig.json");
    assertNull(noChaser.aChaser);
  }

  @Test
  public void isValidMoveTest() {
    Waka testWaka = new Waka(14, 20, 1, 3, null);
    // valid move right
    assertTrue(testGame.isValidMove(testWaka, 39));

    //invalid move down
    assertFalse(testGame.isValidMove(testGame.waka, 40));

    // edge case
    testWaka = new Waka(0, 0, 1, 3, null);
    assertFalse(testGame.isValidMove(testWaka, 37));

    // invalid move up
    testWaka = new Waka(335, 79, 1, 3, null);
    assertFalse(testGame.isValidMove(testWaka, 38));

  }

  @Test
  public void getCellTest() {
    // positive case
    assertNotNull(testGame.getCell(3, 1));
  }

  @Test
  public void frightenGhostsTest() {
    testGame.frightenGhosts();

    for (Ghost g: testGame.ghosts) {
      assertTrue(g.isFrightened());
    }
  }

  @Test
  public void checkCharacterCollisionTest() {
    // no collision
    testGame.checkCharacterCollision(testGame.waka, testGame.ghosts);
    assertTrue(testGame.waka.hasLives());
    for (Ghost g: testGame.ghosts) {
      assertTrue(g.isAlive());
    }

    //Collision with no super fruit
    int collisionX = testGame.ghosts.get(0).getX();
    int collisionY = testGame.ghosts.get(0).getY();

    Waka testWaka = new Waka(collisionX/16, collisionY/16, 1, 1, null);
    testGame.checkCharacterCollision(testWaka, testGame.ghosts);
    assertFalse(testWaka.hasLives());

    //collison with super fruit
    testGame.frightenGhosts();
    testWaka = new Waka(collisionX/16, collisionY/16, 1, 1, null);
    testGame.checkCharacterCollision(testWaka, testGame.ghosts);
    assertFalse(testGame.ghosts.get(0).isAlive());
  }


  @Test
  public void resetGhostsTest() {
    testGame.resetGhosts();

    for (Ghost g: testGame.ghosts) {
      assertEquals(g.getCurrentDirection(), 37);
      assertEquals(g.getX(), g.getInitalPos()[0]);
      assertEquals(g.getY(), g.getInitalPos()[1]);
    }

  }

  @Test
  public void strToCellTest() {
    // some positive cases
    Cell chaser = testGame.strToCell("c", 1, 1);
    assertNotNull(chaser);
    assertEquals(ghosts.size(), 4);

    Cell superFruit = testGame.strToCell("8", 1, 1);
    assertNotNull(superFruit);
    assertEquals(superFruit.getX(), 16);

    Cell wall = testGame.strToCell("2", 1, 1);
    assertNotNull(wall);
    assertEquals(wall.getY(), 16);
  }

  @Test
  public void createGridTest() {
    Cell[][] grid = testGame.createGrid("GridTestMap.txt");
    assertNotNull(grid);
    assertEquals(grid[0][0].getX(), 0);
    assertEquals(grid[0][1].getX(), 16);
    assertEquals(grid[0][2].getX(), 32);
    assertEquals(grid[0][8].getX(), 128);
    assertEquals(grid[1][4].getY(), 16);
    assertEquals(grid[9][4].getY(), 144);
    assertEquals(grid[25][25].getY(), 400);
  }

  @Test
  public void getConfigTest() {
    assertNotNull(testGame.getConfig());
  }

  @Test
  public void tickTestWin() {
    testGame.win();
    testGame.tick();
    assertEquals(testGame.gameMessage, "YOU WIN");
  }

  @Test
  public void tickGameResetTest() {
    testGame.win();
    testGame.tick();
    assertEquals(testGame.gameMessage, "YOU WIN");
    for (int i = 0; i < 600; i++) {
      testGame.tick();
    }

    assertNull(testGame.gameMessage);
  }

  @Test
  public void queueMoveTest() {
    // positive spacebar
    testGame.queueMove(32);
    for (Ghost g: testGame.ghosts) {
      assertTrue(g.debug);
    }

    //positive spacebar off
    testGame.queueMove(32);
    for (Ghost g: testGame.ghosts) {
      assertFalse(g.debug);
    }

    //positive direction
    testGame.queueMove(37);
    assertEquals(testGame.waka.getNextDirection(), 37);

    //positive direction
    testGame.queueMove(40);
    assertEquals(testGame.waka.getNextDirection(), 40);

    //negative cases
    Game game = new Game();
    game.queueMove(-1);
    for (Ghost g: game.ghosts) {
      assertFalse(g.debug);
    }
    assertEquals(game.waka.getNextDirection(), 0);

  }
}
