package com.example.datastoregrpcmicroservice.repository;

import com.example.datastoregrpcmicroservice.model.Data;
import com.example.datastoregrpcmicroservice.model.Summary;

import java.util.Optional;
import java.util.Set;

public interface SummaryRepository {
    Optional<Summary> findBySensorId(Long sensorId, Set<Data.MeasurementType> measurementTypes, Set<Summary.SummaryType> summaryTypes);
    void handle(Data data);
}
