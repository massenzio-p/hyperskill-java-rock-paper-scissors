package rockpaperscissors;

public enum ShapeOption {
    ROCK(2, 1),
    PAPER(0, 2),
    SCISSORS(1, 0);

    private int prevOrdinal;
    private int nextOrdinal;

    ShapeOption(int prevOrdinal, int nextOrdinal) {
        this.prevOrdinal = prevOrdinal;
        this.nextOrdinal = nextOrdinal;
    }

    // 1 - wins; 0 - draw; -1 - loses
    public int playWith(ShapeOption shape) {
        if (shape.ordinal() == prevOrdinal) return 1;
        if (shape.ordinal() == nextOrdinal) return -1;
        return 0;
    }
}
