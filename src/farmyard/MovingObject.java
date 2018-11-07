package farmyard;

import javafx.scene.paint.Color;

/** MovingObject */
public class MovingObject extends StillObject {
  /** The width of the farm. */
  int farmW;
  /** The height of the farm. */
  int farmH;
  /** The flag indicating whether moving right. */
  private boolean goingRight;
  /** The flag indicating whether moving down. */
  private boolean goingDown;
  /** The appearance when moving right. */
  private String appearanceRight;
  /** The appearance when moving left. */
  private String appearanceLeft;

  /**
   * Constructs a new MovingObject.
   *
   * @param appearance the appearance.
   * @param color the color.
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param farmW the width of farm.
   * @param farmH the height of farm.
   */
  public MovingObject(String appearance, Color color, int x, int y, int farmW, int farmH) {
    super(appearance, color, x, y);
    this.farmW = farmW;
    this.farmH = farmH;
    // set appearances for moving right & left
    this.appearanceRight = appearance;
    this.appearanceLeft = reverseAppearance(appearance);
    // randomly set move directions
    goingRight = Math.random() < 0.5;
    goingDown = Math.random() < 0.5;
  }

  /** Moves this object up. */
  void moveUp() {
    if (y > 0) {
      --y;
    }
  }

  /** Moves this object down. */
  void moveDown() {
    if (y < farmH - 1) {
      ++y;
    }
  }

  /** Moves this object left. */
  void moveLeft() {
    if (x > 0) {
      --x;
    }
    appearance = appearanceLeft;
  }

  /** Moves this object right. */
  void moveRight() {
    if (x < farmW - 1) {
      ++x;
    }
    appearance = appearanceRight;
  }

  /**
   * Moves toward a target.
   *
   * @param _x target x coordinate.
   * @param _y target y coordinate.
   */
  void moveToward(int _x, int _y) {
    if (x < _x) {
      moveRight();
    } else if (x > _x) {
      moveLeft();
    }
    if (y < _y) {
      moveDown();
    } else if (y > _y) {
      moveUp();
    }
  }

  /** Moves randomly. */
  void moveRandomly() {
    // 20% chance of changing moving direction
    if (Math.random() < 0.2) {
      goingRight = !goingRight;
    }
    if (Math.random() < 0.2) {
      goingDown = !goingDown;
    }
    if (goingRight) {
      moveRight();
    } else {
      moveLeft();
    }
    if (goingDown) {
      moveDown();
    } else {
      moveUp();
    }
  }

  /**
   * Flips the appearance.
   *
   * @param appearance the appearance when moving right.
   * @return the appearance when moving left.
   */
  private static String reverseAppearance(String appearance) {
    StringBuilder reverse = new StringBuilder("");
    for (int i = appearance.length() - 1; i >= 0; i--) {
      switch (appearance.charAt(i)) {
        case '\\':
          reverse.append('/');
          break;
        case '/':
          reverse.append('\\');
          break;
        case ')':
          reverse.append('(');
          break;
        case '(':
          reverse.append(')');
          break;
        case '>':
          reverse.append('<');
          break;
        case '<':
          reverse.append('>');
          break;
        case '}':
          reverse.append('{');
          break;
        case '{':
          reverse.append('}');
          break;
        case '[':
          reverse.append(']');
          break;
        case ']':
          reverse.append('[');
          break;
        default:
          reverse.append(appearance.charAt(i));
          break;
      }
    }

    return reverse.toString();
  }
}
