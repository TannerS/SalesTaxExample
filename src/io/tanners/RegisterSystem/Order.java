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

/**
 * class to hold each order (each set of inputs with an output)
 */
public class Order implements Iterable<TaxData>{
    // hold all taxed items for order
    private ArrayList<TaxData> orderItems;
    // tax calculator
    private TaxCalculator<SalesTaxData> mCalculator;

    /**
     * constructor, takes in an order of items
     * @param orderItems
     */
    public Order(ArrayList<TaxData> orderItems) {
        // init resources
        this.orderItems = orderItems;
        mCalculator  = new SalesTaxCalculator();
        // quick calculating tax to add back into items tax objects
        for(TaxData mItem : orderItems) {
            try {
                ((SalesTaxData) mItem).setmTaxedAmount(mCalculator.calculateTotal((SalesTaxData) mItem));
            } catch (ValueIsNegativeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * return taxed items
     * @return
     */
    public ArrayList<TaxData> getOrderItems() {
        return orderItems;
    }

    /**
     * set taxed items
     * @param orderItems
     */
    public void setOrderItems(ArrayList<TaxData> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * calculate total taxes for order
     * @return
     */
    public BigDecimal calculateTaxes()
    {
        try {
            return mCalculator.calculateTaxes(orderItems.toArray(new SalesTaxData[orderItems.size()]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * calculate total price for order
     * @return
     */
    public BigDecimal calculateTotal()
    {
        try {
            return mCalculator.calculateTotals(orderItems.toArray(new SalesTaxData[orderItems.size()]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * iterator way to access items in order to return them in order for loops and such
     * @return
     */
    public Iterator<TaxData> iterator() {
        return new OrderIterator(0, orderItems.size());
    }

    /**
     * not implemented
     * @param action
     */
    @Override
    public void forEach(Consumer<? super TaxData> action) {
        throw new UnsupportedOperationException();
    }


    /**
     * not implemented
     * @return
     */
    @Override
    public Spliterator<TaxData> spliterator() {
        throw new UnsupportedOperationException();
    }

    // Inner class example
    public final class OrderIterator implements Iterator<TaxData> {
        // index of current item
        private int mCursor;
        // index of last item
        private final int mEnd;

        /**
         * constructor
         * @param mCursor
         * @param mEnd
         */
        public OrderIterator(int mCursor, int mEnd) {
            this.mCursor = mCursor;
            this.mEnd = mEnd;
        }

        /**
         * checks if items are left
         * @return
         */
        @Override
        public boolean hasNext() {
            return this.mCursor < mEnd;
        }

        /**
         * get next item
         * @return
         */
        @Override
        public TaxData next() {
            if(this.hasNext()) {
                // get current cursor
                int current = mCursor;
                // increment cursor
                mCursor++;
                // return item at current  cursor
                return orderItems.get(current);
            }
            throw new NoSuchElementException();
        }

        /**
         * not implemented
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
