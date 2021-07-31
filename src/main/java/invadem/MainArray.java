package invadem;

import invadem.object.Entity;
import invadem.object.Player;
import invadem.object.Invader;
import invadem.object.Projectile;
import invadem.object.Barrier;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import java.lang.Math;

class Score {
  private int score;
  private int HighScore;
  public Score() {
    this.score = 0;
    this.HighScore = 10000;
  }

  public void updateScore() {
    if (this.score > this.HighScore) {
      this.HighScore = this.score;
    }
  }

  public int getScore() {
    return this.score;
  }

  public int getHighScore() {
    return this.HighScore;
  }

  public void addScore(int score) {
    this.score += score;
  }

  public void setScore(int score) {
    this.score = score;
  }

}

public class MainArray {
  private Score score;
  private int timer;
  private List<Barrier> barriers;
  private List<Invader> invaders;
  private List<Projectile> projectiles;
  private Player player;
  private List<Entity> EntitiesToBeRemoved;
  private List<Entity> AllEntities;
  private int EntityToShoot;
  private boolean InvaderShotOnce;
  private boolean LevelWin;
  private boolean GameEnd;
  private int CurrentLevel;
  static private boolean FiredOnce;
  static private int FinishLine;
  private boolean EndLevel;

  public MainArray() {
    this.CurrentLevel = 0;
    this.projectiles = new ArrayList<Projectile>();
    this.InvaderShotOnce = false;
    this.FiredOnce = false;
    this.FinishLine = 480;
    //bottom of the screen
    this.EntitiesToBeRemoved = new ArrayList<Entity>();
    this.LevelWin = false;
    this.EndLevel = false;
    this.timer = 0;
    this.score = new Score();
  }

  public void draw(PApplet app, List<Integer> keys, boolean SpacePressed) {
    if(!this.levelEnd()) {
      app.text("High Score:" + Integer.toString(score.getHighScore()), 5, 20);
      app.text("Score:" + Integer.toString(score.getScore()), 500, 20);
      app.text("Life:" + Integer.toString(player.getLife()), 40, 420);
      app.text("Current level:" + Integer.toString(this.getCurrentLevel() + 1), 250, 20);
      player.keyUpdate(keys);

      if (player.isShooting(keys) && !FiredOnce) {
        this.projectiles.add(new Projectile(app.loadImage("src/main/resources/projectile.png"),
                                player, player.getX()+player.getWidth()/2,
                                player.getY()+player.getHeight()/2, 1, 3, new int[] {0,-1}, 1, 1));
                                        //return x and y center of player model
        FiredOnce = true;
      } else if(!SpacePressed) {
        FiredOnce = false;
      }

      //Set true if player have already fired once while holding the space.
      //Releasing space reset it.

      this.updateEntities();

      for (Entity entity : this.AllEntities) {
        entity.draw(app);
        if(entity instanceof Invader && entity.isDestroyed()) {
          score.addScore(entity.getScore());
          this.invaders.remove(entity);
        }
        if(entity.isDestroyed()) {
          this.EntitiesToBeRemoved.add(entity);
        }
      }

      for (Invader invader : invaders) {
        invader.setShotInterval(5 - this.getCurrentLevel());
        if (invader.readyToShoot() && !this.InvaderShotOnce) {
          EntityToShoot = this.generateEntityShooter();

          if (invaders.get(EntityToShoot).isPoweredInvader()) {
            this.projectiles.add(new Projectile(app.loadImage("src/main/resources/projectile_lg.png"),
                  invaders.get(EntityToShoot),
                  invaders.get(EntityToShoot).getX() + invaders.get(EntityToShoot).getWidth()/2,
                  invaders.get(EntityToShoot).getY() + invaders.get(EntityToShoot).getHeight()/2,
                  2, 5, new int[] {0,1}, 1, 3));
          } else {
              this.projectiles.add(new Projectile(app.loadImage("src/main/resources/projectile.png"),
                    invaders.get(EntityToShoot),
                    invaders.get(EntityToShoot).getX() + invaders.get(EntityToShoot).getWidth()/2,
                    invaders.get(EntityToShoot).getY() + invaders.get(EntityToShoot).getHeight()/2,
                    1, 3, new int[] {0,1}, 1, 1));
          }
          this.InvaderShotOnce = true;
        }
      }

      this.InvaderShotOnce = false;

      for (Projectile projectile : projectiles) {
        projectile.isCollidedWith(this.AllEntities);
      }

    } else if(this.LevelWin){
      if (this.timer - 120 == 0) {
        this.CurrentLevel += 1;
        this.reset();
        this.timer = 0;
        this.LevelWin = false;
        app.setup();
      } else {
        app.image(app.loadImage("src/main/resources/nextlevel.png"), 320 - 122/2, 224, 122, 16);
        this.timer += 1;
      }
    } else {
      if (this.timer - 120 == 0){
        this.CurrentLevel = 0;
        this.reset();
        app.setup();
        this.timer = 0;
      } else {
        score.updateScore();
        score.setScore(0);
        app.image(app.loadImage("src/main/resources/gameover.png"), 320 - 112/2, 224, 112, 16);
        this.timer += 1;
      }

    }
  }

