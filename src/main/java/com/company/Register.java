package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Register {

    private Map<String, Ticket> tickets;
    public static final int price = 8;

    public StudentTicket createStudentTicket(String studentId, LocalDate validity, int trips) {
        StudentTicket studentTicket = new StudentTicket(studentId, validity, trips);
        tickets.put(studentTicket.getId(), studentTicket);

        return studentTicket;
    }


    public StandardTicket createStandardTicked() {
        StandardTicket standardTicket = new StandardTicket();
        tickets.put(standardTicket.getId(), standardTicket);

        return standardTicket;
    }

    public PupilTicket createPupilTicket(String pupilId, LocalDate validity, int trips) {
        PupilTicket pupilTicket = new PupilTicket(pupilId, validity, trips);
        tickets.put(pupilTicket.getId(), pupilTicket);

        return pupilTicket;
    }

    public Register() {
        tickets = new HashMap<>();
    }

    public boolean checkTicket(String id) {
        Ticket ticket = tickets.get(id);
        LocalDate now = LocalDate.now();
        if (ticket instanceof StandardTicket) {
            StandardTicket standard = (StandardTicket) ticket;
            return standard.getMoney() >= 8;
        } else if (ticket instanceof StudentTicket) {
            StudentTicket student = (StudentTicket) ticket;
            return (now.isBefore(student.getValidity()) || now.isEqual(student.getValidity())) && student.getTrips() > 0;
        } else if (ticket instanceof PupilTicket) {
            PupilTicket pupil = (PupilTicket) ticket;
            return (now.isBefore(pupil.getValidity()) || now.isEqual(pupil.getValidity())) && pupil.getTrips() > 0;
        } else return false;
//        return ticket.getTrips() > 0 && (now.isBefore(ticket.getValidity()) || now.isEqual(ticket.getValidity()));
    }

    public int decrement(String id) {
        Ticket ticket = tickets.get(id);
        if (ticket instanceof StudentTicket) {
            StudentTicket student = (StudentTicket) ticket;
            int rest = student.getTrips() - 1;
            if(rest >= 0){
                student.setTrips(rest);
                return rest;
            } else throw new IllegalStateException("No more trips");
        } else if (ticket instanceof PupilTicket) {
            PupilTicket pupilTicket = (PupilTicket) ticket;
            int rest = pupilTicket.getTrips() - 1;
            if(rest >= 0){
                pupilTicket.setTrips(rest);
                return rest;
            } else throw new IllegalStateException("No more trips");
        } else if (ticket instanceof StandardTicket) {
            StandardTicket standard = (StandardTicket) ticket;
            int rest = standard.getMoney() - 8;
            if(rest >= 0){
                standard.setMoney(rest);
                return rest;
            } else throw new IllegalStateException("Not enough money");
        } else throw new IllegalArgumentException("Illegal type of ticket");

    }

    public Ticket returnTicket(String id) {
        return tickets.get(id);
    }

    public boolean deposit(String id, int money){
        Ticket t = tickets.get(id);
        if(t instanceof StandardTicket){
            ((StandardTicket) t).setMoney(money);
            return true;
        } else return false;
    }

}
