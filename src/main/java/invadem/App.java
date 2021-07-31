package invadem;

import invadem.object.Entity;
import invadem.object.Player;
import invadem.object.Invader;
import invadem.object.Projectile;
import invadem.object.Barrier;
import invadem.MainArray;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.core.PImage;
import processing.core.PFont;

public class App extends PApplet {
    PImage[] ArmouredInvaderSprite;
    PImage[] RegularInvaderSprite;
    PImage[] PowerInvaderSprite;
    PImage[] LeftBarrierSprite;
    PImage[] RightBarrierSprite;
    PImage[] SolidBarrierSprite;
    PImage[] TopBarrierSprite;
    PFont font;
    boolean SpacePressed;
    List<Invader> invaders;
    List<Barrier> barriers;
    Player player;
    MainArray mainArray;
    static List<Integer> keys = new ArrayList<Integer>();
    public App() {
      SpacePressed = false;
      barriers = new ArrayList<Barrier>();
      invaders = new ArrayList<Invader>();
      mainArray = new MainArray();
      //Set up your objects
    }

    public void setup() {
        font = createFont("PressStart2P-Regular.tff", 20);
        textFont(font);
        ArmouredInvaderSprite = new PImage[] {loadImage("src/main/resources/invader1_armoured.png"),
                                              loadImage("src/main/resources/invader2_armoured.png")};
        PowerInvaderSprite = new PImage[] {loadImage("src/main/resources/invader1_power.png"),
                                           loadImage("src/main/resources/invader2_power.png")};
        RegularInvaderSprite = new PImage[] {loadImage("src/main/resources/invader1.png"),
                                      loadImage("src/main/resources/invader2.png") };
        LeftBarrierSprite = new PImage[] {loadImage("src/main/resources/barrier_left1.png"),
                                          loadImage("src/main/resources/barrier_left2.png"),
                                          loadImage("src/main/resources/barrier_left3.png")};
        RightBarrierSprite = new PImage[] {loadImage("src/main/resources/barrier_right1.png"),
                                          loadImage("src/main/resources/barrier_right2.png"),
                                          loadImage("src/main/resources/barrier_right3.png")};
        SolidBarrierSprite = new PImage[] {loadImage("src/main/resources/barrier_solid1.png"),
                                          loadImage("src/main/resources/barrier_solid2.png"),
                                          loadImage("src/main/resources/barrier_solid3.png")};
        TopBarrierSprite = new PImage[] {loadImage("src/main/resources/barrier_top1.png"),
                                        loadImage("src/main/resources/barrier_top2.png"),
                                        loadImage("src/main/resources/barrier_top3.png")};
        frameRate(60);

        player = new Player(loadImage("src/main/resources/tank1.png"), 309, 440, 22, 14, new int[] {0,0}, 3);
        for (int y = 0; y < 4; y++) {
          for (int x = 0; x < 10; x++) {
            if(y < 2) {
              invaders.add(new Invader(RegularInvaderSprite, 180+x*26, 20+y*35, 16, 16, new int[] {0,0}, 1, 100));
            } else if(y < 3) {
              invaders.add(new Invader(PowerInvaderSprite, 180 + x*26, 20+y*35, 16, 16, new int[] {0,0}, 1, 250, true));
            } else if(y < 4) {
              invaders.add(new Invader(ArmouredInvaderSprite, 180 + x*26, 20+y*35, 16, 16, new int[] {0,0}, 3, 250));
            }

          }
        }
        for (int x = 0; x < 3; x++) {
          barriers.add(new Barrier(SolidBarrierSprite, 200 + x*108, 420, 8, 8, 3));
          barriers.add(new Barrier(SolidBarrierSprite, 200 + x*108, 412, 8, 8, 3));
          barriers.add(new Barrier(SolidBarrierSprite, 216 + x*108, 420, 8, 8, 3));
          barriers.add(new Barrier(SolidBarrierSprite, 216 + x*108, 412, 8, 8, 3));

          barriers.add(new Barrier(LeftBarrierSprite, 200 + x*108, 404, 8, 8, 3));
          barriers.add(new Barrier(RightBarrierSprite, 216 + x*108, 404, 8, 8, 3));
          barriers.add(new Barrier(TopBarrierSprite, 208 + x*108, 404, 8, 8, 3));

        }
        //Center = 320
        //Left boundary: x = 180 , Right boundary: x = 460
        //Boundary for invader: x = 185, 455, Spacing = (455-185) / 3 = 90
        //Left barrier starts at x = 200, right barrier at x = 440 (Include sprite)
        mainArray.setPlayer(player);
        mainArray.setInvaders(invaders);
        mainArray.setBarriers(barriers);
        mainArray.setAllEntities();
        mainArray.setFinishLine();

    }


    @Override
    public void keyPressed() {
      if (!(keys.contains(keyCode))) {
        keys.add(keyCode);

        if (keyCode == 32) {
          SpacePressed = true;
        }

      }
    }

    @Override
    public void keyReleased() {
      if (keys.contains(keyCode)) {
        keys.remove((Integer) keyCode);

        if (keyCode == 32) {
          SpacePressed = false;
        }

      }
    }
    //Key list allow the program to read multiple key input

    public List<Integer> getKeys() {
      return keys;
    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        background(0);
        mainArray.draw(this, keys, SpacePressed);

    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");

    }

}
