package invadem.object;

import processing.core.PApplet;
import processing.core.PImage;
import invadem.action.Animation;

public class Invader extends Entity implements Animation {
  private int score;
  private PImage[] img;
  private int StepCount;
  private int FrameCount;
  private boolean ChangeDirection;
  private boolean MoveDownward;
  private int DownwardCount;
  private int SpriteIndex;
  private int ShotInterval;
  private boolean PoweredInvader;
  public Invader(PImage[] img, int x, int y, int width, int height, int[] velocity, int life, int score) {
    super(x, y, width, height, velocity, life, score);
    this.img = img;
    this.StepCount = 0;
    this.FrameCount = 0;
    this.ChangeDirection = false;
    this.MoveDownward = false;
    this.DownwardCount = 0;
    this.ShotInterval = 5;
    this.PoweredInvader = false;
    this.score = score;
  }
  public Invader(PImage[] img, int x, int y, int width, int height, int[] velocity, int life, int score, boolean PoweredInvader) {
    super(x, y, width, height, velocity, life, score);
    this.img = img;
    this.PoweredInvader = PoweredInvader;
    this.StepCount = 0;
    this.FrameCount = 0;
    this.ChangeDirection = false;
    this.MoveDownward = false;
    this.DownwardCount = 0;
    this.ShotInterval = 5;
    this.score = score;
  }

  public boolean changeDirection() {
    if (ChangeDirection) {
      ChangeDirection = false;
    } else {
      ChangeDirection = true;
    }
    return ChangeDirection;
  }

  public void move() {
    if (this.StepCount - 30 == 0 && this.DownwardCount < 9) {
      if (this.SpriteIndex == 0) {
        this.changeSprite();
      }
      this.velocity[0] = 0;
      if (this.DownwardCount == 8) {
        this.StepCount = 0;
        this.DownwardCount = 0;
        this.velocity[1] = 0;
        this.changeDirection();
      }
      if (this.FrameCount % 2 == 0) {
        this.velocity[1] = 1;
        this.DownwardCount += 1;
      } else {
        this.velocity[1] = 0;
      }
      // if invaders move 30 steps in one dircetion, their x velocity will be set to
      // 0 and move downwards by 8 pixels, then change the direction of x velocity.
    } else if (this.FrameCount % 2 == 0 && ChangeDirection) {
      if (this.SpriteIndex == 1) {
        this.changeSprite();
      }
      this.velocity[0] = -1;
      this.StepCount += 1;
    } else if (this.FrameCount % 2 == 0) {
      if(this.SpriteIndex == 1) {
        this.changeSprite();
      }
      this.velocity[0] = 1;
      this.StepCount += 1;
    } else {
      this.velocity[0] = 0;
    }
    //this.FrameCount % 2 == 0 only return true per two frames
  }

  public void tick() {
      this.move();
      this.x += velocity[0];
      this.y += velocity[1];
      this.FrameCount += 1;
  }

  public void draw(PApplet app) {
      app.image(img[SpriteIndex], x, y, width, height);
      tick();
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int[] getVelocity() {
    return this.velocity;
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

  public int getScore() {
    return this.score;
  }

  public int getShotInterval() {
    return this.ShotInterval;
  }

  public PImage getImg() {
    return this.img[SpriteIndex];
  }

  public void getHit(int ProjectileDamage) {
    if (this.life <= ProjectileDamage) {
      this.IsDestroyed = true;
      this.life = 0;
    } else {
      this.life -= ProjectileDamage;
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
    if(this.SpriteIndex < this.img.length - 1) {
      this.SpriteIndex += 1;
    } else {
      this.SpriteIndex = 0;
    }
  }
  
  public boolean readyToShoot() {
    if (this.FrameCount % (this.ShotInterval * 60) == 0 && this.FrameCount != 0) {
      return true;
    }
    return false;
  }

  public void setShotInterval(int interval) {
    if (interval >= 1) {
      this.ShotInterval = interval;
    } else {
      this.ShotInterval = 1;
    }
  }

  public boolean isPoweredInvader() {
    return this.PoweredInvader;
  }

}
