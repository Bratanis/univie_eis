package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.Attraction;
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
@RequestMapping("/attractions")
public class AttractionService {

    private static final Logger logger = LoggerFactory.getLogger(AttractionService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();

    @GetMapping(value = "/by-district", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAttractionsByDistrict(@RequestParam("districtNr") int districtNr) {
        List<Attraction> attractions = new ArrayList<>();
        String sql = "SELECT ID, Name, Address, DistrictNr FROM Attraction WHERE DistrictNr = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, districtNr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                attractions.add(new Attraction(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getInt("DistrictNr")
                ));
            }
        } catch (Exception e) {
            logger.error("Failed to fetch attractions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching attractions: " + e.getMessage());
        }

        if (attractions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(attractions);
    }
}
