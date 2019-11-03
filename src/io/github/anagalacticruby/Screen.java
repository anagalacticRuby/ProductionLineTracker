package io.github.anagalacticruby;

/**
 * This class contains code regarding a product's screen details such as resolution and refresh
 * rate. It has a constructor to be called when creating products with screens.
 *
 * @author Nicholas Hansen
 */
public class Screen implements ScreenSpec {
  private final String resolution;
  private final int refreshrate;
  private final int responsetime;

  /**
   * The constructor that takes in a String resolution, int refresh rate, and another int response
   * time to create a screen object.
   *
   * @param resolution Takes in a String representing the resolution of the screen
   * @param refreshrate An int representing the refresh rate of the screen
   * @param responsetime Another int, this time representing the response time of the screen
   */
  Screen(String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }

  /**
   * This toString method prints the resolution, refresh rate, and response time for a product's
   * screen.
   *
   * @return Returns a String containing the created screen's resolution, refresh rate, and response
   *     time
   */
  public String toString() {
    return "Screen:"
        + "\nResolution: "
        + resolution
        + "\n"
        + "Refresh rate: "
        + refreshrate
        + "\n"
        + "Response time: "
        + responsetime;
  }

  /**
   * An accessor for a created screen's resolution.
   *
   * @return A String containing the screen's resolution
   */
  @Override
  public String getResolution() {
    return this.resolution;
  }

  /**
   * An accessor for a created screen's refresh rate.
   *
   * @return An integer value representing the screen's refresh rate
   */
  @Override
  public int getRefreshRate() {
    return this.refreshrate;
  }

  /**
   * An accessor for a created screen's response time.
   *
   * @return An integer value representing the screen's response time
   */
  @Override
  public int getResponseTime() {
    return this.responsetime;
  }
}
