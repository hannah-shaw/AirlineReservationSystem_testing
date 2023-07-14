package Codebase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TicketCollectionTest {

    private TicketCollection ticketCollection;
    Passenger passenger = new Passenger();
    Flight flight = new Flight();
    Ticket ticket1 = new Ticket();
    Ticket ticket2 = new Ticket();
    Airplane airplane;


    @Before
    public void setUp() {
        airplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        passenger = new Passenger("Yuhang","Huang", 24, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        flight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
        ticket1 = new Ticket(1, 1000, flight, false, passenger);
        ticket2 = new Ticket(2, 1000, flight, false, passenger);
        ticketCollection = new TicketCollection();
        TicketCollection.tickets = new ArrayList<>(); // Ensures tickets list is empty before each test
    }

    @Test
    public void testAddTickets() {
        ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
        Ticket ticket1 = new Ticket(); // Assume Ticket has a no-arg constructor
        Ticket ticket2 = new Ticket();
        ticketsToBeAdded.add(ticket1);
        ticketsToBeAdded.add(ticket2);

        TicketCollection.addTickets(ticketsToBeAdded);

        // Check that tickets were added correctly
        assertTrue(TicketCollection.tickets.contains(ticket1));
        assertTrue(TicketCollection.tickets.contains(ticket2));
    }

    @Test
    public void testGetTicketInfo() {
        ticket1.setTicket_id(1); // Assume Ticket has a setter for ticket_id
        ticket2.setTicket_id(2);
        TicketCollection.tickets.add(ticket1);
        TicketCollection.tickets.add(ticket2);

        Ticket resultTicket = TicketCollection.getTicketInfo(2);

        // Check that the correct ticket was returned
        assertEquals(resultTicket,ticket2);
    }
}
