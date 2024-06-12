package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.DemographicData;
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
@RequestMapping("/analytics")
public class DemographicService {

    private static final Logger logger = LoggerFactory.getLogger(DemographicService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();

    @GetMapping(value = "/visitor-demographics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DemographicData>> getVisitorDemographics(
        @RequestParam(required = false) Integer yearStart,
        @RequestParam(required = false) Integer yearEnd,
        @RequestParam(required = false) Integer districtNr) {

        logger.info("API call with yearStart: {}, yearEnd: {}, districtNr: {}",
            yearStart, yearEnd, districtNr);
    
        
        List<DemographicData> data = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT age, gender FROM portal_database.VisitRecord INNER JOIN portal_database.Visitor ON VisitRecord.VisitorID = Visitor.ID INNER JOIN portal_database.Attraction ON VisitRecord.AttractionID = Attraction.ID WHERE 1=1");

        // Dynamically append conditions based on provided parameters
        if (yearStart != null && yearEnd != null) {
            sql.append(" AND YEAR(VisitTime) BETWEEN ? AND ?");
        }

        if (districtNr != null) {
            sql.append(" AND DistrictNr = ?");
        }

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (yearStart != null && yearEnd != null) {
                stmt.setInt(paramIndex++, yearStart);
                stmt.setInt(paramIndex++, yearEnd);
            }
            if (districtNr != null) {
                stmt.setInt(paramIndex++, districtNr);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Assuming ReviewAnalytics is a suitable class to hold this data
                data.add(new DemographicData(
                    rs.getInt("Age"),
                    rs.getString("Gender")
                ));
            }
        } catch (Exception e) {
            logger.error("Failed to fetch demographic data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(data);
    }
}