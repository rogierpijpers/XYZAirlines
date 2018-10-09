package com.company.service;

import com.company.data.AirportRepository;
import com.company.domain.Airport;
import com.company.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public Airport getAirportById(long id) throws NotFoundException {
        Optional<Airport> opt = airportRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            throw new NotFoundException("Airport not found");
    }

    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }
}
