package farmyard;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** Still Object */
public class StillObject {
  /** The appearance. */
  String appearance;
  /** The colour. */
  private Color colour;
  /** The x coordinate. */
  int x;
  /** The y coordinate. */
  int y;

  /**
   * Constructs a new StillObject.
   *
   * @param appearance the appearance.
   * @param color the color.
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  public StillObject(String appearance, Color color, int x, int y) {
    this.colour = color;
    this.appearance = appearance;
    this.x = x;
    this.y = y;
  }

  /**
   * Draws this object.
   *
   * @param g the graphics context in which to draw this creature.
   * @param gridW the width of farm grid.
   * @param gridH the height of farm grid.
   */
  public void draw(GraphicsContext g, int gridW, int gridH) {
    g.setFill(colour);
    g.fillText(appearance, x * gridW, y * gridH);
  }
}
