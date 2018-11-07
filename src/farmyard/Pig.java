package farmyard;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/** Pig */
public class Pig extends MovingObject {
  /** The animal foods around farm. */
  private ArrayList<AnimalFood> animalFoods;
  /** The manures around farm. */
  private ArrayList<AnimalManure> animalManure;
  /** The counter of food been eaten. */
  private int eaten = 0;

  /**
   * Constructs a new Pig.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param animalFoods the animal foods around farm.
   * @param animalManure the manures around form.
   */
  public Pig(
      int x,
      int y,
      int farmW,
      int farmH,
      ArrayList<AnimalFood> animalFoods,
      ArrayList<AnimalManure> animalManure) {
    super(":(8)", Color.PINK.darker().darker().darker(), x, y, farmW, farmH);
    this.animalFoods = animalFoods;
    this.animalManure = animalManure;
  }

  /** Moves this pig. */
  public void move() {
    // Iterate all animal foods found in farm
    // Eat one at a time
    for (AnimalFood animalFood : animalFoods) {
      if (animalFood.x == x && animalFood.y == y) {
        ++eaten;
        animalFoods.remove(animalFood);
        break;
      }
    }
    if (animalFoods.size() > 0) { // Move toward next food
      AnimalFood animalFood = animalFoods.get(0);
      moveToward(animalFood.x, animalFood.y);
    } else { // Otherwise just move randomly
      moveRandomly();
    }
    // Consider produces a manure only after eaten some food
    if (eaten > 0) {
      if (Math.random() < 0.2) {
        digest();
      }
    }
  }

  /** Produces a manure. */
  private void digest() {
    System.out.println("Pig produces a Manure: " + x + " " + y);
    animalManure.add(new AnimalManure(x, y));
    --eaten;
  }
}
