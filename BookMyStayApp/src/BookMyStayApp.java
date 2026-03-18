abstract class Room {
    private String roomType;
    private int beds;
    private double price;
    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }
    public String getRoomType() {
        return roomType;
    }
    public int getBeds() {
        return beds;
    }
    public double getPrice() {
        return price;
    }
    public abstract void displayDetails();
}
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 1000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 1800.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 3000.0);
    }
    @Override
    public void displayDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}
public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("===================================");
        System.out.println("     Book My Stay - Room Viewer");
        System.out.println("===================================");
        System.out.println("Version: v2.1\n");
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;
        System.out.println("---- Room Details ----\n");
        single.displayDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println("----------------------");
        doubleRoom.displayDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println("----------------------");
        suite.displayDetails();
        System.out.println("Available : " + suiteAvailable);
        System.out.println("----------------------");
        System.out.println("\nSystem Loaded Successfully!");
    }
}
