package com.company.data;

import com.company.domain.Airplane;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {
    @Override
    List<Airplane> findAll();
}
