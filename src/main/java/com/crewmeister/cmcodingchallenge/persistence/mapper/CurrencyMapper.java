package com.crewmeister.cmcodingchallenge.persistence.mapper;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {


    CurrencyDto toCurrencyDto(CurrencyEntity currency);

    List<CurrencyDto> toCurrencies(List<CurrencyEntity> currency);

    @InheritInverseConfiguration
    @Mapping(target = "id" , ignore = true)
    CurrencyEntity toCurrencyEntity(CurrencyDto currency);
}
