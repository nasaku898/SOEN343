package com.soen343.shs.converters;

import com.soen343.shs.dal.model.City;
import com.soen343.shs.dto.CityDTO;
import org.springframework.core.convert.converter.Converter;

public class CityToCityDTOConverter implements Converter<City, CityDTO> {
    @Override
    public CityDTO convert(final City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .temperatureOutside(city.getTemperatureOutside())
                .build();
    }
}
