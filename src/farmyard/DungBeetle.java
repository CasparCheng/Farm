package farmyard;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/** DungBeetle */
public class DungBeetle extends MovingObject {
  /** The manures around farm. */
  private ArrayList<AnimalManure> animalManures;

  /**
   * Constructs a new DungBeetle.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param animalManures the manures around form.
   */
  public DungBeetle(int x, int y, int farmW, int farmH, ArrayList<AnimalManure> animalManures) {
    super("x", Color.DARKGREEN, x, y, farmW, farmH);
    this.animalManures = animalManures;
  }

  /** Moves this DungBeetle. */
  public void move() {
    // Iterate all animal manures found in farm
    // Collect one at a time
    for (AnimalManure animalManure : animalManures) {
      if (animalManure.x == x && animalManure.y == y) {
        animalManures.remove(animalManure);
        break;
      }
    }
    if (animalManures.size() > 0) { // Move toward next manure
      AnimalManure animalManure = animalManures.get(0);
      moveToward(animalManure.x, animalManure.y);
    } else { // Otherwise just move randomly
      moveRandomly();
    }
  }
}
