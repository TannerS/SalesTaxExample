package io.tanners.tax.sales;

public class ValueIsNegativeException extends Exception {
    public static final String NEGATIVE = "Value must be 0 or greater: Negative found";

    public ValueIsNegativeException(String message) {
        super(message);
    }
}
