package Codebase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hannahshaw
 * @version 1.0
 * @date 2023-07-12
 */



public class Airplane
{
    private int airplaneID;
    private String airplaneModel;
    private int businessSitsNumber;
    private int economySitsNumber;
    private int crewSitsNumber;
    public static final List<Airplane> airplanes = new ArrayList<>();

    public Airplane(){}

    public Airplane(int airplaneID, String airplaneModel, int businessSitsNumber, int economySitsNumber, int crewSitsNumber)
    {
        this.airplaneID=airplaneID;
        this.airplaneModel = airplaneModel;
        this.setBusinessSitsNumber(businessSitsNumber);
        this.setEconomySitsNumber(economySitsNumber);
        this.setCrewSitsNumber(crewSitsNumber);
        if(checkAllPropertiesNotEmpty()){
            airplanes.add(this);
        }
        else{
            throw new IllegalArgumentException("Require all field");
        }
    }

    public boolean checkAllPropertiesNotEmpty() {
        return airplaneID != 0 && airplaneModel != null && businessSitsNumber != 0 &&
                economySitsNumber != 0 && crewSitsNumber != 0;
    }

    //Requirement: Seat number must be in the range [1, 300].
    public void validateSitsNumber(int businessSitsNumber, int economySitsNumber,int crewSitsNumber){
        int total = businessSitsNumber + economySitsNumber + crewSitsNumber;
        if(total>300 || total<1) {
            throw new IllegalArgumentException("Seat number must be in the range [1, 300].");
        }
    }

    public int getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(int airplaneID) {
        this.airplaneID = airplaneID;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public int getBusinessSitsNumber() {
        return businessSitsNumber;
    }

    public void setBusinessSitsNumber(int businessSitsNumber) {
        if(businessSitsNumber<0) {
            throw new IllegalArgumentException("Seat number must be positive.");
        }
        this.validateSitsNumber(businessSitsNumber,this.economySitsNumber,this.crewSitsNumber);
        this.businessSitsNumber = businessSitsNumber;
    }

    public int getEconomySitsNumber() {
        return economySitsNumber;
    }

    public void setEconomySitsNumber(int economySitsNumber) {
        if(economySitsNumber<0) {
            throw new IllegalArgumentException("Seat number must be positive.");
        }
        this.validateSitsNumber(this.businessSitsNumber,economySitsNumber,this.crewSitsNumber);
        this.economySitsNumber = economySitsNumber;
    }

    public int getCrewSitsNumber() {
        return crewSitsNumber;
    }

    public void setCrewSitsNumber(int crewSitsNumber) {
        if(crewSitsNumber<0) {
            throw new IllegalArgumentException("Seat number must be positive.");
        }
        this.validateSitsNumber(this.businessSitsNumber,this.economySitsNumber,crewSitsNumber);
        this.crewSitsNumber = crewSitsNumber;
    }

    public String toString() {
        return "Airplane{" +
                "model=" + getAirplaneModel() + '\'' +
                ", business sits=" + getBusinessSitsNumber() + '\'' +
                ", economy sits=" + getEconomySitsNumber() + '\'' +
                ", crew sits=" + getCrewSitsNumber() + '\'' +
                '}';
    }

	public static Airplane getAirPlaneInfo(int airplane_id) {
        Airplane airplane = null;
        for (Airplane a : airplanes) {
            if (a.getAirplaneID() == airplane_id) {
                airplane = a;
                return airplane;
            }
        }
        throw new RuntimeException("No such airplane exists.");
	}
}