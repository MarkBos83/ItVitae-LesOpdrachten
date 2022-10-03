import java.util.InputMismatchException;
import java.util.Scanner;

public class Lucasgetallen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geef een natuurlijk getal: ");
        int n = -1;
        int lucasgetal1 = 2;
        int lucasgetal2 = 1;
        String lucasgetallen = "";

        do {
            try {
                n = scanner.nextInt();
                if (n <= 0) {
                    System.out.print("Dit is geen geldige invoer, probeer het opnieuw: ");
                } else if (n > 45) {
                    System.out.println("Dit getal is te groot, probeer het opnieuw: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Dit is geen geldige invoer, probeer het opnieuw: ");
                scanner.next();
            }
        } while (n <= 0 || n > 45);

        if (n == 1) {
            lucasgetallen += "2";
        } else {
            lucasgetallen += "2 1 ";
        }

        for (int i = 0; i < n - 2; i++) {
            int temp = lucasgetal2 + lucasgetal1;
            lucasgetal1 = lucasgetal2;
            lucasgetal2 = temp;
            lucasgetallen += lucasgetal2 + " ";
        }
        if (lucasgetal1 > 0 && lucasgetal2 > 0) {
            System.out.println(lucasgetallen);
        } else {
            System.out.println("Dit getal is te groot.");
        }
    }
}
