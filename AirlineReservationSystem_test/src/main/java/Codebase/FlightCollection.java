package Codebase;

import java.util.ArrayList;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-12
 */

public class FlightCollection {
	
	public static ArrayList<Flight> flights = new ArrayList<Flight>();

	public static ArrayList<Flight> getFlights() {
		return flights;
	}

	public static void addFlight(Flight flight){
		if(getFlights() != null){
			for(Flight existFlight: getFlights())
				if(flight.getFlightID() == existFlight.getFlightID())
					throw new RuntimeException(flight.getFlightID() + " is already existing");
		}
		if(validateCity(flight.getDepartFrom()) && validateCity(flight.getDepartTo()))
			flights.add(flight);
		else throw new RuntimeException("The city name is invalid");
	}

	public static void addFlights(ArrayList<Flight> flights) {
		for(Flight flight: flights){
			addFlight(flight);
		}
	}

	public static boolean validateCity(String cityName) {
		return cityName != null && cityName.matches("^[a-zA-Z\\s]+$");

	}
	
	public static Flight getFlightInfo(String city1, String city2) {
    	//display the flights where there is a direct flight from city 1 to city2
		if(city1 == null||city2 == null){
			throw new IllegalArgumentException("City name cannot be null");
		}
		if (!city1.matches("^[a-zA-Z\\s]+$")||!city2.matches("^[a-zA-Z\\s]+$"))
			throw new IllegalArgumentException("City name can only contain letter and space");
		for (Flight flight : flights) {
			if(flight.getDepartFrom().equals(city1) && flight.getDepartTo().equals(city2)){
				return flight;
			}
		}
		throw new RuntimeException("No such flight exists");
    }
    
    public static Flight getFlightInfo(String city) {
		if(city == null){
			throw new IllegalArgumentException("City name cannot be null");
		}
    	//SELECT a flight where depart_to = city
		if (!city.matches("^[a-zA-Z\\s]+$"))
			throw new IllegalArgumentException("City name can only contain letter and space");
		for (Flight flight : flights) {
			if(flight.getDepartTo().equalsIgnoreCase(city)){
				return flight;
			}
		}
		throw new RuntimeException("No such flight exists");

    }
    public static Flight getFlightInfo(int flight_id) {
    	//SELECT a flight with a particular flight id
		for (Flight flight : flights) {
			if(flight.getFlightID() == flight_id){
				return flight;
			}
		}
		throw new RuntimeException("No such flight exists");

    }
    

}
