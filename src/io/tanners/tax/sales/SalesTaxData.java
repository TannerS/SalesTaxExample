package io.tanners.tax.sales;

import io.tanners.tax.TaxData;
import io.tanners.tax.exception.ValueIsNegativeException;

import java.math.BigDecimal;

/**
 * Data class to hold the information about an item with it's tax information for later use
 */
public class SalesTaxData extends TaxData
{
    // if it is imported
    private boolean mIsImported;
    // tax bracket
    private SalesTax mTaxPercentage;
    // total tax amount
    private BigDecimal mTaxedAmount;


    public boolean isImported() {
        return mIsImported;
    }

    public void setisImported(boolean mIsImported) {
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
    public BigDecimal getmPrice() {
        return mPrice;
    }

    @Override
    public void setmPrice(BigDecimal mPrice) throws ValueIsNegativeException {
        if(mPrice.doubleValue() < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
        else
            this.mPrice = mPrice;
    }

    public BigDecimal getmTaxedAmount() {
        return mTaxedAmount;
    }

    public void setmTaxedAmount(BigDecimal mTaxedAmount) {
        this.mTaxedAmount = mTaxedAmount;
    }
}