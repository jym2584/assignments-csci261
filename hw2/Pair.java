public class Pair {
    private final int x;
    private final int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair other = (Pair)o;
            return x == other.getX() && y == other.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}
