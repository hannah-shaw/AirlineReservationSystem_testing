package Codebase;
import Codebase.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    Ticket ticket;
    Airplane airplane;
    Passenger passenger;
    Flight flight;

    @BeforeEach
    public void setUp(){
        airplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        passenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        flight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
        ticket = new Ticket(1, 200, flight, false, passenger);
    }
    @Test
    public void testSetandGetTicketId(){
        ticket.setTicket_id(123);
        assertEquals(123, ticket.getTicket_id());
    }

    @Test
    public void testSetPriceandGetPrice(){
        ticket.setPrice(100);
        assertEquals(112, ticket.getPrice());
    }

    @Test
    public void testSetandGetFlight(){
        ticket.setFlight(flight);
        assertEquals(flight, ticket.getFlight());
    }

    @Test
    public void testSetandGetClassVip(){
        ticket.setClassVip(true);
        assertTrue(ticket.getClassVip());
    }

    @Test
    public void testSetandGetPassenger(){
        ticket.setPassenger(passenger);
        assertEquals(passenger, ticket.getPassenger());
    }

    @Test
    public void testSetandGetticketStatus()
    {
        ticket.setTicketStatus(true);
        assertEquals(true, ticket.getTicketStatus());
    }

    @Test
    public void testtoString(){
        assertEquals("Ticket{" +'\n'+
                "Price=" + 200 + "KZT, " + '\n' +
                flight +'\n'+ "Vip status=" + false + '\n' +
                passenger +'\n'+ "Ticket was purchased=" + false + "\n}",ticket.toString());
    }
}