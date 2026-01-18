/**
 * Represents a flight with departure time and priority.
 * Implements Comparable for scheduling order.
 * @author Arshdeep Singh Sharma
 */


public class Flight implements Comparable<Flight> {

    public String flightNumber;
    public String destination;
    public int departureTime; // minutes since midnight
    public int priority;      // 1 = VIP, 2 = Passenger, 3 = Cargo

    public Flight(String flightNumber, String destination, int departureTime, int priority) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureTime = departureTime;
        this.priority = priority;
    }

    @Override
    public int compareTo(Flight other) {
    	//comparing by departure times
    	if(this.departureTime != other.departureTime){
    		return this.departureTime - other.departureTime;
    	}
    	// If equal compare by priority
    	else{
    		return this.priority - other.priority;
    	}
    }


    /**
     Return a human-readable summary of the flight.
     */
    
    public String toString() {
    	// converting departure time to HH:MM format
    	int hours = departureTime / 60;
    	int minutes = departureTime % 60;
    	String time = String.format("%02d:%02d", hours, minutes);
    	String priorityString;
    	
    	// COnverting the priority number to names using switch.
    	switch(priority){
    		case 1:	priorityString = "VIP";
    			break;
    		case 2: priorityString = "Passenger";
    			break;
    		case 3: priorityString = "Cargo";
    			break;
    		default:priorityString="none";
    	}
    	// returning String
    	return "Flight "+flightNumber+" => "+destination+" | Departure: "+time
    	               +" | Priority: "+priorityString;
    
    }

}
