import java.util.Scanner;

public class Primes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        boolean isPrime = true;
        for (int i = 2; i <= input; i++) {
            for (int j = 2; i > j; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(i);
            }
            isPrime = true;
        }

        scanner.close();
    }
}
