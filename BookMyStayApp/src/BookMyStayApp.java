import java.util.LinkedList;
import java.util.Queue;
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
    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue;
        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }
        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
            System.out.println("Request added: "
                    + reservation.getGuestName()
                    + " -> " + reservation.getRoomType());
        }
        public void displayQueue() {
            System.out.println("\nCurrent Booking Request Queue:");
            if (requestQueue.isEmpty()) {
                System.out.println("No pending requests.");
                return;
            }
            for (Reservation r : requestQueue) {
                System.out.println("Guest: " + r.getGuestName()
                        + " | Room: " + r.getRoomType());
            }
        }
        public Reservation peekNextRequest() {
            return requestQueue.peek();
        }
    }
    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single"));
        bookingQueue.addRequest(new Reservation("Bob", "Double"));
        bookingQueue.addRequest(new Reservation("Charlie", "Deluxe"));
)
        bookingQueue.displayQueue();
        System.out.println("\nNext request to be processed:");
        Reservation next = bookingQueue.peekNextRequest();
        if (next != null) {
            System.out.println(next.getGuestName() + " requesting " + next.getRoomType());
        }
        System.out.println("\nRequests are queued. No allocation done yet.");
    }
}