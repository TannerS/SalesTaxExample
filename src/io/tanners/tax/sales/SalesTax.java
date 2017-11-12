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
    // fix for the fact editing the enum in code
    // changes the value for object itself
    // not just one instance of it
    BOOKS_IMPORTED(.05),
    FOOD_IMPORTED(.05),
    MEDICAL_IMPORTED(.05),
    OTHER_IMPORTED(.15);

    // actual variable holding amount
    private double mTaxAmount;
    // constructor
    SalesTax(double mTaxAmount) {
        // assuming this is never negative by programmer
        this.mTaxAmount = mTaxAmount;
    }

//    public void setValue(Double mValue)
//    {
//        mTaxAmount = mValue;
//    }

    public double getValue()
    {
        return mTaxAmount;
    }


////    public void add(double mTaxValueX, double mTaxValueY) {
//    public SalesTax add(SalesTax mTaxValue) {
////        SalesTax.IMPORTED.getValue() + mTaxValue.getValue();
////        return SalesTax(this.mTaxAmount + mTaxValue.getValue());
//        System.out.println("TESTTTTT:" + this.getValue());
//        System.out.println("TESTTTTT:" + this.getValue());
//        mTaxValue.setValue(mTaxValue.getValue() + this.mTaxAmount);
//        return mTaxValue;
////        mTaxAmount = mTaxValueX + mTaxValueY;
//    }
}