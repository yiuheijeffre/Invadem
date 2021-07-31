package invadem.object;

import invadem.object.Entity;
import invadem.object.Player;
import invadem.object.Barrier;
import invadem.object.Invader;
import invadem.object.Projectile;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;

public class Projectile extends Entity{
  private boolean collided;
  private Entity shooter;
  private PImage img;
  private int ProjectileDamage;
  public Projectile(PImage img, Entity shooter, int x, int y, int width, int height, int[] velocity, int life, int ProjectileDamage) {
    super(x, y, width, height, velocity, life);
    this.shooter = shooter;
    this.img = img;
    this.ProjectileDamage = ProjectileDamage;
    this.score = 0;
  }


  public void isCollidedWith(List<? extends Entity> entities) {
      for(Entity entity: entities) {
        if (entity.getX() < this.getX() + this.getWidth() &&
            entity.getX() + entity.getWidth() > this.getX() &&
            entity.getY() < this.getY() + this.getHeight() &&
            entity.getY() + entity.getHeight() > this.getY()) {
            //If Collided
              if (entity != this && entity.getClass() != this.shooter.getClass() && !this.isDestroyed() && !entity.isDestroyed()) {
                this.getHit(this.ProjectileDamage);
                entity.getHit(this.ProjectileDamage);
              }
        }
      }
  }
  public void isCollidedWith(Entity entity) {
      if (entity.getX() < this.getX() + this.getWidth() &&
          entity.getX() + entity.getWidth() > this.getX() &&
          entity.getY() < this.getY() + this.getHeight() &&
          entity.getY() + entity.getHeight() > this.getY()) {
          //If Collided
            if (entity.getClass() != this.shooter.getClass() && !this.isDestroyed() && !entity.isDestroyed()) {
              this.getHit(this.ProjectileDamage);
              entity.getHit(this.ProjectileDamage);
            }
      }
  }
  //Won't have player as parameter

  public void tick() {
      this.x += velocity[0];
      this.y += velocity[1];
  }

  public void draw(PApplet app) {
      app.image(img, x, y, width, height);
      tick();
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public int getLife() {
    return this.life;
  }

  public int getProjectileDamage() {
    return this.ProjectileDamage;
  }

  public boolean isDestroyed() {
    if (this.life <= 0) {
      return true;
    }
    return this.IsDestroyed;
  }

  public int getScore() {
    return this.score;
  }

  public void getHit(int ProjectileDamage) {
    if (this.life <= ProjectileDamage) {
      this.IsDestroyed = true;
      this.life = 0;
    } else {
      this.life -= ProjectileDamage;
    }
  }
}
