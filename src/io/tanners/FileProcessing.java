package io.tanners;

import io.tanners.tax.sales.SalesTaxDataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessing
{
    public static ArrayList<String> loadFileToList(String mFilePath)
    {
        Scanner mInputScanner = null;
        ArrayList<String> mResults = new ArrayList<String>();

        try {
            mInputScanner = new Scanner(new File(mFilePath));

            while(mInputScanner.hasNext())
                mResults.add(mInputScanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return mResults;
    }
}
