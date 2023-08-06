package Codebase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;

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
        Flight flight2 = new Flight(20, "SUZHOU", "BEIJING", "7103", "EasternChina", "16/07/2023 05:35:00", "16/07/2023 15:15:00", airplane2);
        Passenger passenger = new Passenger();
        //Passenger passenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        Ticket ticket = new Ticket(1, 1000, flight1, false, passenger);//passenger hasn't been set, the age return default age:18.
        Ticket ticket2 = new Ticket(2, 1200, flight2, false, passenger);//passenger hasn't been set, the age return default age:18.
        Ticket ticket_Transfer1 = new Ticket(3, 1200, flight1, false, passenger);
        Ticket ticket_Transfer2 = new Ticket(4, 1200, flight2, false, passenger);
        TicketCollection.tickets.add(ticket);
        TicketCollection.tickets.add(ticket2);
        TicketCollection.tickets.add(ticket_Transfer1);
        TicketCollection.tickets.add(ticket_Transfer2);
        FlightCollection.flights.add(flight1);
        FlightCollection.flights.add(flight2);
        ticketSystem = new TicketSystem(ticketCollection, flightCollection,scannerMock);
    }

    @Test
    public final void BuyTicketTestWithTransfer() {
        // Test choose city with invalid city name
        try {
            when(scannerMock.nextInt()).thenReturn(3,4);
            Mockito.when(scannerMock.nextLine())
                    .thenReturn("Ginphy")  // firstName
                    .thenReturn("Yuen")  // secondName
                    .thenReturn("22") //age
                    .thenReturn("Man")  // gender
                    .thenReturn("ginphy@gmail.com")  // email
                    .thenReturn("0412345678")  // phoneNumber
                    .thenReturn("33414521")  // passportNumber
                    .thenReturn("1")
                    .thenReturn("0987198300912")
                    .thenReturn("2000");
            ticketSystem.chooseTicket("BEIJING", "SHANGHAI");
        }
        catch (Exception e1) {
            Assertions.assertEquals("City name cannot miss", e1.getMessage());
        }
    }

    @Test
    public final void ChooseTicketTestWithValidandInvalidCity() {
        // Test choose city with invalid city name
        try {
            ticketSystem.chooseTicket("SHA1231NG123", "SUZHOU");
        }
        catch (Exception e1) {
            Assertions.assertEquals("City name can only contain letter and space", e1.getMessage());
        }
        try {
            ticketSystem.chooseTicket(null, null);
        }
        catch (Exception e2) {
            Assertions.assertEquals("City name cannot be null", e2.getMessage());
        }
    }

    @Test
    public final void testValidateFlightInformation() {
        // Test choose ticket with NO exist flight
        try {
            ticketSystem.chooseTicket("SHANGHAI", "HEFEI");
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

        when(scannerMock.nextInt()).thenReturn(100);
        Exception e = assertThrows(Exception.class, ()-> ticketSystem.buyTicket(100));
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
                .thenReturn("John") // firstName
                .thenReturn("Doe") // secondName
                .thenReturn("30") //age
                .thenReturn("Man") // gender
                .thenReturn("john.doe@gmail.com") // email
                .thenReturn("0412345678") // phoneNumber
                .thenReturn("123456789") // passportNumber
                .thenReturn("0");
        Exception e7 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e7.getMessage().contains("Purchased cancelled"));
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
        TicketCollection.getTickets().get(0).setTicketStatus(false);
        Assertions.assertTrue(e4.getMessage().contains("cardNumber cannot be empty"));
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John") // firstName
                .thenReturn("Doe") // secondName
                .thenReturn("30") //age
                .thenReturn("Man") // gender
                .thenReturn("john.doe@gmail.com") // email
                .thenReturn("0412345678") // phoneNumber
                .thenReturn("123456789") // passportNumber
                .thenReturn("-1");
        Exception e5 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        TicketCollection.getTickets().get(0).setTicketStatus(false);
        Assertions.assertTrue(e5.getMessage().contains("Error Input"));
        Mockito.when(scannerMock.nextLine())
                .thenReturn("John") // firstName
                .thenReturn("Doe") // secondName
                .thenReturn("30") //age
                .thenReturn("Man") // gender
                .thenReturn("john.doe@gmail.com") // email
                .thenReturn("0412345678") // phoneNumber
                .thenReturn("123456789") // passportNumber
                .thenReturn("1")
                .thenReturn("37789410971829")
                .thenReturn("-1");
        Exception e6 = assertThrows(IllegalArgumentException.class, () -> ticketSystem.buyTicket(1));
        Assertions.assertTrue(e6.getMessage().contains("SecurityCode Format Error"));
    }

    @Test
    public void testValidateTicketInformation() throws Exception {
        TicketCollection.tickets.get(0).setClassVip(true);
        TicketCollection.tickets.get(1).setClassVip(false);
        Passenger DummyPassenger = new Passenger("Ginphy", "Yuen", 22, "Man", "ginphy@gmail.com", "0412345678", "33414521", "0987198300912", 2000);
        Mockito.when(scannerMock.nextLine())
                .thenReturn("Ginphy")  // firstName
                .thenReturn("Yuen")  // secondName
                .thenReturn("22") //age
                .thenReturn("Man")  // gender
                .thenReturn("ginphy@gmail.com")  // email
                .thenReturn("0412345678")  // phoneNumber
                .thenReturn("33414521")  // passportNumber
                .thenReturn("1")
                .thenReturn("0987198300912")
                .thenReturn("2000");
        ticketSystem.buyTicket(1);
        assertEquals("Ticket{" +'\n'+
                "Price=" + 1120 + "KZT, " + '\n' +
                FlightCollection.flights.get(0) +'\n'+ "Vip status=" + true + '\n' +
                DummyPassenger+'\n'+ "Ticket was purchased=" + true + "\n}", ticketSystem.ticket.toString());

        // ADDED for coverage
        assertEquals(29,TicketCollection.tickets.get(0).getFlight().getAirplane().getBusinessSitsNumber());
        Passenger DummyPassenge2 = new Passenger("Ginphy", "Yuen", 22, "Man", "ginphy@gmail.com", "0412345678", "33414521", "0987198300912", 2000);
        Mockito.when(scannerMock.nextLine())
                .thenReturn("Ginphy")  // firstName
                .thenReturn("Yuen")  // secondName
                .thenReturn("22") //age
                .thenReturn("Man")  // gender
                .thenReturn("ginphy@gmail.com")  // email
                .thenReturn("0412345678")  // phoneNumber
                .thenReturn("33414521")  // passportNumber
                .thenReturn("1")
                .thenReturn("0987198300912")
                .thenReturn("2000");
        ticketSystem.buyTicket(2);
        assertEquals(99,TicketCollection.tickets.get(1).getFlight().getAirplane().getEconomySitsNumber());

    }

    @Test
    public void testCorrectDisplayValue() throws Exception {
        Mockito.when(scannerMock.nextLine())
                .thenReturn("Ginphy")  // firstName
                .thenReturn("Yuen")  // secondName
                .thenReturn("22") //age
                .thenReturn("Man")  // gender
                .thenReturn("ginphy@gmail.com")  // email
                .thenReturn("0412345678")  // phoneNumber
                .thenReturn("33414521")  // passportNumber
                .thenReturn("1")
                .thenReturn("0987198300912")
                .thenReturn("2000");
        ticketSystem.buyTicket(1);
        assertEquals(1120, ticketSystem.ticket.getPrice());

    }



}


