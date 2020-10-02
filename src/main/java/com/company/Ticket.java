package com.company;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Ticket {
    private String id;

    public Ticket() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}


