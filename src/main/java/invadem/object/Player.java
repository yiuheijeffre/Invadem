package invadem.object;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import processing.event.KeyEvent;
import invadem.action.Shooting;

public class Player extends Entity implements Shooting {
  private PImage img;

  public Player(PImage img, int x, int y, int width, int height, int[] velocity, int life) {
    super(x, y, width, height, velocity, life);
    this.img = img;
    this.score = 0;
  }

  public void keyUpdate(List<Integer> keys) {
    if (keys.contains(39) && keys.contains(37)) {
      this.velocity[0] = 0;
    } else if(keys.contains(37)) {
     this.velocity[0] = -1;
    } else if (keys.contains(39)) {
     this.velocity[0] = 1;
    } else {
     this.velocity[0] = 0;
   }
  }

  public boolean isShooting(List<Integer> keys) {
    if (keys.contains(32)) {
      return true;
    } else{
      return false;
    }
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }
  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getLife() {
    return this.life;
  }

  public int[] getVelocity() {
    return this.velocity;
  }

  public void getHit(int ProjectileDamage) {
    if (this.life <= ProjectileDamage) {
      this.IsDestroyed = true;
      this.life = 0;
    } else {
      this.life -= ProjectileDamage;
    }
  }

  public boolean isDestroyed() {
    if (this.life <= 0) {
      return true;
    }
    return this.IsDestroyed;
  }

  public void tick() {

      this.x += velocity[0];
      this.y += velocity[1];
      if(this.x + velocity[0] <= 179) {
        this.x = 180;
      } else if (this.x + velocity[0] >= 439) {
        this.x = 438;
      }
      //Player sprite width = 22, so boundary for x should be 460-22 = 438
  }

  public void draw(PApplet app) {
      app.image(img, x, y, width, height);
      tick();
  }

  public int getScore() {
    return this.score;
  }

}
