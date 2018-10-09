package com.company.web;

import com.company.domain.Airport;
import com.company.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/airport/")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @RequestMapping
    public List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }
}
