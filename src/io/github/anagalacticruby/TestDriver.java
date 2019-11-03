package io.github.anagalacticruby;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is primarily a test class to confirm that the other classes are working properly.
 *
 * <p>See the ProductionTabController's initialize method for when this class's test method is
 * called.
 *
 * @author Nicholas Hansen
 */
class TestDriver {
  /**
   * This method can be called to input test values for an Audio Product and a Movie Product It has
   * a small random number generator so that the values change every now and then for variety.
   *
   * <p>It is called in the controller class currently, under the initialize().
   */
  public static void testMultimedia() {
    Random rand = new Random();
    int n = rand.nextInt(2);
    ArrayList<MultimediaControl> productList = new ArrayList<>();
    if (n < 1) {
      AudioPlayer newAudioProduct =
          new AudioPlayer(
              "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
      Screen newScreen = new Screen("720x480", 40, 22);
      MoviePlayer newMovieProduct =
          new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
      productList.add(newAudioProduct);
      productList.add(newMovieProduct);
    } else {
      AudioPlayer otherAudioProduct = new AudioPlayer("Walkman", "Sony", "Cassette", "Cassette");
      Screen otherScreen = new Screen("600x450", 20, 10);
      MoviePlayer otherMovieProduct =
          new MoviePlayer("Gameboy", "Nintendo", otherScreen, MonitorType.LED);
      productList.add(otherAudioProduct);
      productList.add(otherMovieProduct);
    }

    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }
}
