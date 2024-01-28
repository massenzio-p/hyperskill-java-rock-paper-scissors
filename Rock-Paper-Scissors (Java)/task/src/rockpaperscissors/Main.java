package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShapeOption userShape = ShapeOption.valueOf(scanner.nextLine().toUpperCase());
        ShapeOption computerShape = computeResponseShape(userShape);
        printWinner(userShape, computerShape);
        scanner.close();
    }

    private static void printWinner(ShapeOption userShape, ShapeOption computerShape) {
        int gameRes = userShape.playWith(computerShape);
        String computerStringShape = computerShape.toString().toLowerCase();
        if (gameRes == -1) {
            System.out.printf("Sorry, but the computer chose %s%n", computerStringShape);
        } else if (gameRes == 1) {
            System.out.printf("Well done. The computer chose %s and failed%n", computerStringShape);
        } else {
            System.out.printf("There is a draw (%s)\n%n", computerStringShape);
        }
    }

    private static ShapeOption computeResponseShape(ShapeOption userShape) {
        return ShapeOption.values()[random.nextInt(3)];
    }
}
