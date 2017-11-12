package io.tanners;

import io.tanners.RegisterSystem.ItemProcessing;
import io.tanners.RegisterSystem.Order;
import io.tanners.tax.TaxCalculator;
import io.tanners.tax.TaxData;
import io.tanners.tax.exception.ValueIsNegativeException;
import io.tanners.tax.sales.SalesTaxCalculator;
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
        Order mCurrentOrder = null;

        try {
            mInputScanner = new Scanner(new File("Inputs"));
            TaxCalculator<SalesTaxData> mCalculator = new SalesTaxCalculator();


            while(mInputScanner.hasNext())
            {
                String mLine = mInputScanner.nextLine();
//            System.out.println(mLine);

                if(mLine.length() <= 0) {
                    mCurrentOrder = new Order(mCurrentOrderValues);
                    System.out.println("Sales Taxes: " + mCurrentOrder.calculateTaxes());
                    System.out.println("Total: " + mCurrentOrder.calculateTotal());
                    mCurrentOrderValues = new ArrayList<TaxData>();
                }
                else {
                    SalesTaxData mData = (SalesTaxData) mProcessor.parseData(mLine);
                    mCurrentOrderValues.add(mData);
                    System.out.println(mData.getmQuantity() + " " + mData.getmItem() + " at " + mCalculator.calculateTotal(mData));
                }
            }

            mCurrentOrder = new Order(mCurrentOrderValues);
            System.out.println("Sales Taxes: " + mCurrentOrder.calculateTaxes());
            System.out.println("Total: " + mCurrentOrder.calculateTotal());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
        }


    }
}
