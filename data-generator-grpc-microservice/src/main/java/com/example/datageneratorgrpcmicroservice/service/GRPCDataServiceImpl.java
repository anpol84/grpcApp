package com.example.datageneratorgrpcmicroservice.service;

import com.example.datageneratorgrpcmicroservice.model.Data;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import com.example.grpccommon.DataServerGrpc;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Stream;

import com.example.grpccommon.GRPCData;
import com.example.grpccommon.MeasurementType;

@Service
@RequiredArgsConstructor
public class GRPCDataServiceImpl implements GRPCDataService{

    @GrpcClient(value = "data-generator-blocking")
    private DataServerGrpc.DataServerBlockingStub blockingStub;

    @GrpcClient(value = "data-generator-async")
    private DataServerGrpc.DataServerStub asyncStub;

    @Override
    public void send(Data data) {
        GRPCData request = GRPCData.newBuilder()
                .setSensorId(data.getSensorId())
                .setTimestamp(
                        Timestamp.newBuilder()
                                .setSeconds(data.getTimestamp().toEpochSecond(ZoneOffset.UTC))
                                .build()
                )
                .setMeasurementType(
                        MeasurementType.valueOf(data.getMeasurementType().name())
                )
                .setMeasurement(data.getMeasurement())
                .build();

        StreamObserver<Empty> responseObserver = new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty empty) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
        asyncStub.addData(request, responseObserver);

    }

    @Override
    public void send(List<Data> data) {
        StreamObserver<Empty> responseObserver = new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty empty) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
        StreamObserver<GRPCData> requestObserver = asyncStub.addStreamOfData(responseObserver);
        for (Data d : data){
            GRPCData request = GRPCData.newBuilder()
                    .setSensorId(d.getSensorId())
                    .setTimestamp(
                            Timestamp.newBuilder()
                                    .setSeconds(d.getTimestamp().toEpochSecond(ZoneOffset.UTC))
                                    .build()
                    )
                    .setMeasurementType(
                            MeasurementType.valueOf(d.getMeasurementType().name())
                    )
                    .setMeasurement(d.getMeasurement())
                    .build();
            requestObserver.onNext(request);
        }
        requestObserver.onCompleted();
    }
}
