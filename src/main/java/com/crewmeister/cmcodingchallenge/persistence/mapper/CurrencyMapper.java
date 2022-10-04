package com.crewmeister.cmcodingchallenge.persistence.mapper;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CurrencyMapper {

     @Mappings({
             @Mapping( source = "currency" , target = "currency")
     })
    CurrencyDto toCurrencyDto(CurrencyEntity currency);

    List<CurrencyDto> toCurrencies(List<CurrencyEntity> currency);

    @InheritInverseConfiguration
    @Mapping(target = "id" , ignore = true)
    CurrencyEntity toCurrencyEntity(CurrencyDto currency);
}
