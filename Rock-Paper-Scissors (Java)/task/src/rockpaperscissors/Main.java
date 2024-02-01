package rockpaperscissors;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static Random random = new Random();
    private static ScoreRepository scoreStorage;

    public static void main(String[] args) {
        File scoreFile = new File("rating.txt");
        Scanner scanner = new Scanner(System.in);

        ShapeOption userShape;
        ShapeOption computerShape;
        String userInput;
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        scoreStorage = new ScoreRepository(scoreFile, username);
        System.out.printf("Hello, %s%n", username);

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
                userShape = ShapeOption.valueOf(userInput.toUpperCase());
                computerShape = computeResponseShape(userShape);
                playGame(userShape, computerShape, scoreStorage, username);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Bye!");
        scanner.close();
    }

    private static void playGame(ShapeOption userShape,
                                 ShapeOption computerShape,
                                 ScoreRepository scoreStorage,
                                 String playerName) {
        int gameRes = userShape.playWith(computerShape);
        String computerStringShape = computerShape.toString().toLowerCase();
        if (gameRes == -1) {
            System.out.printf("Sorry, but the computer chose %s%n", computerStringShape);
        } else if (gameRes == 1) {
            System.out.printf("Well done. The computer chose %s and failed%n", computerStringShape);
            scoreStorage.putScore(playerName, score -> score += 100);
        } else {
            System.out.printf("There is a draw (%s)%n", computerStringShape);
            scoreStorage.putScore(playerName, score -> score += 50);
        }
    }

    private static ShapeOption computeResponseShape(ShapeOption userShape) {
        return ShapeOption.values()[random.nextInt(3)];
    }
}
