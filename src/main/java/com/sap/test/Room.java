package com.sap.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sap.test.Constants.MAX_DAYS;

public class Room {
    private Integer number;
    private Integer availableDays;
    // Assumption is that we want to know who is the guest
    private Map<Integer, Guest> days = new HashMap<>();

    public Integer getAvailableDays() {
        return availableDays;
    }

    public Room(int number) {
        this.number = number;
        this.availableDays = MAX_DAYS;
        for (int i = 0; i < MAX_DAYS; i++) {
            days.put(i, null);
        }
    }

    public boolean isAvailable(int startDay, int endDay) {
        List<Integer> targetDays = getTargetDays(startDay, endDay);
        return targetDays.stream().allMatch(day -> days.get(day) == null);
    }

    public boolean bookRoom(Guest guest, int startDay, int endDay) {
        List<Integer> targetDays = getTargetDays(startDay, endDay);
        targetDays.forEach(key -> {
            days.put(key, guest);
            availableDays--;
        });
        return true;
    }

    private List<Integer> getTargetDays(int startDay, int endDay) {
        return days.keySet().stream()
                .filter(key -> key >= startDay && key <= endDay)
                .collect(Collectors.toList());
    }


}
