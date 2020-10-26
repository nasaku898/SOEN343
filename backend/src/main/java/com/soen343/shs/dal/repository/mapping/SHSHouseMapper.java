//package com.soen343.shs.dal.repository.mapping;
//
//import com.soen343.shs.dal.model.House;
//import com.soen343.shs.dto.HouseDTO;
//import org.mapstruct.BeanMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.NullValuePropertyMappingStrategy;
//
//@Mapper(componentModel = "spring")
//public interface SHSHouseMapper {
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    House updateHouseFromDTO(HouseDTO dto, @MappingTarget House house);
//}
