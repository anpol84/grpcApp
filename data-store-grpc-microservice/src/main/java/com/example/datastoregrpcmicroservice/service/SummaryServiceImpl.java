package com.example.datastoregrpcmicroservice.service;

import com.example.datastoregrpcmicroservice.model.Data;
import com.example.datastoregrpcmicroservice.model.Summary;
import com.example.datastoregrpcmicroservice.model.exception.SensorNotFoundException;
import com.example.datastoregrpcmicroservice.repository.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final SummaryRepository summaryRepository;

    @Override
    public Summary get(Long sensorId, Set<Data.MeasurementType> measurementTypes, Set<Summary.SummaryType> summaryTypes) {
        return summaryRepository.findBySensorId(sensorId,
                measurementTypes == null ? Set.of(Data.MeasurementType.values()) : measurementTypes,
                summaryTypes == null ? Set.of(Summary.SummaryType.values()) : summaryTypes).orElseThrow(
                SensorNotFoundException::new
        );


    }

    @Override
    public void handle(Data data) {
        summaryRepository.handle(data);
    }
}
