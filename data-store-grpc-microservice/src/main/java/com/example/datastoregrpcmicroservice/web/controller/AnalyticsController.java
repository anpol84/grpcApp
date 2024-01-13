package com.example.datastoregrpcmicroservice.web.controller;

import com.example.datastoregrpcmicroservice.model.Data;
import com.example.datastoregrpcmicroservice.model.Summary;
import com.example.datastoregrpcmicroservice.service.SummaryService;
import com.example.datastoregrpcmicroservice.web.dto.SummaryDto;
import com.example.datastoregrpcmicroservice.web.mapper.SummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.PriorityQueue;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final SummaryService summaryService;
    private final SummaryMapper summaryMapper;

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    @GetMapping("/summary/{sensorId}")
    public SummaryDto getSummary(@PathVariable long sensorId,
                                 @RequestParam(value = "mt", required = false)Set<Data.MeasurementType> measurementTypes,
                                 @RequestParam(value = "st", required = false)Set<Summary.SummaryType> summaryTypes){
        Summary summary = summaryService.get(sensorId, measurementTypes, summaryTypes);
        return summaryMapper.toDto(summary);
    }

}
