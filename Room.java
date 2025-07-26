import java.util.*;

public class Room {
    private final String name;
    private final String description;
    private final Map<String, Room> exits = new HashMap<>();
    private final List<String> items = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addExit(String direction, Room room) {
        exits.put(direction.toLowerCase(), room);
    }

    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    public void addItem(String item) {
        items.add(item.toLowerCase());
    }

    public boolean hasItem(String item) {
        return items.contains(item.toLowerCase());
    }

    public void removeItem(String item) {
        items.remove(item.toLowerCase());
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
