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

    public BigDecimal formatValues(BigDecimal mInput)
    {

        return mInput;

    }


    public String displayReceipt()
    {
        ArrayList<TaxData> orderItems = this.mOrder.getOrderItems();
        Order.OrderIterator itr = (Order.OrderIterator) mOrder.iterator();
        StringBuilder mBuilder = new StringBuilder();

        while(itr.hasNext())
        {
            SalesTaxData mTempData = (SalesTaxData) itr.next();

            mBuilder.append(mTempData.getmQuantity()).append(" ").append(mTempData.getmItem()).append(" at ").append(((mTempData).getmTaxedAmount())).append("\n\r");
        }

        mBuilder.append("Sales Taxes: ").append((formatValues(this.mOrder.calculateTaxes())));
        mBuilder.append("\n\rTotal: ").append((formatValues(this.mOrder.calculateTotal()))).append("\n\r");

        return mBuilder.toString();

    }


}
