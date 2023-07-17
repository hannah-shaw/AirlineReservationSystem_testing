package Codebase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TicketSystemTest {

    private TicketSystem ticketSystem;
    private TicketCollection ticketCollection;
    private FlightCollection flightCollection;
    private Scanner scannerMock;
    @BeforeEach
    void setup() {
        flightCollection = new FlightCollection();
        ticketCollection = new TicketCollection();
        scannerMock = Mockito.mock(Scanner.class);
        // Create a valid flight and ticket
        ticketCollection.tickets.removeAll(ticketCollection.tickets);
        Airplane airplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        Flight flight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
        flightCollection.flights.add(flight);

        Passenger passenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        Ticket ticket = new Ticket(1, 1000, flight, false, passenger);
        ticketCollection.tickets.add(ticket);

        ticketSystem = new TicketSystem(ticketCollection, flightCollection);
    }
    @Test//First try of test
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

    @Test//1st version with pre-setup dummy data
    public void testBuyTicketWithAlreadyBooked() throws Exception{
        ticketCollection.tickets.get(0).setTicketStatus(true);
        System.out.println(ticketCollection.tickets.get(0));
        //System.out.println(DummyTicketCollection.getTicketInfo(1));
        when(scannerMock.nextInt()).thenReturn(1); //input=1
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(1));
        assertTrue(e.getMessage().contains("This ticket does not exist or has been booked."));
    }

    @Test
    public void testBuyTicketWithAlreadyBooked2() throws Exception{
        Ticket ticket = new Ticket();
        Flight flight = new Flight();
        ticketCollection.tickets.add(ticket);
        ticketCollection.tickets.get(1).setTicketStatus(true);
        System.out.println(ticketCollection.tickets.toString());

        when(scannerMock.nextInt()).thenReturn(2);
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(2));
        assertTrue(e.getMessage().contains("This ticket does not exist or has been booked."));
    }
//    @Test
//    public void testValidatePassengerInformation() throws Exception {
//        Mockito.when(scannerMock.next())
//                .thenReturn("1")  // Choose ticketID
//                .thenReturn("John")  // firstName
//                .thenReturn("Doe")  // secondName
//                .thenReturn("30") //age
//                .thenReturn("Man")  // gender
//                .thenReturn("john.doe@gmail.com")  // email
//                .thenReturn("0412345678")  // phoneNumber
//                .thenReturn("1234567891111");  // passportNumber
//        Assertions.assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
//    }

    @Test
    public void testCorrectTicketInformation() throws Exception {
    }



}


