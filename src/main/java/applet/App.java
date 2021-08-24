package applet;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import game.Game;
import sprites.*;

public class App extends PApplet {

  /**
  * Width of the display window
  */
  public static final int WIDTH = 448;
  /**
  * Height of the display window
  */
  public static final int HEIGHT = 576;
  private Game game;

  /**
  * Creates an App object used to draw images onto display window.
  */
  public App() {
  }
  /**
  * Loads all images into corresponding sprites, sets frame rate, sets the font and creates a
  * new Game instance.
  *
  * @see Game
  * @see CellSprite
  * @see WakaSprite
  * @see GhostSprite
  */
  public void setup() {
    frameRate(60);

    // cell resources
    CellSprite.Empty.sprite = null;
    CellSprite.Horizontal.sprite = loadImage("src/main/resources/horizontal.png");
    CellSprite.Vertical.sprite = loadImage("src/main/resources/vertical.png");
    CellSprite.LeftUpperCorner.sprite = loadImage("src/main/resources/upLeft.png");
    CellSprite.RightUpperCorner.sprite = loadImage("src/main/resources/upRight.png");
    CellSprite.LeftLowerCorner.sprite = loadImage("src/main/resources/downLeft.png");
    CellSprite.RightLowerCorner.sprite = loadImage("src/main/resources/downRight.png");
    CellSprite.Fruit.sprite = loadImage("src/main/resources/fruit.png");
    CellSprite.Soda.sprite = loadImage("src/main/resources/soda.png");
    //ghost resources
    GhostSprite.Ambusher.sprite = loadImage("src/main/resources/ambusher.png");
    GhostSprite.Chaser.sprite = loadImage("src/main/resources/chaser.png");
    GhostSprite.Ignorant.sprite = loadImage("src/main/resources/ignorant.png");
    GhostSprite.Whim.sprite = loadImage("src/main/resources/whim.png");
    GhostSprite.Frightened.sprite = loadImage("src/main/resources/frightened.png");

    // waka resources
    WakaSprite.Closed.sprite = loadImage("src/main/resources/playerClosed.png");
    WakaSprite.Up.sprite = loadImage("src/main/resources/playerUp.png");
    WakaSprite.Down.sprite = loadImage("src/main/resources/playerDown.png");
    WakaSprite.Right.sprite = loadImage("src/main/resources/playerRight.png");
    WakaSprite.Left.sprite = loadImage("src/main/resources/playerLeft.png");

    //font
    PFont font = this.createFont("src/main/resources/PressStart2P-Regular.ttf", 10);
    this.textFont(font);

    //game object
    this.game = new Game();
  }

  /**
  * Sets size of display window.
  */
  public void settings() {
    size(WIDTH, HEIGHT);
  }

  /**
  * Draws pixels to display window.
  */
  public void draw() {
    background(0, 0, 0);
    stroke(255, 255, 255);
    this.game.draw(this);
  }

  /**
  * Passes keyboard events to game object as integer keycodes.
  */
  public void keyPressed() {
      this.game.queueMove(keyCode);
  }

  public static void main(String[] args) {
    PApplet.main("applet.App");
  }

}
