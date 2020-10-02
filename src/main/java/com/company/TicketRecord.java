package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketRecord {
    private String ticketId;
    private LocalDateTime date;
    private boolean passed;

    public TicketRecord(String ticketId, LocalDateTime date, boolean passed) {
        this.ticketId = ticketId;
        this.date = date;
        this.passed = passed;
    }

    public TicketRecord(String ticketId, boolean passed) {
        this(ticketId, LocalDateTime.now(), passed);
    }

    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isPassed() {
        return passed;
    }

}
