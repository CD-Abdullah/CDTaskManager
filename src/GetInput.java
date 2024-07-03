import java.util.Scanner;

public class GetInput {
    public static int getint(){
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input. Please enter a numeric Val: ");
        }
        int number = scanner.nextInt();

        return number;
    }

    public static String getString(){ // No input validation needed as such for string but this verifies null isnt accepted
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (input.trim().isEmpty()) {
            System.out.print("Invalid input. Please enter a non-empty string: ");
            input = scanner.nextLine();
        }
        return input;
    }
}
