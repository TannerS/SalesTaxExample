package io.tanners.tax.sales;

import io.tanners.tax.ITaxCalculator;
import io.tanners.tax.TaxCalculator;
import io.tanners.tax.exception.ValueIsNegativeException;

import java.util.ArrayList;

//public class SalesTaxCalculator<SalesTaxData>  extends TaxCalculator<SalesTaxData> {
public class SalesTaxCalculator  implements ITaxCalculator<SalesTaxData> {

    @Override
    public Double calculateTax(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return mTaxInfo.getmPrice() * mTaxInfo.getmTaxPercentage().getValue();
    }

    @Override
    public Double calculateTotal(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        validationCheck(mTaxInfo);
        return mTaxInfo.getmPrice() * (1.0 + mTaxInfo.getmTaxPercentage().getValue());
    }

    @Override
    public ArrayList<Double> calculateTaxes(ArrayList<SalesTaxData> mTaxInfo) throws ValueIsNegativeException {
        throw new UnsupportedOperationException("Must be implemented");
    }

    @Override
    public ArrayList<Double> calculateTotals(ArrayList<SalesTaxData> mTaxInfo) throws ValueIsNegativeException {
        throw new UnsupportedOperationException("Must be implemented");
    }

    private void validationCheck(SalesTaxData mTaxInfo) throws ValueIsNegativeException {
        if (mTaxInfo == null)
            throw new NullPointerException();
        else if (mTaxInfo.getmPrice() < 0)
            throw new ValueIsNegativeException(ValueIsNegativeException.NEGATIVE);
    }
}