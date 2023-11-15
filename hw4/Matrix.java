public class Matrix {
    private String identifier;
    private int value;
    private int valueOther;

    public Matrix (String identifier, int value, int valueOther) {
        this.identifier = identifier;
        this.value = value;
        this.valueOther = valueOther;
    }


    public String getIdentifier() {
        return identifier;
    }

    public int getValue() {
        return value;
    }

    public int getValueOther() {
        return valueOther;
    }

    @Override
    public String toString() {
        return identifier;
    }
}