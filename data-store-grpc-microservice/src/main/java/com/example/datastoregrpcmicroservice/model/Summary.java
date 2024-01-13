package com.example.datastoregrpcmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Summary {
    private long sensorId;
    private Map<Data.MeasurementType, List<SummaryEntry>> values;

    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class SummaryEntry{
        private SummaryType type;
        private double value;
        private long counter;

    }

    public enum SummaryType{
        MIN,
        MAX,
        SUM,
        AVG
    }

    public Summary(){
        this.values = new HashMap<>();
    }

    public void addValue(Data.MeasurementType type, SummaryEntry entry){
        if (values.containsKey(type)){
            List<SummaryEntry> entries = new ArrayList<>(values.get(type));
            entries.add(entry);
            values.put(type, entries);
        }else{
            values.put(type, List.of(entry));
        }

    }
}
