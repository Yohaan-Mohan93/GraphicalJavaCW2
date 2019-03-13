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
    AnagramFactory
    createAnagram
    filter
    
  Hours spent on this assignment: 
  8 hours
*/
/******************************************************************************/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*(1)The class implements the design pattern by having a private variable of AnagramFactory type and
 * with method that initializes that variable thus making it a singleton object.
 * (2)This class implements the singleton design pattern because this is a factory which produces output for
 * only a specific amount of time and is not excessively created each time the user needs permutations. 
 */
public class AnagramFactory {

    private static AnagramFactory instance;
    //provided by UOL
    public static AnagramFactory getInstance() {
        if (instance == null) {
            instance = new AnagramFactory();
        }
        return instance;
    }

    private Map<String, Anagram> cache;
    private Dictionary dictionary;
    private PermutationFactory permutationFactory;
    
    //provided by UOL
    private AnagramFactory() {
        cache = new HashMap<>();
        dictionary = Dictionary.getInstance();
        permutationFactory = PermutationFactory.getInstance();
    }
    //provided by UOL
    public Anagram createAnagram(String word) {
        word = word.toLowerCase();
        if (cache.containsKey(word)) {
            return cache.get(word);
        }

        Permutation p = permutationFactory.createPermutation(word);
        List<String> anagrams = filter(p.getPermutations());

        Anagram anagram = new Anagram(word, anagrams);
        cache.put(word, anagram);
        return anagram;
    }
    //provided by UOL
    private List<String> filter(List<String> permutations) {
        //take out any permutations that aren't in the dictionary
       List<String> anagrams = new ArrayList<>();
       for (String s : permutations) {
           if (dictionary.contains(s)) {
               anagrams.add(s);
           }
       }
       return anagrams;
    }
}
