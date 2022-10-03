import java.util.Arrays;
import java.util.Scanner;

public class ArrayMaker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voer het aantal items in: ");
        int NUM_ITEMS = Math.abs(scanner.nextInt());
        int[] items = new int[NUM_ITEMS];
        System.out.print("Voer de waarde van alle items in (gescheiden door spatie): ");
        for (int i = 0; i < NUM_ITEMS; i++) {
            items[i] = scanner.nextInt();
        }
        System.out.println(Arrays.toString(items));

        for (int getallen : items) {
            for (int getal = 0; getal < Math.abs(getallen); getal++) {
                System.out.print("*");
            }
            System.out.print("(" + getallen + ")");
            System.out.println();
        }
    }
}