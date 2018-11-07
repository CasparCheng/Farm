package farmyard;

import javafx.scene.paint.Color;

/** Animal Manure */
public class AnimalManure extends StillObject {

  /**
   * Constructs a new AnimalManure.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  AnimalManure(int x, int y) {
    super(".", Color.BLACK.darker().darker().darker(), x, y);
  }
}
