import java.util.Scanner;

public class Staafdiagram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer een zin in: ");
        String zin = input.nextLine();

        System.out.println("aantal karakters: " + zin.length());
        System.out.println("aantal woorden: " + aantalWoorden(zin));
        System.out.println("aantal klinkers: " + aantalKlinkers(zin));
        System.out.println("palindroom? " + palindroom(zin));
        staafdiagram(zin);
    }

    static int aantalWoorden(String zin) {
        String[] woorden = zin.split(" ");
        return woorden.length;
    }

    static int aantalKlinkers(String zin) {
        int num = 0;
        char[] klinkers = zin.toCharArray();
        for (char k : klinkers) {
            if (k == 'a' || k == 'e' || k == 'i' || k == 'o' || k == 'u' || k == 'y') {
                num++;
            }
        }
        return num;
    }

    static boolean palindroom(String zin) {
        zin = zin.toLowerCase();
        char[] palindroom = zin.toCharArray();
        String omgedraaid = "";

        for (int i = zin.length() - 1; i >= 0; i--) {
            omgedraaid += palindroom[i];
        }
        return omgedraaid.equals(zin);
    }

    static void staafdiagram(String zin) {
        zin = zin.toLowerCase();
        char[] staafdiagram = zin.toCharArray();
        int amount = 0;
        System.out.println();

        for (int i = 0; i < 26; i++) {
            System.out.print((char) (i + 97) + " ");
            for (int j = 0; j < zin.length(); j++) {
                if (staafdiagram[j] == (char) (i + 97)) {
                    amount += 1;
                }
            }
            System.out.println("*".repeat(amount)); // + "(" + amount + ")" kan erbij voor nummers
            amount = 0;
        }
        System.out.println();
    }
}
