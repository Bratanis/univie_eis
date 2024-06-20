package org.omilab.portal_service.service;

import org.omilab.portal_service.DatabaseConnection;
import org.omilab.portal_service.model.Attraction;
import org.omilab.portal_service.model.AttractionOverview;
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

    @GetMapping(value = "/top-visited", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTopVisitedAttractions() {
        List<AttractionOverview> attractions = new ArrayList<>();
        String sql = "SELECT DistrictNr, Attraction.ID, Attraction.Name, COUNT(VisitRecord.ID) AS TotalVisits " +
                     "FROM portal_database.VisitRecord INNER JOIN portal_database.Attraction ON VisitRecord.AttractionID = Attraction.ID " +
                     "GROUP BY Attraction.ID, Attraction.Name " +
                     "ORDER BY TotalVisits DESC " +
                     "LIMIT 10";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                attractions.add(new AttractionOverview(
                    rs.getInt("DistrictNr"),
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getInt("TotalVisits"),
                    null // No average rating for this query
                ));
            }
        } catch (Exception e) {
            logger.error("Failed to fetch top visited attractions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching top visited attractions: " + e.getMessage());
        }

        if (attractions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(attractions);
    }

    @GetMapping(value = "/top-rated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTopRatedAttractions() {
        List<AttractionOverview> attractions = new ArrayList<>();
        String sql = "SELECT DistrictNr, Attraction.ID, Attraction.Name, AVG(VisitRecord.Rating) AS AverageRating " +
                     "FROM portal_database.VisitRecord INNER JOIN portal_database.Attraction ON VisitRecord.AttractionID = Attraction.ID " +
                     "GROUP BY Attraction.ID, Attraction.Name " +
                     "ORDER BY AverageRating DESC " +
                     "LIMIT 10";

        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                attractions.add(new AttractionOverview(
                    rs.getInt("DistrictNr"),
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    null, // No total visits for this query
                    rs.getDouble("AverageRating")
                ));
            }
        } catch (Exception e) {
            logger.error("Failed to fetch top rated attractions", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching top rated attractions: " + e.getMessage());
        }

        if (attractions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(attractions);
    }
}
