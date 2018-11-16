package lv.ctco.guessnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static List<GameResult> results = new ArrayList<>();
    private static final File RESULTS_FILE = new File("results.txt");

    public static void main(String[] args) {
        loadResults();
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
                    GameResult gameResult = new GameResult(name, i, System.currentTimeMillis() - startTime);
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
            System.out.println("Would you like to continue? Enter 'y' to continue, 'n' to end game.");
        } while (isContinueGame());

        showResults();
        saveResults();

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

    private static boolean isContinueGame() {
        while (true) {
            String scan = scanner.next();
            if ("y".equals(scan)) {
                return true;
            }
            if ("n".equals(scan)) {
                return false;
            }
            System.out.println("not valid input, y/n only");
        }
    }

    private static void writeResult(PrintWriter fileOut, int skipCount) {
        //results.stream().filter(r -> )
        results.stream().skip(skipCount).forEach(r -> fileOut.printf("%s \t %d \t %d\n",
                r.name,
                r.triesCount,
                r.duration));
/*        for(GameResult result : results) {
            if (skipCount <= 0) {
                fileOut.printf("%s \t %d \t %d\n",
                        result.name,
                        result.triesCount,
                        result.duration);
            }
            skipCount--;
        }*/
    }

    private static void showResults() {
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                        .<GameResult>thenComparingLong(r -> r.duration))
                .limit(3)
                .forEach(r -> System.out.printf("%s \t %d \t %.2f\n",
                        r.name,
                        r.triesCount,
                        r.duration / 1000.0));

    }

    private static void saveResults() {
        try (PrintWriter fileOut = new PrintWriter(RESULTS_FILE)) {
            int skipCount = results.size() - 5;
            writeResult(fileOut, skipCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadResults() {
        try (Scanner in = new Scanner(RESULTS_FILE)) {
            while (in.hasNext()) {
                GameResult gr = new GameResult();
                gr.name = in.next();
                gr.triesCount = in.nextInt();
                gr.duration = in.nextLong();
                results.add(gr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
