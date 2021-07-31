package invadem;
import processing.core.PApplet;
import processing.event.KeyEvent;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import invadem.object.Invader;
import invadem.object.Player;
import invadem.object.Projectile;
import invadem.object.Barrier;
import invadem.object.Entity;
import invadem.App;
import invadem.MainArray;
import java.util.List;
import java.util.ArrayList;

public class TestApp extends App {
  @Before
  public void setupTest() {
    PApplet.runSketch(new String[] {"sad"}, this);
    delay(2000);
    this.noLoop();
  }

  @Test
  public void spaceKeyTest() {
    keyCode = 32;
    keyPressed();
    assertTrue(this.getKeys().contains(32));
    keyReleased();
    assertTrue(!this.getKeys().contains(32));
  }

  @Test
  public void multipleKeysTest() {
    keyCode = 32;
    keyPressed();

    keyCode = 37;
    keyPressed();
    assertTrue(this.getKeys().contains(37));
    player.keyUpdate(this.getKeys());
    assertEquals(-1, player.getVelocity()[0]);
    keyReleased();

    keyCode = 39;
    keyPressed();
    assertTrue(this.getKeys().contains(39));
    player.keyUpdate(this.getKeys());
    assertEquals(1, player.getVelocity()[0]);

    keyCode = 37;
    keyPressed();
    assertTrue(this.getKeys().contains(39) && this.getKeys().contains(37) && this.getKeys().contains(32));
    player.keyUpdate(this.getKeys());
    assertEquals(0, player.getVelocity()[0]);
  }
  //Test cases should be passes if no user input was given while testing
}
