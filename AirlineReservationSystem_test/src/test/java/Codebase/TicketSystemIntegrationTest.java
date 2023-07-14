package Codebase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketSystemIntegrationTest {
    private TicketSystem ticketSystem;

    @BeforeEach
    public void setup() {
        // 创建stubs
        TicketCollection ticketCollectionStub = new TicketCollection();
        FlightCollection flightCollectionStub = new FlightCollection();

        // 使用stubs创建TicketSystem的实例
        ticketSystem = new TicketSystem(ticketCollectionStub, flightCollectionStub);
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



