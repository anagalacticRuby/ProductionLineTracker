package io.github.anagalacticRuby;

/** */
public class Screen implements ScreenSpec {
  private String resolution;
  private int refreshrate;
  private int responsetime;

  Screen(String resolution, int refreshrate, int responsetime){
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }
  public String toString() {
    return "Resolution: "
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
