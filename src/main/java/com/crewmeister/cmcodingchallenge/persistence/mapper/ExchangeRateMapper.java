package com.crewmeister.cmcodingchallenge.persistence.mapper;

import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeDto toExchangeDto(ExchangeRateEntity exchangeRateEntity);

    List<ExchangeDto> toExchanges(List<ExchangeRateEntity> exchanges);

    @InheritInverseConfiguration
    @Mapping(target = "id" , ignore = true)
    ExchangeRateEntity toExchangeEntity(ExchangeDto currency);

}
