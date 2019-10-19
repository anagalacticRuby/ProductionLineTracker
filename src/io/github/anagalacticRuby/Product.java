package io.github.anagalacticRuby;
/**
 * This is the class that is the powerhouse of the program.
 *
 * <p>A product's name, manufacturer and type are put into a constructor and then printed when the
 * toString is called.
 *
 * @author Nicholas Hansen
 */
public abstract class Product implements Item {
  private int Id;
  private ItemType Type;
  private String Manufacturer;
  private String Name;

  Product(String name, String manufacturer, ItemType type) {
    this.Name = name;
    this.Manufacturer = manufacturer;
    this.Type = type;
  }

  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: " + Type;
  }

  public int getId() {
    return Id;
  }

  public String getManufacturer() {
    return Manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    Manufacturer = manufacturer;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }
}
/**
 * This class is merely used to make sure the Product class works.
 *
 * @author Nicholas Hansen
 */
class Widget extends Product {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}
