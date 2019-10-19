package io.github.anagalacticRuby;

import java.util.Date;

/**
 * This class is where the data about a product's production record is managed.
 * <p>The information will be posted in the Production Log tab after the record production button is pressed.</p>
 * @author Nicholas Hansen
 */
public class ProductionRecord {
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    }

    public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public int getProductionNumber() {
    return productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  ProductionRecord(int productID) {
    productionNumber = 0;
    serialNumber = "0";
    this.dateProduced = new Date();
  }

  ProductionRecord(int productionNumber,int productID, String serialNumber, Date dateProduced) {
      this.productID = productID;
      this.productionNumber = productionNumber;
      this.serialNumber = serialNumber;
      this.dateProduced = new Date();
  }

    @Override
    public String toString() {
        return " Prod. Number: " + productionNumber + " Product ID: " + productID
                + " Serial Number: " + serialNumber + " Date: " + dateProduced;
    }
}
