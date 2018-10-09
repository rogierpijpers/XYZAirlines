package com.company.service;

import com.company.data.AirplaneRepository;
import com.company.domain.Airplane;
import com.company.domain.Airport;
import com.company.web.exception.InsufficientFuelException;
import com.company.web.exception.InvalidFlightPathException;
import com.company.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    public void fillTank(Airplane airplane){
        airplane.fillTank();
    }

    public Airplane getAirplaneById(long id) throws NotFoundException {
        Optional<Airplane> opt = airplaneRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            throw new NotFoundException("Airplane not found");
    }

    public List<Airplane> getAllAirplanes(){
        return airplaneRepository.findAll();
    }

    public void fly(Airplane airplane, Airport destination) throws InsufficientFuelException, InvalidFlightPathException {
        if(airplane.getLocation().equals(destination))
            throw new InvalidFlightPathException("Destination is equal to current location");

        if(airplane.getFuelLevel() >= 2){
            double fuelLevel = airplane.getFuelLevel() - 2;
            airplane.setFuelLevel(fuelLevel);
            airplane.setLocation(destination);
            airplaneRepository.save(airplane);
        }else
            throw new InsufficientFuelException();
    }

    public void updateAirplane(Airplane airplane) throws NotFoundException {
        Optional<Airplane> toReplace = airplaneRepository.findById(airplane.getId());
        if(!toReplace.isPresent())
            throw new NotFoundException("Airplane not found");

        airplaneRepository.save(airplane);
    }

    public void addAirplane(Airplane airplane){
        airplaneRepository.save(airplane);
    }
}
