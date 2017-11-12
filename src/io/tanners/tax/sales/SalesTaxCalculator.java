package io.tanners.tax.sales;

import io.tanners.tax.TaxCalculator;
import io.tanners.tax.exception.ValueIsNegativeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SalesTaxCalculator extends TaxCalculator<SalesTaxData>{

    @Override
    public BigDecimal calculateTax(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return formatDecimalInput((mTaxInfo.getmPrice().multiply(mTaxInfo.getmTaxPercentage().getValue())).multiply(BigDecimal.valueOf(mTaxInfo.getmQuantity())));
    }

    @Override
    public BigDecimal calculateTotal(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return formatDecimalInput(mTaxInfo.getmTaxPercentage().getValue().add(new BigDecimal(1.0)).multiply(new BigDecimal(mTaxInfo.getmQuantity())).multiply(mTaxInfo.getmPrice()));
    }

    @Override
    public BigDecimal calculateTaxes(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        BigDecimal mTaxesTotal = new BigDecimal(0.0);

        for(SalesTaxData mData : mTaxInfo) {
            mTaxesTotal = mTaxesTotal.add(calculateTax(mData));
        }

        return formatDecimalInput(mTaxesTotal);
    }

    @Override
    public BigDecimal calculateTotals(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        BigDecimal mTotal = new BigDecimal(0.0);

        for(SalesTaxData mData : mTaxInfo) {
            mTotal = mTotal.add(calculateTotal(mData));
        }

        return formatDecimalInput(mTotal);
    }

    private void validationCheck(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        if (mTaxInfo == null)
            throw new NullPointerException();
        else if (mTaxInfo.getmPrice().doubleValue() < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
    }

    private BigDecimal formatDecimalInput(BigDecimal mInput)
    {
        DecimalFormat mFormat = new DecimalFormat("#######.###");
        mFormat.setRoundingMode(RoundingMode.HALF_UP);
//        return mFormat.format(mInput);
        return new BigDecimal(mFormat.format(mInput));
    }


}