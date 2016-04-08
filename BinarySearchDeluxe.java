package autoComplete;

import java.util.Comparator;

public class BinarySearchDeluxe
{

    // Return the index of the first key that equals the search key, return-1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)
    {
        if (a.length <= 0)//check for invalid length
        {
            return -1;
        }
        int min = 0;
        int max = a.length - 1;
        int middle;
        while (min <= max)
        {

            middle = (max - min) / 2 + min;
            if (comparator.compare(key, a[middle]) > 0)//passed key is greater than key in
            {                                          //the middle position
                min = middle + 1;
            }
            else if (comparator.compare(key, a[middle]) < 0)//passed key is less than key in
            {                                               //the middle position
                max = middle - 1;
            } 
            else                                       //passed key is equal to the key in
            {                                          //the middle position
                
                if (middle == 0)//check for the first position of the array
                {
                    return middle;
                } 
                else if (comparator.compare(key, a[middle - 1]) > 0)//check if position a[middle--] is also
                                                                       //greater than the passed key
                {
                    return middle;
                } 
                else    //the previous position is also equal or greater than the passed key
                {
                    max = middle - 1;//set max to middle-- and go through the checks again
                }//end inner if else loop
            }//end outer if else loop
        }//end while

        return -1;
    }//end firstIndexOf()

    // Return the index of the last key that equals the search key, return -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key,
            Comparator<Key> comparator)
    {
        if (a.length <= 0)//check for invalid length
        {
            return -1;
        }
        int length = a.length - 1;
        int min = 0;
        int max = length;
        int middle;
        while (min <= max)
        {
            middle = (max - min) / 2 + min;
            if (comparator.compare(key, a[middle]) > 0)//passed key is greater than key in
            {                                          //the middle position 
                min = middle + 1;
            }
            else if (comparator.compare(key, a[middle]) < 0)//passed key is less than key in
            {                                               //the middle position
                max = middle - 1;
            } 
            else        //passed key is equal to the key in
            {          //the middle position
                
                if (middle == length) //is this the last pos in the array?                  
                {                                       
                    return middle;
                }
                else if (comparator.compare(key, a[middle + 1]) < 0)//is the pos next position greater than the key?
                {
                    return middle;
                } 
                else                        //the next position is also equal or less than
                {                            
                    min = middle + 1;  //set min for another run through to check the next higher position
                }//end inner if else loop
            }//end outer if else loop
        }//end while
        return -1;
    }//end lastIndexOf()
}//end class