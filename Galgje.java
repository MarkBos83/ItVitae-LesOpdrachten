import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Galgje {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geef een galgjewoord: ");
        String woord = scanner.nextLine();
        int levens = 10;
        List<Character> chars = new ArrayList<>();
        List<Character> gegokteLetters = new ArrayList<>();
        List<Character> streepjes = new ArrayList<>();

        for (char ch : woord.toCharArray()) {
            chars.add(ch);
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < woord.length(); i++) {
            streepjes.add('_');
        }
        for (char streep : streepjes) {
            System.out.print(streep);
        }
        System.out.println();
        do {
            System.out.println("Raad een letter of het woord: ");
            String raad = scanner.nextLine();
            if(raad.equals(woord)){
                System.out.println("U WIN");
                break;
            }
            if(raad.length()==1) {
                List<Character> geradenLetter = new ArrayList<>();
                for (char ch : raad.toCharArray()) {
                    geradenLetter.add(ch);
                }
                if (!gegokteLetters.contains(geradenLetter.get(0))) {
                    for (int j = 0; j < woord.length(); j++) {
                        if (geradenLetter.get(0).equals(chars.get(j))) {
                            levens += 1;
                            break;
                        }
                    }
                    for (int j = 0; j < woord.length(); j++) {
                        if (geradenLetter.get(0).equals(chars.get(j))) {
                            streepjes.set(j, geradenLetter.get(0));
                        }
                    }
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    for (char streep : streepjes) {
                        System.out.print(streep);
                    }
                    if (streepjes.equals(chars)) {
                        System.out.println("\nJE HEBT GEWONNEN!");
                        break;
                    } else {
                        levens -= 1;
                        System.out.println("\n\nLevens over: " + (levens));
                    }
                    gegokteLetters.add(geradenLetter.get(0));
                    System.out.println("Geraden letters: " + gegokteLetters);
                } else {
                    System.out.println("Deze letter heb je al gegokt.");
                }
            } else {
                System.out.println("Niet het goede woord.");
                levens -= 1;
                System.out.println("Levens over: " + (levens));
            }
        } while (levens != 0);
        if (levens == 0) {
            System.out.println("JE HEBT VERLOREN.");
            System.out.println("Het woord was: " + woord);
        }
    }
}