import java.util.Scanner;
public class ReverseString {

    public static void main(String[] args) {
        System.out.println("Enter the string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String inputStringReverse="";
        for(int i=input.length()-1;i>=0;i--){
            inputStringReverse+=input.charAt(i);
        }
        System.out.println(inputStringReverse);
    }
}