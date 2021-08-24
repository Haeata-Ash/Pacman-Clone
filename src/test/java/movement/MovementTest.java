package test.java.movement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;
import gameobjects.characters.ghosts.Ghost;
import gameobjects.characters.ghosts.Ambusher;
import movement.Movement;

public class MovementTest implements Movement {
  Waka waka;
  Ghost ghost;

  @BeforeEach
  public void createCharacters() {
    waka = new Waka(0, 0, 1, 1, null);
    ghost = new Ambusher(0, 0, 1, null);
  }

  @AfterEach
  public void destroy() {
    waka = null;
    ghost = null;
  }

  @Test
  public void changePositionBasicTest() {
    assertArrayEquals(changePosition(16, 16, 16, 37), new int[] {0, 16});
    assertArrayEquals(changePosition(16, 16, 2, 38), new int[] {16, 14});
    assertArrayEquals(changePosition(348, 200, 1, 40), new int[] {348, 201});
    assertArrayEquals(changePosition(32, 0, 16, 39), new int[] {48, 0});
  }

  @Test
  public void changePositionEdgeTest() {
    assertArrayEquals(changePosition(0, 17, 16, 37), new int[] {-16, 17});
    assertArrayEquals(changePosition(16, 0, 2, 38), new int[] {16, -2});
    assertArrayEquals(changePosition(348, 200, 1000, 40), new int[] {348, 1200});
    assertArrayEquals(changePosition(32, 0, -5, 39), new int[] {27, 0});
  }

  @Test
  public void snapToMapTest() {
    assertArrayEquals(snapToMap(new int[] {234, 540}), new int[] {234, 540});
    assertArrayEquals(snapToMap(new int[] {0, 0}), new int[] {0, 0});
    assertArrayEquals(snapToMap(new int[] {432, 560}), new int[] {432, 560});
  }

  @Test
  public void snapToMapEdgeTest() {
    assertArrayEquals(snapToMap(new int[] {-50, 540}), new int[] {0, 540});
    assertArrayEquals(snapToMap(new int[] {0, -6000000}), new int[] {0, 0});
    assertArrayEquals(snapToMap(new int[] {90000, 560}), new int[] {432, 560});
    assertArrayEquals(snapToMap(new int[] {35, 6000000}), new int[] {35, 560});
    assertArrayEquals(snapToMap(new int[] {800, 561}), new int[] {432, 560});
    assertArrayEquals(snapToMap(new int[] {-1, -1}), new int[] {0, 0});
  }

  @Test
  public void getReverseDirectionTest() {
    assertEquals(getReverseDirection(37), 39);
    assertEquals(getReverseDirection(39), 37);
    assertEquals(getReverseDirection(40), 38);
    assertEquals(getReverseDirection(38), 40);
  }

  @Test
  public void isDirectionTest() {
    assertTrue(isDirection(37));
    assertTrue(isDirection(38));
    assertTrue(isDirection(39));
    assertTrue(isDirection(40));
    assertFalse(isDirection(-3));
    assertFalse(isDirection(-50));
    assertFalse(isDirection(36));
    assertFalse(isDirection(400));
  }

  public void tryMove() {}

}
