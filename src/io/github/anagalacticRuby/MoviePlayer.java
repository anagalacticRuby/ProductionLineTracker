package io.github.anagalacticRuby;

/**
 * This class is where code about movie players is housed.
 *
 * <p>It is similar in structure to the Product class, except that it has screens!
 *
 * @author Nicholas Hansen
 */
public class MoviePlayer extends Product implements MultimediaControl {

  private Screen screen;
  private MonitorType monitorType;

  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  public String toString() {
    return super.toString() + "\n" + "Monitor type:" + monitorType + "\n" + screen;
  }

  @Override
  public void play() {
    System.out.println("Pushin play");
  }

  @Override
  public void stop() {
    System.out.println("Stoppin");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }
}
