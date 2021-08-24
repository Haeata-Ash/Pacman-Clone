package test.java.gameobjects.celltests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import gameobjects.characters.waka.Waka;
import gameobjects.cells.Cell;
import gameobjects.cells.Vertical;
import gameobjects.cells.Wall;

public class WallTest {
  Wall cell;

  @BeforeEach
  public void construct() {
    cell = new Vertical(0,0);
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
    assertFalse(cell.canStep());
  }
}
