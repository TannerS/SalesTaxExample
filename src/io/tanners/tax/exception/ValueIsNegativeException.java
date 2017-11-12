package io.tanners.tax.exception;
/**
 * A custom exception for negative values
 */
public class ValueIsNegativeException extends Exception {
    // static common message that can be used instead of typing in your own
    public static final String NEGATIVE = "Value must be 0 or greater: Negative found";

    public ValueIsNegativeException(String message) {
        super(message);
    }
}
