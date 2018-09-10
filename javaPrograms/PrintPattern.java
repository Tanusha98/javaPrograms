import java.util.Scanner;
public class PrintPattern {

    public static void main(String[] args) {
        System.out.println("Enter the number: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int increment=1;
        while(increment<= input){
            for(int loopIncrement=0;loopIncrement<increment;loopIncrement++){
                System.out.print(increment+" ");
            }
            increment++;
        }
        System.out.println();
    }
}