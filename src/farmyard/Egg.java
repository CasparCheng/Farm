package farmyard;

import javafx.scene.paint.Color;

/** Egg */
public class Egg extends StillObject {

  /**
   * Constructs a new Egg.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  Egg(int x, int y) {
    super("o", Color.ROSYBROWN, x, y);
  }
}
