package invadem.action;

import java.util.List;

public interface Collision<O> {
  public O isCollidedWith(List<O> objects);
}
