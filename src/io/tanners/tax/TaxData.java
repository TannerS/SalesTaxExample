package io.tanners.tax;

import io.tanners.tax.exception.ValueIsNegativeException;

/**
 * Data class to hold the information about an item with it's tax information for later use
 */
public abstract class TaxData
{
    protected int mQuantity;
    protected String mItem;
    protected double mPrice;

    public abstract int getmQuantity();
    public abstract void setmQuantity(int mQuantity) throws ValueIsNegativeException;
    public abstract String getmItem();
    public abstract void setmItem(String mItem);
    public abstract double getmPrice();
    public abstract void setmPrice(double mPrice) throws ValueIsNegativeException;

}