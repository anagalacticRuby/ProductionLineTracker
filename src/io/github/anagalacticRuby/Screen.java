package io.github.anagalacticRuby;

/**
 * This class contains code regarding a product's screen details such as resolution and refresh
 * rate. It has a constructor to be called when creating products with screens.
 *
 * @author Nicholas Hansen
 */
public class Screen implements ScreenSpec {
  private String resolution;
  private int refreshrate;
  private int responsetime;

  Screen(String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }

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

  @Override
  public String getResolution() {
    return null;
  }

  @Override
  public int getRefreshRate() {
    return 0;
  }

  @Override
  public int getResponseTime() {
    return 0;
  }
}
