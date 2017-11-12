package io.tanners.tax;

import io.tanners.tax.exception.ValueIsNegativeException;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ITaxCalculator <T extends TaxData>{
    /**
     * takes in a object that is used to calculate the tax
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public BigDecimal calculateTax(T mTaxInfo) throws ValueIsNegativeException;

    /**
     * takes in a object that is used to calculate the total price with taxes
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public BigDecimal calculateTotal(T mTaxInfo) throws ValueIsNegativeException;

    /**
     * takes in a list of objects that is used to calculate the tax
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public BigDecimal calculateTaxes(T[] mTaxInfo) throws ValueIsNegativeException;

    /**
     * takes in a list of objects that is used to calculate the total price with taxes
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public BigDecimal calculateTotals(T[] mTaxInfo) throws ValueIsNegativeException;
}
