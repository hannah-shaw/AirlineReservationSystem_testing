package Codebase;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TicketSystemTest {

    private TicketSystem ticketSystem;

    @Before
    public void setUp() {
        ticketSystem = new TicketSystem();
    }

    @Test
    public void testBuyTicket() throws Exception {
        // 模拟用户输入
        String simulatedUserInput = "John\nDoe\n30\nM\njohn.doe@example.com\n+1234567890\n12345678\n1\n1234567812345678\n123";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
            ticketSystem.buyTicket(1);  // 假设 1 是一个有效的票号
        } finally {
            System.setIn(stdin);
        }
    }

    // 在这里你可以添加更多的测试方法来测试其它情况，例如测试购票失败的情况，测试选择票等。
}
