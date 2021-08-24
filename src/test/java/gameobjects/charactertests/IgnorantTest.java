package test.java.gameobjects.charactertests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.AssertionError;
import java.util.ArrayList;

import game.Game;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;
import gameobjects.characters.ghosts.Ghost;
import gameobjects.characters.ghosts.Ignorant;
import sprites.WakaSprite;
import gameobjects.cells.Fruit;

public class IgnorantTest {

  @Test
  public void constructor() {
    assertNotNull(new Ignorant(1,1,1, null));
  }

  @Test
  public void testTargetClose() {
    Game game = new Game("IgnorantTestClose.json");
    assertArrayEquals(game.ghosts.get(0).getTarget(), new int[] {0,560});
  }

  @Test
  public void testTargetFar() {
    Game game = new Game("IgnorantTestFar.json");
    assertArrayEquals(game.ghosts.get(0).getTarget(), new int[] {224,416});
  }


}
