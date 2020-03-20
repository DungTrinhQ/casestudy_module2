import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dictionary {
    private Node head;

    public Dictionary() {
        head = null;
    }

    public void loadDict(String fileName) {
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
                Node node = new Node(word, mean);
                if (head == null) {
                    head = node;
                } else {
                    Node currentNode = head;
                    while (currentNode.next != null) {
                        currentNode = currentNode.next;
                    }
                    currentNode.next = node;
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
        Node newNode = new Node(word, mean);
        if (head == null) {
            head = newNode;
            return;
        }
        if (head.word.compareTo(word) > 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.word.equals(word)) {
                if (currentNode.mean.indexOf(mean) < 0)
                    currentNode.concatMean(mean);
                else
                    System.out.println("Từ và nghĩa đã tồn tại!");
                return;
            }
            if (currentNode.next.word.compareTo(word) > 0) {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                return;
            }
            currentNode = currentNode.next;
        }
        if (currentNode.word.equals(word)) {
            if (currentNode.mean.indexOf(mean) < 0)
                currentNode.concatMean(mean);
            else
                System.out.println("Từ và nghĩa đã tồn tại!");
            return;
        }
        currentNode.next = newNode;
        return;
    }

    public void tranSlate(String word) {
        if (head == null) {
            System.out.println("Từ điển trống!");
            return;
        }
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.word.equals(word)) {
                System.out.println(currentNode.mean);
                return;
            }
            currentNode = currentNode.next;
        }
        System.out.println("Không tìm thấy!");
        return;
    }

    public void delete(String word) {
        if (head == null) {
            System.out.println("Từ điển trống!");
            return;
        }
        if (head.word.equals(word)) {
            if (head.next == null) {
                head = null;
            } else {
                head = head.next;
            }
            System.out.println(word + " đã xóa!");
            return;
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.next.word.equals(word)) {
                currentNode.next = currentNode.next.next;
                System.out.println(word + " đã xóa!");
                return;
            }
            currentNode = currentNode.next;
        }
        if (currentNode.word.equals(word)) {
            currentNode = null;
            System.out.println(word + " đã xóa!");
            return;
        }
        System.out.println("Không tìm thấy!");
        return;
    }

    public void save(String fileName) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            if (head == null) {
                System.out.println("Từ điển trống!");
                return;
            }
            Node currentNode = head;
            while (currentNode.next != null) {
                bufferedWriter.write(currentNode.toString() + "\n");
                currentNode = currentNode.next;
            }
            bufferedWriter.write(currentNode.toString());
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
        System.out.println("THUẬT NGỮ TIẾNG ANH CHUYÊN NGÀNH CNTT");
        System.out.println("=====================================");
        System.out.println("----------------MENU-----------------");
        System.out.println("1. Dịch:   translate_<word>");
        System.out.println("2. Thêm từ:    add_<word>_<mean>");
        System.out.println("3. Xóa từ:    delete_<word>");
        System.out.println("4. Thoát:    quit");
    }

}