import java.io.*;
class HotelData implements Serializable {
    String[] roomTypes;
    int[] availability;
    public HotelData(String[] roomTypes, int[] availability) {
        this.roomTypes = roomTypes;
        this.availability = availability;
    }
}
public class BookMyStayApp {
    static String[] roomTypes = {"Single", "Double", "Suite"};
    static int[] availability = {5, 3, 2};
    static final String FILE_NAME = "hotel_data.ser";
    public static void saveData() {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            HotelData data = new HotelData(roomTypes, availability);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    public static void loadData() {
        try {
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HotelData data = (HotelData) in.readObject();
            roomTypes = data.roomTypes;
            availability = data.availability;
            in.close();
            fileIn.close();
            System.out.println("Data loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting with default values.");
        }
    }
    public static void bookRoom(String roomType, int quantity) {
        int index = findRoomIndex(roomType);
        if (index == -1) {
            System.out.println("Invalid room type");
            return;
        }
        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        }
        if (quantity > availability[index]) {
            System.out.println("Not enough rooms available");
            return;
        }
        availability[index] -= quantity;
        System.out.println("Booked " + quantity + " " + roomType + " room(s)");
    }
    public static int findRoomIndex(String roomType) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equals(roomType)) {
                return i;
            }
        }
        return -1;
    }
    public static void displayRooms() {
        System.out.println("\nCurrent Inventory:");
        for (int i = 0; i < roomTypes.length; i++) {
            System.out.println(roomTypes[i] + " -> " + availability[i]);
        }
    }
    public static void main(String[] args) {
        loadData();
        displayRooms();
        System.out.println("\nBooking Rooms...");
        bookRoom("Single", 2);
        bookRoom("Suite", 1);
        displayRooms();
        System.out.println("\nSaving data before exit...");
        saveData();
    }
}