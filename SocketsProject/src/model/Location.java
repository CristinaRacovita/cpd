package model;

import java.util.Map;

public class Location {
    private final String name;
    private final Map<Integer, Boolean> availableMonth;

    public Location(String name, Map<Integer, Boolean> availableMonth) {
        this.name = name;
        this.availableMonth = availableMonth;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Boolean> getAvailableMonth() {
        return availableMonth;
    }
}