  public int generateEntityShooter() {
    return (int) Math.round(Math.random() * (this.invaders.size()-1));
  }

  public void updateEntities() {
    for(Projectile projectile : this.projectiles) {
      if(!projectile.isDestroyed() && !this.AllEntities.contains(projectile)) {
        this.AllEntities.add(projectile);
      }
    }

    this.AllEntities.removeAll(this.EntitiesToBeRemoved);
    this.EntitiesToBeRemoved.clear();
  }

  public boolean levelEnd() {
    if (this.invaders == null || this.player == null) {
      return true;
    }
    if (this.invaders.size() == 0) {
      this.LevelWin = true;
      return true;
    }
    if (this.player.isDestroyed()) {
      return true;
    }
    for(Invader invader : invaders ) {
      if(invader.getY() == FinishLine) {
        return true;
      }
    }
    return false;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setInvaders(List<Invader> invaders) {
    this.invaders = invaders;
    //Deep copy, so that it isn't a reference to the orginal invaders
  }

  public void setBarriers(List<Barrier> barriers) {
    this.barriers = barriers;
    //Deep copy, so that it isn't a reference to the original barriers
  }

  public void setAllEntities() {
    this.AllEntities = new ArrayList<Entity>();
    if(this.player != null) {
      this.AllEntities.add(this.player);
    }
    if(this.barriers != null) {
      this.AllEntities.addAll(this.barriers);
    }
    if(this.invaders != null) {
      this.AllEntities.addAll(this.invaders);
    }
    if(this.projectiles != null) {
      this.AllEntities.addAll(this.projectiles);
    }
  }

  public void setFinishLine() {
    if(this.player != null) {
      this.FinishLine = player.getY() - player.getHeight() - 10;
      if (this.barriers != null) {
        for (Barrier barrier : this.barriers) {
          if (this.FinishLine > barrier.getY() - barrier.getHeight() - 10) {
            this.FinishLine = barrier.getY() - barrier.getHeight() - 10;
          }
        }
      }
    } else {
      this.FinishLine = 480;
    }
  }

  public int getCurrentLevel() {
    return this.CurrentLevel;
  }

  public void reset() {
    if(this.AllEntities != null) {
      this.AllEntities.clear();
    }
    if(this.invaders != null) {
      this.invaders.clear();
    }
    if(this.projectiles != null) {
      this.projectiles.clear();
    }
    if(this.barriers != null) {
      this.barriers.clear();
    }
    if(this.player != null) {
      this.player = null;
    }
  }

  public Player getPlayer() {
    return this.player;
  }

  public List<Invader> getInvaders() {
    return this.invaders;
  }

  public List<Barrier> getBarriers() {
    return this.barriers;
  }

  public List<Projectile> getProjectiles() {
    return this.projectiles;
  }

  public List<Entity> getAllEntities() {
    return this.AllEntities;
  }

  public int getFinishLine() {
    return this.FinishLine;
  }

}
