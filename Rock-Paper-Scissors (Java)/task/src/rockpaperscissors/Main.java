package rockpaperscissors;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static Random random = new Random();
    private static ScoreRepository scoreStorage;

    public static void main(String[] args) {
        File scoreFile = new File("rating.txt");
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.print("Enter your name: ");
        String username = scanner.nextLine();

        scoreStorage = new ScoreRepository(scoreFile, username);
        System.out.printf("Hello, %s%n", username);

        userInput = scanner.nextLine();
        String[] options = null;

        if (!userInput.isEmpty()) {
            options = userInput.split(",");
        }

        System.out.println("Okay, let's start");

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("!exit")) {
                break;
            }
            if (userInput.equals("!rating")) {
                System.out.printf("Your rating: %d%n", scoreStorage.getScore(username));
                continue;
            }
            try {
                playGame(options, userInput, username);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Bye!");
        scanner.close();
    }

    private static void playGame(String[] options,
                                 String userShapeString,
                                 String playerName) {
        int gameRes;
        String computerStringShape;
        if (options == null) {
            ShapeOption userShape = ShapeOption.valueOf(userShapeString.toUpperCase());
            ShapeOption computerShape = computeResponseShape(userShape);
            gameRes = userShape.playWith(computerShape);
            computerStringShape = computerShape.toString().toLowerCase();
        } else {
            int computerShapeIdx = new Random().nextInt(options.length);
            computerStringShape = options[computerShapeIdx];
            if (userShapeString.equals(computerStringShape)) {
                gameRes = 0;
            } else {
                gameRes = 1;
                int optionsHalfLength = options.length / 2;
                int userOptionIdx = findIndexOf(options, userShapeString);
                int computerOptionIdx = findIndexOf(options, computerStringShape);

                int userDefeaterBoundary = (userOptionIdx + optionsHalfLength + 1) % options.length;
                if (userOptionIdx < userDefeaterBoundary) {
                    if (userOptionIdx < computerOptionIdx && computerOptionIdx < userDefeaterBoundary) {
                        gameRes = -1;
                    }
                } else {
                    if (userOptionIdx < computerOptionIdx || computerOptionIdx < userDefeaterBoundary) {
                        gameRes = -1;
                    }
                }
            }
        }

        handleResult(gameRes, computerStringShape, playerName);
    }

    private static int findIndexOf(String[] options, String option) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(option)) return i;
        }
        return -1;
    }

    private static void handleResult(int gameRes, String computerStringShape, String playerName) {
        if (gameRes == -1) {
            System.out.printf("Sorry, but the computer chose %s%n", computerStringShape);
        } else if (gameRes == 1) {
            System.out.printf("Well done. The computer chose %s and failed%n", computerStringShape);
            scoreStorage.putScore(playerName, score -> score + 100);
        } else {
            System.out.printf("There is a draw (%s)%n", computerStringShape);
            scoreStorage.putScore(playerName, score -> score + 50);
        }
    }

    private static ShapeOption computeResponseShape(ShapeOption userShape) {
        return ShapeOption.values()[random.nextInt(3)];
    }
}
