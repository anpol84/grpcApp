package com.example.datastoregrpcmicroservice.service;

import com.example.datastoregrpcmicroservice.model.Data;
import com.example.datastoregrpcmicroservice.model.Summary;

import java.util.Set;

public interface SummaryService {
    Summary get(Long sensorId, Set<Data.MeasurementType> measurementTypes, Set<Summary.SummaryType> summaryTypes);
    void handle(Data data);

}
