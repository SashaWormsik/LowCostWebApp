package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Aircraft;
import com.sasha.grodno.website.service.CrudService;

public interface AircraftService extends CrudService<Aircraft> {
    void deleteById(Integer id);
}
