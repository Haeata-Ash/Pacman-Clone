package test.java.gameobjects.celltests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import gameobjects.characters.waka.Waka;
import gameobjects.cells.Cell;
import gameobjects.cells.Empty;

public class EmptyTest {
  Empty cell;

  @BeforeEach
  public void construct() {
    cell = new Empty(0,0);
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
}
