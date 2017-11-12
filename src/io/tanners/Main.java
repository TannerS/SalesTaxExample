package io.tanners;

import io.tanners.RegisterSystem.ItemProcessing;
import io.tanners.tax.sales.SalesTax;
import io.tanners.tax.sales.SalesTaxData;
import io.tanners.tax.sales.SalesTaxDataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ItemProcessing mProcessor = new ItemProcessing();
        Scanner mInputScanner = null;

        try {
            mInputScanner = new Scanner(new File("Inputs"));


            while(mInputScanner.hasNext())
            {
                String mLine = mInputScanner.nextLine();
                System.out.println(mLine);
                SalesTaxData mData = (SalesTaxData) mProcessor.parseData(mLine);





            }











        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }






    }
}
