package io.tanners.tax.sales;

public class SalesTaxData
{
    private int mQuantity;
    private String mItem;
    private double mPrice;
    private boolean mIsImported;
    private SalesTax mTaxPercentage;

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmItem() {
        return mItem;
    }

    public void setmItem(String mItem) {
        this.mItem = mItem;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

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
        this.mTaxPercentage = mTaxPercentage;
    }
}