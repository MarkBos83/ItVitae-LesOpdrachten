import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(fibonacci(input.nextInt()));
    }

    static int fibonacci(int n) {
        return n == 0 ? 0 : ((n == 1) ? 1 : fibonacci(n - 1) + fibonacci(n - 2));
    }
}
