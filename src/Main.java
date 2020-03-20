import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();

        Scanner input = new Scanner(System.in);
        String command = new String();
        String[] commands = new String[3];

        dict.loadDict("C:\\CodeGym\\Dictionary\\Dict.txt");
        dict.use();
        do {
            System.out.println("Nhập câu lệnh theo định dạng trên các lựa chọn: ");
            System.out.print("> ");
            command = input.nextLine();
            commands = command.split("_", 3);

            switch (commands[0]) {
                case "translate":
                    dict.tranSlate(commands[1]);
                    break;
                case "add":
                    dict.add(commands[1], commands[2]);
                    dict.save("C:\\CodeGym\\Dictionary\\Dict.txt");
                    break;
                case "delete":
                    dict.delete(commands[1]);
                    dict.save("C:\\CodeGym\\Dictionary\\Dict.txt");
                    break;
                case "quit":
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình");
                    break;
                default:
                    System.out.println("Lỗi!");
            }
        } while (commands[0].equals("quit") == false);
        input.close();
    }
}