package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT name, date, address, type, districtNr FROM portal_database.Event";
    
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                events.add(new Event(
                    rs.getString("name"),
                    rs.getDate("date"),
                    rs.getString("address"),
                    rs.getString("type"),
                    rs.getInt("districtNr")
                ));
            }
        } catch (Exception e) {
            logger.error("Failed to fetch events", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching events: " + e.getMessage());
        }
    
        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
    
        return ResponseEntity.ok(events);
    }

    /* 

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
    */
}
