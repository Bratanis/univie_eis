package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.DistrictOverviewData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mayor")
public class MayorService {

    private static final Logger logger = LoggerFactory.getLogger(MayorService.class);
    private DatabaseConnection dbConnection = new DatabaseConnection();


    @GetMapping(value = "/district-overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DistrictOverviewData>> getDistrictOverview() {

        logger.info("Fetching district overview data");
        List<DistrictOverviewData> data = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT Attraction.DistrictNr, COUNT(VisitRecord.ID) AS TotalVisits, SUM(VisitRecord.MoneySpent) AS TotalMoneySpent, AVG(VisitRecord.Rating) AS AverageRating FROM portal_database.VisitRecord INNER JOIN portal_database.Attraction ON VisitRecord.AttractionID = Attraction.ID GROUP BY Attraction.DistrictNr");

        try (Connection conn = dbConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            logger.info("Executing SQL query: {}", sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int districtNr = rs.getInt("DistrictNr");
                int totalVisits = rs.getInt("TotalVisits");
                int totalMoneySpent = rs.getInt("TotalMoneySpent");
                double averageRating = rs.getDouble("AverageRating");

                logger.info("Retrieved data - DistrictNr: {}, TotalVisits: {}, TotalMoneySpent: {}, AverageRating: {}",
                            districtNr, totalVisits, totalMoneySpent, averageRating);

                data.add(new DistrictOverviewData(districtNr, totalVisits, totalMoneySpent, averageRating));
            }
            logger.info("Data fetched successfully with {} records", data.size());
        } catch (Exception e) {
            logger.error("Failed to fetch district overview data: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        if (data.isEmpty()) {
            logger.warn("No data found for district overview");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(data);
    }
}
