package farmyard;

/** Wind */
public class Wind {

  /**
   * The last vertical direction of wind.
   *
   * <p>1 if wind blows upward, 0 if wind doesn't blow vertically, -1 if wind blows downward.
   */
  private static int lastUp = 0;

  /**
   * The last horizontal direction of wind.
   *
   * <p>1 if wind blows left, 0 if wind doesn't blow horizontally, -1 if wind blows right.
   */
  private static int lastLeft = 0;

  /**
   * Get the vertical direction of wind.
   *
   * <p>Wind will Keep blowing the same direction 30% the time. Turn around 10% of the time.
   * Otherwise no wind.
   *
   * @return 1 if wind blows upward, 0 if wind doesn't blow vertically, -1 if wind blows downward.
   */
  public static int windBlowingUp() {
    if (Math.random() > 0.3) {
      if (Math.random() < 0.1) {
        lastUp = -lastUp;
      }
    }
    return lastUp;
  }

  /**
   * Get the horizontal direction of wind.
   *
   * <p>Wind will Keep blowing the same direction 30% the time. Turn around 10% of the time.
   * Otherwise no wind.
   *
   * @return 1 if wind blows left, 0 if wind doesn't blow horizontally, -1 if wind blows right.
   */
  public static int windBlowingLeft() {
    if (Math.random() > 0.3) {
      if (Math.random() < 0.1) {
        lastLeft = -lastLeft;
      }
    }
    return lastLeft;
  }
}
