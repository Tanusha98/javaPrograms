import java.util.*;
public class Sort {

    public static void main(String[] args) {
        System.out.println("Enter the string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int sumOfEvenDigits=0;
        Integer a[]=new Integer[input.length()];
        for(int increment=0;increment<input.length();increment++){
            a[increment]=input.charAt(increment)-48;
            if(a[increment]%2==0){
                sumOfEvenDigits+=a[increment];
            }
        }
        Arrays.sort(a,Collections.reverseOrder());
        System.out.print("Sorted number in non-increasing order :");
        for(int increment=0;i<input.length();increment++){
            System.out.print(a[increment]);
        }
        System.out.println("\n Sum of even numbers : " +sumOfEvenDigits);
        if(k>15)
            System.out.println("True");
        else
            System.out.println("False");
    }
}