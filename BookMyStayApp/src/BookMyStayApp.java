import java.util.HashMap;
import java.util.Map;
    static class Room {
        private String type;
        private double price;
        private String amenities;
        public Room(String type, double price, String amenities) {
            this.type = type;
            this.price = price;
            this.amenities = amenities;
        }
        public String getType() {
            return type;
        }
        public double getPrice() {
            return price;
        }

        public String getAmenities() {
            return amenities;
        }
    }
    static class RoomInventory {
        private Map<String, Integer> inventory;
        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single", 10);
            inventory.put("Double", 0);   // Example: unavailable
            inventory.put("Deluxe", 3);
        }
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public Map<String, Integer> getAllInventory() {
            return inventory;
        }
    }
    static class SearchService {

        private RoomInventory inventory;
        private Map<String, Room> roomCatalog;

        public SearchService(RoomInventory inventory) {
            this.inventory = inventory;
            this.roomCatalog = new HashMap<>();

            // Initialize room details
            roomCatalog.put("Single", new Room("Single", 1000, "Bed, WiFi"));
            roomCatalog.put("Double", new Room("Double", 1800, "Double Bed, WiFi, TV"));
            roomCatalog.put("Deluxe", new Room("Deluxe", 3000, "King Bed, WiFi, TV, AC"));
        }
        public void searchAvailableRooms() {
            System.out.println("Available Rooms:\n");

            for (String roomType : roomCatalog.keySet()) {
                int available = inventory.getAvailability(roomType);
                if (available > 0) {
                    Room room = roomCatalog.get(roomType);

                    System.out.println("Room Type: " + room.getType());
                    System.out.println("Price: ₹" + room.getPrice());
                    System.out.println("Amenities: " + room.getAmenities());
                    System.out.println("Available: " + available);
                    System.out.println("-----------------------------");
                }
            }
        }
    }
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        SearchService searchService = new SearchService(inventory);
        searchService.searchAvailableRooms();
        System.out.println("Search completed. Inventory remains unchanged.");
    }