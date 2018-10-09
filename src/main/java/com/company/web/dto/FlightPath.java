package com.company.web.dto;

public class FlightPath {
    private long airplaneId;
    private long destinationId;

    public long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }
}