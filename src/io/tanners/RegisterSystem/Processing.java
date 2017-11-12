package io.tanners.RegisterSystem;

import io.tanners.tax.TaxData;

/**
 * Abstract class which can be used for future inheriting
 */
public abstract class Processing {
    public abstract TaxData parseData(String mInput);
}
