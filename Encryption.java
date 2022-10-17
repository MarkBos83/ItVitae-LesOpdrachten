import java.util.Scanner;

public class Encryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("voer een leesbare tekenreeks in: ");
        String input = scanner.nextLine().toUpperCase();
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char a = input.charAt(i);
            if (a != 32) {
                a += 3;
            }
            if (a > 90) {
                a = (char) (a - 26);
            }
            output += a;
        }
        System.out.println(output);
    }
}
