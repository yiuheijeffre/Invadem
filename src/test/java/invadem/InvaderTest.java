package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;
import invadem.object.Invader;
import java.util.List;
import java.util.ArrayList;

public class InvaderTest {

    PImage[] sprites = new PImage[] {new PImage(0,0), new PImage(1,1)};

   @Test
   public void testInvaderConstruction() {
       Invader invader = new Invader(null, 0, 0, 0, 0, new int[] {0, 0}, 0, 0);
       assertNotNull(invader);
   }

   @Test
   public void testPoweredInvaderConstruction() {
       Invader PoweredInvader = new Invader(null, 0, 0, 0, 0, new int[] {0, 0}, 0, 0, true);
       assertNotNull(PoweredInvader);
   }

   @Test
   public void testInvaderGetX() {
     Invader invader = new Invader(null, 1, 0, 0, 0, new int[] {0,0}, 0, 0);
     Invader PoweredInvader = new Invader(null, 2, 0, 0, 0, new int[] {0, 0}, 0, 0, true);
     assertEquals(1, invader.getX());
     assertEquals(2, PoweredInvader.getX());
   }

   @Test
   public void testInvaderGetY() {
     Invader invader = new Invader(null, 0, 1, 0, 0, new int[] {0,0}, 0, 0);
     Invader PoweredInvader = new Invader(null, 0, 2, 0, 0, new int[] {0, 0}, 0, 0, true);
     assertEquals(1, invader.getY());
     assertEquals(2, PoweredInvader.getY());
   }

   @Test
   public void testInvaderGetWidth() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 0);
     Invader PoweredInvader = new Invader(null, 0, 0, 2, 0, new int[] {0, 0}, 0, 0, true);
     assertEquals(1, invader.getWidth());
     assertEquals(2, PoweredInvader.getWidth());
   }

   @Test
   public void testInvaderGetHeight() {
     Invader invader = new Invader(null, 0, 0, 0, 1, new int[] {0,0}, 0, 0);
     Invader PoweredInvader = new Invader(null, 0, 0, 0, 2, new int[] {0, 0}, 0, 0, true);
     assertEquals(1, invader.getHeight());
     assertEquals(2, PoweredInvader.getHeight());
   }

   @Test
   public void testInvaderGetLife() {
     Invader invader = new Invader(null, 0, 0, 0, 0, new int[] {0,0}, 1, 0);
     Invader PoweredInvader = new Invader(null, 0, 0, 0, 0, new int[] {0, 0}, 2, 0, true);
     assertEquals(1, invader.getLife());
     assertEquals(2, PoweredInvader.getLife());
   }

   @Test
   public void testInvaderGetVelocity() {
     Invader invader = new Invader(null, 0, 0, 0, 0, new int[] {1,2}, 0, 0);
     Invader PoweredInvader = new Invader(null, 0, 0, 0, 0, new int[] {3,4}, 0, 0, true);
     assertEquals(1, invader.getVelocity()[0]);
     assertEquals(2, invader.getVelocity()[1]);
     assertEquals(3, PoweredInvader.getVelocity()[0]);
     assertEquals(4, PoweredInvader.getVelocity()[1]);
   }

   @Test
   public void testInvaderGetScore() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     Invader PoweredInvader = new Invader(null, 0, 0, 2, 0, new int[] {0, 0}, 0, 250, true);
     assertEquals(100, invader.getScore());
     assertEquals(250, PoweredInvader.getScore());
   }

   @Test
   public void testInvaderGetShotInterval() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     assertEquals(5, invader.getShotInterval());
     //Default: 5 seconds between each shot
   }

   @Test
   public void testInvaderSetShotInterval() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     invader.setShotInterval(1);
     assertEquals(1, invader.getShotInterval());
     invader.setShotInterval(0);
     assertEquals(1, invader.getShotInterval());
     //Maimum fire rate for invaders is 1 shot per second
   }

   @Test
   public void testInvaderChangeDirection() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     assertEquals(true, invader.changeDirection());
     assertEquals(false, invader.changeDirection());
     //Direction is changed as soon as changeDirection() is called, so false -> true and vice versa
     //thus I first check if it is true if changeDirection() is called
   }

   @Test
   public void testInvaderIsDestroyed() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 3, 100);
     assertEquals(false, invader.isDestroyed());
   }

   @Test
   public void testInvaderGetHit() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 3, 100);
     invader.getHit(1);
     assertEquals(2, invader.getLife());
     invader.getHit(3);
     assertEquals(0, invader.getLife());
     assertEquals(true, invader.isDestroyed());
   }

   @Test
   public void testInvaderReadyToShoot() {
     Invader invader = new Invader(sprites, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     assertEquals(false, invader.readyToShoot());
     for(int i = 0; i < 300; i++) {
       invader.tick();
     }
     //After 5 seconds
     assertEquals(true, invader.readyToShoot());
     invader.tick();
     assertEquals(false, invader.readyToShoot());
   }

   @Test
   public void testIsPoweredInvader() {
     Invader invader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     Invader PoweredInvader = new Invader(null, 0, 0, 1, 0, new int[] {0,0}, 0, 100, true);
     assertEquals(false, invader.isPoweredInvader());
     assertEquals(true, PoweredInvader.isPoweredInvader());
   }

   @Test
   public void testInvaderChangeSprite() {
     Invader invader = new Invader(sprites, 0, 0, 1, 0, new int[] {0,0}, 0, 100);
     invader.changeSprite();
     assertEquals(sprites[1], invader.getImg());
     invader.changeSprite();
     assertEquals(sprites[0], invader.getImg());
   }

   @Test
   public void testInvaderMove() {
     Invader invader = new Invader(sprites, 0, 0, 0, 0, new int[] {0,0}, 0, 0);
     assertEquals(sprites[0], invader.getImg());
     for(int i = 0; i < 60; i++) {
       invader.tick();
     }
     //Move 30 pixels to the right in 60 ticks/frames
     assertEquals(30, invader.getX());
     assertEquals(0, invader.getY());
     assertEquals(sprites[1], invader.getImg());
     //Sprite is changed the moment invader moved 30 pixels
     for(int i = 0; i < 16; i++) {
       invader.tick();
     }
     //Move 8 pixels downwards in 16 ticks/frames
     assertEquals(30, invader.getX());
     assertEquals(8, invader.getY());
     for(int i = 0; i < 60; i++) {
       invader.tick();
     }
     //Move 30 pixels to the left
     assertEquals(0, invader.getX());
     assertEquals(8, invader.getY());
   }

}
