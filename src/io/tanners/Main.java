package io.tanners;

import io.tanners.RegisterSystem.ItemProcessing;
import io.tanners.RegisterSystem.Order;
import io.tanners.tax.TaxData;
import io.tanners.tax.sales.SalesTaxData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ItemProcessing mProcessor = new ItemProcessing();
        Scanner mInputScanner = null;
        ArrayList<TaxData> mCurrentOrderValues = new  ArrayList<TaxData>();
        final String NEW_LINE = System.getProperty("line.separator");
        Order mCurrentOrder = null;

        try {
            mInputScanner = new Scanner(new File("Inputs"));


            while(mInputScanner.hasNext())
            {
                String mLine = mInputScanner.nextLine();

                if(mLine.trim().contains(NEW_LINE)) {
                    mCurrentOrder = new Order(mCurrentOrderValues);

                    System.out.println("Sales Taxes: " + mCurrentOrder.calculateTaxes());
                    System.out.println("Total: " + mCurrentOrder.calculateTotal());

                    mCurrentOrderValues = new ArrayList<TaxData>();
                }
                else {
                    SalesTaxData mData = (SalesTaxData) mProcessor.parseData(mLine);
                    mCurrentOrderValues.add(mData);
                    System.out.println(mData.getmQuantity() + " " + mData.getmItem() + " at " + mData.getmPrice());
                }
            }











        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }






    }
}
