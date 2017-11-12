package io.tanners;

import io.tanners.RegisterSystem.ItemProcessing;
import io.tanners.RegisterSystem.Order;
import io.tanners.RegisterSystem.Printout;
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
        Printout mPrint = null;

        try {
            mInputScanner = new Scanner(new File("Inputs"));

            while(mInputScanner.hasNext())
            {
                String mLine = mInputScanner.nextLine();

                if(mLine.length() <= 0) {
                    mCurrentOrder = new Order(mCurrentOrderValues);
                    mPrint = new Printout(mCurrentOrder);
                    System.out.println(mPrint.displayReceipt());
                    mCurrentOrderValues = new ArrayList<TaxData>();
                }
                else {
                    SalesTaxData mData = (SalesTaxData) mProcessor.parseData(mLine);
                    mCurrentOrderValues.add(mData);
                }
            }

            mCurrentOrder = new Order(mCurrentOrderValues);
            mPrint = new Printout(mCurrentOrder);
            System.out.println(mPrint.displayReceipt());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
