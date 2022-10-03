import java.util.InputMismatchException;
import java.util.Scanner;

public class EvenOneven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("geef een geheel positief getal: ");
        int getal = -1;
        do {
            try {
                getal = Math.abs(scanner.nextInt());
            } catch (InputMismatchException e) {
                System.out.print("geen geldig getal, probeer het opnieuw: ");
                scanner.next();
            }
        } while (getal <= 0);
        int even = 0;
        int oneven = 0;

        for (int i = 1; i <= getal; i++) {
            if (i % 2 == 0) {
                even += i;
            } else {
                oneven += i;
            }
        }
        System.out.println("som van oneven getallen tot en met " + getal + " is " + oneven);
        System.out.println("som van even getallen tot en met " + getal + " is " + even);
        System.out.println("verschil tussen 2 sommen is " + Math.abs(even - oneven));
    }
}

