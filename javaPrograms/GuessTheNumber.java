import java.util.Scanner;
public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println("Guess the number: ");
            input=scanner.nextInt();
            if(input>=0 && input<=100){         //check if input is between 0 and 100
                if(input>21)
                    System.out.println("Number guessed is more than original number");
                else if (input<21)
                    System.out.println("Number guessed is less than original number");
                else
                    System.out.println("Number guessed matches the original number");
            }
            else{
                System.out.println("Enter a number between 0 and 100");
            }
        }
        while(input!=21);           // 21 is the original number that user has to guess.
    }
}