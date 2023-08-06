package Codebase;
import Codebase.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.nio.channels.NonWritableChannelException;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    private int cnt=1;
    Ticket ticket;
    Airplane airplane;
    Passenger passenger;
    Flight flight;

    @BeforeEach
    public void setUp(){
        airplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        passenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        flight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", airplane);
        ticket = new Ticket(cnt, 1000, flight, false, passenger);
        cnt++;
    }
    @Test
    public void testSetandGetTicketId(){
        ticket.setTicket_id(123);
        assertEquals(123, ticket.getTicket_id());
    }

    @Test
    public void testSetPriceandGetPrice(){
        ticket.setPrice(100);
        assertEquals(100, ticket.getPrice());
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
        // no false test
        ticket.setClassVip(false);
        assertFalse(ticket.getClassVip());
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

        // no false test
        ticket.setTicketStatus(false);
        assertFalse(ticket.ticketStatus());
    }

    @Test
    public void testtoString(){
        assertEquals("Ticket{" +'\n'+
                "Price=" + 1000 + "KZT, " + '\n' +
                flight +'\n'+ "Vip status=" + false + '\n' +
                passenger +'\n'+ "Ticket was purchased=" + false + "\n}",ticket.toString());
    }
    @Test
    void testTicketStatusAfterCreation() {
        assertFalse(ticket.getTicketStatus());
    }
    void testTicketPriceAlwaysApplied() {
        assertNotEquals(0, ticket.getPrice());
    }

    @Test
    void testDiscountByAge() {

        passenger.setAge(10);
        ticket.setPrice(1000);
        assertEquals(500, ticket.getPrice());

        passenger.setAge(14); // boundary value
        ticket.setPrice(1000);
        assertEquals(500, ticket.getPrice());
        passenger.setAge(15); // boundary value
        ticket.setPrice(1000);
        assertEquals(1000, ticket.getPrice());

        passenger.setAge(59); // boundary value
        ticket.setPrice(1000);
        assertEquals(1000, ticket.getPrice());

        passenger.setAge(30);
        ticket.setPrice(1000);
        assertEquals(1000, ticket.getPrice());

        passenger.setAge(60); // boundary value
        ticket.setPrice(1000);
        assertEquals(0, ticket.getPrice());

        passenger.setAge(70);
        ticket.setPrice(1000);
        assertEquals(0, ticket.getPrice());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            passenger.setAge(0);
        });
        assertEquals("Age should be positive", exception.getMessage());
    }

    @Test
    void testServiceTaxApplied() {
        ticket.setPrice(1000);
        ticket.serviceTax();
        assertEquals(1120, ticket.getPrice());
    }

    @Test
    void testFlightPassengerInformationValid() {
        assertNotNull(ticket.getFlight());
        assertNotNull(ticket.getPassenger());
    }

    @Test
    void testIsTicketPriceValid() {
        Throwable exception1 = assertThrows(RuntimeException.class, () -> ticket.setPrice(-1));
        Assertions.assertEquals(("Invalid price value"), exception1.getMessage());
    }

    @Test
    void testIsFlightPassengerValid() {
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, 1000, null, false, passenger);
        });
        Assertions.assertEquals("Invalid flight input", exception1.getMessage());
        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, 1000, flight, false, null);
        });
        Assertions.assertEquals("Invalid passenger input", exception2.getMessage());
    }


}
