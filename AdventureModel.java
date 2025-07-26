import java.util.*;

public class AdventureModel {
    private final Map<String, Room> rooms = new HashMap<>();
    private Adventurer player;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        setupWorld();
        greet();
        gameLoop();
        System.out.println("Goodbye, Adventurer!");
    }

    private void setupWorld() {
        Room foyer = new Room("Foyer", "A small foyer with dusty floors. An old key glints on a table.");
        Room hall = new Room("Hall", "A long, dimly lit hall. You hear a faint dripping sound.");
        Room treasure = new Room("Treasure Room", "A glittering room filled with gold and gems.");

        foyer.addExit("north", hall);
        hall.addExit("south", foyer);
        hall.addExit("east", treasure);
        treasure.addExit("west", hall);

        foyer.addItem("key");
        treasure.addItem("coin");

        rooms.put("foyer", foyer);
        rooms.put("hall", hall);
        rooms.put("treasure", treasure);

        System.out.print("Enter your name, Adventurer: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Player";
        player = new Adventurer(name, foyer);
    }

    private void greet() {
        System.out.println("\nWelcome, " + player.getName() + "!");
        System.out.println("Type commands like: go north, look, take key, drop coin, exit");
        System.out.println("-------------------------------------------------------------");
        System.out.println(player.look());
    }

    private void gameLoop() {
        while (true) {
            System.out.print("\n> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String response = processCommand(line);
            if (response == null) break;
            System.out.println(response);
        }
    }

    public String processCommand(String input) {
        String[] parts = input.trim().toLowerCase().split("\\s+", 2);
        String verb = parts[0];
        String arg = parts.length > 1 ? parts[1] : "";

        switch (verb) {
            case "go":
                if (arg.isEmpty()) return "Go where?";
                return player.go(arg);
            case "look":
                return player.look();
            case "take":
                if (arg.isEmpty()) return "Take what?";
                return player.take(arg);
            case "drop":
                if (arg.isEmpty()) return "Drop what?";
                return player.drop(arg);
            case "exit":
            case "quit":
                return null;
            case "inventory":
            case "inv":
                return player.showInventory();
            case "help":
                return helpText();
            default:
                return "I don't understand that command. Type 'help' for options.";
        }
    }

    private String helpText() {
        return String.join("\n",
            "Available commands:",
            "  go <direction>   - Move to another room (e.g., 'go north')",
            "  look             - Describe the current room",
            "  take <item>      - Pick up an item",
            "  drop <item>      - Drop an item from your inventory",
            "  inventory / inv  - Show what you're carrying",
            "  exit / quit      - Leave the game"
        );
    }
}
