package com.example.busapp;

public class BusPOJO {
    private String busID;
    private String busNumber;
    private String busDate;
    private String busFrom;
    private String busTo;
    private String price;

    public BusPOJO(){
    }

    public BusPOJO(String busDate, String busTo, String busFrom) {
        this.busDate = busDate;
        this.busTo = busTo;
        this.busFrom = busFrom;
    }

    public BusPOJO(String busID, String busNumber, String busDate, String busFrom, String busTo, String price) {
        super();
        this.busID = busID;
        this.busNumber = busNumber;
        this.busDate = busDate;
        this.busFrom = busFrom;
        this.busTo = busTo;
        this.price = price;
    }





    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusDate() {
        return busDate;
    }

    public void setBusDate(String busDate) {
        this.busDate = busDate;
    }

    public String getBusFrom() {
        return busFrom;
    }

    public void setBusFrom(String busFrom) {
        this.busFrom = busFrom;
    }

    public String getBusTo() {
        return busTo;
    }

    public void setBusTo(String busTo) {
        this.busTo = busTo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "BusPOJO [busID=" + busID + ", busNumber=" + busNumber + ", busDate=" + busDate + ", busFrom=" + busFrom
                + ", busTo=" + busTo + ", price=" + price + "]";
    }
}
