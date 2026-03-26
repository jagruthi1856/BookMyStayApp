class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class BookMyStayApp {
    static String[] roomTypes = {"Single", "Double", "Suite"};
    static int[] availability = {5, 3, 2};
    public static int findRoomIndex(String roomType) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equals(roomType)) {
                return i;
            }
        }
        return -1; // not found
    }
    public static void bookRoom(String roomType, int quantity) throws InvalidBookingException {
        int index = findRoomIndex(roomType);
        if (index == -1) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
        if (quantity <= 0) {
            throw new InvalidBookingException("Quantity must be greater than 0");
        }
        if (quantity > availability[index]) {
            throw new InvalidBookingException("Not enough rooms available");
        }
        if (availability[index] - quantity < 0) {
            throw new InvalidBookingException("Inventory cannot go negative");
        }
        availability[index] -= quantity;
        System.out.println("Booking successful! Remaining " + roomType + ": " + availability[index]);
    }
    public static void displayRooms() {
        System.out.println("\nCurrent Inventory:");
        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println(roomTypes[i] + " -> " + availability[i]);
        }
    }

    public static void main(String[] args) {
        displayRooms();
        try {
            System.out.println("\nValid Booking:");
            bookRoom("Single", 2);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            System.out.println("\nInvalid Room Type:");
            bookRoom("Deluxe", 1);

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            System.out.println("\nInvalid Quantity:");
            bookRoom("Double", 0);

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            System.out.println("\nOverbooking:");
            bookRoom("Suite", 5);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        displayRooms();
    }
}