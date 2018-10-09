package com.company.data;

import com.company.domain.Airplane;
import com.company.domain.Airport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirportRepository extends CrudRepository<Airport, Long> {
    Airport findByName(String name);
    @Override
    List<Airport> findAll();
}
