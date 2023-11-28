package ch.bbw.zork;

public class Player {

    private String name;
    private int health;
    private int maxHealth;

    private Item[] inventory = new Item[10];
    private int maxWeight;
    private int currentWeight;

    public Player(String name) {
        this.name = name;
        this.health = 5;
        this.maxHealth = 5;
        this.maxWeight = 100;
        this.currentWeight = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addInventory(Item item) {
        if (currentWeight + item.getWeight() <= maxWeight) {
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] == null) {
                    inventory[i] = item;
                    currentWeight += item.getWeight();
                    break;
                }
            }
        }
    }

    public void removeInventory(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == item) {
                inventory[i] = null;
                currentWeight -= item.getWeight();
                break;
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Item[] getInventory() {
        return inventory;
    }

}
