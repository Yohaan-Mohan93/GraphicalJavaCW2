import java.util.List;

public class Permutation {
    private String word;
    private List<String> permutations;

    public Permutation(String word, List<String> permutations) {
        this.word = word;
        this.permutations = permutations;
    }

    public String getWord() {
        return word;
    }

    public List<String> getPermutations() {
        return permutations;
    }
}
