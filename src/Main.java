import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();

        Scanner input = new Scanner(System.in);
        String command = new String();
        String[] commands = new String[3];

        dict.loadDictionaryFromFile("C:\\CodeGym\\Dictionary\\Dict.txt");
        dict.use();
        do {
            System.out.print("> ");
            command = input.nextLine();
            commands = command.split(" ", 3);
            switch (commands[0]) {
                case "add":
                    dict.add(commands[1], commands[2]);
                    break;
                case "translate":
                    dict.tranSlate(commands[1]);
                    break;
                case "delete":
                    dict.delete(commands[1]);
                    break;
                case "save":
                    dict.save("C:\\CodeGym\\Dictionary\\Dict.txt");
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Error!");
            }
        } while (commands[0].equals("quit") == false);

        input.close();
    }

}