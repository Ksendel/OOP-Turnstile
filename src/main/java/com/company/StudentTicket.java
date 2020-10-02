package com.company;

import java.time.LocalDate;
import java.util.UUID;

public class StudentTicket extends Ticket {
    private final String studentId;
    private int trips;
    private LocalDate validity;

    StudentTicket(String studentId, LocalDate validity, int trips) {
        super();
        this.studentId = studentId;
        setValidity(validity);
        setTrips(trips);
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public String getStudentId() {
        return studentId;
    }
}
