package io.tanners.tax.sales;

import io.tanners.tax.TaxCalculator;
import io.tanners.tax.exception.ValueIsNegativeException;

import java.util.ArrayList;

public class SalesTaxCalculator extends TaxCalculator<SalesTaxData>{

    @Override
    public Double calculateTax(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return mTaxInfo.getmPrice() * mTaxInfo.getmTaxPercentage().getValue() * mTaxInfo.getmQuantity();
    }

    @Override
    public Double calculateTotal(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return mTaxInfo.getmPrice() * (1.0 + mTaxInfo.getmTaxPercentage().getValue() * mTaxInfo.getmQuantity());
    }

    @Override
    public Double calculateTaxes(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        double mTaxesTotal = 0.0;

        for(SalesTaxData mData : mTaxInfo) {
            mTaxesTotal += calculateTax(mData);
        }

        return mTaxesTotal;
    }

    @Override
    public Double calculateTotals(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        double mTotal = 0.0;

        for(SalesTaxData mData : mTaxInfo) {
            mTotal += calculateTotal(mData);
        }

        return mTotal;
    }

    private void validationCheck(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        if (mTaxInfo == null)
            throw new NullPointerException();
        else if (mTaxInfo.getmPrice() < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
    }
}