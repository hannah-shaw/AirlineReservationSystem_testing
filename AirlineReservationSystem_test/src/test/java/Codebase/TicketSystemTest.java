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
        scannerMock = Mockito.mock(Scanner.class);
        // Create a valid flight and ticket
        TicketCollection.tickets.clear();
        Airplane airplane1 = new Airplane(5171, "Boeing747", 30, 130, 6);
        Airplane airplane2 = new Airplane(3009, "MH370", 50, 100, 15);
        Flight flight1 = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane1);
        Flight flight2 = new Flight(10, "SUZHOU", "BEIJING", "7103", "EasternChina", "16/07/2023 05:35:00", "16/07/2023 15:15:00", airplane2);
        Passenger passenger = new Passenger();
        //Passenger passenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        Ticket ticket = new Ticket(1, 1000, flight1, false, passenger);//passenger hasn't been set, the age return 0.
        TicketCollection.tickets.add(ticket);
        FlightCollection.flights.add(flight1);
        FlightCollection.flights.add(flight2);
        ticketSystem = new TicketSystem(ticketCollection, flightCollection,scannerMock);
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
    public final void testValidateFlightInformation() {
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
        TicketCollection.tickets.get(0).setTicketStatus(true);
        System.out.println(TicketCollection.tickets.get(0));
        //System.out.println(DummyTicketCollection.getTicketInfo(1));
        when(scannerMock.nextInt()).thenReturn(1); //input=1
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.chooseTicket("SUZHOU","SHANGHAI"));
        Assertions.assertTrue(e.getMessage().contains("This ticket does not exist or has been booked."));
    }

    @Test
    public void testBuyTicketWithAlreadyBooked2() throws Exception{
        TicketCollection.tickets.get(0).setTicketStatus(true);
        System.out.println(TicketCollection.tickets.toString());

        when(scannerMock.nextInt()).thenReturn(2);
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(2));
        Assertions.assertTrue(e.getMessage().contains("This ticket does not exist or has been booked."));
    }
    @Test
    public void testValidatePassengerInformation() throws Exception {
        System.out.println(TicketCollection.tickets.get(0).getTicket_id());
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John")  // firstName
                .thenReturn("Doe")  // secondName
                .thenReturn("-1"); //age
        Exception e = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e.getMessage().contains("Age should be positive"));
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John")  // firstName
                .thenReturn("Doe")  // secondName
                .thenReturn("30") //age
                .thenReturn("Man")  // gender
                .thenReturn("john.doe@gmail.com")  // email
                .thenReturn("")  // phoneNumber
                .thenReturn("123456789");  // passportNumber
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e1.getMessage().contains("phoneNumber cannot be empty"));
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John")  // firstName
                .thenReturn("Doe")  // secondName
                .thenReturn("30") //age
                .thenReturn("Man")  // gender
                .thenReturn("john.doe@gmail.com")  // email
                .thenReturn("0412345678")  // phoneNumber
                .thenReturn("12345678911111111");  // passportNumber
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e2.getMessage().contains("Passport number should not be more than 9 characters long"));
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John")  // firstName
                .thenReturn("Doe")  // secondName
                .thenReturn("30") //age
                .thenReturn("Man")  // gender
                .thenReturn("johnom");  // email
        Exception e3 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e3.getMessage().contains("Invalid email format"));
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John")  // firstName
                .thenReturn("Doe")  // secondName
                .thenReturn("30") //age
                .thenReturn("Man")  // gender
                .thenReturn("john.doe@gmail.com")  // email
                .thenReturn("0412345678")  // phoneNumber
                .thenReturn("123456789")  // passportNumber
                .thenReturn("1")
                .thenReturn("")
                .thenReturn("2000");
        Exception e4 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e4.getMessage().contains("cardNumber cannot be empty"));
    }

    @Test
    public void testValidateTicketInformation() throws Exception {

    }
    @Test
    public void testCorrectDisplayValue() throws Exception {
        Mockito.when(scannerMock.nextLine())
                .thenReturn("Ginphy")  // firstName
                .thenReturn("Yuen")  // secondName
                .thenReturn("22") //age
                .thenReturn("Man")  // gender
                .thenReturn("ginphy@gmail.com")  // email
                .thenReturn("13290959072")  // phoneNumber
                .thenReturn("33414521")  // passportNumber
                .thenReturn("1")
                .thenReturn("0987198300912")
                .thenReturn("2000");
        ticketSystem.buyTicket(1);

    }



}


