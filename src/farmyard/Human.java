package farmyard;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/** Human */
public class Human extends MovingObject {
  /** eggs in human's basket */
  private ArrayList<Egg> myBasket = new ArrayList<>();
  /** animal food dropped in farm */
  private ArrayList<AnimalFood> animalFoods;
  /** eggs produced in farm */
  private ArrayList<Egg> eggs;

  /**
   * Constructs a new Human.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param farmW the width of farm.
   * @param farmH the height of farm.
   * @param animalFoods the animal food dropped in farm.
   * @param eggs the eggs produced in farm.
   */
  public Human(
      int x, int y, int farmW, int farmH, ArrayList<AnimalFood> animalFoods, ArrayList<Egg> eggs) {
    super("H", Color.SANDYBROWN.darker(), x, y, farmW, farmH);
    this.animalFoods = animalFoods;
    this.eggs = eggs;
  }

  /** Drops at most 4 pieces of food all around. */
  private void dropFood() {
    // Drop food into valid place only
    if (x > 0) {
      if (y > 0) { // top left
        animalFoods.add(new AnimalFood(x - 1, y - 1, farmW, farmH));
      }
      if (y < farmH - 1) { // bottom left
        animalFoods.add(new AnimalFood(x - 1, y + 1, farmW, farmH));
      }
    }
    if (x < farmW - 1) {
      if (y > 0) { // top right
        animalFoods.add(new AnimalFood(x + 1, y - 1, farmW, farmH));
      }
      if (y < farmH - 1) { // bottom right
        animalFoods.add(new AnimalFood(x + 1, y + 1, farmW, farmH));
      }
    }
  }

  /**
   * Draws this human in farm.
   *
   * @param g the graphics context in which to draw this item.
   */
  public void draw(GraphicsContext g, int gridW, int gridH) {
    super.draw(g, gridW, gridH);
    // Draw the egg basket as well
    g.fillText(" (Eggs: " + myBasket.size() + ")", (x + 1) * gridW, y * gridH);
  }

  /** Moves this human. */
  public void move() {
    // Iterate all eggs found in farm
    // Pick up one at a time
    for (Egg egg : eggs) {
      if (egg.x == x && egg.y == y) {
        System.out.println("Human picks up an Egg: " + egg.x + " " + egg.y);
        eggs.remove(egg);
        myBasket.add(egg);
        break;
      }
    }
    if (eggs.size() > 0) { // Move toward next egg
      Egg egg = eggs.get(0);
      System.out.println("Human moves toward an Egg: " + egg.x + " " + egg.y);
      moveToward(egg.x, egg.y);
    } else { // Otherwise just move randomly
      moveRandomly();
    }
    // Figure out whether to drop food
    if (Math.random() < 0.05) {
      dropFood();
    }
  }
}
