package lv.ctco.guessnum;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) {
        do {
            System.out.println("What's your name? ");
            String name = scanner.next();
            System.out.println("Let's play a little game, " + name + ". Guess My Number?");
            int randomNumber = random.nextInt(100) + 1;
            boolean isBingo = false;

            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= 10; i++) {
                int enteredNumber = getEnteredNumber();
                if (randomNumber == enteredNumber) {
                    System.out.println(i + "). Bingo! Number is " + enteredNumber);
                    GameResult gameResult = new GameResult();
                    gameResult.name = name;
                    gameResult.triesCount = i;
                    gameResult.duration = System.currentTimeMillis() - startTime;
                    results.add(gameResult);
                    isBingo = true;
                    break;
                } else if (randomNumber > enteredNumber) {
                    System.out.println(i + "). Sorry, My number was: greater, but yoi said " + enteredNumber);
                } else {
                    System.out.println(i + "). Sorry, My number was: less but yoi said " + enteredNumber);
                }
            }
            if (!isBingo){
                System.out.println(" ");
                System.out.println("HAHAHAHAA! My number was: " + randomNumber);
            }
            System.out.println("Would you like to continue? Enter 'y' to continue");
        } while ("y".equals(scanner.next()));
        for(GameResult result : results) {
            System.out.println(result.name + " \t " + result.triesCount + " \t " + result.duration / 1000 + "s");
        }
        for(GameResult result : results) {
            System.out.printf("%s %d %.2f sec\n",
                                result.name,
                                result.triesCount,
                                result.duration / 1000.0);
        }

    }

    private static int getEnteredNumber() {
        while(true) {
            try {
                int enteredNumber = scanner.nextInt();
                if (enteredNumber <= 0|| enteredNumber > 100) {
                    System.out.println("Error! Number is not in allowed range 1..100");
                    continue;
                }
                return enteredNumber;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("You are cheater fo " + e);
            }
        }

    }
}
