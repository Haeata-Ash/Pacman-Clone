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
import gameobjects.characters.ghosts.Ambusher;
import sprites.WakaSprite;
import gameobjects.cells.Fruit;

public class AmbusherTest {
  Game game = new Game("AmbusherTest.json");


  @Test
  public void constructor() {
    assertNotNull(new Ambusher(1,1,1, null));
  }

  @Test
  public void testTarget() {
    assertArrayEquals(game.ghosts.get(0).getTarget(), new int[] {32,416});
  }
}
