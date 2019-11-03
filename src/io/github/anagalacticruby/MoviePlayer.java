package io.github.anagalacticruby;

/**
 * This class is where code about movie players is housed.
 *
 * <p>It is similar in structure to the Product class, except that it has screens!
 *
 * @author Nicholas Hansen
 */
public class MoviePlayer extends Product implements MultimediaControl {

  private final Screen screen;
  private final MonitorType monitorType;

  /**
   * This is a constructor for MoviePlayer products.
   *
   * @param name The name of the movie player product
   * @param manufacturer The manufacturer of the movie player product
   * @param screen The screen details of the movie player product
   * @param monitorType The monitor type of the movie player product
   */
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * A toString method to print the details of a created MoviePlayer product when called.
   *
   * <p>The super it calls is that of the Product classes'.
   *
   * @return A string containing the MoviePlayer's product details as well as the monitor and screen
   *     details.
   */
  public String toString() {
    return super.toString() + "\n" + "Monitor type:" + monitorType + "\n" + screen;
  }

  /** Playing the movie. */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /** Stopping the movie. */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /** Going to the previous movie on the device. */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /** Goes to the next movie on the device. */
  @Override
  public void next() {
    System.out.println("Next");
  }
}
