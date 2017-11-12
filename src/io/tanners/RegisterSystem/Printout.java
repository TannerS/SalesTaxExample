package io.tanners.RegisterSystem;

import io.tanners.tax.TaxData;
import io.tanners.tax.sales.SalesTaxData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Printout {
    private final float ROUNDNG = 0.05f;
    private Order mOrder;

    public Printout(Order mOrder) {
        this.mOrder = mOrder;
    }

    public BigDecimal formatValues(BigDecimal mInput)
    {
//        mInput = mInput.divide(new BigDecimal(ROUNDNG), 2, RoundingMode.CEILING);
//        mInput = new BigDecimal(Math.ceil(mInput.doubleValue()));
//        mInput= mInput.multiply(new BigDecimal(ROUNDNG));
        return mInput;

    }


    public String displayReceipt()
    {
        ArrayList<TaxData> orderItems = this.mOrder.getOrderItems();
        Order.OrderIterator itr = (Order.OrderIterator) mOrder.iterator();
        StringBuilder mBuilder = new StringBuilder();

//        for(TaxData mData : orderItems)for(TaxData mData : orderItems)
        while(itr.hasNext())
        {
            SalesTaxData mTempData = (SalesTaxData) itr.next();

            if(mTempData == null)
                System.out.println("DEBUG 2");
//            System.out.println(mData.getmQuantity() + " " + mData.getmItem() + " at " + formatDisplay(formatValues(orderItems.calculateTotal(mData))));
//            System.out.println(mTempData.getmQuantity() + " " + mTempData.getmItem() + " at " + formatDisplay(formatValues((mTempData).getmTaxedAmount())));
            mBuilder.append(mTempData.getmQuantity()).append(" ").append(mTempData.getmItem()).append(" at ").append((formatValues((mTempData).getmTaxedAmount()))).append("\n\r");
//            System.out.println(mTempData.getmQuantity() + " " + mTempData.getmItem() + " at " + mOrdermTempData.getmPrice());
//            System.out.println(mTempData.getmQuantity() + " " + mTempData.getmItem() + " at " + mOrdermTempData.getmPrice());
        }

//        mBuilder.append("Sales Taxes: ").append(formatValues(this.mOrder.calculateTaxes()));
        mBuilder.append("Sales Taxes: ").append((formatValues(this.mOrder.calculateTaxes())));
        mBuilder.append("\n\rTotal: ").append((formatValues(this.mOrder.calculateTotal()))).append("\n\r");
//        mBuilder.append("\n\rTotal: ").append(formatValues(this.mOrder.calculateTotal())).append("\n\r");

        return mBuilder.toString();

    }


}
