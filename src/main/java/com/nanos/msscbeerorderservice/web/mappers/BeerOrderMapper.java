package com.nanos.msscbeerorderservice.web.mappers;

import com.nanos.brewery.model.BeerOrderDto;
import com.nanos.msscbeerorderservice.domain.BeerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class,BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    @Mapping(target = "customerId", source = "customer.id")
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
