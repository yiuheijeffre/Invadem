package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import invadem.object.Projectile;
import invadem.object.Invader;
import invadem.object.Player;

public class ProjectileTest {

    PImage img = new PImage(0, 0);

   @Test
   public void testProjectileConstruction() {
       Projectile projectile = new Projectile(null, null, 0, 0, 0, 0, new int[] {0,0}, 0, 0);
       assertNotNull(projectile);
   }

   @Test
   public void testProjectileGetX() {
     Projectile projectile = new Projectile(null, null, 1, 0, 0, 0, new int[] {0,0}, 0, 0);
     assertEquals(1, projectile.getX());
   }

   @Test
   public void testProjectileGetY() {
     Projectile projectile = new Projectile(null, null, 0, 1, 0, 0, new int[] {0,0}, 0, 0);
     assertEquals(1, projectile.getY());
   }

   @Test
   public void testProjectileGetWidth() {
     Projectile projectile = new Projectile(null, null, 0, 0, 1, 0, new int[] {0,0}, 0, 0);
     assertEquals(1, projectile.getWidth());
   }

   @Test
   public void testProjectileGetHeight() {
     Projectile projectile = new Projectile(null, null, 0, 0, 0, 1, new int[] {0,0}, 0, 0);
     assertEquals(1, projectile.getHeight());
   }

   @Test
   public void testProjectileGetLife() {
     Projectile projectile = new Projectile(null, null, 0, 0, 0, 0, new int[] {0,0}, 1, 0);
     assertEquals(1, projectile.getLife());
   }

   @Test
   public void testProjectileGetProjectileDamage() {
     Projectile projectile = new Projectile(null, null, 0, 0, 0, 0, new int[] {0,0}, 0, 1);
     assertEquals(1, projectile.getProjectileDamage());
   }

   @Test
   public void testProjectileGetScore() {
     Projectile projectile = new Projectile(null, null, 0, 0, 0, 0, new int[] {0,0}, 1, 0);
     assertEquals(0, projectile.getScore());
   }

   @Test
   public void testProjectileIsDestroyed() {
     Projectile projectile = new Projectile(null, null, 0, 0, 0, 0, new int[] {0,0}, 1, 0);
     assertEquals(false, projectile.isDestroyed());
   }

   @Test
   public void testProjectileGetHit() {
     Projectile projectile = new Projectile(null, null, 0, 0, 0, 0, new int[] {0,0}, 1, 1);
     projectile.getHit(projectile.getProjectileDamage());
     assertEquals(true, projectile.isDestroyed());
     assertEquals(0, projectile.getLife());
   }

   @Test
   public void testProjectileCollision() {
     List<Invader> invaders = new ArrayList<Invader>();
     Invader invader = new Invader(null, 0, 0, 16, 16, new int[] {0, 0}, 3, 0);
     Invader invader2 = new Invader(null, 100, 0, 16, 16, new int[] {0, 0}, 3, 0);
     Invader invader3 = new Invader(null, 200, 0, 16, 16, new int[] {0, 0}, 3, 0);
     Player player = new Player(null, 100, 100, 20, 20, new int[] {0,0}, 3);
     invaders.add(invader);
     invaders.add(invader2);
     invaders.add(invader3);
     Projectile InvaderProjectile1 = new Projectile(null, invader, 100, 100, 1, 1, new int[] {0,-1}, 1, 1);
     //Won't hurt invader 2 since it is shot from an invader
     for(int i = 0; i < 101; i++) {
       InvaderProjectile1.isCollidedWith(invaders);
       InvaderProjectile1.tick();
     }
     assertEquals(false, InvaderProjectile1.isDestroyed());
     assertEquals(3, invader2.getLife());

     Projectile PlayerProjectile1 = new Projectile(null, player, 90, 100, 1, 1, new int[] {0,-1}, 1, 1);
     //Won't hurt invader 2 since it doesn't collide with the invader hitbox
     for(int i = 0; i < 101; i++) {
       PlayerProjectile1.isCollidedWith(invaders);
       PlayerProjectile1.tick();
     }
     assertEquals(false, InvaderProjectile1.isDestroyed());
     assertEquals(3, invader2.getLife());

     Projectile PlayerProjectile2 = new Projectile(null, player, 115, 100, 1, 1, new int[] {0,-1}, 1, 1);
     //Will hurt invader 2 since it collide with the invader hitbox
     for(int i = 0; i < 101; i++) {
       PlayerProjectile2.isCollidedWith(invaders);
       PlayerProjectile2.tick();
     }
     assertEquals(true, PlayerProjectile2.isDestroyed());
     assertEquals(2, invader2.getLife());

     Projectile InvaderProjectile2 = new Projectile(null, invader2, 100, 0, 1, 1, new int[] {0,1}, 2, 1);
     Projectile InvaderProjectile3 = new Projectile(null, invader2, 119, 0, 1, 1, new int[] {0,1}, 1, 1);
     //Shooting from invader to player and it will hit the player
     for(int i = 0; i < 102; i++) {
       InvaderProjectile2.isCollidedWith(player);
       InvaderProjectile2.tick();
       InvaderProjectile3.isCollidedWith(player);
       InvaderProjectile3.tick();
     }
     assertEquals(true, InvaderProjectile2.isDestroyed());
     assertEquals(0, player.getLife());




   }

}
