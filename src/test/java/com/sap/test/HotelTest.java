package com.sap.test;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.sap.test.Constants.ACCEPT;
import static com.sap.test.Constants.DECLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelTest {

    @Test
    void test_1a() {
        println("TEST 1a");
        Hotel hotel = new Hotel(1);
        Guest guest = new Guest("Booking 1", -4, 2);
        String result = hotel.bookRoom(guest);
        assertResult(guest, DECLINE, result);
    }

    @Test
    void test_1b() {
        println("TEST 1b");
        Hotel hotel = new Hotel(1);
        Guest guest = new Guest("Booking 1", 200, 400);
        String result = hotel.bookRoom(guest);
        assertResult(guest, DECLINE, result);
    }

    @Test
    void test_2() {
        println("TEST 2");
        Hotel hotel = new Hotel(3);
        Map<Guest, String> expectedGuest = new LinkedHashMap<>();
        expectedGuest.put(new Guest("Booking 1", 0, 5), ACCEPT);
        expectedGuest.put(new Guest("Booking 2", 7, 13), ACCEPT);
        expectedGuest.put(new Guest("Booking 3", 3, 9), ACCEPT);
        expectedGuest.put(new Guest("Booking 4", 5, 7), ACCEPT);
        expectedGuest.put(new Guest("Booking 5", 6, 6), ACCEPT);
        expectedGuest.put(new Guest("Booking 6", 0, 4), ACCEPT);
        expectedGuest.forEach((guest, expected) -> {
            String result = hotel.bookRoom(guest);
            assertResult(guest, expected, result);
        });
    }

    @Test
    void test_3() {
        println("TEST 3");
        Hotel hotel = new Hotel(3);
        Map<Guest, String> expectedGuest = new LinkedHashMap<>();
        expectedGuest.put(new Guest("Booking 1", 1, 3), ACCEPT);
        expectedGuest.put(new Guest("Booking 2", 2, 5), ACCEPT);
        expectedGuest.put(new Guest("Booking 3", 1, 9), ACCEPT);
        expectedGuest.put(new Guest("Booking 4", 0, 15), DECLINE);
        expectedGuest.forEach((guest, expected) -> {
            String result = hotel.bookRoom(guest);
            assertResult(guest, expected, result);
        });
    }

    @Test
    void test_4() {
        println("TEST 4");
        Hotel hotel = new Hotel(3);
        Map<Guest, String> expectedGuest = new LinkedHashMap<>();
        expectedGuest.put(new Guest("Booking 1", 1, 3), ACCEPT);
        expectedGuest.put(new Guest("Booking 2", 0, 15), ACCEPT);
        expectedGuest.put(new Guest("Booking 3", 1, 9), ACCEPT);
        expectedGuest.put(new Guest("Booking 4", 2, 5), DECLINE);
        expectedGuest.put(new Guest("Booking 5", 4, 9), ACCEPT);
        expectedGuest.forEach((guest, expected) -> {
            String result = hotel.bookRoom(guest);
            assertResult(guest, expected, result);
        });
    }

    @Test
    void test_5() {
        println("TEST 5");
        Hotel hotel = new Hotel(2);
        Map<Guest, String> expectedGuest = new LinkedHashMap<>();
        expectedGuest.put(new Guest("Booking 1", 1, 3), ACCEPT);
        expectedGuest.put(new Guest("Booking 2", 0, 4), ACCEPT);
        expectedGuest.put(new Guest("Booking 3", 2, 3), DECLINE);
        expectedGuest.put(new Guest("Booking 4", 5, 5), ACCEPT);
        expectedGuest.put(new Guest("Booking 5", 4, 10), ACCEPT);
        expectedGuest.put(new Guest("Booking 6", 10, 10), ACCEPT);
        expectedGuest.put(new Guest("Booking 7", 6, 7), ACCEPT);
        expectedGuest.put(new Guest("Booking 8", 8, 10), DECLINE);
        expectedGuest.put(new Guest("Booking 9", 8, 9), ACCEPT);
        expectedGuest.forEach((guest, expected) -> {
            String result = hotel.bookRoom(guest);
            assertResult(guest, expected, result);
        });
    }

    private void assertResult(Guest guest, String expected, String actual) {
        try {
            assertEquals(expected, actual);
            println(guest.toString() + actual);
        } catch (AssertionFailedError e) {
            println(guest.toString() + actual + " - Test Failed");
            throw e;
        }
    }

    private void println(String string) {
        System.out.println(string);
    }

}
