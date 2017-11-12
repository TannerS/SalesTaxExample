package io.tanners.tax;
/**
 *
 * The parent class that will be used to defined a common behavior.
 * This can be extended for other sub classes such as SalesTax or if needed, FederalTax.
 * This gives more chances for a parent class to define more common behaviors if ever needed.
 * and is an example of what we can do with
 *
 */
public abstract class TaxCalculator<T extends TaxData> implements ITaxCalculator<T>{ }
