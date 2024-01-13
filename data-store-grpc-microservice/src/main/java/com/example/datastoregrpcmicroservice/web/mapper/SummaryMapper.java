package com.example.datastoregrpcmicroservice.web.mapper;

import com.example.datastoregrpcmicroservice.model.Summary;
import com.example.datastoregrpcmicroservice.web.dto.SummaryDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SummaryMapper extends Mappable<Summary, SummaryDto>{

}
