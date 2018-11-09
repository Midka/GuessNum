package lv.ctco.guessnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Guess My Number?");
        int randomNumber = random.nextInt(100) + 1;
        int enteredNumber;
        for (int i = 1; i <= 10; i++) {
            try {
                enteredNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You are cheater fo " + e);
                return;
            }
            if (randomNumber == enteredNumber) {
                System.out.println(i + "). Bingo! Number is " + enteredNumber);
                return;
            } else if (randomNumber > enteredNumber) {
                System.out.println(i + "). Sorry, My number was: greater, but yoi said " + enteredNumber);
            } else {
                System.out.println(i + "). Sorry, My number was: less but yoi said " + enteredNumber);
            }
        }
        System.out.println("Sorry, My number was: " + randomNumber);

    }
}
