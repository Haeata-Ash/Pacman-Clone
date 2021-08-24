package test.java.gameobjects.charactertests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.AssertionError;
import game.Game;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;
import gameobjects.characters.ghosts.Ghost;
import sprites.WakaSprite;
import applet.App;
import processing.core.PApplet;
import gameobjects.cells.Fruit;

public class WakaTest {
  Waka characTest;

  @BeforeEach
  public void construct() {
    characTest = new Waka(14, 20, 1, 1, null);
  }

  @BeforeEach
  public void resetFruits() {
    Fruit.resetTotalFruits();
  }

  @AfterEach
  public void destroy() {
    characTest = null;
  }

  @Test
  public void constructorTest() {
    assertNotNull(characTest);
  }

  @Test
  public void setCurrentSpriteTest() {
    App app = new App();
    PApplet.runSketch(new String[] {"applet.App"}, app);
    app.setup();

    Game g = new Game("MoveTestMap.json");
    assertEquals(g.waka.setCurrentSprite(), WakaSprite.Left);

    g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(40);
    g.waka.tryMove();
    assertEquals(g.waka.setCurrentSprite(), WakaSprite.Down);

    g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(38);
    g.waka.tryMove();
    assertEquals(g.waka.setCurrentSprite(), WakaSprite.Up);

    g = new Game("MoveTestMap.json");
    g.waka.setNextDirection(39);
    g.waka.tryMove();
    assertEquals(g.waka.setCurrentSprite(), WakaSprite.Right);

    g = new Game("MoveTestMap.json");
    for (int i = 0; i < 3; i++) {
      System.out.println(g.waka.setCurrentSprite());
    }

    assertEquals(g.waka.setCurrentSprite(), WakaSprite.Closed);

    for (int i = 0; i < 9; i++) {
      g.waka.setCurrentSprite();
    }

    assertEquals(g.waka.setCurrentSprite(), WakaSprite.Left);

    assertEquals(WakaSprite.Right.getDirectionalSprite(37), WakaSprite.Left);
    assertEquals(WakaSprite.Right.getDirectionalSprite(38), WakaSprite.Up);
    assertEquals(WakaSprite.Right.getDirectionalSprite(39), WakaSprite.Right);
    assertEquals(WakaSprite.Right.getDirectionalSprite(40), WakaSprite.Down);
    assertNull(WakaSprite.Right.getDirectionalSprite(-1));
    assertNull(WakaSprite.Right.getDirectionalSprite(0));

  }

  @Test
  public void killTest() {
    characTest.kill();
    assertFalse(characTest.hasLives());
  }

  @Test
  public void eatFruitTest() {
    Fruit f = new Fruit(0, 0);

    try{
      characTest.eatFruit();
    } catch (NullPointerException e) {
      return;
    }

    throw new AssertionError();
  }

  @Test
  public void superFruitModeTest() {
    Game game = new Game("BasicTestMap.json");
    game.waka.superFruitMode();
    for (Ghost ghost: game.ghosts) {
      assertTrue(ghost.isFrightened());
    }
  }

  @Test
  public void hasLivesTest() {
    assertTrue(characTest.hasLives());
    Waka noLife = new Waka(14, 20, 1, 0, null);
    assertFalse(noLife.hasLives());
  }






}
