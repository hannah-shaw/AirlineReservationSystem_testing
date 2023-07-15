package Codebase;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicketSystemTest {


    TicketSystem ticketSystem;

    @BeforeEach
    public void setUp(){
        // Dummy Data:
        Airplane DummyAirplane = new Airplane(5171, "Boeing747", 30, 130, 6);
        Passenger DummyPassenger = new Passenger("Barry","Ellen", 30, "Man", "HuangYH723@outlook.com", "0412345678", "CN", "10001", 2000);
        Flight DummyFlight = new Flight(10, "SHANGHAI", "SUZHOU", "0001", "EasternChina", "05/07/2023 13:55:00", "16/07/2023 01:35:00", DummyAirplane);
        Ticket DummyTicket = new Ticket(1, 1000, DummyFlight, false, DummyPassenger);
        FlightCollection DummyFlightCollection = new FlightCollection();
        TicketCollection DummyTicketCollection = new TicketCollection();
        FlightCollection.flights.add(DummyFlight);
        TicketCollection.tickets.add(DummyTicket);

        // Create ticketSystem by Dummy Data
        ticketSystem = new TicketSystem(DummyTicketCollection, DummyFlightCollection);

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

//    @Test
//    public final void ChooseTicketTicketWithValidCity() {
//        try {
//
//            ticketSystem.chooseTicket("SUZHOU", "SHANGHAI");
//            String inputID = String.format("10");
//            System.setIn(new ByteArrayInputStream(inputID.getBytes()));
//
//        } catch (Exception e3) {
//            throw new RuntimeException(e3);
//        }
//    }




}