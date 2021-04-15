package model;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Locations {
    private final CopyOnWriteArrayList<Location> locationList;

    public Locations() {
        this.locationList = getLocations();
    }

    public void addNewLocation(String location) {
        Map<Integer, Boolean> availableMonths = Collections.synchronizedMap(new HashMap<>());

        for (int i = 0; i < 12; i++) {
            availableMonths.put(i + 1, true);
        }

        locationList.add(new Location(location, availableMonths));
    }

    public String bookLocationForMonth(Integer locationPosition, Integer numberOfMonths) {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Location location = locationList.get(locationPosition - 1);
        Map<Integer, Boolean> availableMonths = Collections.synchronizedMap(new HashMap<>());
        boolean isAvailable = true;


        for (int j = 0; j < location.getAvailableMonth().size(); j++) {
            if (currentMonth <= j + 1) {
                if (location.getAvailableMonth().get(j + 1)) {
                    if (numberOfMonths > 0) {
                        availableMonths.put(j + 1, false);
                        numberOfMonths--;
                    }
                } else {
                    isAvailable = false;
                    break;
                }
            }
        }

        if (isAvailable) {
            for (int i = 0; i < location.getAvailableMonth().size(); i++) {
                if (!availableMonths.containsKey(i + 1)) {
                    availableMonths.put(i + 1, location.getAvailableMonth().get(i + 1));
                }
            }

            locationList.set(locationPosition - 1, new Location(location.getName(), availableMonths));

            return "Booked :)";
        }
        return "No available for so much months. Please be careful what you picked :(";

    }

    public String getLocationsDetails() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < locationList.size(); i++) {
            names.append(i + 1).append(". ").append(locationList.get(i).getName()).append(" Not Available in:\n");
            for (int j = 0; j < locationList.get(i).getAvailableMonth().size(); j++) {
                if (!locationList.get(i).getAvailableMonth().get(j + 1)) {
                    names.append("Month: ").append(j + 1).append("\n");
                }
            }
        }

        return names.toString();
    }

    public String getLocationsNames() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < locationList.size(); i++) {
            names.append(i + 1).append(". ").append(locationList.get(i).getName()).append("\n");
        }

        return names.toString();
    }

    private CopyOnWriteArrayList<Location> getLocations() {
        Map<Integer, Boolean> availableMonths = Collections.synchronizedMap(new HashMap<>());

        for (int i = 0; i < 12; i++) {
            availableMonths.put(i + 1, true);
        }
        CopyOnWriteArrayList<Location> locations = new CopyOnWriteArrayList<>();

        Location location1 = new Location("Chicago", availableMonths);
        Location location2 = new Location("New York", availableMonths);
        Location location3 = new Location("Cluj-Napoca", availableMonths);
        Location location4 = new Location("Roma", availableMonths);
        Location location5 = new Location("Prague", availableMonths);
        Location location6 = new Location("Bucharest", availableMonths);
        Location location7 = new Location("Budapesta", availableMonths);

        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        locations.add(location5);
        locations.add(location6);
        locations.add(location7);

        return locations;

    }
}
