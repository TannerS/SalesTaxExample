package io.tanners.RegisterSystem;

import io.tanners.tax.TaxCalculator;
import io.tanners.tax.TaxData;
import io.tanners.tax.exception.ValueIsNegativeException;
import io.tanners.tax.sales.SalesTax;
import io.tanners.tax.sales.SalesTaxCalculator;
import io.tanners.tax.sales.SalesTaxData;

import java.util.ArrayList;

public class Order {
    private ArrayList<TaxData> orderItems;
    private double mTotal;
    private double mTaxTotal;

    public Order(ArrayList<TaxData> orderItems) {
        this.orderItems = orderItems;
    }

    public ArrayList<TaxData> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<TaxData> orderItems) {
        this.orderItems = orderItems;
    }

    public Double calculateTaxes()
    {
        TaxCalculator<SalesTaxData> mCalculator = new SalesTaxCalculator();

        try {

            return mCalculator.calculateTaxes(orderItems.toArray(new SalesTaxData[orderItems.size()]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return -1.0;
        }
    }


    public Double calculateTotal()
    {
        TaxCalculator<SalesTaxData> mCalculator = new SalesTaxCalculator();

        try {

            return mCalculator.calculateTotals(orderItems.toArray(new SalesTaxData[orderItems.size()]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return -1.0;
        }
    }




}
