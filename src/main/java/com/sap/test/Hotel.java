package com.sap.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.sap.test.Constants.*;

public class Hotel {

    private List<Room> rooms;

    public Hotel(int numberOfRooms) {
        if (numberOfRooms >= MIN_ROOMS && numberOfRooms <= MAX_ROOMS) {
            this.rooms = new ArrayList<>();
            for (int i = 1; i <= numberOfRooms; i++) {
                rooms.add(new Room(i));
            }
        } else {
            throw new RuntimeException("Invalid number of rooms. The number of rooms should be in range " + MIN_ROOMS + "-" + MAX_ROOMS);
        }
    }

    public String bookRoom(Guest guest) {
        int startDay = guest.getStartDay();
        int endDay = guest.getEndDay();
        if (startDay < MIN_DAYS || endDay < MIN_DAYS || startDay > MAX_DAYS || endDay > MAX_DAYS || startDay > endDay) {
            return DECLINE;
        }

        // find available rooms
        List<Room> availableRooms = new ArrayList<>();
        rooms.forEach(room -> {
            if (room.isAvailable(startDay, endDay)) {
                availableRooms.add(room);
            }
        });

        // book best matching room
        if (!availableRooms.isEmpty()) {
            // Ascending sort available rooms by number of available days
            availableRooms.sort(Comparator.comparingInt(Room::getAvailableDays));
            for (Room room : availableRooms) {
                if (room.bookRoom(guest, startDay, endDay)) {
                    return ACCEPT;
                }
            }
        }
        return DECLINE;
    }


}
