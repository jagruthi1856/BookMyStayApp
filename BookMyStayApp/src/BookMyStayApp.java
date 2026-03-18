import java.util.HashMap;
import java.util.Map;
public class BookMyStayApp {
    static class RoomInventory {
        private Map<String, Integer> inventory;
        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single", 10);
            inventory.put("Double", 5);
            inventory.put("Deluxe", 3);
        }
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
        public void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }
        public void displayInventory() {
            System.out.println("Current Room Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
        System.out.println("\nChecking availability for Double room:");
        System.out.println("Available: " + inventory.getAvailability("Double"));
        System.out.println("\nUpdating Double room availability to 4...");
        inventory.updateAvailability("Double", 4);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}