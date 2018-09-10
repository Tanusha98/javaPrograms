import java.util.Scanner;
public class DisplayInputType {

    public static void main(String[] args) {
        System.out.println("Enter the character: ");
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);      //Input
        int inputAsciiValue=input;                  //Input ascii value
        if (47<inputAsciiValue && inputAsciiValue<58) {
            System.out.println("Number");
        }
        else if (64<inputAsciiValue && inputAsciiValue<91) {
            System.out.println("Capital letter");
        }
        else if (96<inputAsciiValue && inputAsciiValue<123) {
            System.out.println("Small letter");
        }
        else {
            System.out.println("Special character");
        }
    }
}