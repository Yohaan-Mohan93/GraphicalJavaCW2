import java.util.List;

public class Anagram {
    private String word;
    private List<String> anagrams;

    public Anagram(String word, List<String> anagrams) {
          this.word = word;
          this.anagrams = anagrams;
    }

    public String getWord() {
        return word;
    }

    public List<String> getAnagrams() {
        return anagrams;
    }
}
