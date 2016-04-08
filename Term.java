package autoComplete;

import java.util.Comparator;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;

public class Term implements Comparable<Term>
{
    private String query;
    private double weight = 0;

    public Term(String query, double weight)
    {
        if(query == null)
        {
            throw new NullPointerException();
        }//end if
        else if(weight < 0)
        {
            throw new IllegalArgumentException();
        }//end else if
        else
        {//initalize values
            this.query = query;
            this.weight = weight;
        }//end else
    }//end constructor
    

    public static Comparator<Term> byPrefixOrder(int r)
    {
        if(r < 0)//check if the search term is valid
        {
            throw new IllegalArgumentException();
        }
        return new ByPrefixOrder(r);
    }

    public static Comparator<Term> byReverseWeightOrder()//method that returns a comparator object
    {
        return new ByReverseWeightOrder();
    }

    @Override
    public int compareTo(Term o)//lexiographic comparison
    {
        return this.query.compareTo(o.query);
    }

    public static class ByReverseWeightOrder implements Comparator<Term>
    {
        @Override
        public int compare(Term t, Term t1)
        {
            return (int) (t1.weight - t.weight);//comparison basedon weight values
        }
    }//end class

    public static class ByPrefixOrder implements Comparator<Term>
    {
        private int r;

        public ByPrefixOrder(int r)
        {
            this.r = r;
        }//end comparator

        @Override
        public int compare(Term t1, Term t2)
        {
            int r1 = t1.query.length() < r ? t1.query.length() : r;//set end of substring based on
            int r2 = t2.query.length() < r ? t2.query.length() : r;// if the stored value is longer
                                                                   //or the searched value is larger 
            
            return t1.query.substring(0, r1).compareTo(t2.query.substring(0, r2));//comparison and return
        }  
    }//end  ByPrefixOrder
    
    public String toString()
        {
            return this.weight + "\t" + this.query;
        }
}//end class Term
