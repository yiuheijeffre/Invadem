package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.PImage;
import processing.core.PApplet;
import invadem.object.Invader;
import invadem.object.Player;
import invadem.object.Projectile;
import invadem.object.Barrier;
import invadem.object.Entity;
import invadem.MainArray;
import java.util.List;
import java.util.ArrayList;

public class MainArrayTest {

  @Test
  public void testScoreGetScore() {
    Score score = new Score();
    assertEquals(0, score.getScore());
  }

  @Test
  public void testScoreGetHightScore() {
    Score score = new Score();
    assertEquals(10000, score.getHighScore());
  }

  @Test
  public void testScoreAddScore() {
    Score score = new Score();
    score.addScore(100);
    assertEquals(100, score.getScore());
    score.addScore(100);
    assertEquals(200, score.getScore());
  }

  @Test
  public void testScoreSetScore() {
    Score score = new Score();
    score.setScore(500);
    assertEquals(500, score.getScore());
  }

  @Test
  public void testScoreUpdateScore() {
    Score score = new Score();
    score.setScore(100);
    score.updateScore();
    assertEquals(10000, score.getHighScore());
    score.setScore(20000);
    assertEquals(10000, score.getHighScore());
    score.updateScore();
    assertEquals(20000, score.getHighScore());
  }

  @Test
  public void testMainArrayConstruction() {
    MainArray TestMainArray = new MainArray();
    assertNotNull(TestMainArray);
  }

  @Test
  public void testMainArrayGetCurrentLevel() {
    MainArray TestMainArray = new MainArray();
    assertEquals(0, TestMainArray.getCurrentLevel());
  }

