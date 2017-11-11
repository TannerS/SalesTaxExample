package io.tanners.tax.sales;//package io.tanners.tax.sales;

/**
 * A custom exception for empty array values
 */
public class ArrayEmptyException extends Exception {
    // static common message that can be used instead of typing in your own
    public static final String EMPTY = "Array must not be empty.";

    public ArrayEmptyException(String message) {
        super(message);
    }
}