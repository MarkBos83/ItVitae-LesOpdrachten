import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LetterDiamand {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("voer een letter in: ");
        char[] chars = scanner.nextLine().toUpperCase().toCharArray();
        printToList(chars[0]);

    }

    static void printToList(char a) {
        List<String> list = new ArrayList<>();
        int b = a - 65;
        list.add(" ".repeat(b) + (char) 65 + " ".repeat(b));
        for (int i = 2; i <= b; i++) {
            list.add(" ".repeat(b - i + 1) + (char) (64 + i) + " ".repeat(i * 2 - 3) + (char) (64 + i) + " ".repeat(b - i + 1));
        }
        list.add(a + " ".repeat(b * 2 - 1) + a);
        for (int j = b; j >= 2; j--) {
            list.add(" ".repeat(b - j + 1) + (char) (64 + j) + " ".repeat(j * 2 - 3) + (char) (64 + j) + " ".repeat(b - j + 1));
        }
        list.add(" ".repeat(b) + (char) 65 + " ".repeat(b));
        for (String g : list) {
            System.out.println(g);
        }
    }
}

