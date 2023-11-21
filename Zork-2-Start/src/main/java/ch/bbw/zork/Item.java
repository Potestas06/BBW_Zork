package ch.bbw.zork;

public class Item {
    private String description;
    private int weight;

    private boolean isKey = false;

    public Item(String description, int weight) {
        this.description = description;
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }
}
