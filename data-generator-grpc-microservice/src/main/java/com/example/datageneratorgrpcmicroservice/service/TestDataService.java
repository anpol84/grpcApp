package com.example.datageneratorgrpcmicroservice.service;

import com.example.datageneratorgrpcmicroservice.model.test.DataTestOptions;
import org.springframework.stereotype.Service;


public interface TestDataService {
    void sendMessages(DataTestOptions testOptions);
}