  @Test
  public void testMainArraySetPlayer() {
    MainArray TestMainArray = new MainArray();
    Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 0);
    TestMainArray.setPlayer(player);
    assertEquals(player, TestMainArray.getPlayer());
    TestMainArray.setPlayer(null);
    assertEquals(null, TestMainArray.getPlayer());
  }

  @Test
  public void testMainArraySetInvaders() {
    MainArray TestMainArray = new MainArray();
    List<Invader> invaders = new ArrayList<Invader>();
    Invader invader = new Invader(null, 0, 0, 16, 16, new int[] {0, 0}, 3, 0);
    Invader invader2 = new Invader(null, 100, 0, 16, 16, new int[] {0, 0}, 3, 0);
    Invader invader3 = new Invader(null, 200, 0, 16, 16, new int[] {0, 0}, 3, 0);
    invaders.add(invader);
    invaders.add(invader2);
    invaders.add(invader3);
    TestMainArray.setInvaders(invaders);
    assertEquals(invaders, TestMainArray.getInvaders());
    TestMainArray.setInvaders(null);
    assertEquals(null, TestMainArray.getInvaders());
  }

  @Test
  public void testMainArraySetBarriers() {
    MainArray TestMainArray = new MainArray();
    List<Barrier> barriers = new ArrayList<Barrier>();
    Barrier a = new Barrier(null, 0, 0, 0, 0, 3);
    Barrier b = new Barrier(null, 0, 0, 0, 0, 3);
    Barrier c = new Barrier(null, 0, 0, 0, 0, 3);
    barriers.add(a);
    barriers.add(b);
    barriers.add(c);
    TestMainArray.setBarriers(barriers);
    assertEquals(barriers, TestMainArray.getBarriers());
    TestMainArray.setBarriers(null);
    assertEquals(null, TestMainArray.getBarriers());
  }

  @Test
  public void testMainArrayGetProjectiles() {
    MainArray TestMainArray = new MainArray();
    assertNotNull(TestMainArray.getProjectiles());
  }

  @Test
  public void testMainArrayGetFinishLine() {
    MainArray TestMainArray = new MainArray();
    assertEquals(480, TestMainArray.getFinishLine());
  }

  @Test
  public void testMainArraySetFinishLine() {
    MainArray TestMainArray = new MainArray();
    TestMainArray.setFinishLine();
    assertEquals(480, TestMainArray.getFinishLine());
    //no player and barriers were set, so finish line will be at the bottom of the screen
    Player player = new Player(null, 0, 460, 0, 0, new int[] {0,0}, 0);
    TestMainArray.setPlayer(player);
    TestMainArray.setFinishLine();
    assertEquals(450, TestMainArray.getFinishLine());
    //No barriers were set, so the finish line is set to be player's y-level - player's height - 10
    List<Barrier> barriers = new ArrayList<Barrier>();
    Barrier a = new Barrier(null, 0, 400, 0, 10, 3);
    Barrier b = new Barrier(null, 0, 400, 0, 0, 3);
    Barrier c = new Barrier(null, 0, 400, 0, 10, 3);
    barriers.add(a);
    barriers.add(b);
    barriers.add(c);
    TestMainArray.setBarriers(barriers);
    TestMainArray.setFinishLine();
    assertEquals(380, TestMainArray.getFinishLine());
    //Barriers were set, so the finish line will set to be barrier's y-level -  barrier's height - 10
  }

  @Test
  public void testMainArraySetAllEntities() {
    MainArray TestMainArray = new MainArray();
    TestMainArray.setAllEntities();
    assertNotNull(TestMainArray.getAllEntities());
  }

  @Test
  public void testMainArrayReset() {
    MainArray TestMainArray = new MainArray();
    Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 0);
    List<Barrier> barriers = new ArrayList<Barrier>();
    List<Barrier> BarriersClone = new ArrayList<Barrier>();
    Barrier a = new Barrier(null, 0, 0, 0, 0, 3);
    Barrier b = new Barrier(null, 0, 0, 0, 0, 3);
    List<Invader> invaders = new ArrayList<Invader>();
    Invader invader = new Invader(null, 0, 0, 16, 16, new int[] {0, 0}, 1, 0);
    invaders.add(invader);
    barriers.add(a);
    barriers.add(b);
    BarriersClone.addAll(barriers);
    TestMainArray.setPlayer(player);
    TestMainArray.setInvaders(invaders);
    TestMainArray.setBarriers(BarriersClone);
    TestMainArray.setAllEntities();
    assertEquals(barriers, TestMainArray.getBarriers());
    TestMainArray.reset();
    assertNotEquals(barriers, TestMainArray.getBarriers());
    barriers.clear();
    assertEquals(barriers, TestMainArray.getBarriers());
    //Reset will clear all elements in barriers, player, invaders, projectiles and AllEntities
  }

  @Test
  public void testMainArrayLevelEnd() {
    MainArray TestMainArray = new MainArray();
    TestMainArray.setInvaders(null);
    assertEquals(true, TestMainArray.levelEnd());

    List<Invader> invaders = new ArrayList<Invader>();
    Invader invader = new Invader(null, 0, 0, 16, 16, new int[] {0, 0}, 1, 0);
    Invader invader2 = new Invader(null, 100, 0, 16, 16, new int[] {0, 0}, 1, 0);
    Invader invader3 = new Invader(null, 200, 0, 16, 16, new int[] {0, 0}, 1, 0);
    invaders.add(invader);
    invaders.add(invader2);
    invaders.add(invader3);
    TestMainArray.setInvaders(invaders);
    assertEquals(true, TestMainArray.levelEnd());
    //No player is set, so the level is ended even if invaders is set

    Player player = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 3);
    TestMainArray.setPlayer(player);
    assertEquals(false, TestMainArray.levelEnd());
    //The game is running now as level end doesn't return true

    Player DestroyedPlayer = new Player(null, 0, 0, 0, 0, new int[] {0,0}, 0);
    TestMainArray.setPlayer(DestroyedPlayer);
    assertEquals(true, TestMainArray.levelEnd());
    //player have 0 hit point, so player is considered destroyed, and level is ended

    Invader InvaderAtFinishLine = new Invader(null, 100, 480, 16, 16, new int[] {0, 0}, 1, 0);
    TestMainArray.setPlayer(player);
    invaders.add(InvaderAtFinishLine);
    TestMainArray.setInvaders(invaders);
    assertEquals(true, TestMainArray.levelEnd());
    //Default finish line is set at 480 (Bottom of the screen)

    invaders.clear();
    TestMainArray.setInvaders(invaders);
    assertEquals(true, TestMainArray.levelEnd());
    //All invaders in the invader list have been removed/killed, so level end
  }

  @Test
  public void testMainArrayGenerateEntityShooter() {
    MainArray TestMainArray = new MainArray();
    List<Invader> invaders = new ArrayList<Invader>();
    Invader invader = new Invader(null, 0, 0, 16, 16, new int[] {0, 0}, 1, 0);
    Invader invader2 = new Invader(null, 100, 0, 16, 16, new int[] {0, 0}, 1, 0);
    Invader invader3 = new Invader(null, 200, 0, 16, 16, new int[] {0, 0}, 1, 0);
    invaders.add(invader);
    invaders.add(invader2);
    invaders.add(invader3);
    TestMainArray.setInvaders(invaders);
    int RandomInvaderIndex = TestMainArray.generateEntityShooter();
    assert(RandomInvaderIndex >= 0 && RandomInvaderIndex < invaders.size());
    //Index >= 0 and Index < size of invader list
  }

}
