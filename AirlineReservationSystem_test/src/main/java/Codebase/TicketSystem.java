package Codebase;

import org.junit.jupiter.api.Assertions;

import java.sql.*;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class TicketSystem {

    Passenger passenger = new Passenger();
    Flight flight = new Flight();
    Ticket ticket = new Ticket();
    private Scanner in;
    private TicketCollection ticketCollection;
    private FlightCollection flightCollection;
    //combine the construct function of BuyTicket and ChooseTicket
    public TicketSystem(TicketCollection ticketCollection,FlightCollection flightCollection,Scanner in){
        this.ticketCollection = ticketCollection;
        this.flightCollection = flightCollection;
        this.in = in;
    }

    public void chooseTicket(String city1, String city2) throws Exception
    {
        int counter = 1;
        int idFirst = 0;
        int idSecond = 0;

        flight = flightCollection.getFlightInfo(city1, city2);

        // Check if the city parameters are valid
        if(city1 == "" || city2 == ""){
            throw new IllegalArgumentException("City name cannot miss");
        }
        if (!city1.matches("^[a-zA-Z\\s]+$") || !city2.matches("^[a-zA-Z\\s]+$"))
            throw new IllegalArgumentException("City name can only contain letter and space");

        if(flight != null)
        {
            ticketCollection.getAllTickets();
            System.out.println("\nEnter ID of ticket you want to choose:");
            int ticket_id = in.nextInt();
            this.buyTicket(ticket_id);
        }
    }

    public void showTicket()
    {
        try{
            System.out.println("You have bought a ticket for flight" + ticket.flight.getDepartFrom() + " - " + ticket.flight.getDepartTo()
            + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        }
        catch (NullPointerException e){
            return;
        }
    }

    public void buyTicket(int ticket_id) throws Exception{
        //method for buying one ticket with direct flight
        int flight_id = 0;

        Ticket validTicket = ticketCollection.getTicketInfo(ticket_id);
        System.out.println(validTicket);
        //if there is a valid ticket id was input then we buy it, otherwise show message.
        if(validTicket == null)
        {
            System.out.println("This ticket does not exist or has been booked.");
            throw new IllegalArgumentException("This ticket does not exist or has been booked.");
//            System.out.println("This ticket does not exist or has been booked.");
        }else{
            //select flight_id from ticket where ticke_id=" + ticket_id

            flight_id = validTicket.getFlight().getFlightID();

            try{
                System.out.println("Enter your First Name: ");
                //String firstName = "";
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);
                System.out.println("Your First Name: " + firstName);


                System.out.println("Enter your Second name:");
                //String secondName = "";
                String secondName = secondName = in.nextLine();
                passenger.setSecondName(secondName); //setting passengers info
                System.out.println("Your Second Name: " + secondName);


                System.out.println("Enter your age:");
                //Integer age = 0;
                //in.next();
                int age = Integer.parseInt(in.nextLine());
                passenger.setAge(age);
                System.out.println("Your age: " + age);


                System.out.println("Enter your gender: ");
                //String gender = "";
                String gender = in.nextLine();
                passenger.setGender(gender);
                System.out.println("Your gender: " + gender);


                System.out.println("Enter your e-mail address");
                //String email = "";
                String email = in.nextLine();
                passenger.setEmail(email);
                System.out.println("Your eamil: " + email);


                System.out.println("Enter your phone number (+7):");
                //String phoneNumber = "";
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);
                System.out.println("Your phoneNumber: " + phoneNumber);

                System.out.println("Enter your passport number:");
                //String passportNumber = "";
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);
                System.out.println("Your passportNumber: " + passportNumber);
                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = Integer.parseInt(in.nextLine());
                if (purch == 0)
                {
                    return;
                } else
                {
                    System.out.println("----------------------You are buying ticket now.----------------------");
                    flight = flightCollection.getFlightInfo(flight_id);

                    int airplane_id = flight.getAirplane().getAirplaneID();

                    Airplane airplane = Airplane.getAirPlaneInfo(airplane_id);

                    ticket = TicketCollection.getTicketInfo(ticket_id);

                    ticket.setPassenger(passenger);
                    ticket.setTicket_id(ticket_id);
                    ticket.setFlight(flight);
                    ticket.serviceTax();
                    ticket.setPrice(ticket.getPrice());
                    ticket.setClassVip(ticket.getClassVip());
                    ticket.setTicketStatus(true);
                    if (ticket.getClassVip() == true)
                    {
                        airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
                    } else
                    {
                        airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
                    }

                }
                System.out.println("Your bill: " + ticket.getPrice() + "\n");

                System.out.println("Enter your card number:");
                //String cardNumber = "";
                String cardNumber = in.nextLine();
                passenger.setCardNumber(cardNumber);
                System.out.println("Your card number is:" + cardNumber);

                System.out.println("Enter your security code:");
                int securityCode = Integer.parseInt(in.nextLine());
                //Integer securityCode = 0;
                passenger.setSecurityCode(securityCode);
                System.out.println("Security code confirm");


            } catch (PatternSyntaxException patternException)
            {
                patternException.printStackTrace();
            }
        }
    }
    public void buyTicket(int ticket_id_first, int ticket_id_second) throws Exception{
        //method for buying two tickets with transfer flight
        int flight_id_first = 0;

        int flight_id_second = 0;


        System.out.println(ticket_id_first + " " + ticket_id_second);

        Ticket validTicketfirst = TicketCollection.getTicketInfo(ticket_id_first);

        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_first);


        System.out.println("Processing...");

        //if there is a valid ticket id was input then we buy it, otherwise show message

        if(validTicketfirst!=null || validTicketSecond!=null)
        {
            System.out.println("This ticket does not exist.");
            return;
        }

        else
        {
            flight_id_first = validTicketfirst.getFlight().getFlightID();

            flight_id_second = validTicketfirst.getFlight().getFlightID();


            try
            {
                System.out.println("Enter your First Name: ");
                String firstName = "";
                passenger.setFirstName(firstName);


                System.out.println("Enter your Second name:");
                String secondName = "";
                passenger.setSecondName(secondName); //setting passengers info

                System.out.println("Enter your age:");
                Integer age = 0;
                in.nextLine();
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = "";
                //passenger.setGender(gender));

                System.out.println("Enter your e-mail address");
                String email = "";
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = "";
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = "";
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0)
                {
                    return;
                }
                else
                {

                    //  "select * from flight, airplane where flight_id=" + flight_id_first + " and flight.airplane_id=airplane.airplane_id");
                    Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);

                    int airplane_id_first = flight_first.getAirplane().getAirplaneID();

                    Airplane airplane_first = Airplane.getAirPlaneInfo(airplane_id_first);

                    Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);

                    int airplane_id_second = flight_second.getAirplane().getAirplaneID();

                    Airplane airpairplane_second  = Airplane.getAirPlaneInfo(airplane_id_second);

                    Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);

                    Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                    ticket_first.setPassenger(passenger);
                    ticket_first.setTicket_id(ticket_id_first);
                    ticket_first.setFlight(flight_first);
                    ticket_first.setPrice(ticket_first.getPrice());
                    ticket_first.setClassVip(ticket_first.getClassVip());
                    ticket_first.setTicketStatus(true);

                    if (ticket_first.getClassVip() == true)
                    {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else
                    {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }

                    System.out.println("--*-*-");

                    ticket_second.setPassenger(passenger);
                    ticket_second.setTicket_id(ticket_id_second);
                    ticket_second.setFlight(flight_first);
                    ticket_second.setPrice(ticket_second.getPrice());
                    ticket_second.setClassVip(ticket_second.getClassVip());
                    ticket_second.setTicketStatus(true);
                    if (ticket_second.getClassVip() == true)
                    {
                        airpairplane_second.setBusinessSitsNumber(airpairplane_second.getBusinessSitsNumber() - 1);
                    } else
                    {
                        airpairplane_second.setEconomySitsNumber(airpairplane_second.getEconomySitsNumber() - 1);
                    }

                    System.out.println("--*-*-");

                    ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());

                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    System.out.println("Enter your card number:");

                    String cardNumber = "";
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    Integer securityCode = 0;
                    passenger.setSecurityCode(securityCode);

                }
            } catch (PatternSyntaxException patternException)
            {
                patternException.printStackTrace();
            }
        }
    }
}
