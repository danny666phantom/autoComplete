package autoComplete;

import java.util.Arrays;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
/*Authors Michael Burt and
          Daniel Lorenzo  
*/

public final class Autocomplete
{
    private final int length;
    private final Term[] terms;

    // Initialize and sort terms.
    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms)
    {
        this.length = terms.length;
        this.terms = new Term[length];

        // Defensive copy to make it immutable
        System.arraycopy(terms, 0, this.terms, 0, length);

        Quick.sort(this.terms);//sort array lexiographicly

    }// end constructor

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix)
    {
        //find first instance of searched term
        int first1 = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));
        //find second instance of searched term
        int last1 = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0),
                Term.byPrefixOrder(prefix.length()));

        int quantity = numberOfMatches(prefix);//find length of sub array
        Term[] temp1 = new Term[quantity];// create small array to hold results

        if (first1 != -1 && last1 != -1)
        {
            for (int k = 0; k < quantity; k++)
            {
                temp1[k] = terms[first1 + k];//results array gets values copied from
            }//end for                               //"Autocomplete" immutable array
        }//end if

        if (first1 < 0)// no results found
        {
            temp1[0] = new Term("No results found", 0);
        }//end if
        
        //order results based on weighted order
        Arrays.sort(temp1, Term.byReverseWeightOrder());

        return temp1;
    }//end allMatches()

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix)
    {
        Term searchTerm = new Term(prefix, 0);
        int first1 = BinarySearchDeluxe.firstIndexOf(terms, searchTerm,Term.byPrefixOrder(prefix.length()));
        
        int last1 = BinarySearchDeluxe.lastIndexOf(terms, searchTerm,Term.byPrefixOrder(prefix.length()));
        return last1 - first1 + 1;
    }

    public static void main(String[] args)
    {

        //Read in the terms from a file.
        String filename = "//cities.txt";
        
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++)
        {
            Double weight = in.readDouble(); // Read the next weight.
            in.readChar(); // Scan past the tab.
            String query = in.readString(); // Read the next query.
            terms[i] = new Term(query, weight); // Construct the term.
        }

        // Read in queries from standard input and print out the top k matching terms.
        int k = 10;
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine())
        {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
            {
                StdOut.println("results" + results[i]);
            }
        }
    }

}
