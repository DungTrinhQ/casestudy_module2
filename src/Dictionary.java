import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dictionary {
    private Word head;

    public Dictionary() {
        head = null;
    }

    public void loadDictionaryFromFile(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            String word;
            String mean;
            String currentLine = null;
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((currentLine = bufferedReader.readLine()) != null) {
                int index = currentLine.indexOf(":");
                word = currentLine.substring(0, index);
                mean = currentLine.substring(index + 2,
                        currentLine.length());
                Word node = new Word(word, mean);
                if (head == null) {
                    head = node;
                } else {
                    Word currentWord = head;
                    while (currentWord.next != null) {
                        currentWord = currentWord.next;
                    }
                    currentWord.next = node;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return;
    }

    public void add(String word, String mean) {
        Word newWord = new Word(word, mean);
        if (head == null) {
            head = newWord;
            return;
        }
        if (head.word.compareTo(word) > 0) {
            newWord.next = head;
            head = newWord;
            return;
        }
        Word currentWord = head;
        while (currentWord.next != null) {
            if (currentWord.word.equals(word)) {
                if (currentWord.mean.indexOf(mean) < 0)
                    currentWord.concatMean(mean);
                else
                    System.out.println("Word and meanings already exists!");
                return;
            }
            if (currentWord.next.word.compareTo(word) > 0) {
                newWord.next = currentWord.next;
                currentWord.next = newWord;
                return;
            }
            currentWord = currentWord.next;
        }
        if (currentWord.word.equals(word)) {
            if (currentWord.mean.indexOf(mean) < 0)
                currentWord.concatMean(mean);
            else
                System.out.println("Word and meanings already exists!");
            return;
        }
        currentWord.next = newWord;
        return;
    }

    public void tranSlate(String word) {
        if (head == null) {
            System.out.println("Dictionary is empty!");
            return;
        }
        Word currentWord = head;
        while (currentWord != null) {
            if (currentWord.word.equals(word)) {
                System.out.println(currentWord.mean);
                return;
            }
            currentWord = currentWord.next;
        }
        System.out.println("Not found!");
        return;
    }

    public void delete(String word) {
        if (head == null) {
            System.out.println("Dictionary is empty!");
            return;
        }
        if (head.word.equals(word)) {
            if (head.next == null) {
                head = null;
            } else {
                head = head.next;
            }
            System.out.println(word + " deleted!");
            return;
        }
        Word currentWord = head;
        while (currentWord.next != null) {
            if (currentWord.next.word.equals(word)) {
                currentWord.next = currentWord.next.next;
                System.out.println(word + " deleted!");
                return;
            }
            currentWord = currentWord.next;
        }
        if (currentWord.word.equals(word)) {
            currentWord = null;
            System.out.println(word + " deleted!");
            return;
        }
        System.out.println("Not found!");
        return;
    }

    public void save(String fileName) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            if (head == null) {
                System.out.println("Dictionary is empty!");
                return;
            }
            Word currentWord = head;
            while (currentWord.next != null) {
                bufferedWriter.write(currentWord.toString() + "\n");
                currentWord = currentWord.next;
            }
            bufferedWriter.write(currentWord.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return;
    }

    public void use() {
        System.out.println("> add <word>: <mean>");
        System.out.println("> translate <word>");
        System.out.println("> delete <word>");
        System.out.println("> save");
        System.out.println("> quit");
    }

}