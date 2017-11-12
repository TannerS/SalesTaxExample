package io.tanners.tax.sales;

import io.tanners.tax.TaxCalculator;
import io.tanners.tax.exception.ValueIsNegativeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * class to calculate the taxes
 */
public class SalesTaxCalculator extends TaxCalculator<SalesTaxData>{

    /**
     * takes in a object that is used to calculate the tax
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    @Override
    public BigDecimal calculateTax(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        // validate the data
        validationCheck(mTaxInfo);
        // calculate and return tax

        return formatDecimalInput((mTaxInfo.getmPrice().multiply(mTaxInfo.getmTaxPercentage().getValue())).multiply(BigDecimal.valueOf(mTaxInfo.getmQuantity())));
    }
    /**
     * takes in a object that is used to calculate the total price with taxes
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    @Override
    public BigDecimal calculateTotal(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        // validate the data
        validationCheck(mTaxInfo);
        // calculate and return tax
        return formatDecimalInput(mTaxInfo.getmTaxPercentage().getValue().add(new BigDecimal(1.0)).multiply(new BigDecimal(mTaxInfo.getmQuantity())).multiply(mTaxInfo.getmPrice()));
    }
    /**
     * takes in a list of objects that is used to calculate the tax
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    @Override
    public BigDecimal calculateTaxes(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        BigDecimal mTaxesTotal = new BigDecimal(0.0);

        for(SalesTaxData mData : mTaxInfo) {
            // validate the data
            validationCheck(mData);
            // get that current tax
            mTaxesTotal = mTaxesTotal.add(calculateTax(mData));
        }
        // return result
        return formatDecimalInput(mTaxesTotal);
    }
    /**
     * takes in a list of objects that is used to calculate the total price with taxes
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    @Override
    public BigDecimal calculateTotals(SalesTaxData[] mTaxInfo) throws ValueIsNegativeException {
        BigDecimal mTotal = new BigDecimal(0.0);

        for(SalesTaxData mData : mTaxInfo) {
            // validate the data
            validationCheck(mData);
            // get that current tax
            mTotal = mTotal.add(calculateTotal(mData));
        }
        // return result
        return formatDecimalInput(mTotal);
    }

    /**
     * some simple validation of the object
     * @param mTaxInfo
     * @throws ValueIsNegativeException
     */
    private void validationCheck(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        if (mTaxInfo == null)
            throw new NullPointerException();
        else if (mTaxInfo.getmPrice().doubleValue() < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
    }

    /**
     * format the data in a proper format
     * @param mInput
     * @return
     */
    private BigDecimal formatDecimalInput(BigDecimal mInput)
    {
        DecimalFormat mFormat = new DecimalFormat("#######.###");
        // round value up
        mFormat.setRoundingMode(RoundingMode.HALF_UP);
        // return result
        return new BigDecimal(mFormat.format(mInput));
    }



}