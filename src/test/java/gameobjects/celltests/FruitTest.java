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

public class FruitTest {
  Cell cell;

  @BeforeEach
  public void construct() {
    cell = new Fruit(0, 0);
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
  public void overloadConstructor() {
    Cell fruit = new Fruit(0, 0, 0, 0);
    assertNotNull(fruit);
  }

  @Test
  public void stepTestWin() {
    // test if step adds fruit to waka
    Waka waka = new Waka(0,0,1,1,null);
    try {
      cell.step(waka);
    } catch (NullPointerException e) {
      return;
    }
    throw new AssertionError();
  }

  @Test
  public void stepTestWithoutFruit() {
    // test that steo doesn't add fruit to waka if already stepped
    Waka waka = new Waka(0,0,1,1,null);
    Fruit f = new Fruit(1,1);
    f.step(waka);

    try {
      f.step(waka);
    } catch (NullPointerException e) {
      throw new AssertionError();
    }
  }

  @Test
  public void canStepTest() {
    assertTrue(cell.canStep());
  }

  @Test
  public void hasFruitTest() {
    Waka waka = new Waka(0,0,1,1,null);
    Fruit f = new Fruit(1,1);
    assertTrue(f.hasFruit());
    f.step(waka);

    assertFalse(f.hasFruit());
  }

  @Test
  public void getTotalFruitsTest() {
    Fruit f2 = new Fruit(1,1);
    Fruit f3 = new Fruit(1,1);
    Fruit f4 = new Fruit(1,1);
    Fruit f5 = new Fruit(1,1);
    assertEquals(Fruit.getTotalFruits(), 5);
  }

  @Test
  public void resetTotalFruitsTest() {
    Fruit f2 = new Fruit(1,1);
    Fruit f3 = new Fruit(1,1);
    Fruit f4 = new Fruit(1,1);
    Fruit f5 = new Fruit(1,1);
    Fruit.resetTotalFruits();
    assertEquals(Fruit.getTotalFruits(), 0);
  }

}
