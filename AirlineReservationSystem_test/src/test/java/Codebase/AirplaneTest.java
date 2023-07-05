package Codebase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-05
 */

class AirplaneTest {

    Airplane airplane;

    @BeforeEach
    void setUp() {
        airplane = new Airplane(1, "Boeing 737",
                12, 147, 5);
    }

    @Test
    void testGetAirplaneID() {
        assertEquals(1, airplane.getAirplaneID());
    }

    @Test
    void testSetAirplaneID() {
        airplane.setAirplaneID(2);
        assertEquals(2, airplane.getAirplaneID());
    }

    @Test
    void testGetAirplaneModel() {
        assertEquals("Boeing 737", airplane.getAirplaneModel());
    }

    @Test
    void testSetAirplaneModel() {
        airplane.setAirplaneModel("Boeing 777");
        assertEquals("Boeing 777", airplane.getAirplaneModel());
    }

    @Test
    void testGetBusinessSitsNumber() {
        assertEquals(12, airplane.getBusinessSitsNumber());
    }

    @Test
    void testSetBusinessSitsNumber() {
        airplane.setBusinessSitsNumber(18);
        assertEquals(18, airplane.getBusinessSitsNumber());
    }

    @Test
    void testGetEconomySitsNumber() {
        assertEquals(147, airplane.getEconomySitsNumber());
    }

    @Test
    void testSetEconomySitsNumber() {
        airplane.setEconomySitsNumber(200);
        assertEquals(200, airplane.getEconomySitsNumber());
    }

    @Test
    void testGetCrewSitsNumber() {
        assertEquals(5, airplane.getCrewSitsNumber());
    }

    @Test
    void testSetCrewSitsNumber() {
        airplane.setCrewSitsNumber(4);
        assertEquals(4, airplane.getCrewSitsNumber());
    }
    @Test
    void testSetNegativeSitsNumber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            airplane.setBusinessSitsNumber(-1);
        });
        Assertions.assertEquals("Seat number must be positive.", exception.getMessage());
    }

    @Test
    void testOutRangeNumber(){
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> {
            airplane = new Airplane(1, "Boeing 737",
                    100, 200, 5);
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
    }

    @Test
    void testToString() {
        assertEquals("Airplane{" +
                "model=" + "Boeing 737" + '\'' +
                ", business sits=" + "12" + '\'' +
                ", economy sits=" + "147" + '\'' +
                ", crew sits=" + "5" + '\'' +
                '}', airplane.toString());
    }

    @Test
    void testGetAirPlaneInfo() {
        Airplane ap1 = Airplane.getAirPlaneInfo(1);
        assertEquals("Airplane{" +
                "model=" + "Boeing 737" + '\'' +
                ", business sits=" + "12" + '\'' +
                ", economy sits=" + "147" + '\'' +
                ", crew sits=" + "5" + '\'' +
                '}', ap1.toString());

        Airplane ap2 = Airplane.getAirPlaneInfo(10000);
        assertEquals(null, ap2);
    }
}