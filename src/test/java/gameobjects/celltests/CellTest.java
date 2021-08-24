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

public class CellTest {
  Cell cell;

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

}
