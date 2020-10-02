package com.company;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TurnstileTest extends TestCase {

    Register register = new Register();
    Turnstile turnstile = new Turnstile(register);

    public void testPassByStudent() {
        String studentId = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 1;
        StudentTicket st = register.createStudentTicket(studentId, validity, trips);

        assert turnstile.pass(st.getId());
        assert !turnstile.pass(st.getId());


        studentId = UUID.randomUUID().toString();
        validity = LocalDate.now().minusDays(1);
        trips = 1;
        st = register.createStudentTicket(studentId, validity, trips);

        assert !turnstile.pass(st.getId());
    }

    public void testPassByPupil() {
        String pupilId = UUID.randomUUID().toString();
        LocalDate validity = LocalDate.now().plusDays(1);
        int trips = 1;
        PupilTicket st = register.createPupilTicket(pupilId, validity, trips);

        assert turnstile.pass(st.getId());
        assert !turnstile.pass(st.getId());


        pupilId = UUID.randomUUID().toString();
        validity = LocalDate.now().minusDays(1);
        trips = 1;
        st = register.createPupilTicket(pupilId, validity, trips);

        assert !turnstile.pass(st.getId());
    }

    public void testGetPassed() {
        Register register = new Register();
        Turnstile turnstile = new Turnstile(register);

        StudentTicket stud = register.createStudentTicket(UUID.randomUUID().toString(), LocalDate.now(), 1);
        StandardTicket stand = register.createStandardTicked();
        register.deposit(stand.getId(), Register.price);
        PupilTicket pupil = register.createPupilTicket(UUID.randomUUID().toString(), LocalDate.now(), 1);

        turnstile.pass(stud.getId());
        turnstile.pass(stand.getId());
        turnstile.pass(pupil.getId());

        assert turnstile.getPassed() == 3;
    }

    public void testGetUnpassed() {
        Register register = new Register();
        Turnstile turnstile = new Turnstile(register);

        StudentTicket stud = register.createStudentTicket(UUID.randomUUID().toString(), LocalDate.now(), 1);
        StandardTicket stand = register.createStandardTicked();
        register.deposit(stand.getId(), Register.price);
        PupilTicket pupil = register.createPupilTicket(UUID.randomUUID().toString(), LocalDate.now(), 1);

        turnstile.pass(stud.getId());
        turnstile.pass(stand.getId());
        turnstile.pass(pupil.getId());

        turnstile.pass(stud.getId());
        turnstile.pass(stand.getId());
        turnstile.pass(pupil.getId());

        assert turnstile.getPassed() == 3;
        assert turnstile.getUnpassed() == 3;
    }

    public void testGetPassedUnPassedGrouped() {
        Register register = new Register();
        Turnstile turnstile = new Turnstile(register);

        StudentTicket stud = register.createStudentTicket(UUID.randomUUID().toString(), LocalDate.now(), 1);
        StandardTicket stand = register.createStandardTicked();
        register.deposit(stand.getId(), Register.price);
        PupilTicket pupil = register.createPupilTicket(UUID.randomUUID().toString(), LocalDate.now(), 1);

        turnstile.pass(stud.getId());
        turnstile.pass(stand.getId());
        turnstile.pass(pupil.getId());

        turnstile.pass(stud.getId());
        turnstile.pass(stand.getId());
        turnstile.pass(pupil.getId());

        HashMap<String, Long> expectedPassed = new HashMap<>();

        expectedPassed.put("StudentTicket", 1L);
        expectedPassed.put("StandardTicket", 1L);
        expectedPassed.put("PupilTicket", 1L);

        Map<String, Long> passed = turnstile.getPassedGrouped();
        Map<String, Long> unpassed = turnstile.getUnPassedGrouped();
        assert passed.equals(expectedPassed);
        assert unpassed.equals(expectedPassed);
    }

}