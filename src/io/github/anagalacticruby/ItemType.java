package io.github.anagalacticruby;

public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public final String code;

  /**
   * A simple little mutator that sets the current code value to the string code value passed in.
   *
   * @param code The string code value of the enum constant
   */
  ItemType(String code) {
    this.code = code;
  }

  /**
   * This is a simple string method that grabs the enum constant and looks at the code value of it.
   *
   * @return A string containing the code value of the enum constant.
   */
  String getCode() {
    return code;
  }
}
