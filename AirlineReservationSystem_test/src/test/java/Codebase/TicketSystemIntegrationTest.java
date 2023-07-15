package Codebase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TicketSystemIntegrationTest {
    // Set Dummy Data
    private TicketSystem ticketSystem;
    Flight flight;
    Ticket ticket;
    Passenger passenger;
    Airplane airplane;
    MockedStatic<TicketCollection> ticketCollectionMock;
    MockedStatic<FlightCollection> flightCollectionMock;

    Airplane airplaneMock() {
        Airplane airplane = mock(Airplane.class);
        Mockito.when(airplane.getAirplaneID()).thenReturn(007);
        Mockito.when(airplane.getAirplaneModel()).thenReturn("MH370");
        Mockito.when(airplane.getBusinessSitsNumber()).thenReturn(80);
        Mockito.when(airplane.getEconomySitsNumber()).thenReturn(200);
        Mockito.when(airplane.getCrewSitsNumber()).thenReturn(20);
        return airplane;
    }

    Passenger passengerMock() {
        Passenger passenger = mock(Passenger.class);
        Mockito.when(passenger.getFirstName()).thenReturn("Jinhui");
        Mockito.when(passenger.getSecondName()).thenReturn("Yuan");
        Mockito.when(passenger.getPhoneNumber()).thenReturn("13290959072");
        Mockito.when(passenger.getSecurityCode()).thenReturn(2000);
        Mockito.when(passenger.getAge()).thenReturn(24);
        Mockito.when(passenger.getCardNumber()).thenReturn("10005");
        Mockito.when(passenger.getGender()).thenReturn("Man");
        Mockito.when(passenger.getEmail()).thenReturn("Jinhui@163.com");
        Mockito.when(passenger.getPassport()).thenReturn("CN");
        return passenger;
    }

    @BeforeAll
    public void SetUpForAll() {
        flight = mock(Flight.class);
        flight.setAirplane(airplaneMock());
        ticket = mock(Ticket.class);
        ticket.setFlight(flight);
    }

    @BeforeEach
    public void SetUpForEach() {
        // 创建stubs
        TicketCollection ticketCollectionStub = new TicketCollection();
        FlightCollection flightCollectionStub = new FlightCollection();

        // 使用stubs创建TicketSystem的实例
        ticketSystem = new TicketSystem(ticketCollectionStub, flightCollectionStub);
    }

    @Test
    public void testShowTicketWithValidTicket() throws ParseException {
        //ticketSystem = new TicketSystem(ticketCollectionMock, flightCollectionMock);
        Airplane airplane = mock(Airplane.class);
        Mockito.when(ticket.getFlight()).thenReturn(new Flight(1, "Sydney", "Melbourne", "xxx", "XXX", "15/06/2023 23:15:00", "16/06/2023 00:45:00", airplane));
        assertEquals("", "");
        assertTrue(ticketSystem.showTicket());
    }


    @Test
    public void testChooseTicketWithValidCity() throws Exception {
        // 创建 stubs
        TicketCollection ticketCollectionStub = new TicketCollection();
        FlightCollection flightCollectionStub = new FlightCollection();

        // 使用 stubs 创建 TicketSystem 的实例
        TicketSystem ticketSystem = new TicketSystem(ticketCollectionStub, flightCollectionStub);

        // 调用方法并进行断言检查
        try {
            ticketSystem.chooseTicket("validCity1", "validCity2");
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

}



