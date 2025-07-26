import java.util.*;

public class Adventurer {
    private final String name;
    private Room currentRoom;
    private final List<String> inventory = new ArrayList<>();

    public Adventurer(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }

    public String getName() {
        return name;
    }

    public String go(String direction) {
        Room next = currentRoom.getExit(direction);
        if (next == null) {
            return "You can't go " + direction + " from here.";
        }
        currentRoom = next;
        return look();
    }

    public String look() {
        StringBuilder sb = new StringBuilder();
        sb.append("== ").append(currentRoom.getName()).append(" ==\n");
        sb.append(currentRoom.getDescription()).append("\n");

        if (!currentRoom.getItems().isEmpty()) {
            sb.append("Items here: ").append(String.join(", ", currentRoom.getItems())).append("\n");
        }

        if (!currentRoom.getExits().isEmpty()) {
            sb.append("Exits: ").append(String.join(", ", currentRoom.getExits().keySet())).append("\n");
        }
        return sb.toString();
    }

    public String take(String item) {
        if (!currentRoom.hasItem(item)) {
            return "There is no '" + item + "' here.";
        }
        currentRoom.removeItem(item);
        inventory.add(item);
        return "You take the " + item + ".";
    }

    public String drop(String item) {
        if (!inventory.contains(item)) {
            return "You don't have a '" + item + "'.";
        }
        inventory.remove(item);
        currentRoom.addItem(item);
        return "You drop the " + item + ".";
    }

    public String showInventory() {
        if (inventory.isEmpty()) return "Your inventory is empty.";
        return "You are carrying: " + String.join(", ", inventory);
    }
}
