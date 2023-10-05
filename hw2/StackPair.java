import java.io.IOException;
import java.util.Arrays;

public class StackPair {
    private Pair[] array;
    private int size;

    public StackPair(int n) {
        this.array = new Pair[n];
        this.size = 0;
    }

    public void push(Pair pair) {
        this.array[this.size] = pair;
        this.size++;
    }

    public Pair pop() {
        Pair toReturn = this.array[this.size - 1];
        this.array[this.size - 1] = null;
        this.size--;
        return toReturn;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    public Pair peek() {
        return this.array[this.size - 1];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws IOException {
        Pair[] pairs = TestCases.generatePairs("input1.txt");
        StackPair stack = new StackPair(pairs.length);
        stack.push(pairs[0]);
        stack.push(pairs[1]);
        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
