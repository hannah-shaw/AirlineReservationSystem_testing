package Codebase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-05
 */
class FlightTest {

    private Flight flight;

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
        flight = new Flight(1, "Melbourne", "Suzhou", "SM001",
                "China Airlines", "05/07/2023 13:55:00", "16/07/2023 01:35:00", mockAirplane());
    }

    @Test
    void testDateTimeFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Flight(2, "Melbourne", "Suzhou",
                "SM002", "China Airlines", "2023-07-05 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));

    }

    @Test
    void testAllFieldsAreRequired() {
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> new Flight(3, "", "Suzhou",
                "SM003", "China Airlines", "05/07/2023 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("the Depart to destination cannot be empty", exception1.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> new Flight(4, "Beijing", "",
                "SM004", "China Airlines", "05/07/2023 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("the Depart from place cannot be empty", exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> new Flight(5, "Beijing", "Suzhou",
                "", "China Airlines", "05/07/2023 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("the code cannot be empty", exception3.getMessage());

        Throwable exception4 = assertThrows(IllegalArgumentException.class, () -> new Flight(6, "Beijing", "Suzhou",
                "SM006", "", "05/07/2023 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("the company cannot be empty", exception4.getMessage());

        Throwable exception5 = assertThrows(IllegalArgumentException.class, () -> new Flight(7, "Beijing", "Suzhou",
                "SM007", "China Airlines", "",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("Date from cannot be empty", exception5.getMessage());

        Throwable exception6 = assertThrows(IllegalArgumentException.class, () -> new Flight(8, "Beijing", "Suzhou",
                "SM008", "China Airlines", "05/07/2023 00:00:00",
                "", mockAirplane()));
        Assertions.assertEquals("Date To cannot be empty", exception6.getMessage());
    }
}