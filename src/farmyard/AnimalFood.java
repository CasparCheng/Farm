package farmyard;

import javafx.scene.paint.Color;

/** Animal Food */
public class AnimalFood extends MovingObject {

  /**
   * Constructs an new AnimalFood.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param farmW the width of farm.
   * @param farmH the height of farm.
   */
  public AnimalFood(int x, int y, int farmW, int farmH) {
    super("%", Color.GRAY.darker().darker().darker(), x, y, farmW, farmH);
  }

  /**
   * Blown by wind.
   *
   * @param windUp 1 if wind blows upward, 0 if wind doesn't blow vertically, -1 if wind blows
   *     downward.
   * @param windLeft 1 if wind blows left, 0 if wind doesn't blow horizontally, -1 if wind blows
   *     right.
   */
  public void blown(int windUp, int windLeft) {
    // Food may float up or down
    if (windUp == 1) {
      moveUp();
    } else if (windUp == -1) {
      moveDown();
    }
    // Food may float left or right
    if (windLeft == 1) {
      moveLeft();
    } else if (windLeft == -1) {
      moveRight();
    }
  }
}
