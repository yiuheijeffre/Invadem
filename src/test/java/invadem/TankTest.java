package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;
import invadem.object.Player;
import java.util.List;
import java.util.ArrayList;

public class TankTest {
    PImage img = new PImage(0, 0);



   @Test
   public void testTankConstruction() {
       Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 0);
       assertNotNull(player);
   }

   @Test
   public void testTankGetX() {
     Player player = new Player(null, 1, 0, 0, 0, new int[] {0,0}, 0);
     assertEquals(1, player.getX());
   }

   @Test
   public void testTankGetY() {
     Player player = new Player(null, 0, 1, 0, 0, new int[] {0,0}, 0);
     assertEquals(1, player.getY());
   }

   @Test
   public void testTankGetWidth() {
     Player player = new Player(null, 0, 0, 1, 0, new int[] {0,0}, 0);
     assertEquals(1, player.getWidth());
   }

   @Test
   public void testTankGetHeight() {
     Player player = new Player(null, 0, 0, 0, 1, new int[] {0,0}, 0);
     assertEquals(1, player.getHeight());
   }

   @Test
   public void testTankGetLife() {
     Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 3);
     assertEquals(3, player.getLife());
   }

   @Test
   public void testTankGetVelocity() {
     Player player = new Player(null, 1, 0, 0, 0, new int[] {1,1}, 0);
     assertEquals(1, player.getVelocity()[0]);
     assertEquals(1, player.getVelocity()[1]);
   }

   @Test
   public void testTankGetScore() {
     Player player = new Player(null, 1, 0, 0, 0, new int[] {0,0}, 0);
     assertEquals(0, player.getScore());
   }

   @Test
   public void testTankIsDestroyed() {
     Player player = new Player(null, 1, 0, 0, 0, new int[] {0,0}, 3);
     assertEquals(false, player.isDestroyed());
   }

   @Test
   public void testTankGetHit1() {
     Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 3);
     player.getHit(1);
     assertEquals(2, player.getLife());
   }

   @Test
   public void testTankGetHit2() {
     Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 3);
     player.getHit(4);
     assertEquals(0, player.getLife());
     assertEquals(true, player.isDestroyed());
   }

   @Test
   public void testTankKeyUpdate() {
     Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 3);
     List<Integer> keys = new ArrayList<Integer>();
     keys.add(31);
     player.keyUpdate(keys);
     assertEquals(0, player.getVelocity()[0]);
     //Testing key that isn't arrow keys
     keys.add(37);
     player.keyUpdate(keys);
     assertEquals(-1, player.getVelocity()[0]);
     //Left arrow key is pressed
     keys.clear();
     keys.add(39);
     player.keyUpdate(keys);
     assertEquals(1, player.getVelocity()[0]);
     //Right arrow key is pressed
     keys.add(37);
     player.keyUpdate(keys);
     assertEquals(0, player.getVelocity()[0]);
     //Both right and left arrow keys are pressed
   }

   @Test
   public void testTankShoot() {
     Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 0);
     List<Integer> keys = new ArrayList<Integer>();
     assertEquals(false, player.isShooting(keys));
     keys.add(32);
     assertEquals(true, player.isShooting(keys));
     //32 is keycode for space key
   }

   @Test
   public void testTankMove() {
     Player player = new Player(null, 300, 300, 0, 0, new int[] {0,0}, 1);
     //Player is in game boundary
     List<Integer> keys = new ArrayList<Integer>();
     keys.add(37);
     player.keyUpdate(keys);
     player.tick();
     assertEquals(299, player.getX());
     //The left arrow key is pressed, so the tank should move to the left by one pixel
     //in one tick
     player.tick();
     player.tick();
     player.tick();
     player.tick();
     assertEquals(295, player.getX());
     //5 ticks have been passed/called, so the tank should be at x = -5
     keys.add(39);
     player.keyUpdate(keys);
     player.tick();
     assertEquals(295, player.getX());
     //Right arrow key is now pressed as well as the left arrow key, so the tank should not move
     keys.clear();
     keys.add(39);
     player.keyUpdate(keys);
     player.tick();
     assertEquals(296, player.getX());
     //Only right arrow key is pressed, the tank should move to the right by one pixel in one tick
     keys.clear();
     keys.add(31);
     player.keyUpdate(keys);
     player.tick();
     assertEquals(296, player.getX());
     //Neither left nor right arrow keys are pressed, so no movement should be made
   }

   @Test
   public void testTankMoveLeftBoundary() {
     Player player = new Player(null, 181, 0, 0, 0, new int[] {0,0}, 1);
     List<Integer> keys = new ArrayList<Integer>();
     keys.add(37);
     player.keyUpdate(keys);
     player.tick();
     player.tick();
     player.tick();
     assertEquals(180, player.getX());
     keys.clear();
     keys.add(39);
     player.keyUpdate(keys);
     player.tick();
     assertEquals(181, player.getX());
   }

   @Test
   public void testTankMoveRightBoundary() {
     Player player = new Player(null, 437, 0, 22, 0, new int[] {0,0}, 1);
     //Tank's player model have a width of 22 pixel
     List<Integer> keys = new ArrayList<Integer>();
     keys.add(39);
     player.keyUpdate(keys);
     player.tick();
     player.tick();
     assertEquals(460, player.getX() + player.getWidth());
     //460 is the right boundary for tank
     keys.clear();
     keys.add(37);
     player.keyUpdate(keys);
     player.tick();
     assertEquals(459, player.getX() + player.getWidth());
   }

}
