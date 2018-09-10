import java.util.Scanner;
public class EvenOrOddChecker {

    public static void main(String[] args) {
        System.out.println("Enter the number: ");
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        if(input<30 && input>20){
            if(input%2!=0)
                System.out.println("Tom");
            else
                System.out.println("Jerry");
        }
    }
}