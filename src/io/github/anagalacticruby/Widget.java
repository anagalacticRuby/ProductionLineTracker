package io.github.anagalacticruby;

/**
 * This class is to make sure the product class works.
 *
 * <p>The widget class is also how the program is able to add Products to the TableView in the
 * Product Line tab. Why? Because the Product class is abstract, and we cannot make instances of an
 * abstract class. However we can make instances of subclasses that extend an abstract class.
 *
 * @author Nicholas Hansen
 */
class Widget extends Product {

  /**
   * A constructor for Widgets which also happen to be Products.
   *
   * <p>It takes the same values of a Product (name,manufacturer,ItemType), allowing users to create
   * Widget Objects. The fact that it takes the same parameters as a Product allows the program to
   * store them in an ObservableList (See the controller), and do other things that Products would
   * normally be required for. This is because of inheritance and Polymorphism.
   *
   * @param name The name of the widget (Effectively a Product name)
   * @param manufacturer The manufacturer of the Widget (Product manufacturer)
   * @param type The ItemType of the Widget (Product Type)
   */
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

  Widget(int id, String name, String manufacturer, ItemType type) {
    super(id, name, manufacturer, type);
  }
}
