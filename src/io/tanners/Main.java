package io.tanners;

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
        HashMap<SalesTax, ArrayList<String>> mTaxWordsList = new HashMap<SalesTax, ArrayList<String>>();

        /*
            For each category of tax, (in this case, food, books, medical), we
            load each category into a hashmap that contains a list of words
            that relate to that category, this is so we can parse a line in the file
            and know which tax to apply by finding key words.
            In other implementations, if we knew a head of time what exactly would
            be said we can filter out these small cases. Or even go as far as using
            natural language processing. In my implementation, I assume the words loaded
            in cover the basis of what will be read in and in real life, using this, would
            be thousands of words, so an easier way would be to load in each type of tax
            based on category by order (for example, load all books first, then medical,
            then food, then other, however I am trying to stick to the way the input is
            on the prompt. So doing it this way without optimization, would be slow
        */

        mTaxWordsList.put(SalesTax.FOOD, FileProcessing.loadFileToList("FoodWords"));
        mTaxWordsList.put(SalesTax.BOOKS, FileProcessing.loadFileToList("Books"));
        mTaxWordsList.put(SalesTax.MEDICAL, FileProcessing.loadFileToList("Medical"));

        Scanner mInputScanner = null;
        try {
            mInputScanner = new Scanner(new File("Inputs"));

            SalesTaxDataParser mDataParser = new SalesTaxDataParser(mTaxWordsList);

            while(mInputScanner.hasNext())
            {
                String mLine = mInputScanner.nextLine();
                System.out.println(mLine);
                //SalesTaxData mData = mDataParser.parseFile(mLine);

                //mDat




            }











        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }






    }
}
