package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import invadem.object.Barrier;
import processing.core.PImage;

public class BarrierTest {

   PImage[] sprites = new PImage[] {new PImage(0,0), new PImage(1,1)};
   //Sprites is needed since most of the method will change sprites

   @Test
   public void barrierConstruction() {
       Barrier b = new Barrier(null, 0, 0, 0, 0, 3);
       assertNotNull(b);
   }

   @Test public void testBarrierGetX() {
       Barrier b = new Barrier(sprites, 1, 0, 0, 0, 3);
       assertEquals(1, b.getX());
   }

   @Test public void testBarrierGetY() {
       Barrier b = new Barrier(sprites, 0, 1, 0, 0, 3);
       assertEquals(1, b.getY());
   }

   @Test public void testBarrierGetWidth() {
       Barrier b = new Barrier(sprites, 0, 0, 1, 0, 3);
       assertEquals(1, b.getWidth());
   }

   @Test public void testBarrierGetHeight() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 1, 3);
       assertEquals(1, b.getHeight());
   }

   @Test public void testBarrierGetScore() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 1, 3);
       assertEquals(0, b.getScore());
   }

   @Test
   public void testBarrierGetSprite() {
      Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
      assertEquals(sprites[0], b.getImg());
   }

    @Test
    public void testBarrierChangeSprite() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       b.changeSprite();
       assertEquals(sprites[1], b.getImg());
    }

    @Test
    public void testBarrierChangeSprite2() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       b.changeSprite();
       b.changeSprite();
       assertEquals(sprites[1], b.getImg());
    }
    //Change sprite 2 times, when there's only two image in the sprites
    //Sprite wont be changed to prevent IndexOutOfBoundException

   @Test
   public void testBarrierNotDestroyed() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       assertEquals(false, b.isDestroyed());
   }

   @Test
   public void testBarrierHitPoints() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       assertEquals(3, b.getLife());
   }

   @Test
   public void testBarrierHitPoints1() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       b.getHit(1);
       assertEquals(2, b.getLife());
   }

   @Test
   public void testBarrierHitPoints2() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       b.getHit(2);
       assertEquals(1, b.getLife());
   }

   @Test
   public void testBarrierIsDestroyed() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       b.getHit(3);
       assertEquals(true, b.isDestroyed());
   }

   @Test public void testBarrierGetHit() {
       Barrier b = new Barrier(sprites, 0, 0, 0, 0, 3);
       b.getHit(1);
       assertEquals(2, b.getLife());
       assertEquals(sprites[1], b.getImg());
   }
   //Sprite should be changed as soon as barrier get hit


}
