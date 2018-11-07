package farmyard;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/** Chicken */
public class Chicken extends MovingObject {
  /** The animal foods around farm. */
  private ArrayList<AnimalFood> animalFoods;
  /** The manures around farm. */
  private ArrayList<AnimalManure> animalManure;
  /** The eggs around farm. */
  private ArrayList<Egg> eggs;
  /** The counter of food been eaten. */
  private int eaten = 0;

  /**
   * Constructs a new Chicken.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param animalFoods the animal foods around farm.
   * @param animalManure the manures around form.
   * @param eggs the eggs around farm.
   */
  public Chicken(
      int x,
      int y,
      int farmW,
      int farmH,
      ArrayList<AnimalFood> animalFoods,
      ArrayList<AnimalManure> animalManure,
      ArrayList<Egg> eggs) {
    super("/'/>", Color.RED, x, y, farmW, farmH);
    this.animalFoods = animalFoods;
    this.animalManure = animalManure;
    this.eggs = eggs;
  }

  /** Moves this chicken. */
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
    // Consider lays an egg or/and produces a manure only after eaten some food
    if (eaten > 0) {
      if (Math.random() < 0.1) {
        layEgg();
      }
      if (Math.random() < 0.2) {
        digest();
      }
    }
  }

  /** Lays an egg. */
  private void layEgg() {
    System.out.println("Chicken produces an Egg: " + x + " " + y);
    eggs.add(new Egg(x, y));
  }

  /** Produces a manure. */
  private void digest() {
    System.out.println("Chicken produces a Manure: " + x + " " + y);
    animalManure.add(new AnimalManure(x, y));
    --eaten;
  }
}
