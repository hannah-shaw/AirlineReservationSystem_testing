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

    FlightCollection f;

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
        f = new FlightCollection();
        f.flights = new ArrayList<>();
    }
    @AfterEach
    void afterEach(){
        f.flights.clear();
    }

    @Test
    void testIsCityNameValid(){
        boolean result = f.validateCity("123456");
        assertFalse(result);
        boolean result1 = f.validateCity("!@#");
        assertFalse(result1);
        boolean result3 = f.validateCity("Paris");
        assertTrue(result3);
        boolean result4 = f.validateCity("Fuzhou");
        assertTrue(result4);
        boolean result5 = f.validateCity("Fuzhou//?");
        assertFalse(result5);
        boolean result6 = f.validateCity("1/Nanjing_");
        assertFalse(result6);
    }

    @Test
    void testAddFlights(){
        f.addFlights(flights);
        Assertions.assertEquals(2, f.getFlights().size());
    }

    @Test
    void testAddFlightsByInvalidCity(){
        flights = new ArrayList<Flight>();
        flight1 = new Flight(5, "Beijing123", "Suzhou", "SM005",
                "China Airlines", "05/07/2023 00:00:00","16/06/2023 19:45:00",  mockAirplane());
        flights.add(flight1);
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            f.addFlights(flights);
        });
        Assertions.assertEquals("The city name is invalid", exception.getMessage());
    }

    @Test
    void testAddFlightsByExistingFlight(){
        testAddFlights();

        flight1 = new Flight(3, "Beijing", "Suzhou", "SM003",
                "China Airlines", "05/07/2023 00:00:00","16/06/2023 19:45:00",  mockAirplane());

        Throwable exception = assertThrows(RuntimeException.class, () -> {
            f.addFlight(flight1);
        });
        Assertions.assertEquals("3 is already existing", exception.getMessage());
    }


    @Test
    void testGetFlightInfoByCity(){
        testAddFlights();

        Assertions.assertEquals(flight1, f.getFlightInfo("Suzhou", "Beijing"));
        Assertions.assertEquals(flight2, f.getFlightInfo("London"));

        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> f.getFlightInfo(null));
        Assertions.assertEquals("City name cannot be null", exception1.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> f.getFlightInfo("123Beijing"));
        Assertions.assertEquals("City name can only contain letter and space", exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> f.getFlightInfo("Suzhou","Shanghai101"));
        Assertions.assertEquals("City name can only contain letter and space", exception3.getMessage());

        Throwable exception4 = assertThrows(RuntimeException.class, () -> f.getFlightInfo("Suzhou","Nanjing"));
        Assertions.assertEquals("No such flight exists", exception4.getMessage());
    }

    @Test
    void testGetFlightInfoById() {
        testAddFlights();
        Assertions.assertEquals(flight1, f.getFlightInfo(3));
        Assertions.assertEquals(flight2, f.getFlightInfo(4));

        Throwable exception1 = assertThrows(RuntimeException.class, () -> f.getFlightInfo(1));
        Assertions.assertEquals(("No such flight exists"), exception1.getMessage());
    }


    @Test
    void testAddExistFlight() {
        // Add the flight2 twice
        flights.add(flight2);
        Throwable exception1 = assertThrows(RuntimeException.class, () -> f.addFlights(flights));
        Assertions.assertEquals(("4 is already existing"), exception1.getMessage());
    }

}