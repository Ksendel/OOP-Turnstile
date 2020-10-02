package com.company;

import java.util.*;

public class Turnstile {
    private Register register;
    private ArrayList<TicketRecord> records;

    public Turnstile(Register register) {
        this.register = register;
        this.records = new ArrayList<>();
    }

    public boolean pass(String id) {
        if (register.checkTicket(id)) {
            register.decrement(id);
            records.add(new TicketRecord(id, true));
            return true;
        } else{
            records.add(new TicketRecord(id, false));
            return false;
        }
    }

//    public Ticket ticketInfo(String id) {
//
//    }

    public ArrayList<TicketRecord> getRecords() {
        return records;
    }

    public long getPassed() {
        return records.stream().filter(r -> r.isPassed()).count();
    }

    public long getUnpassed() {
        return records.stream().filter(r -> !r.isPassed()).count();
    }

    public Map<String, Long> getPassedGrouped(){
        long studentPassCount = records.stream()
                .filter(r -> r.isPassed())
                .filter(r -> register.returnTicket(r.getTicketId()) instanceof StudentTicket)
                .count();
        long standardPassCount = records.stream()
                .filter(r -> r.isPassed())
                .filter(r -> register.returnTicket(r.getTicketId()) instanceof StandardTicket)
                .count();
        long pupilTicket = records.stream()
                .filter(r -> r.isPassed())
                .filter(r -> register.returnTicket(r.getTicketId()) instanceof PupilTicket)
                .count();

        Map<String, Long> passedGrouped = new HashMap<>();
        passedGrouped.put("StudentTicket", studentPassCount);
        passedGrouped.put("StandardTicket", standardPassCount);
        passedGrouped.put("PupilTicket", pupilTicket);

        return passedGrouped;
    }

    public Map<String, Long> getUnPassedGrouped(){
        long studentPassCount = records.stream()
                .filter(r -> !r.isPassed())
                .filter(r -> register.returnTicket(r.getTicketId()) instanceof StudentTicket)
                .count();
        long standardPassCount = records.stream()
                .filter(r -> !r.isPassed())
                .filter(r -> register.returnTicket(r.getTicketId()) instanceof StandardTicket)
                .count();
        long pupilTicket = records.stream()
                .filter(r -> !r.isPassed())
                .filter(r -> register.returnTicket(r.getTicketId()) instanceof PupilTicket)
                .count();

        Map<String, Long> unPassedGrouped = new HashMap<String, Long>();
        unPassedGrouped.put("StudentTicket", studentPassCount);
        unPassedGrouped.put("StandardTicket", standardPassCount);
        unPassedGrouped.put("PupilTicket", pupilTicket);

        return unPassedGrouped;
    }
}
