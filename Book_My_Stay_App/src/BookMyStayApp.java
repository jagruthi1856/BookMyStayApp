class BookingSystem {
    static String[] roomTypes = {"Single", "Double", "Suite"};
    static int[] availability = {2, 2, 1}; // small numbers to show conflicts
    public static int findRoomIndex(String roomType) {
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equals(roomType)) {
                return i;
            }
        }
        return -1;
    }
    public synchronized void bookRoom(String guestName, String roomType) {
        int index = findRoomIndex(roomType);
        if (index == -1) {
            System.out.println(guestName + ": Invalid room type");
            return;
        }
        if (availability[index] > 0) {
            System.out.println(guestName + " is booking " + roomType + "...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            availability[index]--;
            System.out.println(guestName + " successfully booked " + roomType +
                    ". Remaining: " + availability[index]);
        } else {
            System.out.println(guestName + ": No " + roomType + " rooms available");
        }
    }
}
class GuestThread extends Thread {
    BookingSystem system;
    String guestName;
    String roomType;
    public GuestThread(BookingSystem system, String guestName, String roomType) {
        this.system = system;
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public void run() {
        system.bookRoom(guestName, roomType);
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        GuestThread g1 = new GuestThread(system, "Guest1", "Single");
        GuestThread g2 = new GuestThread(system, "Guest2", "Single");
        GuestThread g3 = new GuestThread(system, "Guest3", "Single");
        GuestThread g4 = new GuestThread(system, "Guest4", "Double");
        GuestThread g5 = new GuestThread(system, "Guest5", "Double");
        GuestThread g6 = new GuestThread(system, "Guest6", "Suite");
        GuestThread g7 = new GuestThread(system, "Guest7", "Suite");
        g1.start();
        g2.start();
        g3.start();
        g4.start();
        g5.start();
        g6.start();
        g7.start();
    }
}