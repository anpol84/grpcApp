package com.example.datageneratorgrpcmicroservice.service;

import com.example.datageneratorgrpcmicroservice.model.Data;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GRPCDataService {
    void send(Data data);
    void send(List<Data> data);
}
