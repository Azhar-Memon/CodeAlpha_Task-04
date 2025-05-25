package codealpha;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category; // Standard, Deluxe, Suite categories of rooms
    boolean isBooked;
    String guestName;

    Room(int number, String category) {
        this.roomNumber = number;
        this.category = category;
        this.isBooked = false;
        this.guestName = "";
    }
}

class Hotel {
    Room[] rooms = new Room[10];

    Hotel() {
        // Initializes the room numbers and categories
        for (int i = 0; i < rooms.length; i++) {
            String category;
            if (i < 4)
                category = "Standard";
            else if (i < 7)
                category = "Deluxe";
            else
                category = "Suite";

            rooms[i] = new Room(100 + i, category);
        }
    }

    void showAvailableRooms() { // shows how many rooms are available
        System.out.println();
        System.out.println("Available Rooms:");
        boolean found = false;
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.category);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms available.");
        }
        System.out.println();
    }

    void bookRoom(String category, String guestName) { // books room 
        for (Room room : rooms) {
            if (!room.isBooked && room.category.equalsIgnoreCase(category)) {
                room.isBooked = true;
                room.guestName = guestName;
                System.out.println();
                System.out.println("Room " + room.roomNumber + " has been booked successfully for " + guestName);
                processPayment(room.category);
                System.out.println();
                return;
            }
        }
        System.out.println();
        System.out.println("Sorry, no " + category + " rooms are currently available.");
        System.out.println();
    }

    void viewBookings() { // views booked rooms
        System.out.println();
        System.out.println("Current Bookings:");
        boolean anyBooking = false;
        for (Room room : rooms) {
            if (room.isBooked) {
                System.out.println("Room " + room.roomNumber + " (" + room.category + ") - Booked by " + room.guestName);
                anyBooking = true;
            }
        }
        if (!anyBooking) {
            System.out.println("No bookings yet.");
        }
        System.out.println();
    }

    void processPayment(String category) { // payment system
        int price = 0;
        switch (category) {
            case "Standard":
                price = 5000;
                break;
            case "Deluxe":
                price = 8000;
                break;
            case "Suite":
                price = 12000;
                break;
        }
        System.out.println("Payment of PKR " + price + " received for " + category + " room. Thank you for choosing us!");
    }
}

public class CodeAlpha_Task04 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Hotel myHotel = new Hotel();

        while (true) {
            System.out.println();
            System.out.println("========== HOTEL MENU ==========");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine(); // Clear buffer
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number from 1 to 4.");
                input.nextLine(); // clear input
                continue;
            }

            switch (choice) {
                case 1:
                    myHotel.showAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = input.nextLine();

                    System.out.print("Enter room category (Standard / Deluxe / Suite): ");
                    String category = input.nextLine();

                    myHotel.bookRoom(category, name);
                    break;

                case 3:
                    myHotel.viewBookings();
                    break;

                case 4:
                    System.out.println();
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 4.");
            }
        }
    }
}
