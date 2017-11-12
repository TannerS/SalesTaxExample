package io.tanners.tax;

import io.tanners.tax.exception.ValueIsNegativeException;

import java.math.BigDecimal;

/**
 * Data class to hold the information about an item with it's tax information for later use
 */
public abstract class TaxData
{
    protected int mQuantity;
    protected String mItem;
    protected BigDecimal mPrice;

    public abstract int getmQuantity();
    public abstract void setmQuantity(int mQuantity) throws ValueIsNegativeException;
    public abstract String getmItem();
    public abstract void setmItem(String mItem);
    public abstract BigDecimal getmPrice();
    public abstract void setmPrice(BigDecimal mPrice) throws ValueIsNegativeException;

}