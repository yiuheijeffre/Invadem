package invadem.object;

import processing.core.PApplet;

public abstract class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int[] velocity;
    protected int life;
    protected boolean IsDestroyed;
    protected int score;

    public Entity(int x, int y, int width, int height, int life) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.life = life;
      this.IsDestroyed = false;
      this.score = 0;
    }

    public Entity(int x, int y, int width, int height, int[] velocity, int life) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.life = life;
        this.IsDestroyed = false;
        this.score = 0;
    }

    public Entity(int x, int y, int width, int height, int[] velocity, int life, int score) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.life = life;
        this.IsDestroyed = false;
        this.score = score;
    }

    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLife();
    public abstract void getHit(int ProjectileDamage);
    public abstract void draw(PApplet app);
    public abstract boolean isDestroyed();
    public abstract int getScore();
}
