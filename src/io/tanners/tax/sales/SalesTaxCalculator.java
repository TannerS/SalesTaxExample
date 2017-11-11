package io.tanners.tax.sales;

import java.util.ArrayList;

public class SalesTaxCalculator {

    public double calculateTaxes(double mPrice, SalesTax mTax) throws ValueIsNegativeException, NullPointerException {
        if (mTax == null)
            throw new NullPointerException();
        else if (mPrice < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
        else
            return mPrice * (1.0 + mTax.getValue());
    }

    public ArrayList<Double> calculateTaxes(ArrayList<SalesTaxCalculatorWrapper> mTaxInfo) throws NullPointerException, ValueIsNegativeException {
        ArrayList<Double> mTaxResults = new ArrayList<Double>();

        for(SalesTaxCalculatorWrapper item : mTaxInfo)
            mTaxResults.add(this.calculateTaxes(item.mPrice, item.mTax));

        return mTaxResults;
    }

    public class SalesTaxCalculatorWrapper
    {
        private double mPrice;
        private SalesTax mTax;

        public SalesTaxCalculatorWrapper(double mPrice, SalesTax mTax) {
            this.mPrice = mPrice;
            this.mTax = mTax;
        }

        public double getmPrice() {
            return mPrice;
        }

        public void setmPrice(double mPrice) {
            this.mPrice = mPrice;
        }

        public SalesTax getmTax() {
            return mTax;
        }

        public void setmTax(SalesTax mTax) {
            this.mTax = mTax;
        }
    }
}
