package org.omilab.portal_service.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventService {

    @GetMapping("/name/{name}")
    public String getEventByName(@PathVariable("name") String name) {
        return "Returns Event with name: " + name;
    }

    @GetMapping("/id/{id}")
    public String getEventById(@PathVariable("id") String id) {
        return "Returns Event with id: " + id;
    }

    @GetMapping("/all")
    public String getAllEvents() {
        return "Returns all events";
    }

    @GetMapping("/districts/{districts}")
    public List<String> getEventsByDistricts(@PathVariable("districts") String districtStr) {

        // Convert comma-separated String to List of Integers for districts
        List<Integer> districts = null;
        if (districtStr != null && !districtStr.isEmpty()) {
            districts = Arrays.stream(districtStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        return List.of("Attractions in district " + districts, "More attractions in district " + districts);
    }
}
