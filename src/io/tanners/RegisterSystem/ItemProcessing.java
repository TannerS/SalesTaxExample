package io.tanners.RegisterSystem;

import io.tanners.tax.TaxData;
import io.tanners.tax.exception.ValueIsNegativeException;
import io.tanners.tax.sales.SalesTax;
import io.tanners.tax.sales.SalesTaxData;
import io.tanners.tax.exception.ArrayEmptyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Pre-processing the data
 */
public class ItemProcessing extends Processing
{
    // hashmap to hold the tax value along with list of items to look for
    private HashMap<SalesTax, ArrayList<String>> mTaxableItems;
    // key word used to find improted workds
    private final String IMPORT_KEY_WORD = "imported";

    /**
     * constructor
     */
    public ItemProcessing() {
        // init hashmap
        mTaxableItems = new HashMap<SalesTax, ArrayList<String>>();
        // read in files and the matching tax bracket
        mTaxableItems.put(SalesTax.FOOD, loadItemsIntoList("FoodItems"));
        mTaxableItems.put(SalesTax.BOOKS, loadItemsIntoList("BookItems"));
        mTaxableItems.put(SalesTax.MEDICAL, loadItemsIntoList("MedicalItems"));
        mTaxableItems.put(SalesTax.OTHER, loadItemsIntoList("OtherItems"));
    }

    /**
     * load a line of a file (a string) to a arraylist
     * @param mFilePath
     * @return
     */
    public ArrayList<String> loadItemsIntoList(String mFilePath)
    {
        // scanner object to read in file
        Scanner mInputScanner = null;
        // arraylist to hold results
        ArrayList<String> mResults = new ArrayList<String>();

        try {
            // load in scanner object with file
            mInputScanner = new Scanner(new File(mFilePath));
            // loop file while there is data to loop
            while(mInputScanner.hasNext())
                // save a line to the results list
                mResults.add(mInputScanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        // return list of results
        return mResults;
    }

    /**
     * parse a line of data and create an object with all the information about the item to be used later
     * @param mInput
     * @return
     */
    @Override
    public TaxData parseData(String mInput)
    {
        SalesTaxData mData = new SalesTaxData();
        // trim line of data, just in case
        mInput = mInput.trim();
        // split by spaces, as per input guidlines
        String[] mParsedResults = mInput.split(" ");
        // get quantity of item
        try {
            mData.setmQuantity(parseIntFromStr(mParsedResults[0]));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return null;
        }
        // set item but concatenating the string in format of 'amount {product name here } at price'
        // where the { } is what is returned
        String mItem = null;
        try {
            mItem = concatenateString(mParsedResults, 1,mParsedResults.length-2);
        } catch (ArrayEmptyException e) {
            e.printStackTrace();
            return null;
        }
        // set the item
        mData.setmItem(mItem);
        //check if item is imported
        if(checkImported(mItem))
            mData.setisImported(true);
        // loop possible words to determine the tax type
        for (SalesTax key : mTaxableItems.keySet()) {
            // if line of data has word that shows which tax info it may be
            if (matchesCategoryWord(mItem, mTaxableItems.get(key))) {
                // since this example, the only thing that can add on to an import tax
                // is the base 10%, so here we work with that
                // check if item is imported
                if(mData.isImported())
                    mData.setmTaxPercentage(getImportedType(key));
                else
                    mData.setmTaxPercentage(key);
                // end loop
                break;
            }
        }
        try {
            // set price of item
            mData.setmPrice(new BigDecimal(Double.parseDouble(mParsedResults[mParsedResults.length-1])));
        } catch (ValueIsNegativeException e) {
            e.printStackTrace();
            return null;
        }
        // return parsed data
        return mData;
    }


    /**
     * This including the added enum values were for a work around
     * where modifying the enum value to add the import tax value
     * modified it for all enums of this type vs that one single copy.
     * @param mTax
     * @return
     */
    private SalesTax getImportedType(SalesTax mTax) {
        switch (mTax) {
            case BOOKS:
                return SalesTax.BOOKS_IMPORTED;
            case FOOD:
                return SalesTax.FOOD_IMPORTED;
            case MEDICAL:
                return SalesTax.MEDICAL_IMPORTED;
            case OTHER:
                return SalesTax.OTHER_IMPORTED;
            default:
                return null;
        }
    }

    /**
     * Checks if line of data contains a possible keyword to tell what kind of tax to apply
     * @param mInput
     * @param mCategoriesWords
     * @return
     */
    private boolean matchesCategoryWord(String mInput, ArrayList<String> mCategoriesWords)
    {
        // trim and format line to common format
        String newInput = mInput.trim().toLowerCase();
        // loop words of keywords which can help determine the tax
        for(String mCurrentWOrd : mCategoriesWords)
        {
            // if the data line contains that key word
            if(mCurrentWOrd.toLowerCase().contains(newInput))
                // return true
                return true;
        }
        // return false
        return false;
    }

    /**
     * convert string to int
     * @param mNumber
     * @return
     */
    private int parseIntFromStr(String mNumber)
    {
        try{
            // convert string to integer
            return Integer.parseInt(mNumber);
        }
        catch(NumberFormatException ex)
        {
            ex.printStackTrace();
            // safe default return for error
            return -1;
        }
    }

    /**
     * per request format, second value after quantity should be the word imported, if imported
     * @param mInput
     * @return
     */
    private boolean checkImported(String mInput)
    {
        // convert to common format
        // check if matches keyword to check for imported product
        return mInput.toLowerCase().trim().contains(IMPORT_KEY_WORD);
    }

    /**
     * concatenate a array of string values
     * this is used to combine the parts of the string of data that consist of the item's name
     * @param mInput
     * @param mStart
     * @param mEnd
     * @return
     * @throws ArrayEmptyException
     */
    private String concatenateString(String[] mInput, int mStart, int mEnd) throws ArrayEmptyException {
        // check for null
        if(mInput == null)
            // throw exception
            throw new NullPointerException();
            // check for invalid index size
        else if(mInput.length <= 0)
            // throw exception
            throw new ArrayEmptyException(ArrayEmptyException.EMPTY);
            // array is valid
        else {
            // I learned StringBuilder is more efficient and faster than string += string,
            // so I use it each time I have too
            StringBuilder mBuilder = new StringBuilder();
            // loop parts of array from start to finish
            for (int i = mStart; i < mEnd; i++) {
                // append words with space
                mBuilder.append(mInput[i]);
                mBuilder.append(" ");
            }
            // return built string
            return mBuilder.toString();
        }
    }

}
