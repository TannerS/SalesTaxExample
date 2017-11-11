package io.tanners.tax.sales;

import io.tanners.tax.Tax;

//public class SalesTax extends Tax<SalesTax.SalesTaxValues> {
//public class SalesTax extends Tax<SalesTax.SalesTaxValues> {

//    @Override
//    public SalesTaxValues getmValue() {
//        return mValue;
//    }
//
//    @Override
//    public void setmValue(SalesTaxValues mValue) {
//        // if the value is not null
//        if(mValue != null)
//            // set the class variable
//            this.mValue = mValue;
//        // value passed in is null
//        else
//            // throw a common exception
//            throw new NullPointerException();
//    }

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
//    public enum SalesTaxValues {
        // each is valued at it's tax amount
        BOOKS(0),
        FOOD(0),
        MEDICAL(0),
        OTHER(.10),
        IMPORTED(OTHER.mTaxAmount + .05);
        // actual variable holding amount
        private final double mTaxAmount;
        // constructor
//        SalesTaxValues(double mTaxAmount) {
        SalesTax(double mTaxAmount) {
            // assuming this is never negative by programmer
            this.mTaxAmount = mTaxAmount;
        }
        // retrun the current value
        public double getValue()
        {
            return mTaxAmount;
        }
    }
//}