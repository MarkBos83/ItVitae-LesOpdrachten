import java.util.Scanner;

public class Palindroom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geef een String op: ");
        String input = scanner.nextLine();
        String output = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            output += input.charAt(i);
        }
        System.out.println(output);
        if (input.equalsIgnoreCase(output)) {
            System.out.println("Palindroom!");
        } else {
            System.out.println("Dit is geen palindroom.");
        }
    }
}
