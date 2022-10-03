import java.util.Scanner;

public class Tafelmaker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Tafelmaker: Geef de grootte aan: ");
        int grootte = input.nextInt();

        for (int i = 0; i < grootte + 1; i++) {
            if (i == 0) {
                System.out.print("*| ");
                for (int k = 1; k < grootte + 1; k++) {
                    System.out.print(k + " ");
                }
                System.out.println();
                for (int l = 0; l < grootte + 1; l++) {
                    System.out.print("---");
                }
            } else {
                System.out.print(i + "| ");
            }
            for (int j = 1; j < grootte + 1; j++) {
                if (i != 0) {
                    System.out.print((i * j) + " ");
                }
            }
            System.out.println();
        }
    }
}