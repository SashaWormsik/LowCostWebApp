package com.sasha.grodno.website.service.iterface;

import com.sasha.grodno.website.model.Airplane;
import com.sasha.grodno.website.service.CrudService;
import org.springframework.data.domain.Page;

public interface AirplaneService extends CrudService<Airplane> {
    void deleteById(Integer id);
    void updateAirplaneById(Airplane airplane, Integer id);
    Page<Airplane> getAirplanesPage(Integer pageNumber);
}
