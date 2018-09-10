import java.util.Scanner;
public class RepeatLastNCharacters {

    public static void main(String[] args) {
        System.out.println("Enter the string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("Enter the number: ");
        int InputN = scanner.nextInt();
        String LastNCharacters = input.substring(input.length() - InputN);
        while (InputN > 0) {
            input += LastNCharacters;
            InputN--;
        }
        System.out.println(input);
    }
}