package com.company.web;

import com.company.data.AirplaneRepository;
import com.company.data.AirportRepository;
import com.company.domain.Airplane;
import com.company.domain.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataSeeder {
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private AirportRepository airportRepository;

    @PostConstruct
    public void seed(){
        seedAirports();
        seedAirplanes();
    }

    private void seedAirports(){
        Airport airport = new Airport();
        airport.setName("LAX");
        airport.setCity("Los Angeles");
        airport.setCountry("USA");
        airportRepository.save(airport);

        Airport airport1 = new Airport();
        airport1.setName("JFK");
        airport1.setCity("New York");
        airport1.setCountry("USA");
        airportRepository.save(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Heathrow");
        airport2.setCity("London");
        airport2.setCountry("UK");
        airportRepository.save(airport2);
    }

    private void seedAirplanes(){
        for(int i = 0; i < 15; i++){
            Airplane airplane = new Airplane();
            airplane.fillTank();
            airplane.setType("Boeing 737-800");
            if(i < 5)
                airplane.setLocation(airportRepository.findByName("LAX"));
            else if(i < 10)
                airplane.setLocation(airportRepository.findByName("JFK"));
            else
                airplane.setLocation(airportRepository.findByName("Heathrow"));

            airplaneRepository.save(airplane);
        }
    }

}
