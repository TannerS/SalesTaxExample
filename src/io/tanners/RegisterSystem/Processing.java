package io.tanners.RegisterSystem;

import io.tanners.tax.TaxData;

public abstract class Processing {
    public abstract TaxData parseData(String mInput);
}
