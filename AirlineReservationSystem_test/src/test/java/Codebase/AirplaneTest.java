package Codebase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author hannahshaw
 * @version 2.0
 * @date 2023-08-02
 */

public class AirplaneTest {

    Airplane airplane;
    Airplane airplane1, airplane2;

    @BeforeEach
    public void setUp() {
        airplane = new Airplane(1, "Boeing 737",
                50, 50, 50);


    }

    @Test
    public void testGetAirplaneID() {
        assertEquals(1, airplane.getAirplaneID());

        int expectedAirplaneID = 123;
        airplane.setAirplaneID(expectedAirplaneID);
        int actualAirplaneID = airplane.getAirplaneID();
        assertEquals(expectedAirplaneID, actualAirplaneID);
    }

    @Test
    public void testSetAirplaneID() {
        airplane.setAirplaneID(2);
        assertEquals(2, airplane.getAirplaneID());

        Airplane airplane1 = new Airplane();
        airplane1.setAirplaneID(1);
        Assertions.assertEquals(1, airplane1.getAirplaneID());
    }

    @Test
    public void testAirplaneIDReturn0() {
        airplane.setAirplaneID(0);
        Assertions.assertEquals(0, airplane.getAirplaneID());
    }

    @Test
    public void testGetAirplaneModel() {
        assertEquals("Boeing 737", airplane.getAirplaneModel());
    }

    @Test
    public void testSetAirplaneModel() {
        airplane.setAirplaneModel("Boeing 777");
        assertEquals("Boeing 777", airplane.getAirplaneModel());
    }

    @Test
    public void testGetBusinessSitsNumber() {
        assertEquals(50, airplane.getBusinessSitsNumber());
    }

    @Test
    public void testSetBusinessSitsNumber() {
        Throwable e;
        airplane.setBusinessSitsNumber(18);
        assertEquals(18, airplane.getBusinessSitsNumber());
        airplane.setBusinessSitsNumber(0);
        assertEquals(0, airplane.getBusinessSitsNumber());

        assertThrows(IllegalArgumentException.class, () -> airplane.setBusinessSitsNumber(301));

        airplane.setBusinessSitsNumber(1);
        Assertions.assertEquals(1, airplane.getBusinessSitsNumber());
        e = assertThrows(IllegalArgumentException.class, () -> airplane.setBusinessSitsNumber(300));
        Assertions.assertEquals("Seat number must be in the range [1, 300].",e.getMessage());

        airplane.setBusinessSitsNumber(150);
        Assertions.assertEquals(150, airplane.getBusinessSitsNumber());

        e = assertThrows(IllegalArgumentException.class, () -> airplane.setBusinessSitsNumber(-1));
        Assertions.assertEquals("Seat number must be positive.",e.getMessage());

        assertThrows(IllegalArgumentException.class, () -> airplane.setBusinessSitsNumber(500));
    }

    @Test
    public void testGetEconomySitsNumber() {
        assertEquals(50, airplane.getEconomySitsNumber());
    }

    @Test
    public void testSetEconomySitsNumber() {
        airplane.setEconomySitsNumber(200);
        assertEquals(200, airplane.getEconomySitsNumber());
        airplane.setEconomySitsNumber(0);
        assertEquals(0, airplane.getEconomySitsNumber());
        Throwable e = assertThrows(IllegalArgumentException.class, () -> airplane.setEconomySitsNumber(-1));
        Assertions.assertEquals("Seat number must be positive.",e.getMessage());
    }

    @Test
    public void testGetCrewSitsNumber() {
        assertEquals(50, airplane.getCrewSitsNumber());
    }

    @Test
    public void testSetCrewSitsNumber() {
        airplane.setCrewSitsNumber(4);
        assertEquals(4, airplane.getCrewSitsNumber());
        airplane.setCrewSitsNumber(0);
        assertEquals(0, airplane.getCrewSitsNumber());
        airplane.setCrewSitsNumber(141);
        assertEquals(141, airplane.getCrewSitsNumber());
        Throwable e = assertThrows(IllegalArgumentException.class, () -> airplane.setCrewSitsNumber(-1));
        Assertions.assertEquals("Seat number must be positive.",e.getMessage());
    }
    @Test
    public void testSetNegativeSitsNumber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            airplane.setBusinessSitsNumber(-1);
        });
        Assertions.assertEquals("Seat number must be positive.", exception.getMessage());
    }

    @Test
    public void testOutRangeNumber(){
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
            airplane = new Airplane(1, "Boeing 737",
                    100, 100, 101);
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception1.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> {
            airplane.setEconomySitsNumber(400);
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> {
            airplane.setBusinessSitsNumber(400);
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception3.getMessage());

        Throwable exception4 = assertThrows(IllegalArgumentException.class, () -> {
            airplane.setCrewSitsNumber(400);
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception4.getMessage());

        Throwable exception5 = assertThrows(IllegalArgumentException.class, () -> {
            airplane = new Airplane(1, "Boeing 737",
                    101, 100,100 );
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception5.getMessage());

        airplane = new Airplane(1, "Boeing 737",
                100, 100,100 );
        Assertions.assertEquals(100,airplane.getBusinessSitsNumber());

        Throwable exception6 = assertThrows(IllegalArgumentException.class, () -> {
            airplane = new Airplane(1, "Boeing 737",
                    0, 0,0 );
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception6.getMessage());
        Throwable exception7 = assertThrows(IllegalArgumentException.class, () -> {
            airplane = new Airplane(1, "Boeing 737",
                    200, 100, 50);
        });
        Assertions.assertEquals("Seat number must be in the range [1, 300].", exception7.getMessage());

        Throwable exception8 = assertThrows(IllegalArgumentException.class, () -> {
                    airplane = new Airplane(1, "Boeing 737",
                            1, 0, 0);
                });
        Assertions.assertEquals("Require all field",exception8.getMessage());

        exception8 = assertThrows(IllegalArgumentException.class, () -> {
                    airplane = new Airplane(1, "Boeing 737",
                            300, 0, 0);
                });
        Assertions.assertEquals("Require all field",exception8.getMessage());

    }

    @Test
    public void testToString() {
        setUp();
        assertEquals("Airplane{" +
                "model=" + "Boeing 737" + '\'' +
                ", business sits=" + "50" + '\'' +
                ", economy sits=" + "50" + '\'' +
                ", crew sits=" + "50" + '\'' +
                '}', airplane.toString());

    }

    @Test
    public void testGetAirPlaneInfo() {
        Airplane ap1 = Airplane.getAirPlaneInfo(1);
        assertEquals("Airplane{" +
                "model=" + "Boeing 737" + '\'' +
                ", business sits=" + "50" + '\'' +
                ", economy sits=" + "50" + '\'' +
                ", crew sits=" + "50" + '\'' +
                '}', ap1.toString());

        Throwable exception6 = assertThrows(RuntimeException.class, () -> {
            Airplane ap2 = Airplane.getAirPlaneInfo(10000);
        });
        Assertions.assertEquals("No such airplane exists.", exception6.getMessage());
    }
}