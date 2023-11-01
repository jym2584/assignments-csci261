public class Item {
    private final int weight;
    private final int cost;
    private final int id;

    /**
     * Represents an item for the knapsack problem
     * @param weight
     * @param cost
     * @param id
     */
    public Item(int weight, int cost, int id) {
        this.weight = weight;
        this.cost = cost;
        this.id = id;
    }

    /**
     * Alternate constructor for problem #2
     * @param weight
     * @param cost
     */
    public Item(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
        this.id = 0;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Item(weight=%s, cost=%s, id=%s)", weight, cost, id);
    }
}
