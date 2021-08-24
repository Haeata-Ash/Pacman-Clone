package test.java.gameobjects.charactertests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import game.Game;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;

public class CharacterTest {
  Waka characTest;

  @BeforeEach
  public void construct() {
    characTest = new Waka(14, 20, 1, 3, null);
  }

  @AfterEach
  public void destroy() {
    characTest = null;
  }

  @Test
  public void getCurrentDirection() {
    assertEquals(characTest.getCurrentDirection(), 37);
  }

  @Test
  public void setNextDirectionTest() {
    assertEquals(characTest.getNextDirection(), 0);

    characTest.setNextDirection(39);
    assertEquals(characTest.getNextDirection(), 39);

    characTest.setNextDirection(37);
    assertEquals(characTest.getNextDirection(), 37);

    characTest.setNextDirection(38);
    assertEquals(characTest.getNextDirection(), 38);

    characTest.setNextDirection(40);
    assertEquals(characTest.getNextDirection(), 40);

    characTest.setNextDirection(-900);
    assertEquals(characTest.getNextDirection(), 40);

    characTest.setNextDirection(1000000);
    assertEquals(characTest.getNextDirection(), 40);
  }

  @Test
  public void getSpeedTest() {
    assertEquals(characTest.getSpeed(), 1);
  }

  @Test
  public void getInitialPosTest() {
    assertArrayEquals(characTest.getInitalPos(), new int[] {14*16, 20*16});
  }

  @Test
  public void getLocationTest() {
    assertArrayEquals(characTest.getLocation(), new int[] {14*16, 20*16});
  }

  @Test
  public void tryMoveTest() {
    Game g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(38);
    g.waka.tryMove();
    assertNotEquals(g.waka.getInitalPos()[1], g.waka.getLocation()[1]);

    g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(39);
    g.waka.tryMove();
    assertNotEquals(g.waka.getInitalPos()[0], g.waka.getLocation()[0]);

    g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(40);
    g.waka.tryMove();
    assertNotEquals(g.waka.getInitalPos()[1], g.waka.getLocation()[1]);

    g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(37);
    g.waka.tryMove();
    assertNotEquals(g.waka.getInitalPos()[0], g.waka.getLocation()[0]);

    Game invalidMove = new Game("ShortestPathTest1.json");
    invalidMove.waka.setNextDirection(38);
    invalidMove.waka.tryMove();
    assertEquals(invalidMove.waka.getInitalPos()[1], invalidMove.waka.getLocation()[1]);
  }

  @Test
  public void resetTest() {
    Game g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(38);
    g.waka.tryMove();
    g.waka.reset();
    assertArrayEquals(g.waka.getInitalPos(), g.waka.getLocation());
    assertEquals(g.waka.getCurrentDirection(), 37);
    assertEquals(g.waka.getNextDirection(), -1);

  }
}
