package com.example.datastoregrpcmicroservice.config;

import com.example.datastoregrpcmicroservice.model.Data;

public class RedisSchema {
    public static String sensorsKeys(){
        return KeyHelper.getKey("sensors");
    }

    public static String summaryKey(long sensorId, Data.MeasurementType measurementType){
        return KeyHelper.getKey("sensors:" + sensorId + ":" + measurementType.name().toLowerCase());
    }
}
