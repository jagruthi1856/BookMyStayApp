import java.util.Stack;
class InvalidCancellationException extends Exception {
    public InvalidCancellationException(String message) {
        super(message);
    }
}
public class BookMyStayApp{
    static String[] roomTypes = {"Single", "Double", "Suite"};
    static int[] availability = {5, 3, 2};
    static boolean[] booked = {false, false, false};
    static Stack<String> rollbackStack = new Stack<>();
    public static int findRoomIndex(String roomType) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equals(roomType)) {
                return i;
            }
        }
        return -1;
    }
    public static void bookRoom(String roomType) {
        int index = findRoomIndex(roomType);
        if (index == -1) {
            System.out.println("Invalid room type");
            return;
        }
        if (availability[index] <= 0) {
            System.out.println("No rooms available");
            return;
        }
        availability[index]--;
        booked[index] = true;
        rollbackStack.push(roomType);
        System.out.println("Booking successful for " + roomType);
    }
    public static void cancelBooking(String roomType) throws InvalidCancellationException {
        int index = findRoomIndex(roomType);
        if (index == -1) {
            throw new InvalidCancellationException("Invalid room type");
        }
        if (!booked[index]) {
            throw new InvalidCancellationException("No booking exists for " + roomType);
        }
        if (rollbackStack.isEmpty() || !rollbackStack.peek().equals(roomType)) {
            throw new InvalidCancellationException("Cancellation must follow latest booking (LIFO)");
        }
        rollbackStack.pop();
        availability[index]++;
        booked[index] = false;
        System.out.println("Cancellation successful for " + roomType);
    }
    public static void displayRooms() {
        System.out.println("\nCurrent Inventory:");
        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println(roomTypes[i] + " -> " + availability[i]);
        }
    }
    public static void main(String[] args) {
        displayRooms();
        System.out.println("\n--- Booking Rooms ---");
        bookRoom("Single");
        bookRoom("Double");
        displayRooms();
        try {
            System.out.println("\n--- Valid Cancellation ---");
            cancelBooking("Double");

        } catch (InvalidCancellationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            System.out.println("\n--- Invalid Cancellation (LIFO violation) ---");
            cancelBooking("Single");
        } catch (InvalidCancellationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            System.out.println("\n--- Invalid Cancellation (No booking) ---");
            cancelBooking("Suite");
        } catch (InvalidCancellationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        displayRooms();
    }
}