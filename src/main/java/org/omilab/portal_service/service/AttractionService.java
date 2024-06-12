package org.omilab.portal_service.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AttractionService {

    // Returns all attractions in the database (as JSON)
    @GetMapping(value = "/attractions/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllAttractions() {
        return "second";
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() {
        return "index";
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTest() {
        return "redirect:/test.html";
    }

    @GetMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTest1() {
        return "redirect:/index.html";
    }

    @GetMapping(value = "/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTest2() {
        return "redirect:/ratings.html";
    }

    // Returns attraction by name
    @GetMapping(value = "/attractions/name/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getAttractionByName(@PathVariable String name) {
        return "Hello Attraction " + name;
    }

    // Returns attraction by id
    @GetMapping(value = "/attractions/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAttractionById(@PathVariable String id) {
        return "Attraction with id " + id;
    }

    // Returns all attractions with specified district number(s) - parameter is a list of 0 to n district numbers
    @GetMapping(value = "/attractions/districts/{districts}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAttractionsByDistricts(@PathVariable String districtStr) {

        // Convert comma-separated String to List of Integers for districts
        List<Integer> districts = null;
        if (districtStr != null && !districtStr.isEmpty()) {
            districts = Arrays.stream(districtStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        return  null;
    }
}