package Codebase;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketCollectionTest {

    private TicketCollection ticketCollection;
    Passenger passenger;
    Flight flight;
    Ticket ticket1;
    Ticket ticket2 ;
    Airplane airplane;


    @Before
    public void setUp() {
        airplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        passenger = new Passenger("Yuhang","Huang", 24, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        flight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
        ticket1 = new Ticket(1, 1000, flight, false, passenger);
        ticket2 = new Ticket(2, 1000, flight, false, passenger);
        ticketCollection = new TicketCollection();
        ticketCollection.tickets = new ArrayList<>(); // Ensures tickets list is empty before each test
    }

    @Test
    public void testgetTickets(){
        ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
        for(int i=0;i<10;i++){
            Ticket ticket = new Ticket(i,1000,flight,false,passenger);
            ticketsToBeAdded.add(ticket);
        }
        TicketCollection.addTickets(ticketsToBeAdded);
        assertEquals(ticketsToBeAdded, ticketCollection.getTickets());
        //Changes to be made, test getalltickets()
        TicketCollection.tickets.clear();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> TicketCollection.getAllTickets()
        );
        Assertions.assertEquals("There is no ticket in the system.", thrown.getMessage());

    }
    @Test
    public void testAddTickets() {
        ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
        Ticket ticket2 = new Ticket(); // Assume Ticket has a no-arg constructor
        ticketsToBeAdded.add(ticket2);
        ticketCollection.addTickets(ticketsToBeAdded);
        // Check that tickets were added correctly
        assertTrue(ticketCollection.tickets.contains(ticket2));
        // Changes to be made
        ticketsToBeAdded.clear();
        Ticket nullticket = new Ticket();
        nullticket = null;
        ticketsToBeAdded.add(nullticket);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> TicketCollection.addTickets(ticketsToBeAdded)
        );
        Assertions.assertEquals("Invalid null ticket in list", thrown.getMessage());
    }

    @Test
    public void testAddTicketIsValid() {
        try {
            ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
            Ticket ticket2 = new Ticket(2, 1000, flight, false, null);
            ticketsToBeAdded.add(ticket2);
            TicketCollection.addTickets(ticketsToBeAdded);

        } catch (Exception exception1) {
            Assertions.assertEquals("Invalid passenger input", exception1.getMessage());
        }
    }
    @Test
    public void testAddTicketTwice() {
        try {
            ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
            Ticket ticket3 = new Ticket(3, 1000, flight, false, passenger);
            Ticket ticket4 = new Ticket(3, 1000, flight, false, passenger); // the same with ticket3
            ticketsToBeAdded.add(ticket3);
            ticketsToBeAdded.add(ticket4);
            TicketCollection.addTickets(ticketsToBeAdded);
        } catch (Exception exception1) {
            Assertions.assertEquals("ID:3 ticket was add twice", exception1.getMessage());
        }
    }

    // ------------------
    // Add this test case to up Line Coverage
    // ------------------
    @Test
    public void testAddTicketExist() {
        // ------------------
        // Edit the test method to address PIT negative condition mutation here
        // ------------------
//        try {
//            ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
//            Ticket ticket5 = new Ticket(5, 1000, flight, false, passenger);
//            ticketsToBeAdded.add(ticket5);
//            TicketCollection.addTickets(ticketsToBeAdded);
//            TicketCollection.addTickets(ticketsToBeAdded);
//        } catch (Exception exception2) {
//            Assertions.assertEquals("ID:5 ticket is already exist", exception2.getMessage());
//        }
        ArrayList<Ticket> ticketsToBeAdded = new ArrayList<>();
        Ticket ticket5 = new Ticket(5, 1000, flight, false, passenger);
        ticketsToBeAdded.add(ticket5);
        TicketCollection.addTickets(ticketsToBeAdded);
        Throwable e2 = assertThrows(IllegalArgumentException.class, () -> {
            TicketCollection.addTickets(ticketsToBeAdded);
        });
        Assertions.assertEquals("ID:5 ticket is already exist", e2.getMessage());
    }

    @Test
    public void testGetTicketInfo() {
        ticket1.setTicket_id(1); // Assume Ticket has a setter for ticket_id
        ticket2.setTicket_id(2);
        ticketCollection.tickets.add(ticket1);
        ticketCollection.tickets.add(ticket2);

        Ticket resultTicket = ticketCollection.getTicketInfo(2);

        // Check that the correct ticket was returned
        assertEquals(resultTicket,ticket2);
    }
}
