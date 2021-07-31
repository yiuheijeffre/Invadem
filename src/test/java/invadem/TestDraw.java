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

public class TestDraw extends App {
  @Before
  public void setupTest() {
    PApplet.runSketch(new String[] {"sad"}, this);
    delay(6000);
    this.noLoop();
  }

  @Test
  public void testDraw() {
    assertTrue(mainArray.getProjectiles().size() > 0);
    //Test to see if invader fire a projectile and if projecile is in projectiles list
  }
  
}
