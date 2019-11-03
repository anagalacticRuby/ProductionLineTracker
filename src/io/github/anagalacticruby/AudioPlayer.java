package io.github.anagalacticruby;

/**
 * This class deals with audio products that do not have screens.
 *
 * <p>With the help of a constructor it can obtain the supported audio formats and playlist formats,
 * and then display them with the use of the toString method.
 *
 * @author Nicholas Hansen
 */
public class AudioPlayer extends Product implements MultimediaControl {
  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;

  /**
   * A constructor for AudioPlayer Products.
   *
   * @param name The name of the Audio player
   * @param manufacturer The manufacturer of the Audio player
   * @param supportedAudioFormats The supported audio formats of the audio player
   * @param supportedPlaylistFormats The supported playlist formats of the audio player
   */
  AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * This method overrides the Object class's toString method.
   *
   * <p>This method returns the Product information(name,manufacturer,type) about the Audio Player,
   * and then adds on the supported audio and playlist formats.
   *
   * @return Returns a string of information about Audio Player Products
   */
  public String toString() {
    return super.toString()
        + "\n"
        + "Supported Audio Formats: "
        + supportedAudioFormats
        + "\n"
        + "Supported Playlist Formats: "
        + supportedPlaylistFormats;
  }

  /** Meant to represent the audio player 'playing' audio. */
  @Override
  public void play() {
    System.out.println("Just push play!");
  }

  /** Meant to represent the audio player 'stopping' its audio. */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /** Going to the previous song of the audio player. */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /** Going to the next song of the audio player. */
  @Override
  public void next() {
    System.out.println("Next");
  }
}
