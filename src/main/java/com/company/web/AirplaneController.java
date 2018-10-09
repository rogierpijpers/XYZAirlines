package com.company.web;

import com.company.domain.Airplane;
import com.company.domain.Airport;
import com.company.service.AirplaneService;
import com.company.service.AirportService;
import com.company.web.dto.FlightPath;
import com.company.web.exception.InsufficientFuelException;
import com.company.web.exception.InvalidFlightPathException;
import com.company.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/airplane/")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private AirportService airportService;

    @RequestMapping
    public List<Airplane> getAllAirplanes(){
        return airplaneService.getAllAirplanes();
    }

    @RequestMapping("/{id}")
    public Airplane getAirplaneById(@PathVariable("id") int id) throws NotFoundException {
        return airplaneService.getAirplaneById(id);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.PUT)
    public void updateAirplane(@RequestBody Airplane airplane) throws NotFoundException {
        airplaneService.updateAirplane(airplane);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addAirplane(@RequestBody Airplane airplane){
        airplaneService.addAirplane(airplane);
    }

    @RequestMapping(value="/fillTank/{id}", method=RequestMethod.PUT)
    public void fillTank(@PathVariable("id") int id) throws NotFoundException {
        Airplane airplane = airplaneService.getAirplaneById(id);
        airplaneService.fillTank(airplane);
    }

    @RequestMapping(value="/fly/", method = RequestMethod.POST)
    public void fly(@RequestBody FlightPath flightPath) throws NotFoundException, InsufficientFuelException, InvalidFlightPathException {
        Airplane airplane = airplaneService.getAirplaneById(flightPath.getAirplaneId());
        Airport airport = airportService.getAirportById(flightPath.getDestinationId());

        airplaneService.fly(airplane, airport);
    }
}
