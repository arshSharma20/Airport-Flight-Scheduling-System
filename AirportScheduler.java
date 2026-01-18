/**
 * Console-based driver for the Airport Flight Scheduling System.
 * Allows users to manage and view scheduled flights.
 * @author Arshdeep Singh Sharma
 */

import java.util.Scanner;


public class AirportScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MinHeap schedule = new MinHeap(10);

        int choice;

        do {
            System.out.println("\n--- Airport Flight Scheduler ---");
            System.out.println("1. Add new flight");
            System.out.println("2. View next flight");
            System.out.println("3. Remove next flight (departed)");
            System.out.println("4. Show all flights (sorted by departure)");
            System.out.println("5. Show current heap (unsorted)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter flight number: ");
                    String fn = sc.next();
                    System.out.print("Enter destination: ");
                    String dest = sc.next();
                    System.out.print("Enter departure time (in minutes since midnight): ");
                    int dep = sc.nextInt();
                    System.out.print("Enter priority (1=VIP, 2=Passenger, 3=Cargo): ");
                    int pr = sc.nextInt();
                    schedule.insert(new Flight(fn, dest, dep, pr));
                    System.out.println("Flight added successfully!");
                    break;
                }
                case 2: {
                    Flight next = schedule.peek();
                    if (next != null) System.out.println("Next flight to depart: " + next);
                    break;
                }
                case 3: {
                    Flight departed = schedule.remove();
                    if (departed != null)
                        System.out.println("Departed: " + departed);
                        break;
                }
                case 4: {
                    Flight[] sorted = schedule.heapSort();
                    if (sorted.length == 0)
                        System.out.println("No flights scheduled.");
                    else {
                        System.out.println("\nEnd-of-Day Sorted Schedule:");
                        for (Flight f : sorted)
                            System.out.println(f);
                    }
                    break;
                }
                case 5: {
                    schedule.printHeap();
                    break;
                }
                case 6: {
                    System.out.println("Exiting scheduler. Goodbye!");
                    break;
                }
                default:{
                     System.out.println("Invalid choice, try again.");
                     break;
                }
            }

        } while (choice != 6);

        sc.close();
    }
}
