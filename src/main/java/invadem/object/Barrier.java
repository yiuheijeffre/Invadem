package invadem.object;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import invadem.action.Animation;

public class Barrier extends Entity implements Animation{
    private PImage[] img;
    private int SpriteIndex;
    public Barrier(PImage[] img,int x, int y, int width, int height, int life) {
      super(x, y, width, height, life);
      this.SpriteIndex = 0;
      this.img = img;
      this.score = 0;
    }

    // public void tick() {
    //
    // }
    //tick() is not necessary in barrier

    public void draw(PApplet app) {
      app.image(img[SpriteIndex], x, y, width, height);
    }

    public PImage getImg() {
      return this.img[this.SpriteIndex];
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

    public void getHit(int ProjectileDamage) {
      if (this.life <= ProjectileDamage) {
        this.IsDestroyed = true;
        this.life = 0;
      } else {
        this.life -= ProjectileDamage;
        this.changeSprite();
      }
      //When life = 1 and getHit() is called, it means it's destroyed

    }

    public boolean isDestroyed() {
      if (this.life <= 0) {
        return true;
      }
      return this.IsDestroyed;
    }

    public void changeSprite() {
      if(this.SpriteIndex < img.length - 1) {
        this.SpriteIndex += 1;
      }
    }

    public int getScore() {
      return this.score;
    }
    //Barrier doesn't carry any score

}
