import java.util.Scanner;

public class Vowel
{
    public static void main(String args[])
    {
        String inputString;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a string : ");
        inputString = scan.next();
        inputString=inputString.toLowerCase();
        char stringElement;
        for(int increment=0;increment<inputString.length();increment++) {
            stringElement=inputString.charAt(increment);
            if ((int)stringElement<97 || (int)stringElement>122)
                System.out.println("Error: "+stringElement+" is not a letter");
            else if (stringElement == 'a' || stringElement == 'e' || stringElement == 'i' || stringElement == 'o' || stringElement == 'u') {
                System.out.println(stringElement + " - Vowel ");
            } else {
                System.out.println(stringElement + " - Consonant ");
            }

        }
    }
}