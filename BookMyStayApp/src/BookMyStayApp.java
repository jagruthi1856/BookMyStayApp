import java.util.*;
public class BookMyStayApp {
    static class Service {
        private String serviceName;
        private double cost;
        public Service(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }
        public String getServiceName() {
            return serviceName;
        }

        public double getCost() {
            return cost;
        }
    }
    static class AddOnServiceManager {
        private Map<String, List<Service>> reservationServices = new HashMap<>();
        public void addService(String reservationId, Service service) {
            reservationServices
                    .computeIfAbsent(reservationId, k -> new ArrayList<>())
                    .add(service);
            System.out.println("Added service: " + service.getServiceName()
                    + " to Reservation ID: " + reservationId);
        }

        public void displayServices(String reservationId) {
            List<Service> services = reservationServices.get(reservationId);
            System.out.println("\nServices for Reservation ID: " + reservationId);

            if (services == null || services.isEmpty()) {
                System.out.println("No add-on services selected.");
                return;
            }
            for (Service s : services) {
                System.out.println("- " + s.getServiceName() + " (₹" + s.getCost() + ")");
            }
        }
        public double calculateTotalCost(String reservationId) {
            List<Service> services = reservationServices.get(reservationId);
            if (services == null) return 0;
            double total = 0;
            for (Service s : services) {
                total += s.getCost();
            }
            return total;
        }
    }
    public static void main(String[] args) {
        String reservationId = "R101";
        AddOnServiceManager serviceManager = new AddOnServiceManager();
        serviceManager.addService(reservationId, new Service("Breakfast", 200));
        serviceManager.addService(reservationId, new Service("Airport Pickup", 500));
        serviceManager.addService(reservationId, new Service("Extra Bed", 300));
        serviceManager.displayServices(reservationId);t
        double totalCost = serviceManager.calculateTotalCost(reservationId);
        System.out.println("\nTotal Add-On Cost: ₹" + totalCost);
        System.out.println("\nNote: Booking and inventory remain unchanged.");
    }
}