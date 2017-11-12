package io.tanners.RegisterSystem;

import io.tanners.tax.TaxData;
import io.tanners.tax.sales.SalesTaxData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Printout {
    private Order mOrder;

    public Printout(Order mOrder) {
        this.mOrder = mOrder;
    }

    /**
     * display the receipt of items with custom formatting
     * @return
     */
    public String displayReceipt()
    {
        // create iterator onject
        Order.OrderIterator itr = (Order.OrderIterator) mOrder.iterator();
        StringBuilder mBuilder = new StringBuilder();
        // iterate items
        while(itr.hasNext())
        {
            // get item
            SalesTaxData mTempData = (SalesTaxData) itr.next();
            // create message
            mBuilder.append(mTempData.getmQuantity()).append(" ").append(mTempData.getmItem()).append(" at ").append(((mTempData).getmTaxedAmount())).append("\n\r");
        }
        // create rest of message
        mBuilder.append("Sales Taxes: ").append((this.mOrder.calculateTaxes()));
        mBuilder.append("\n\rTotal: ").append((this.mOrder.calculateTotal())).append("\n\r");
        // return output string
        return mBuilder.toString();
    }
}
