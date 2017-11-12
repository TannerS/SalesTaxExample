package io.tanners.tax.sales;

import io.tanners.tax.TaxData;
import io.tanners.tax.exception.ValueIsNegativeException;

/**
 * Data class to hold the information about an item with it's tax information for later use
 */
public class SalesTaxData extends TaxData
{
    protected boolean mIsImported;
    private SalesTax mTaxPercentage;

    public boolean ismIsImported() {
        return mIsImported;
    }

    public void setmIsImported(boolean mIsImported) {
        this.mIsImported = mIsImported;
    }

    public SalesTax getmTaxPercentage() {
        return mTaxPercentage;
    }

    public void setmTaxPercentage(SalesTax mTaxPercentage) {
        if(mTaxPercentage == null)
            throw new NullPointerException();
        else
            this.mTaxPercentage = mTaxPercentage;
    }

    @Override
    public int getmQuantity() {
        return mQuantity;
    }

    @Override
    public void setmQuantity(int mQuantity) throws ValueIsNegativeException {
        if(mQuantity < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
        else
            this.mQuantity = mQuantity;
    }

    @Override
    public String getmItem() {
        return mItem;
    }

    @Override
    public void setmItem(String mItem) {
        this.mItem = mItem;
    }

    @Override
    public double getmPrice() {
        return mPrice;
    }

    @Override
    public void setmPrice(double mPrice) throws ValueIsNegativeException {
        if(mPrice < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
        else
            this.mPrice = mPrice;
    }
}