package com.company;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

public class RegisterTest {

    Register register = new Register();

    @Test
    public void testCreateStudentTicket() {
        String studentID = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 1;
        StudentTicket st = register.createStudentTicket(studentID, validity, trips);
        assert st.getStudentId().equals(studentID);
        assert st.getValidity().equals(validity);
        assert st.getTrips() == trips;
        assert register.returnTicket(st.getId()) == st;
    }

    @Test
    public void testCreateStandardTicked() {
        StandardTicket st = register.createStandardTicked();
        assert st.getMoney() == 0;
        assert register.returnTicket(st.getId()) == st;
    }

    @Test
    public void testCreatePupilTicket() {
        String pupilID = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 1;
        PupilTicket st = register.createPupilTicket(pupilID, validity, trips);
        assert st.getPupilId().equals(pupilID);
        assert st.getValidity().equals(validity);
        assert st.getTrips() == trips;
        assert register.returnTicket(st.getId()) == st;
    }

    @Test
    public void testCheckStandardTicket() {
        StandardTicket st = register.createStandardTicked();
        assert !register.checkTicket(st.getId());

        register.deposit(st.getId(), 16);
        assert register.checkTicket(st.getId());
    }

    @Test
    public void testCheckStudentTicket() {
        String studentID = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 0;
        StudentTicket st = register.createStudentTicket(studentID, validity, trips);
        assert !register.checkTicket(st.getId());

        validity = LocalDate.now().minusDays(1);
        trips = 1;
        st = register.createStudentTicket(studentID, validity, trips);
        assert !register.checkTicket(st.getId());

        validity = LocalDate.now().minusDays(1);
        trips = 0;
        st = register.createStudentTicket(studentID, validity, trips);
        assert !register.checkTicket(st.getId());

        validity = LocalDate.now().plusDays(1);
        trips = 1;
        st = register.createStudentTicket(studentID, validity, trips);
        assert register.checkTicket(st.getId());
    }

    @Test
    public void testCheckPupilTicket() {
        String pupilID = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 0;
        PupilTicket st = register.createPupilTicket(pupilID, validity, trips);
        assert !register.checkTicket(st.getId());

        validity = LocalDate.now().minusDays(1);
        trips = 1;
        st = register.createPupilTicket(pupilID, validity, trips);
        assert !register.checkTicket(st.getId());

        validity = LocalDate.now().minusDays(1);
        trips = 0;
        st = register.createPupilTicket(pupilID, validity, trips);
        assert !register.checkTicket(st.getId());

        validity = LocalDate.now().plusDays(1);
        trips = 1;
        st = register.createPupilTicket(pupilID, validity, trips);
        assert register.checkTicket(st.getId());
    }

    @Test
    public void testDecrementStandardTicket() {
        StandardTicket st = register.createStandardTicked();
        int money = 8;
        int rest = 0;

        register.deposit(st.getId(), money);
        assert register.decrement(st.getId()) == rest;

        try {
            register.decrement(st.getId());
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalStateException e) {
            assertEquals("Not enough money", e.getMessage());
        }
    }

    @Test
    public void testDecrementStudentTicket() {
        String studentID = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 1;
        StudentTicket st = register.createStudentTicket(studentID, validity, trips);

        assert register.decrement(st.getId()) == 0;

        try {
            register.decrement(st.getId());
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalStateException e) {
            assertEquals("No more trips", e.getMessage());
        }
    }

    @Test
    public void testDecrementPupilTicket() {
        String studentID = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 1;
        PupilTicket st = register.createPupilTicket(studentID, validity, trips);

        assert register.decrement(st.getId()) == 0;

        try {
            register.decrement(st.getId());
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalStateException e) {
            assertEquals("No more trips", e.getMessage());
        }
    }
}