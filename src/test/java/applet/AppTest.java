package applet;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import applet.App;

class AppTest {

  @Test
  public void construction() {
    App app = new App();
    assertNotNull(app);
  }

  @Test
  public void dimensions() {
    assertEquals(App.WIDTH, 448);
    assertEquals(App.HEIGHT, 576);
  }

  @Test
  public void sketchTest() {
    App app = new App();
    PApplet.runSketch(new String[] {"applet.App"}, app);
    app.setup();
  }

}
