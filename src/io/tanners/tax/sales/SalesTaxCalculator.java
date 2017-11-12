package io.tanners.tax.sales;

import io.tanners.tax.TaxCalculator;
import io.tanners.tax.exception.ValueIsNegativeException;

import java.math.BigDecimal;

public class SalesTaxCalculator extends TaxCalculator<SalesTaxData>{

    @Override
    public BigDecimal calculateTax(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return (mTaxInfo.getmPrice().multiply(mTaxInfo.getmTaxPercentage().getValue())).multiply(BigDecimal.valueOf(mTaxInfo.getmQuantity()));
    }

    @Override
    public BigDecimal calculateTotal(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return mTaxInfo.getmTaxPercentage().getValue().add(new BigDecimal(1.0)).multiply(new BigDecimal(mTaxInfo.getmQuantity())).multiply(mTaxInfo.getmPrice());
    }

    @Override
    public BigDecimal calculateTaxes(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        BigDecimal mTaxesTotal = new BigDecimal(0.0);

        for(SalesTaxData mData : mTaxInfo) {
            mTaxesTotal = mTaxesTotal.add(calculateTax(mData));
        }

        return mTaxesTotal;
    }

    @Override
    public BigDecimal calculateTotals(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        BigDecimal mTotal = new BigDecimal(0.0);

        for(SalesTaxData mData : mTaxInfo) {
            mTotal = mTotal.add(calculateTotal(mData));
        }

        return mTotal;
    }

    private void validationCheck(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        if (mTaxInfo == null)
            throw new NullPointerException();
        else if (mTaxInfo.getmPrice().doubleValue() < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
    }
}