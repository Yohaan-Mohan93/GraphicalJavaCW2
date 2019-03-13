/******************************************************************************/
/*!
\file   Dictionary.java
\author Yohaan Mohan
\par    email: mohan011\@mymail.sim.edu.sg
\par    Student Number: 160291137
\par    Course: C02220
\par    Coursework Assignment #2
\date   12/03/2018
\brief  
    This file contains the implementation of the following methods for the 
    coursework.
      
    Methods include:
    getInstance
    PermutationFactory
    createPermutation
    permute
    
  Hours spent on this assignment: 
  8 hours
*/
/******************************************************************************/
import java.util.*;

/*(1)The class implements the design pattern by having a private variable of PermutationFactory type and
 * with method that initializes that variable thus making it a singleton object.
 * (2)This class implements the singleton design pattern because this is a factory which produces output for
 * only a specific amount of time and is not excessively created each time the user needs permutations. 
 */
public class PermutationFactory {

    private static PermutationFactory instance;
    //provided by UOL
    public static PermutationFactory getInstance() {
        if (instance == null) {
            instance = new PermutationFactory();
        }
        return instance;
    }

    private Map<String, Permutation> cache;
    //provided by UOL
    private PermutationFactory() {
        cache = new HashMap<>();
    }
    //provided by UOL
    public Permutation createPermutation(String word) {
        word = word.toLowerCase();
        if (cache.containsKey(word)) {
            return cache.get(word);
        }
        List<String> permutations = new ArrayList<>(permute(word));

        //we don't consider a word to be a permutation of itself, so remove it if it's in the List
        if (permutations.contains(word)) {
            permutations.remove(word);
        }

        Permutation permutation = new Permutation(word, permutations);
        cache.put(word, permutation);

        return permutation;
    }
    
    //provided by UOL
    //modified from https://stackoverflow.com/q/4240080
    private TreeSet<String> permute(String s) {
        TreeSet<String> permutations = new TreeSet<>();
        if(s.length() == 1) {
            permutations.add(s);
        } else {
            for (int i=0; i < s.length(); i++) {
                TreeSet<String> temp = permute(s.substring(0, i)+s.substring(i+1));
                for (String string : temp) {
                    permutations.add(s.charAt(i)+string);
                }
            }
        }
        return permutations;
    }
}
