package com.company;

public class StandardTicket extends Ticket {
    private int money;

    StandardTicket() {
        super();
        setMoney(0);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
