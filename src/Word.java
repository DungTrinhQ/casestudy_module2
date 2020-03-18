public class Word {
    public String word;
    public String mean;
    public Word next;

    public Word(String word, String mean) {
        this.word = word;
        this.mean = mean;
        next = null;
    }

    public void concatMean(String concatMean) {
        String mean = String
                .format("%s, %s", this.mean, concatMean);
        this.mean = mean;
    }

    public String toString() {
        return String.format("%s: %s", word, mean);
    }

}