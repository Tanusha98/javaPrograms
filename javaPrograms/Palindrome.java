import java.util.Scanner;
public class Palindrome {

    public static void main(String[] args) {
        System.out.println("Enter the number: ");
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long b = n;
        long i,r;
        for ( i = 0, r = 0; b > 0; b = b / 10) {
            r *= 10;
            r += b % 10;
            if ((b % 10) % 2 == 0) {
                i += b % 10;
            }
        }
        if (n == r) {
            System.out.println(n + " is a palindrome and the sum of even numbers is ");
            if (i > 25)
                System.out.print("greater than 25.");
            else
                System.out.print("lesser than 25.");
        } else
            System.out.println(n + " is not a palindrome.");

    }
}