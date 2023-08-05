package Codebase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.ParseException;

import static Codebase.Flight.stringToTimestamp;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hannahshaw
 * @version 2.0
 * @date 2023-08-03
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
        Airplane airplane = new Airplane(001, "Boeing 737",
                12, 147, 5);
        flight = new Flight(1, "Melbourne", "Suzhou", "SM001",
                "China Airlines", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
    }

    @Test
    public void testConstructor() {
        Flight flight1 = new Flight();
        assertNotNull(flight1);
    }

    @Test
    void testGetFlightID() {
        Airplane airplane = new Airplane(001, "Boeing 737",
                12, 147, 5);
        flight = new Flight(1000, "Melbourne", "Suzhou", "SM001",
                "China Airlines", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
        Assertions.assertEquals(1000,flight.getFlightID());
    }
    @Test
    void testSetFlightID(){
        flight.setFlightID(1);
        Assertions.assertEquals(1,flight.getFlightID());

        flight.setFlightID(10);
        Assertions.assertEquals(10,flight.getFlightID());

        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setFlightID(0));
        Assertions.assertEquals("Flight ID should be a positive number", exception1.getMessage());

        exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setFlightID(-1));
        Assertions.assertEquals("Flight ID should be a positive number", exception1.getMessage());
    }

    @Test
    void testGetDepartTo() {
        Assertions.assertEquals("Melbourne",flight.getDepartTo());
    }
    @Test
    void testSetDepartTo(){
        flight.setDepartTo("Beijing");
        Assertions.assertEquals("Beijing",flight.getDepartTo());
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setDepartTo(null));
        Assertions.assertEquals("the Depart to destination cannot be empty", exception1.getMessage());

    }

    @Test
    void testGetDepartFrom() {
        Assertions.assertEquals("Suzhou",flight.getDepartFrom());
    }
    @Test
    void testSetDepartFrom(){
        flight.setDepartFrom("Beijing");
        Assertions.assertEquals("Beijing",flight.getDepartFrom());
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setDepartFrom(null));
        Assertions.assertEquals("the Depart from place cannot be empty", exception1.getMessage());

    }

    @Test
    void testGetCode() {
        Assertions.assertEquals("SM001",flight.getCode());
    }
    @Test
    void testSetCode(){
        flight.setCode("C919");
        Assertions.assertEquals("C919",flight.getCode());
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setCode(null));
        Assertions.assertEquals("the code cannot be empty", exception1.getMessage());

    }

    @Test
    void testGetDateTo() {
        Assertions.assertEquals(stringToTimestamp("16/07/2023 01:35:00"),flight.getDateTo());
    }
    @Test
    void testSetDateTo(){
        flight.setDateTo("16/07/2023 01:35:01");
        Assertions.assertEquals(stringToTimestamp("16/07/2023 01:35:01"),flight.getDateTo());
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setDateTo(null));
        Assertions.assertEquals("Date To cannot be empty", exception1.getMessage());

    }

    @Test
    void testGetDateFrom() {
        Assertions.assertEquals(stringToTimestamp("05/07/2023 13:55:00"),flight.getDateFrom());
    }
    @Test
    void testSetDateFrom(){
        flight.setDateFrom("16/07/2023 01:35:01");
        Assertions.assertEquals(stringToTimestamp("16/07/2023 01:35:01"),flight.getDateFrom());
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setDateFrom(null));
        Assertions.assertEquals("Date from cannot be empty", exception1.getMessage());

    }

    @Test
    void testGetCompany() {
        Assertions.assertEquals("China Airlines",flight.getCompany());
    }
    @Test
    void testSetCompany(){
        flight.setCompany("Qantas");
        Assertions.assertEquals("Qantas",flight.getCompany());
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> flight.setCompany(null));
        Assertions.assertEquals("the company cannot be empty", exception1.getMessage());

    }

    @Test
    void testDateTimeFormat() {
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> new Flight(2, "Melbourne", "Suzhou",
                "SM002", "China Airlines", "2023-07-05 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("Time format error", exception1.getMessage());
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> new Flight(2, "Melbourne", "Suzhou",
                "SM002", "China Airlines", "06/07/2023 00:00:00",
                "07/07/2023 00-00-00", mockAirplane()));
        Assertions.assertEquals("Time format error", exception2.getMessage());

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

        Throwable exception7 = assertThrows(IllegalArgumentException.class, () -> new Flight(8, "Beijing", "Suzhou",
                "SM008", "China Airlines", "05/07/2023 00:00:00",
                "06/07/2023 00:00:00", null));
        Assertions.assertEquals("airplane cannot be empty", exception7.getMessage());
        Throwable exception8 = assertThrows(IllegalArgumentException.class, () -> new Flight(0, "Beijing", "Suzhou",
                "SM008", "China Airlines", "05/07/2023 00:00:00",
                "06/07/2023 00:00:00", mockAirplane()));
        Assertions.assertEquals("All info cannot be empty", exception8.getMessage());

    }
}