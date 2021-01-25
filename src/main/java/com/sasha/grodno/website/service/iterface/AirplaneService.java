package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.service.CrudService;

public interface AirplaneService extends CrudService<Airplane> {
    void deleteById(Integer id);
    void updateAirplaneById(Airplane airplane, Integer id);
}
