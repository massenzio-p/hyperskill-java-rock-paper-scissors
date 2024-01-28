package rockpaperscissors;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShapeOption userShape = ShapeOption.valueOf(scanner.nextLine().toUpperCase());
        ShapeOption computerShape = computeResponseShape(userShape);
        System.out.printf("Sorry, but the computer chose %s%n", computerShape.toString().toLowerCase());
        scanner.close();
    }

    private static ShapeOption computeResponseShape(ShapeOption userShape) {
        return switch (userShape) {
            case ROCK -> ShapeOption.PAPER;
            case PAPER -> ShapeOption.SCISSORS;
            default -> ShapeOption.ROCK;
        };
    }
}
