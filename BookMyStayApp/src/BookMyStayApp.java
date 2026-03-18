import java.util.*;

public class BookMyStayApp {

    static class Reservation {
        private String guestName;
        private String roomType;
        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }
    static class RoomInventory {
        private Map<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single", 2);
            inventory.put("Double", 1);
            inventory.put("Deluxe", 1);
        }
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
        public void decrementRoom(String roomType) {
            inventory.put(roomType, getAvailability(roomType) - 1);
        }
        public void displayInventory() {
            System.out.println("\nCurrent Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        public void addRequest(Reservation r) {
            queue.offer(r);
        }

        public Reservation getNextRequest() {
            return queue.poll(); // removes from queue (FIFO)
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
    static class BookingService {
        private RoomInventory inventory;
        private Set<String> allocatedRoomIds = new HashSet<>();
        private Map<String, Set<String>> roomAllocations = new HashMap<>();
        public BookingService(RoomInventory inventory) {
            this.inventory = inventory;
        }
        public void processBookings(BookingRequestQueue requestQueue) {
            while (!requestQueue.isEmpty()) {
                Reservation request = requestQueue.getNextRequest();
                String roomType = request.getRoomType();
                String guestName = request.getGuestName();
                System.out.println("\nProcessing request: " + guestName + " -> " + roomType);
                if (inventory.getAvailability(roomType) > 0) {
                    String roomId = generateRoomId(roomType);
                    while (allocatedRoomIds.contains(roomId)) {
                        roomId = generateRoomId(roomType);
                    }
                    allocatedRoomIds.add(roomId);
                    roomAllocations
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);
                    inventory.decrementRoom(roomType);
                    System.out.println("Booking Confirmed!");
                    System.out.println("Guest: " + guestName);
                    System.out.println("Room Type: " + roomType);
                    System.out.println("Allocated Room ID: " + roomId);

                } else {
                    System.out.println("Booking Failed! No rooms available for " + roomType);
                }
            }
        }
        private String generateRoomId(String roomType) {
            return roomType.substring(0, 1).toUpperCase() + (100 + new Random().nextInt(900));
        }
        public void displayAllocations() {
            System.out.println("\nRoom Allocations:");
            for (Map.Entry<String, Set<String>> entry : roomAllocations.entrySet()) {
                System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
            }
        }
    }
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue requestQueue = new BookingRequestQueue();
        BookingService bookingService = new BookingService(inventory);
        requestQueue.addRequest(new Reservation("Alice", "Single"));
        requestQueue.addRequest(new Reservation("Bob", "Single"));
        requestQueue.addRequest(new Reservation("Charlie", "Single")); // Should fail
        requestQueue.addRequest(new Reservation("David", "Deluxe"));
        bookingService.processBookings(requestQueue);
        bookingService.displayAllocations();

        inventory.displayInventory();
    }
}