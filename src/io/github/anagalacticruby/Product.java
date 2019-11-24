package io.github.anagalacticruby;

/**
 * This is the class that is the powerhouse of the program.
 *
 * <p>A product's name, manufacturer and type are put into a constructor and then printed when the
 * toString is called.
 *
 * @author Nicholas Hansen
 */
public abstract class Product implements Item {
  private int id;
  private final ItemType type;
  private String manufacturer;
  private String name;

  /**
   * A constructor for products. This is integral to the program's functionality.
   *
   * <p>It is where most of the other classes draw inspiration from or rely upon in order to do
   * anything of value.
   *
   * @param name The name of the product
   * @param manufacturer The manufacturer of the product
   * @param type The type of the product
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * This method prints the details of the product constructed when called upon.
   *
   * @return A string containing the product's details
   */
  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: " + type;
  }

  /**
   * An accessor for the Id value of a product.
   *
   * @return An integer value representing the id
   */
  public int getId() {
    return id;
  }

  /**
   * An accessor for the manufacturer of a product.
   *
   * @return A string containing the product's manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * A mutator that sets the manufacturer of a product.
   *
   * @param manufacturer Takes in a String meant to represent a product's manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * An accessor for the name of a product.
   *
   * @return A String containing the name of a product
   */
  public String getName() {
    return name;
  }

  /**
   * A mutator that sets the name of a product.
   *
   * @param name Takes in a String meant to represent a product's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * An accessor that gets the item type of a product.
   *
   * @return The type of the product. See ItemType enum
   */
  public ItemType getType() {
    return type;
  }
}
