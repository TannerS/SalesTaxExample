package io.tanners.tax.sales;

import java.math.BigDecimal;

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
    BOOKS(new BigDecimal(0)),
    FOOD(new BigDecimal(0)),
    MEDICAL(new BigDecimal(0)),
    OTHER(new BigDecimal(.10)),
    // fix for the fact editing the enum in code
    // changes the value for object itself
    // not just one instance of it
    BOOKS_IMPORTED(new BigDecimal(.05)),
    FOOD_IMPORTED(new BigDecimal(.05)),
    MEDICAL_IMPORTED(new BigDecimal(.05)),
    OTHER_IMPORTED(new BigDecimal(.15));

    // actual variable holding amount
    private BigDecimal mTaxAmount;
    // constructor
    SalesTax(BigDecimal mTaxAmount) {
        // assuming this is never negative by programmer
        this.mTaxAmount = mTaxAmount;
    }

    public BigDecimal getValue()
    {
        return mTaxAmount;
    }
}