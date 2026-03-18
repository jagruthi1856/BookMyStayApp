import java.util.*;
class Reservation {
    private int bookingId;
    private String customerName;
    private String roomType;
    private int nights;
    public Reservation(int bookingId, String customerName, String roomType, int nights) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
    }
    public int getBookingId() {
        return bookingId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getRoomType() {
        return roomType;
    }
    public int getNights() {
        return nights;
    }
    @Override
    public String toString() {
        return "BookingID: " + bookingId +
                ", Name: " + customerName +
                ", Room: " + roomType +
                ", Nights: " + nights;
    }
}
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }
    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(history); // prevents modification
    }
}
class BookingReportService {
    public void printAllBookings(List<Reservation> reservations) {
        System.out.println("\n--- Booking History ---");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n--- Booking Summary Report ---");
        int totalBookings = reservations.size();
        int totalNights = 0;
        for (Reservation r : reservations) {
            totalNights += r.getNights();
        }
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Nights Booked: " + totalNights);
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();
        Reservation r1 = new Reservation(101, "Alice", "Deluxe", 3);
        Reservation r2 = new Reservation(102, "Bob", "Suite", 2);
        Reservation r3 = new Reservation(103, "Charlie", "Standard", 1);
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);
        List<Reservation> bookings = history.getAllReservations();
        reportService.printAllBookings(bookings);
        reportService.generateSummary(bookings);
    }
}