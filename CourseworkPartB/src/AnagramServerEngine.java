/******************************************************************************/
/*!
\file   AnagramServerEngine.java
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
       AnagramServerEngine
       getAvailableCommands
       parseCommand
       show
       anagram
       permutation
       formatWords
       main
    
  Hours spent on this assignment: 
  8 hours
*/
/******************************************************************************/
import java.util.*;

public class AnagramServerEngine {
    private AnagramFactory anagramFactory;
    private PermutationFactory permutationFactory;
    //provided by UOL
    public AnagramServerEngine() {
        anagramFactory = AnagramFactory.getInstance();
        permutationFactory = PermutationFactory.getInstance();
    }
    //provided by UOL
    public String getAvailableCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%n", "ANAGRAM <word>  show all anagrams of the word <word>"));
        sb.append(String.format("%s%n", "PERMUTE <word>  show all permutations of the word <word>"));
        sb.append(String.format("%s%n", "SHOW HELP            show this help"));
        return sb.toString();
    }
    //provided by UOL
    public String parseCommand(String command) {
        //each command has an instruction and an argument. split the incoming string on the first whitespace character (or whitespace characterS if they are contiguous)
        String[] words = command.split("\\s+", 2);
        if (words.length < 2) {
            return "Syntax: <command> <argument>.";
        }

        //make both strings lower case and trim any excess whitespace to make comparisons easier
        String instruction = words[0].toLowerCase().trim();
        String argument = words[1].toLowerCase().trim();

        switch (instruction) {
            case "show":
                return show(argument);
            case "anagram":
                return anagram(argument);
            case "permute":
                return permutation(argument);
            default: //everything that isn't a known command
                return "I don't understand '" + instruction + "'.";
        }
    }
    //provided by UOL
    private String show(String command) {
        switch (command.toLowerCase()) {
            case "help":		return getAvailableCommands();

            default: 			return "I don't know how to show that!";
        }
    }
    //provided by UOL
    private String anagram(String word) {
    	long startTime = System.currentTimeMillis();
    	Anagram a = anagramFactory.createAnagram(word);
        String anagrams = formatWords(a.getAnagrams());
        long endTime = System.currentTimeMillis();
        
        System.out.println("Execution Time:" + (endTime - startTime));
        return String.format("Anagrams of %s: %s%n", word, anagrams);
    }
    //provided by UOL
    private String permutation(String word) {
    	Permutation p = permutationFactory.createPermutation(word);
        String permutations = formatWords(p.getPermutations());
        
        return String.format("Permutations of %s: %s%n", word, permutations);
    }
    
    private String formatWords(List<String> words) {
        StringBuffer output = new StringBuffer();
        for (String s : words) {
            output.append("/n");
        	output.append(s);
            output.append(" ");
        	
        }

        if (output.length() == 0) {
            output.append("<none found>");
        }

        return output.toString();
    }
    //provided by UOL
    public static void main(String[] args) {
        new AnagramServer();
    }


}

