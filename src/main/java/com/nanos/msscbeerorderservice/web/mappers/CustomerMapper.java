package com.nanos.msscbeerorderservice.web.mappers;

import com.nanos.brewery.model.CustomerDto;
import com.nanos.msscbeerorderservice.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}