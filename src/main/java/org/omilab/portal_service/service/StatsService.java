package org.omilab.portal_service.service;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stats")
public class StatsService {

    @GetMapping("/spending")
    public String searchAttractions(
            @RequestParam(value = "districts", required = false) String districtStr,
            @RequestParam(value = "ageRange", required = false) String ageRangeStr,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "timeRange", required = false) String timeRangeStr) {

        List<Integer> districts = null;
        if (districtStr != null && !districtStr.isEmpty()) {
            districts = Arrays.stream(districtStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        List<DateTime> timeRange = null;
        if (timeRangeStr != null && !timeRangeStr.isEmpty()) {
            timeRange = Arrays.stream(timeRangeStr.split(","))
                    .map(DateTime::parse)
                    .collect(Collectors.toList());
        }

        List<Integer> ageRange = null;
        if (ageRangeStr != null && !ageRangeStr.isEmpty()) {
            ageRange = Arrays.stream(ageRangeStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        StringBuilder sb = new StringBuilder("Attractions filtered by: \n");
        if (districts != null) sb.append("Districts: ").append(districts).append("\n");
        if (ageRange != null) sb.append("Age Range: ").append(ageRange).append("\n");
        if (gender != null) sb.append("Gender: ").append(gender).append("\n");
        if (timeRange != null) sb.append("Time Range: ").append(timeRange).append("\n");

        return sb.toString();
    }

    @GetMapping("/visits")
    public String searchEvents(
            @RequestParam(value = "districts", required = false) String districtStr,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "ageRange", required = false) String ageRangeStr,
            @RequestParam(value = "timeRange", required = false) String timeRangeStr) {

        List<Integer> districts = null;
        if (districtStr != null && !districtStr.isEmpty()) {
            districts = Arrays.stream(districtStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        List<DateTime> timeRange = null;
        if (timeRangeStr != null && !timeRangeStr.isEmpty()) {
            timeRange = Arrays.stream(timeRangeStr.split(","))
                    .map(DateTime::parse)
                    .collect(Collectors.toList());
        }

        List<Integer> ageRange = null;
        if (ageRangeStr != null && !ageRangeStr.isEmpty()) {
            ageRange = Arrays.stream(ageRangeStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        StringBuilder sb = new StringBuilder("Attractions filtered by: \n");
        if (districts != null) sb.append("Districts: ").append(districts).append("\n");
        if (ageRange != null) sb.append("Age Range: ").append(ageRange).append("\n");
        if (gender != null) sb.append("Gender: ").append(gender).append("\n");
        if (timeRange != null) sb.append("Time Range: ").append(timeRange).append("\n");

        return sb.toString();
    }
}
