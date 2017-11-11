package io.tanners.tax;

import io.tanners.tax.sales.SalesTax;
import io.tanners.tax.sales.SalesTaxCalculator;
import io.tanners.tax.sales.ValueIsNegativeException;

import java.util.ArrayList;

/**
 *
 * The parent class that will be used to defined a common behavior.
 * This can be extended for other sub classes such as SalesTax or if needed, FederalTax.
 * This gives more chances for a parent class to define more common behaviors if ever needed.
 *
 */
public abstract class TaxCalculator<T> {
    public abstract Double calculateTax(T mTaxInfo) throws ValueIsNegativeException;
    public abstract Double calculateTotal(T mTaxInfo) throws ValueIsNegativeException;
    public abstract ArrayList<Double> calculateTaxes(ArrayList<T> mTaxInfo) throws ValueIsNegativeException;
    public abstract ArrayList<Double> calculateTotals(ArrayList<T> mTaxInfo) throws ValueIsNegativeException;
}
