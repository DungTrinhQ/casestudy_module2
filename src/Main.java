import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        dict.loadDict("C:\\CodeGym\\Dictionary\\Dict.txt");
        dict.use();
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.out.print("> ");
            System.out.println("Nhập số để chọn yêu cầu:");
            choice = input.next();
            switch (choice) {
                case "1":
                    System.out.println("Nhập từ cần dịch: ");
                    String word1 = input.next();
                    dict.tranSlate(word1);
                    dict.save("C:\\CodeGym\\Dictionary\\Dict.txt");
                    break;
                case "2":
                    System.out.println("Nhập từ cần thêm: ");
                    String word2 = input.next();
                    System.out.println("Nhập nghĩa của từ cần: ");
                    String mean = input.next();
                    dict.add(word2, mean);
                    dict.save("C:\\CodeGym\\Dictionary\\Dict.txt");
                    break;
                case "3":
                    System.out.println("Nhập từ muốn xóa: ");
                    String word3 = input.next();
                    dict.delete(word3);
                    dict.save("C:\\CodeGym\\Dictionary\\Dict.txt");
                    break;
                case "4":
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình");
                    break;
                default:
                    System.out.println("Error!");
            }
        } while (choice.equals("4") == false);

        input.close();
    }

}