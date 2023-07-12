package Codebase;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-12
 */
public class PassengerTest {
    Passenger passenger;
    @BeforeEach
    void setUp() {
        passenger = new Passenger("Maggie","Smith",22,"Woman",
                "123@gmail.com","0412345678","1234567","123456",123);
    }

    @Test
    public void testAllGetFunction(){
        setUp();
        assertEquals("Maggie",passenger.getFirstName());
        assertEquals("Smith",passenger.getSecondName());
        assertEquals(22,passenger.getAge());
        assertEquals("Woman",passenger.getGender());
        assertEquals("123@gmail.com",passenger.getEmail());
        assertEquals("0412345678",passenger.getPhoneNumber());
        assertEquals("1234567",passenger.getPassport());
        assertEquals("123456",passenger.getCardNumber());
        assertEquals(123,passenger.getSecurityCode());
    }
    @Test
    public void testToString(){
        setUp();
        assertEquals("Passenger{" + " Fullname= "+ "Maggie"+" "+"Smith"+
                " ,email='" + "123@gmail.com" + '\'' +
                ", phoneNumber='" + "0412345678" + '\'' +
                ", passport='" + "1234567" +
                '}',passenger.toString());
    }


    @Test
    public void testAllFieldsRequired() {
        Throwable exception1 = assertThrows(IllegalArgumentException.class, () -> new Passenger("","Smith",
                22,"Woman","123@gmail.com","0412345678","1234567","123456",123));
        Assertions.assertEquals("Firstname cannot be empty", exception1.getMessage());

        Throwable exception2 = assertThrows(IllegalArgumentException.class, () -> new Passenger("Maggie","",
                22,"Woman","123@gmail.com","0412345678","1234567","123456",123));
        Assertions.assertEquals("secondName cannot be empty", exception2.getMessage());

        Throwable exception3 = assertThrows(IllegalArgumentException.class, () -> new Passenger("Maggie","Smith",
                22,"","123@gmail.com","0412345678","1234567","123456",123));
        Assertions.assertEquals("Gender cannot be empty", exception3.getMessage());

        Throwable exception4 = assertThrows(IllegalArgumentException.class, () -> new Passenger("Maggie","Smith",
                22,"Woman","","0412345678","1234567","123456",123));
        Assertions.assertEquals("Email cannot be empty", exception4.getMessage());

        Throwable exception5 = assertThrows(IllegalArgumentException.class, () -> new Passenger("Maggie","Smith",
                22,"Woman","123@gmail.com","","1234567","123456",123));
        Assertions.assertEquals("phoneNumber cannot be empty", exception5.getMessage());

        Throwable exception6 = assertThrows(IllegalArgumentException.class, () -> new Passenger("Maggie","Smith",
                22,"Woman","123@gmail.com","","","123456",123));
        Assertions.assertEquals("Passport number cannot be empty", exception6.getMessage());

        Throwable exception7 = assertThrows(IllegalArgumentException.class, () -> new Passenger("Maggie","Smith",
                22,"Woman","123@gmail.com","0412345678","1234567","",123));
        Assertions.assertEquals("cardNumber cannot be empty", exception7.getMessage());

    }

    @Test
    public void testCreatePassengerWithInvalidGender() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",22,"NotWoman",
                    "123@gmail.com","0412345678","1234567","123456",123);
        });
        Assertions.assertEquals("Invalid gender field value", e.getMessage());
    }

    @Test
    public void testCreatePassengerWithInvalidName() {
        Throwable e1 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("123Maggie","Smith",22,"Woman",
                    "123@gmail.com","0412345678","1234567","123456",123);
        });
        Assertions.assertEquals("Firstname should not start with a number or symbol and can contain only lower-case and upper-case alphabet letters", e1.getMessage());

        Throwable e2 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith123",22,"Woman",
                    "123@gmail.com","0412345678","1234567","123456",123);
        });
        Assertions.assertEquals("secondName should not start with a number or symbol and can contain only lower-case and upper-case alphabet letters", e2.getMessage());
    }

    @Test
    public void testCreatePassengerWithInvalidAge() {
        Throwable e1 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",-22,"Woman",
                    "123@gmail.com","0412345678","1234567","123456",123);
        });
        Assertions.assertEquals("Age should be positive", e1.getMessage());

    }
    @Test
    public void testCreatePassengerWithInvalidEmail() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",22,"Woman",
                    "123gmail.com","0412345678","1234567","123456",123);
        });
        Assertions.assertEquals("Invalid email format", e.getMessage());
    }

    @Test
    public void testPhoneNumber() {
        Throwable e1 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",22,"Woman",
                    "123@gmail.com","12345678","1234567","123456",123);
        });
        Assertions.assertEquals("Invalid phone number", e1.getMessage());

        Throwable e2 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",22,"Woman",
                    "123@gmail.com","+61 123456789","1234567","123456",123);
        });
        Assertions.assertEquals("Invalid phone number", e2.getMessage());

        Throwable e3 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",22,"Woman",
                    "123@gmail.com","0412345","1234567","123456",123);
        });
        Assertions.assertEquals("Invalid phone number", e3.getMessage());

        new Passenger("Maggie","Smith",22,"Woman",
                "123@gmail.com","+61 412345678","1234567","123456",123);

        new Passenger("Maggie","Smith",22,"Woman",
                "123@gmail.com","0412345678","1234567","123456",123);

        new Passenger("Maggie","Smith",22,"Woman",
                "123@gmail.com","0512345678","1234567","123456",123);
    }

    @Test
    public void testPassport() {
        Throwable e1 = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Maggie","Smith",22,"Woman",
                    "123@gmail.com","0512345678","123456789012345","123456",123);
        });
        Assertions.assertEquals("Passport number should not be more than 9 characters long", e1.getMessage());

    }


}