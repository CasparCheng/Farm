package farm_simulation;

import farmyard.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/** Our take on the "classical" game Farm Ville. */
public class Main extends Application {

  /** The width of a farm grid. */
  private static final int gridW = 10;
  /** The height of a farm grid. */
  private static final int gridH = 6;
  /** The width of the canvas. */
  private static final int canvasW = 640;
  /** The height of the canvas. */
  private static final int canvasH = 480;
  /** The width of the farm. */
  private static final int farmW = canvasW / gridW;
  /** The height of the farm. */
  private static final int farmH = canvasH / gridH;
  /** The humans living in the farm. */
  private ArrayList<Human> humans = new ArrayList<>();
  /** The chickens fed in the farm. */
  private ArrayList<Chicken> chickens = new ArrayList<>();
  /** The pigs fed in the farm. */
  private ArrayList<Pig> pigs = new ArrayList<>();
  /** The eggs produced in the farm. */
  private ArrayList<Egg> eggs = new ArrayList<>();
  /** The animal food dropped in the farm. */
  private ArrayList<AnimalFood> animalFoods = new ArrayList<>();
  /** The animal manure produced in the farm. */
  private ArrayList<AnimalManure> animalManures = new ArrayList<>();
  /** The dung beetles living in the farm. */
  private ArrayList<DungBeetle> dungBeetles = new ArrayList<>();

  /**
   * Main entry.
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Starts simulation.
   *
   * @param primaryStage top level JavaFX container.
   */
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("FarmVille");

    // Prepare the canvas and the graphics context
    Group root = new Group();
    Scene theScene = new Scene(root);
    primaryStage.setScene(theScene);
    Canvas canvas = new Canvas(canvasW, canvasH);
    root.getChildren().add(canvas);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    // Add items to the farm
    dungBeetles.add(new DungBeetle(53, 62, farmW, farmH, animalManures));
    dungBeetles.add(new DungBeetle(12, 27, farmW, farmH, animalManures));
    dungBeetles.add(new DungBeetle(4, 17, farmW, farmH, animalManures));
    chickens.add(new Chicken(0, 35, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(12, 30, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(4, 17, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(58, 15, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(18, 43, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(35, 16, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(35, 16, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(22, 46, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(18, 23, farmW, farmH, animalFoods, animalManures, eggs));
    chickens.add(new Chicken(47, 6, farmW, farmH, animalFoods, animalManures, eggs));
    pigs.add(new Pig(20, 10, farmW, farmH, animalFoods, animalManures));
    pigs.add(new Pig(40, 60, farmW, farmH, animalFoods, animalManures));
    humans.add(new Human(30, 30, farmW, farmH, animalFoods, eggs));

    // Draw everything for the 1st time
    drawShapes(gc);

    // Define animation
    Timeline gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);

    // Define keyframe along time-line
    KeyFrame kf =
        new KeyFrame(
            Duration.seconds(0.5),
            e -> {
              for (AnimalFood animalFood : animalFoods) {
                animalFood.blown(Wind.windBlowingUp(), Wind.windBlowingLeft());
              }
              for (Chicken chicken : chickens) {
                chicken.move();
              }
              for (Pig pig : pigs) {
                pig.move();
              }
              for (DungBeetle dungBeetle : dungBeetles) {
                dungBeetle.move();
              }
              for (Human human : humans) {
                human.move();
              }
              drawShapes(gc);
            });

    // Add keyframe to animation
    gameLoop.getKeyFrames().add(kf);
    // Play animation
    gameLoop.play();
    // Show stage
    primaryStage.show();
  }

  /**
   * Clears the canvas then draw all items in the farm.
   *
   * @param gc GraphicsContext object.
   */
  private void drawShapes(GraphicsContext gc) {
    // Clear the canvas first
    gc.clearRect(0, 0, canvasW, canvasH);
    // Draw every items in the farm
    for (AnimalManure animalManure : animalManures) {
      animalManure.draw(gc, gridW, gridH);
    }
    for (DungBeetle dungBeetle : dungBeetles) {
      dungBeetle.draw(gc, gridW, gridH);
    }
    for (AnimalFood animalFood : animalFoods) {
      animalFood.draw(gc, gridW, gridH);
    }
    for (Egg egg : eggs) {
      egg.draw(gc, gridW, gridH);
    }
    for (Chicken chicken : chickens) {
      chicken.draw(gc, gridW, gridH);
    }
    for (Pig pig : pigs) {
      pig.draw(gc, gridW, gridH);
    }
    for (Human human : humans) {
      human.draw(gc, gridW, gridH);
    }
  }
}
