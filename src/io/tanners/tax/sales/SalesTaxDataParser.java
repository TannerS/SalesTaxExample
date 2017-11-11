package io.tanners.tax.sales;

import java.util.ArrayList;
import java.util.HashMap;

public class SalesTaxDataParser
{
    private final String IMPORT_KEY_WORD = "imported";

    private HashMap<SalesTax, ArrayList<String>> mTaxWordsList;

    public SalesTaxDataParser(HashMap<SalesTax, ArrayList<String>> mTaxWordsList)
    {
        this.mTaxWordsList = mTaxWordsList;
    }

    public SalesTaxData parseFile(String mInput)
    {
        SalesTaxData mData = new SalesTaxData();

        try {
            mInput = mInput.trim();

            String[] mParsedResults = mInput.split(" ");

            mData.setmQuantity(parseIntFromStr(mParsedResults[0]));

            if(checkImported(mParsedResults[1]))
                mData.setmIsImported(true);
            // set item but concatenating the string in format of 'amount {product name here } at price'
            // where the { } is what is returned
            String mItem = concatenateString(mParsedResults, 1,mParsedResults.length-3);

            mData.setmItem(mItem);

            for (SalesTax key : mTaxWordsList.keySet()) {
                //iterate over key
                if (matchesCategoryWord(mItem, mTaxWordsList.get(key))) {
                    mData.setmTaxPercentage(key);
                    break;
                }
            }

            mData.setmPrice(Double.parseDouble(mParsedResults[mParsedResults.length-1]));


        } catch (ArrayEmptyException e) {
            e.printStackTrace();
            return null;
        }

        return mData;
    }

    private boolean matchesCategoryWord(String mInput, ArrayList<String> mCategoriesWords)
    {
        String newIput = mInput.trim().toLowerCase();

        for(String mCurrentWOrd : mCategoriesWords)
        {
            if(newIput.contains(mCurrentWOrd.toLowerCase()))
                return true;
        }

        return false;
    }


    private int parseIntFromStr(String mNumber)
    {
        try {

            return Integer.parseInt(mNumber);
        }
        catch(NumberFormatException ex)
        {
            return -1;
        }
    }

    private boolean checkImported(String mInput)
    {
        return mInput.toLowerCase().trim().equals(IMPORT_KEY_WORD);
    }

    private String concatenateString(String[] mInput, int mStart, int mEnd) throws ArrayEmptyException {
        if(mInput == null)
            throw new NullPointerException();
        else if(mInput.length <= 0)
            throw new ArrayEmptyException(ArrayEmptyException.EMPTY);
        else {
            // I learned StringBuilder is more efficient and faster than string += string,
            // so I use it each time I have too
            StringBuilder mBuilder = new StringBuilder();

            for (int i = mStart; i < mEnd; i++) {
                mBuilder.append(mInput[i]);
                mBuilder.append(" ");
            }

            return mBuilder.toString();
        }
    }
}
