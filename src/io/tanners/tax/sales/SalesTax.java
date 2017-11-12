package io.tanners.tax.sales;
/**
 *
 * Using a common enum can ensure the the data passed into the above methods
 * are only with in the realm of the data we are expecting.
 * For example, if I made it like this (a property)
 *
 * public final static int BOOKS = 1;
 *
 * this can work as fine, however since it is an int, the methods can take
 * in ANY int. this can cause problems without proper validation. However,
 * in this way, we can only pass in the proper enum types in which hold the
 * values we are able to work with via the prompt.
 *
 */
public enum SalesTax {
    // each is valued at it's tax amount
    BOOKS(0),
    FOOD(0),
    MEDICAL(0),
    OTHER(.10),
    IMPORTED(OTHER.mTaxAmount + .05);
    // actual variable holding amount
    private final double mTaxAmount;
    // constructor
    SalesTax(double mTaxAmount) {
        // assuming this is never negative by programmer
        this.mTaxAmount = mTaxAmount;
    }
    public double getValue()
    {
        return mTaxAmount;
    }
}