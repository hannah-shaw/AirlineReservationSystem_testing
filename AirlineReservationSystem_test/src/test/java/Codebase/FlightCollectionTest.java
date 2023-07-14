package Codebase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-08
 */
class FlightCollectionTest {
    ArrayList<Flight> flights;
    Flight flight1,flight2;

    private Airplane mockAirplane() {
        Airplane airplane = new Airplane(001, "Boeing 737",
                12, 147, 5);
        assertEquals("Airplane{" +
                "model=" + "Boeing 737" + '\'' +
                ", business sits=" + "12" + '\'' +
                ", economy sits=" + "147" + '\'' +
                ", crew sits=" + "5" + '\'' +
                '}', airplane.toString());
        return airplane;
    }

    @BeforeEach
    void setup(){
        flights = new ArrayList<Flight>();
        flight1 = new Flight(3, "Beijing", "Suzhou", "SM003",
                "China Airlines", "05/07/2023 00:00:00","16/06/2023 19:45:00", mockAirplane());
        flight2 = new Flight(4,"London", "Shanghai", "SM004",
                "China Airlines", "15/02/2023 20:15:00", "16/02/2023 10:19:00", mockAirplane());
        flights.add(flight1);
        flights.add(flight2);
    }
    @AfterEach
    void afterEach(){
        FlightCollection.flights.clear();
    }

    @Test
    void testIsCityNameValid(){
        boolean result = FlightCollection.validateCity("123456");
        assertFalse(result);
        boolean result1 = FlightCollection.validateCity("!@#");
        assertFalse(result1);
        boolean result3 = FlightCollection.validateCity("Paris");
        assertTrue(result3);
        boolean result4 = FlightCollection.validateCity("Fuzhou");
        assertTrue(result4);
        boolean result5 = FlightCollection.validateCity("Fuzhou//?");
        assertFalse(result5);
        boolean result6 = FlightCollection.validateCity("1/Nanjing_");
        assertFalse(result6);
    }

    @Test
    void testAddFlights(){
        FlightCollection.addFlights(flights);
        Assertions.assertEquals(2, FlightCollection.getFlights().size());
    }

    @Test
    void testAddFlightsByInvalidCity(){
        flights = new ArrayList<Flight>();
        flight1 = new Flight(5, "Beijing123", "Suzhou", "SM005",
                "China Airlines", "05/07/2023 00:00:00","16/06/2023 19:45:00",  mockAirplane());
        flights.add(flight1);
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            FlightCollection.addFlights(flights);
        });
        Assertions.assertEquals("The city name is invalid", exception.getMessage());
    }

    @Test
    void testAddFlightsByExistingFlight(){
        testAddFlights();

        flight1 = new Flight(3, "Beijing", "Suzhou", "SM003",
                "China Airlines", "05/07/2023 00:00:00","16/06/2023 19:45:00",  mockAirplane());

        Throwable exception = assertThrows(RuntimeException.class, () -> {
            FlightCollection.addFlight(flight1);
        });
        Assertions.assertEquals("3 is already existing", exception.getMessage());
    }


    @Test
    void testGetFlightInfoByCity(){
        testAddFlights();

        Assertions.assertEquals(flight1, FlightCollection.getFlightInfo("Suzhou", "Beijing"));
        Assertions.assertEquals(flight2, FlightCollection.getFlightInfo("London"));

        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> FlightCollection.getFlightInfo(null));
        Assertions.assertEquals("City name cannot be null", exception1.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> FlightCollection.getFlightInfo("123Beijing"));
        Assertions.assertEquals("City name can only contain letter and space", exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> FlightCollection.getFlightInfo("Suzhou","Shanghai101"));
        Assertions.assertEquals("City name can only contain letter and space", exception3.getMessage());

        Throwable exception4 = assertThrows(RuntimeException.class, () -> FlightCollection.getFlightInfo("Suzhou","Nanjing"));
        Assertions.assertEquals("No such flight exists", exception4.getMessage());
    }

    @Test
    void testGetFlightInfoById() {
        testAddFlights();
        Assertions.assertEquals(flight1, FlightCollection.getFlightInfo(3));
        Assertions.assertEquals(flight2, FlightCollection.getFlightInfo(4));

        Throwable exception1 = assertThrows(RuntimeException.class, () -> FlightCollection.getFlightInfo(1));
        Assertions.assertEquals(("No such flight exists"), exception1.getMessage());
    }


    @Test
    void testAddExistFlight() {
        // Add the flight2 twice
        flights.add(flight2);
        Throwable exception1 = assertThrows(RuntimeException.class, () -> FlightCollection.addFlights(flights));
        Assertions.assertEquals(("4 is already existing"), exception1.getMessage());
    }

}