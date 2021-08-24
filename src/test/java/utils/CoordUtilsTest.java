package test.java.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import utils.CoordUtils;

public class CoordUtilsTest implements CoordUtils {

  @Test
  public void packTest() {
    assertArrayEquals(pack(1, 2), new int[] {1, 2});
    assertArrayEquals(pack(800, -55), new int[] {800, -55});
  }

  @Test public void getDistanceTest() {
    assertEquals(getDistance(new int[] {33, 15}, new int[] {30, 11}), 5);
    assertEquals(getDistance(new int[] {400, 333}, new int[] {394, 341}), 10);
  }

  @Test public void getDistanceTestNull1() {
    try {
      double d = getDistance(new int[] {1,1}, null);
    } catch (NullPointerException e) {
    return;
    }

    throw new AssertionError();
  }

  @Test public void getDistanceTestNull2() {
    try {
      double d = getDistance(null, new int[] {54,650});
    } catch (NullPointerException e) {
    return;
    }

    throw new AssertionError();
  }

  @Test public void getDistanceTestNull3() {
    try {
      double d = getDistance(null, null);
    } catch (NullPointerException e) {
    return;
    }

    throw new AssertionError();
  }

  @Test
  public void toPixelCoordsTest() {
    assertEquals(toPixelCoords(5), 5*16);
    assertEquals(toPixelCoords(-51), -51*16);
  }

  @Test
  public void toGridCoordsTest() {
    assertEquals(toGridCoords(16), 16/16);
    assertEquals(toGridCoords(453), 453/16);
  }

  @Test
  public void isGridSpaceTest() {
    assertTrue(isGridSpace(64, 192));
    assertFalse(isGridSpace(-16, 0));
    assertFalse(isGridSpace(333, 16));
  }


}
