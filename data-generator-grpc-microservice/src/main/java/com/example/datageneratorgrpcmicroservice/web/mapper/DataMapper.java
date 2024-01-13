package com.example.datageneratorgrpcmicroservice.web.mapper;

import com.example.datageneratorgrpcmicroservice.model.Data;
import com.example.datageneratorgrpcmicroservice.web.dto.DataDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto>{
}
