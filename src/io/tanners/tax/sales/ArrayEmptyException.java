package io.tanners.tax.sales;//package io.tanners.tax.sales;

public class ArrayEmptyException extends Exception {

    public static final String EMPTY = "Array must not be empty.";

    public ArrayEmptyException(String message) {
        super(message);
    }
}