package Codebase;

import java.io.Console;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-12
 */

public class Flight {
    private int flightID;
    private String departTo;
    private String departFrom;
    private String code;
    private String company;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    Airplane airplane;
    
    public Flight(){}

    public Flight(int flight_id, String departTo, String departFrom, String code, String company, String dateFrom,String dateTo, Airplane airplane) {
        this.setFlightID(flight_id);
        this.setDateFrom(dateFrom);
        this.setDateTo(dateTo);
        this.setDepartFrom(departFrom);
        this.setDepartTo(departTo);
        this.setCode(code);
        this.setCompany(company);
        this.setAirplane(airplane);
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightid) {
        if (flightid <= 0) {
            throw new IllegalArgumentException("Flight ID should be a positive number");
        }
        this.flightID = flightid;
    }

    public String getDepartTo() {
        return departTo;
    }

    public void setDepartTo(String departTo) {
        if (departTo == null || departTo == "") {
            throw new IllegalArgumentException("the Depart to destination cannot be empty");
        }
        this.departTo = departTo;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        if (departFrom == null || departFrom == "") {
            throw new IllegalArgumentException("the Depart from place cannot be empty");
        }
        this.departFrom = departFrom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code == "") {
            throw new IllegalArgumentException("the code cannot be empty");
        }
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (company == null || company == "") {
            throw new IllegalArgumentException("the company cannot be empty");
        }
        this.company = company;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom){
        if (dateFrom == null || dateFrom == "") {
            throw new IllegalArgumentException("Date from cannot be empty");
        }
        this.dateFrom = Flight.stringToTimestamp(dateFrom);
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo){
        if (dateTo == null || dateTo == "") {
            throw new IllegalArgumentException("Date To cannot be empty");
        }
        this.dateTo = Flight.stringToTimestamp(dateTo);
    }

    public void setAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("airplane cannot be empty");
        }
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String toString()
    {
            return "Flight{" + airplane.toString() +
                    ", date to=" +  getDateTo() + ", " + '\'' +
                    ", date from='" + getDateFrom() + '\'' +
                    ", depart from='" + getDepartFrom() + '\'' +
                    ", depart to='" + getDepartTo() + '\'' +
                    ", id=" + getFlightID() + '\'' +
                    ", company=" + getCompany() + '\'' +
                    ", code=" + getCode() + '\'' +
                    '}';
    }

    public static Timestamp stringToTimestamp(String dateStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("This city name is invalid!");
        }
        Timestamp timestamp;
        timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
