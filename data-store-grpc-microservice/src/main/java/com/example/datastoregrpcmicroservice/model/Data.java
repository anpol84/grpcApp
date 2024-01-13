package com.example.datastoregrpcmicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.example.grpccommon.GRPCData;

@NoArgsConstructor
@Getter
@Setter
public class Data {
    private Long id;
    private Long sensorId;
    private LocalDateTime timestamp;
    private double measurement;
    private MeasurementType measurementType;

    public enum MeasurementType {
        TEMPERATURE,
        VOLTAGE,
        POWER
    }

    public Data(GRPCData data) {
        this.id = data.getId();
        this.sensorId = data.getId();
        this.timestamp = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(
                        data.getTimestamp().getSeconds(),
                        data.getTimestamp().getNanos()
                ),
                ZoneId.systemDefault()
        );
        this.measurement = data.getMeasurement();
        this.measurementType = MeasurementType.valueOf(data.getMeasurementType().name());
    }
}
