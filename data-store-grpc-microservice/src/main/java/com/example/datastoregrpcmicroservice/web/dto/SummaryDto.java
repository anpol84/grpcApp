package com.example.datastoregrpcmicroservice.web.dto;

import com.example.datastoregrpcmicroservice.model.Data;
import com.example.datastoregrpcmicroservice.model.Summary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.List;
import java.util.Map;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SummaryDto {
    private long sensorId;
    private Map<Data.MeasurementType, List<Summary.SummaryEntry>> values;


}