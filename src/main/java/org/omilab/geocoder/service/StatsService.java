//package org.omilab.geocoder.service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.joda.time.DateTime;
//
//@Path("/stats")
//public class StatsService {
//
//    @GET
//    @Path("/spending")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response searchAttractions(
//            @QueryParam("districts") String districtStr, //TODO: Districts separately?
//            @QueryParam("ageRange") String ageRangeStr,
//            @QueryParam("gender") String gender,
//            @QueryParam("timeRange") String timeRangeStr) {
//
//        List<Integer> districts = null;
//        if (districtStr != null && !districtStr.isEmpty()) {
//            districts = Arrays.stream(districtStr.split(","))
//                              .map(Integer::parseInt)
//                              .collect(Collectors.toList());
//        }
//
//        List<DateTime> timeRange = null;
//        if (timeRangeStr != null && !timeRangeStr.isEmpty()) {
//            timeRange = Arrays.stream(timeRangeStr.split(","))
//                              .map(DateTime::parse)
//                              .collect(Collectors.toList());
//        }
//
//        // Convert comma-separated String to List of Integers for ageRange
//        List<Integer> ageRange = null;
//        if (ageRangeStr != null && !ageRangeStr.isEmpty()) {
//            ageRange = Arrays.stream(ageRangeStr.split(","))
//                             .map(Integer::parseInt)
//                             .collect(Collectors.toList());
//        }
//
//        StringBuilder sb = new StringBuilder("Attractions filtered by: \n");
//        if (districts != null) sb.append("Districts: ").append(districts).append("\n");
//        if (ageRange != null) sb.append("Age Range: ").append(ageRange).append("\n");
//        if (gender != null) sb.append("Gender: ").append(gender).append("\n");
//        if (timeRange != null) sb.append("Time Range: ").append(timeRange).append("\n");
//
//        return Response.ok(sb.toString()).build();
//    }
//
//    @GET
//    @Path("/visits")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response searchEvents(
//            @QueryParam("districts") String districtStr,
//            @QueryParam("gender") String gender,
//            @QueryParam("ageRange") String ageRangeStr,
//            @QueryParam("timeRange") String timeRangeStr) {
//
//                List<Integer> districts = null;
//                if (districtStr != null && !districtStr.isEmpty()) {
//                    districts = Arrays.stream(districtStr.split(","))
//                                      .map(Integer::parseInt)
//                                      .collect(Collectors.toList());
//                }
//
//                List<DateTime> timeRange = null;
//                if (timeRangeStr != null && !timeRangeStr.isEmpty()) {
//                    timeRange = Arrays.stream(timeRangeStr.split(","))
//                                      .map(DateTime::parse)
//                                      .collect(Collectors.toList());
//                }
//
//                // Convert comma-separated String to List of Integers for ageRange
//                List<Integer> ageRange = null;
//                if (ageRangeStr != null && !ageRangeStr.isEmpty()) {
//                    ageRange = Arrays.stream(ageRangeStr.split(","))
//                                     .map(Integer::parseInt)
//                                     .collect(Collectors.toList());
//                }
//
//                StringBuilder sb = new StringBuilder("Attractions filtered by: \n");
//                if (districts != null) sb.append("Districts: ").append(districts).append("\n");
//                if (ageRange != null) sb.append("Age Range: ").append(ageRange).append("\n");
//                if (gender != null) sb.append("Gender: ").append(gender).append("\n");
//                if (timeRange != null) sb.append("Time Range: ").append(timeRange).append("\n");
//
//                return Response.ok(sb.toString()).build();
//
//            }
//
//
//}
