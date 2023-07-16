package Codebase;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TicketSystemTest {

    Airplane DummyAirplane;
    Passenger DummyPassenger;
    Flight DummyFlight;
    Ticket DummyTicket;
    FlightCollection DummyFlightCollection;
    TicketCollection DummyTicketCollection;
    TicketSystem ticketSystem;
    Scanner scannerMock;

    @BeforeEach
    public void setUp(){
        // Dummy Data:
        DummyAirplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        DummyPassenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        DummyFlight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", DummyAirplane);
        DummyTicket = new Ticket(1, 1000, DummyFlight, false, DummyPassenger);
        DummyFlightCollection = new FlightCollection();
        DummyTicketCollection = new TicketCollection();
        DummyFlightCollection.flights.add(DummyFlight);
        DummyTicketCollection.tickets.add(DummyTicket);
        scannerMock = Mockito.mock(Scanner.class);
        // Create ticketSystem by Dummy Data
        ticketSystem = new TicketSystem(DummyTicketCollection, DummyFlightCollection);
        ticketSystem.in = scannerMock;

    }

    @Test
    public final void ChooseTicketTestWithInvalidCity() {
        // Test choose city with invalid city name
        try {
            ticketSystem.chooseTicket("SHANG123", "SUZHOU");
        }
        catch (Exception e1) {
            Assertions.assertEquals("City name can only contain letter and space", e1.getMessage());
        }
    }

    @Test
    public final void ChooseTicketTicketWithInvalidFlight() {
        // Test choose ticket with NO exist flight
        try {
            ticketSystem.chooseTicket("SHANGHAI", "SUZHOU");
        }
        catch (Exception e2) {
            Assertions.assertEquals("No such flight exists", e2.getMessage());
        }
    }

    @Test
    public void testBuyTicketWithAlreadyBooked() throws Exception{
        DummyTicketCollection.tickets.get(0).setTicketStatus(true);
        System.out.println(DummyTicketCollection.tickets.get(0));
        //System.out.println(DummyTicketCollection.getTicketInfo(1));
        when(scannerMock.nextInt()).thenReturn(1); //input=1
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(1));
        assertTrue(e.getMessage().contains("This ticket does not exist or has been booked."));
    }

    @Test
    public void testValidatePassengerInformation() throws Exception {
        when(scannerMock.nextInt()).thenReturn(1);
        when(scannerMock.next()).thenReturn("John");
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(1));
    }

    @Test
    public void testCorrectTicketInformation() throws Exception {
        when(scannerMock.nextInt()).thenReturn(1);
        when(scannerMock.next()).thenReturn("John");
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(1));
    }
}


