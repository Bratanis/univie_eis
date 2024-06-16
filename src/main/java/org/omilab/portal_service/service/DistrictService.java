package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.DistrictData;
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

@RestController
@RequestMapping("/districts")
public class DistrictService {

    private static final Logger logger = LoggerFactory.getLogger(DistrictService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDistrictDescription(@RequestParam("districtNr") Integer number) {
        if (number == null) {
            return ResponseEntity.badRequest().body("District number is required");
        }

        String sql = "SELECT Description FROM District WHERE number = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, number);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String description = rs.getString("Description");
                DistrictData districtData = new DistrictData(number, description);
                return ResponseEntity.ok(districtData);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            logger.error("Failed to fetch district description", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching district description: " + e.getMessage());
        }
    }
}
