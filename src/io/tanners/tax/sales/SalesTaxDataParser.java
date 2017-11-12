//package io.tanners.tax.sales;
//
//import io.tanners.tax.exception.ArrayEmptyException;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class SalesTaxDataParser
//{
//    private final String IMPORT_KEY_WORD = "imported";
//
//    private HashMap<SalesTax, ArrayList<String>> mTaxWordsList;
//
//    /**
//     * Constructor, takes in hashmap of valus to be searched for to help with category check
//     * @param mTaxWordsList
//     */
//    public SalesTaxDataParser(HashMap<SalesTax, ArrayList<String>> mTaxWordsList)
//    {
//        this.mTaxWordsList = mTaxWordsList;
//    }
//
//    /**
//     * Checks if line of data contains a possible keyword to tell what kind of tax to apply
//     * @param mInput
//     * @param mCategoriesWords
//     * @return
//     */
//    private boolean matchesCategoryWord(String mInput, ArrayList<String> mCategoriesWords)
//    {
//        // trim and format line to common format
//        String newIput = mInput.trim().toLowerCase();
//        // loop words of keywords which can help determine the tax
//        for(String mCurrentWOrd : mCategoriesWords)
//        {
//            // if the data line contains that key word
//            if(newIput.contains(mCurrentWOrd.toLowerCase()))
//                // return true
//                return true;
//        }
//        // return false
//        return false;
//    }
//
//    /**
//     * convert string to int
//     * @param mNumber
//     * @return
//     */
//    private int parseIntFromStr(String mNumber)
//    {
//        try{
//            // convert string to integer
//            return Integer.parseInt(mNumber);
//        }
//        catch(NumberFormatException ex)
//        {
//            // safe default return for error
//            return -1;
//        }
//    }
//
//    /**
//     * per request format, second value after quantity should be the word imported, if imported
//     * @param mInput
//     * @return
//     */
//    private boolean checkImported(String mInput)
//    {
//        // convert to common format
//        // check if matches keyword to check for imported product
//        return mInput.toLowerCase().trim().contains(IMPORT_KEY_WORD);
//    }
//
//    /**
//     * concatenate a array of string values
//     * this is used to combine the parts of the string of data that consist of the item's name
//     * @param mInput
//     * @param mStart
//     * @param mEnd
//     * @return
//     * @throws ArrayEmptyException
//     */
//    private String concatenateString(String[] mInput, int mStart, int mEnd) throws ArrayEmptyException {
//        // check for null
//        if(mInput == null)
//            // throw exception
//            throw new NullPointerException();
//        // check for invalid index size
//        else if(mInput.length <= 0)
//            // throw exception
//            throw new ArrayEmptyException(ArrayEmptyException.EMPTY);
//        // array is valid
//        else {
//            // I learned StringBuilder is more efficient and faster than string += string,
//            // so I use it each time I have too
//            StringBuilder mBuilder = new StringBuilder();
//            // loop parts of array from start to finish
//            for (int i = mStart; i < mEnd; i++) {
//                // append words with space
//                mBuilder.append(mInput[i]);
//                mBuilder.append(" ");
//            }
//            // return built string
//            return mBuilder.toString();
//        }
//    }
//}
