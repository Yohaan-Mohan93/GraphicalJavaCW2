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
    Dictionary
    contains
    
  Hours spent on this assignment: 
  8 hours
*/
/******************************************************************************/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//Task B Question 1
/*(1)The class implements the design pattern by having a private variable of Dictionary type and
 * with method that initializes that variable thus making it a singleton object.
 * (2)This class implements the singleton design pattern because there should only one Dictionary
 * which means that will be no data conflicts as there is only one dictionary to reference.
 */

//Task B Question 3
/*
 * The new data structure HashSet is an improvement because it is a sorted when it is initialized
 * and also it removes all duplicates. 
 * To show the difference, the original time taken for the anagrams of Manchester is 869.632s(14.49 mins)
 * and the new data structure takes 11.121 seconds which is 98.72% reduction in computation time.   
 */
public class Dictionary {
    private static Dictionary instance;
    //provided by UOL
    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }
    private Set<String> words;
    //provided by UOL
    private Dictionary() {
        try {
        	words = new HashSet<String>(Files.readAllLines(Paths.get("words.small")));
        	
        } catch (IOException e) {
            System.err.println("dictionary file not found!");
            words = new HashSet<>(); //empty dictionary
        }
    }
    //provided by UOL    
    public boolean contains(String word) {
        return words.contains(word);
    }
}
