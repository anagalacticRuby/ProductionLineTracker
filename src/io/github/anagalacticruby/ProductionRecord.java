package io.github.anagalacticruby;

import java.util.Date;

/**
 * This class is where the data about a product's production record is managed.
 *
 * <p>The information will be posted in the Production Log's text area after the record production
 * button is pressed.
 *
 * @author Nicholas Hansen
 */
class ProductionRecord {
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /**
   * An accessor for the serial number of a product that has been recorded.
   *
   * @return A String containing the serial number of a product
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * A mutator that sets the value of a recorded product's serial number.
   *
   * @param serialNumber Takes in a String that holds the new serial number to set the old one to
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * An accessor for the date that which a product has been produced.
   *
   * <p>Note that it is a real date updated in real time.
   *
   * @return A Date value (Essentially a long) that represents the date that a product was recorded
   *     to be produced on on
   */
  public Date getDateProduced() {
    return new Date(dateProduced.getTime());
  }

  /**
   * A mutator for the date that which a product has been produced.
   *
   * @param dateProduced Takes in a Date value and sets the old one to the one provided
   */
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * An accessor for the production number of a recorded product.
   *
   * @return An integer value representing the production number of a recorded product
   */
  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   * An accessor for the productID of a recorded product.
   *
   * @return An integer value that represents the productID
   */
  public int getProductID() {
    return productID;
  }

  /**
   * A mutator that sets the current productID of a recorded product to the one passed in.
   *
   * @param productID Takes in an integer that will become the new productID
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * A mutator that sets the production number of a recorded product to the one passed in.
   *
   * @param productionNumber The new production number that will be replacing the old one.
   */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * A constructor that takes only the productID of a recorded product and sets the details to the
   * 'factory default'.
   *
   * @param productID Takes in the value meant to represent a productID
   */
  ProductionRecord(int productID) {
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  /**
   * A constructor that takes a product and the amount of said products that need to be produced.
   *
   * <p>It generates a special serial number for each product passed in.
   *
   * @param product The product that needs to be recorded
   * @param itemCount How many times this product has been created
   */
  ProductionRecord(Product product, int itemCount) {

    /*
     * This little if statement ensures there are no Null Pointer Exceptions. Why? It adds on a
     * 'placeholder' character to the end of the product's manufacturer. This enables the rest of
     * the method to work, as it requires a 3-character string.
     */

    if (product.getManufacturer().length() < 3) {
      product.setManufacturer(product.getManufacturer() + '-');
    }

    if (itemCount < 10) {
      this.serialNumber =
          product.getManufacturer().substring(0, 3)
              + product.getType().getCode()
              + "0000"
              + itemCount;
    } else if (itemCount < 100) {
      this.serialNumber =
          product.getManufacturer().substring(0, 3)
              + product.getType().getCode()
              + "000"
              + itemCount;
    } else if (itemCount < 1000) {
      this.serialNumber =
          product.getManufacturer().substring(0, 3)
              + product.getType().getCode()
              + "00"
              + itemCount;
    } else if (itemCount < 10000) {
      this.serialNumber =
          product.getManufacturer().substring(0, 3) + product.getType().getCode() + "0" + itemCount;
    } else {
      this.serialNumber =
          product.getManufacturer().substring(0, 3) + product.getType().getCode() + itemCount;
    }
    dateProduced = new Date();
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * A constructor that will be used by the database later to retrieve recorded products.
   *
   * @param productionNumber The production number of a recorded product
   * @param productID The productID of a recorded product
   * @param serialNumber The serial number of a recorded product
   * @param dateProduced The date that a recorded product has been produced on
   */
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {

    this.productID = productID;
    this.productionNumber = productionNumber;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * A toString method that will return the details of a recorded product.
   *
   * <p>This information is printed in the Production Log tab's text area.
   *
   * @return A String containing the production number, ID, serial number, and date produced of a
   *     recorded product
   */
  @Override
  public String toString() {
    return "Prod. Number: "
        + productionNumber
        + " Product ID: "
        + productID
        + " Serial Number: "
        + serialNumber
        + " Date: "
        + dateProduced;
  }
}
