package gameobjects.characters.waka;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.HashMap;
import java.util.Arrays;
import game.Game;
import movement.Movement;
import gameobjects.characters.Character;
import gameobjects.cells.Cell;
import gameobjects.cells.Fruit;
import utils.CoordUtils;
import utils.Config;
import sprites.WakaSprite;
import gameobjects.characters.ghosts.Ghost;

/**
* The Waka character which the player controls.
* @see Character
*/
public final class Waka extends Character {

  private int lives;
  private int fruitsEaten = 0;
  private WakaSprite wakaSprite = WakaSprite.Left;

  /**
  * Creates a Waka instance.
  *
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param speed Speed of Waka in pixels per second.
  * @param lives Number of lives waka stars with.
  * @param game Game object to which Waka belongs.
  *
  * @see Game
  */
  public Waka(int x, int y, int speed, int lives, Game game) {
    super(x, y, speed, game, -4, -4);
    this.currentDirection = 37;
    this.lives = lives;
  }


  /**
  * Sets and updates waka's current WakaSprite object. The WakaSprite is responsible for
  * updating waka's display sprite.
  * @return A WakaSprite
  * @see WakaSprite
  */
  public WakaSprite setCurrentSprite() {
    return wakaSprite.update(currentDirection);
  }

  /**
  * Updates Waka's state every frame.
  */
  public void tick() {
    if (this.isGridSpace(this.getX(), this.getY())) {
      this.step();
    }
    this.tryMove();
    wakaSprite = setCurrentSprite();
    sprite = wakaSprite.sprite;
  }

  /**
  * Kills Waka by removing a life and resetting its location and direction.
  */
  public void kill() {
    this.reset();
    lives -= 1;
  }

  /**
  * Draws a game objecyt to the display window.
  * @param app display window instance.
  */
  public void draw(PApplet app) {
    this.tick();
    app.image(this.sprite, x + xOffset, y + yOffset);

    for (int i = 0; i < this.lives; i++) {
      app.image(WakaSprite.Right.sprite, 10 + i*32, 545);
    }
  }

  /**
  * Waka eats 1 fruit
  */
  public void eatFruit() {
    this.fruitsEaten += 1;
    if (this.fruitsEaten == Fruit.getTotalFruits()) {
      this.game.win();
    }
  }

  /**
  * Waka steps on a Cell object.
  */
  public void step() {
    Cell currentCell = game.getCell(toGridCoords(x), toGridCoords(y));
    currentCell.step(this);
  }

  /**
  * Sets Waka to super fruit mode, where he may collide with ghosts to destory them,
  * without losing a life.
  */
  public void superFruitMode() {
    game.frightenGhosts();
  }

  /**
  * Checks if Waka has at least 1 life.
  * @return True if Waka has lives, false otherwise.
  */
  public boolean hasLives() {
    if (lives <= 0) {
      return false;
    }
    return true;
  }

  /**
  * Turns all ghosts invisible if they are alive.
  * @see Ghost
  */
  public void drinkSoda() {
    for (Ghost g: game.ghosts) {
      g.invis();
    }
  }

}
