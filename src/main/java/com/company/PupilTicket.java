package com.company;

import java.time.LocalDate;
import java.util.UUID;

public class PupilTicket extends Ticket{
    private final String pupilId;
    private LocalDate validity;
    private int trips;

    public PupilTicket(String pupilId, LocalDate validity, int trips) {
        super();
        this.trips = trips;
        this.pupilId = pupilId;
        this.validity = validity;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public String getPupilId() {
        return pupilId;
    }
}
