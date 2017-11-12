package io.tanners.tax;

import io.tanners.tax.exception.ValueIsNegativeException;

import java.util.ArrayList;

public interface ITaxCalculator <T extends TaxData>{
    /**
     * takes in a object that is used to calculate the tax
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public Double calculateTax(T mTaxInfo) throws ValueIsNegativeException;

    /**
     * takes in a object that is used to calculate the total price with taxes
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public Double calculateTotal(T mTaxInfo) throws ValueIsNegativeException;

    /**
     * takes in a list of objects that is used to calculate the tax
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public ArrayList<Double> calculateTaxes(ArrayList<T> mTaxInfo) throws ValueIsNegativeException;

    /**
     * takes in a list of objects that is used to calculate the total price with taxes
     * @param mTaxInfo
     * @return
     * @throws ValueIsNegativeException
     */
    public ArrayList<Double> calculateTotals(ArrayList<T> mTaxInfo) throws ValueIsNegativeException;
}
