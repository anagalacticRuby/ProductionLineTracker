package sample;

public class AudioPlayer extends Product implements MultimediaControl {
  private String audioSpecification;
  private String mediaType;

  AudioPlayer(String name, String manufacturer, String audioSpecification) {
    super(name, manufacturer, "Audio");
    this.audioSpecification = audioSpecification;
    //this.mediaType = mediaType;
  }
    public String toString() {
      return super.toString() + "\n" + "Supported Audio Formats: " + audioSpecification  +
              "\n" + "Supported Playlist Formats: " + mediaType;

    }
  @Override
  public void play() {}

  @Override
  public void stop() {}

  @Override
  public void previous() {}

  @Override
  public void next() {}
}
