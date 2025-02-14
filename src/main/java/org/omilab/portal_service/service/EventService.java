package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();

    @GetMapping(value = "/by-district", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEventsByDistrict(@RequestParam("districtNr") int districtNr) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT name, date, address, type, districtNr FROM portal_database.Event WHERE districtNr = ? ORDER BY date DESC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, districtNr);
            ResultSet rs = stmt.executeQuery();

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
}
