package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.SpendingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/analytics")
public class SpendingsService {

    private static final Logger logger = LoggerFactory.getLogger(SpendingsService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();

    @GetMapping(value = "/visitor-spendings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpendingData>> getVisitorSpendings(
        @RequestParam(required = false) Integer ageMin,
        @RequestParam(required = false) Integer ageMax,
        @RequestParam(required = false) String gender,
        @RequestParam(required = false) Integer yearStart,
        @RequestParam(required = false) Integer yearEnd,
        @RequestParam(required = false) Integer districtNr) {

        logger.info("API call with ageMin: {}, ageMax: {}, gender: '{}', yearStart: {}, yearEnd: {}, districtNr: {}",
            ageMin, ageMax, gender, yearStart, yearEnd, districtNr);
    
        
        List<SpendingData> spendings = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT age, name, gender, districtNr, moneySpent, visitTime FROM portal_database.VisitRecord INNER JOIN portal_database.Visitor ON VisitRecord.VisitorID = Visitor.ID INNER JOIN portal_database.Attraction ON VisitRecord.AttractionID = Attraction.ID WHERE 1=1");

        // Dynamically append conditions based on provided parameters
        if (ageMin != null && ageMax != null) {
            sql.append(" AND Age BETWEEN ? AND ?");
        }
        if (gender != null && !gender.isEmpty()) {
            if (gender.equals("diverse")) {
                sql.append(" AND Gender NOT IN ('Male', 'Female')");
            } else {
                sql.append(" AND Gender = ?");
            }
        }
        if (yearStart != null && yearEnd != null) {
            sql.append(" AND YEAR(VisitTime) BETWEEN ? AND ?");
        }

        if (districtNr != null) {
            sql.append(" AND DistrictNr = ?");
        }

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (ageMin != null && ageMax != null) {
                stmt.setInt(paramIndex++, ageMin);
                stmt.setInt(paramIndex++, ageMax);
            }
            if (gender != null && !gender.isEmpty() && !gender.equals("diverse")) {
                stmt.setString(paramIndex++, gender);
            }
            if (yearStart != null && yearEnd != null) {
                stmt.setInt(paramIndex++, yearStart);
                stmt.setInt(paramIndex++, yearEnd);
            }
            if (districtNr != null) {
                stmt.setInt(paramIndex++, districtNr);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                spendings.add(new SpendingData(
                    rs.getInt("Age"),
                    rs.getString("Name"),
                    rs.getString("Gender"),
                    rs.getInt("DistrictNr"),
                    rs.getInt("MoneySpent"),
                    rs.getTimestamp("VisitTime")

                ));
            }
        } catch (Exception e) {
            logger.error("Failed to fetch visitor spendings", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (spendings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(spendings);
    }
}