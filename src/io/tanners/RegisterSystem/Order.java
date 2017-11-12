package io.tanners.RegisterSystem;

import io.tanners.tax.TaxCalculator;
import io.tanners.tax.TaxData;
import io.tanners.tax.exception.ValueIsNegativeException;
import io.tanners.tax.sales.SalesTax;
import io.tanners.tax.sales.SalesTaxCalculator;
import io.tanners.tax.sales.SalesTaxData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Order implements Iterable<TaxData>{
    private ArrayList<TaxData> orderItems;
    private double mTotal;
    private double mTaxTotal;
    private TaxCalculator<SalesTaxData> mCalculator;

    public Order(ArrayList<TaxData> orderItems) {
        this.orderItems = orderItems;

        mCalculator  = new SalesTaxCalculator();

        for(TaxData mItem : orderItems) {
            try {
                ((SalesTaxData) mItem).setmTaxedAmount(mCalculator.calculateTotal((SalesTaxData) mItem));
            } catch (ValueIsNegativeException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<TaxData> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<TaxData> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal calculateTaxes()
    {
        try {
            return mCalculator.calculateTaxes(orderItems.toArray(new SalesTaxData[orderItems.size()]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BigDecimal calculateTotal()
    {
        try {
            return mCalculator.calculateTotals(orderItems.toArray(new SalesTaxData[orderItems.size()]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Iterator<TaxData> iterator() {
        return new OrderIterator(0, orderItems.size());
    }

    @Override
    public void forEach(Consumer<? super TaxData> action) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Spliterator<TaxData> spliterator() {
        return null;
    }

    // Inner class example
    public final class OrderIterator implements Iterator<TaxData> {
        private int mCursor;
        private final int mEnd;

        public OrderIterator(int mCursor, int mEnd) {
            this.mCursor = mCursor;
            this.mEnd = mEnd;
        }

        public boolean hasNext() {
            return this.mCursor < mEnd;
        }

        public TaxData next() {
            if(this.hasNext()) {
                int current = mCursor;
                mCursor++;
                return orderItems.get(current);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
