package com.example.dataanalysergrpcmicroservice.service;

import com.example.dataanalysergrpcmicroservice.model.Data;
import com.example.grpccommon.AnalyticsServerGrpc;
import com.example.grpccommon.GRPCAnalyticsRequest;
import com.example.grpccommon.GRPCData;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.example.grpccommon.MeasurementType;

import java.time.ZoneOffset;
import java.util.List;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class GRPCAnalyticsService extends AnalyticsServerGrpc.AnalyticsServerImplBase {
    private final DataService dataService;

    @Override
    public void askForData(GRPCAnalyticsRequest request, StreamObserver<GRPCData> responseObserver) {
        List<Data> data = dataService.getWithBatch(request.getBatchSize());
        for (Data d : data) {
            GRPCData dataRequest = GRPCData.newBuilder()
                    .setSensorId(d.getSensorId())
                    .setTimestamp(
                            Timestamp.newBuilder()
                                    .setSeconds(d.getTimestamp().toEpochSecond(ZoneOffset.UTC)).build()
                    )
                    .setMeasurement(d.getMeasurement())
                    .setMeasurementType(MeasurementType.valueOf(d.getMeasurementType().name()))
                    .build();

            responseObserver.onNext(dataRequest);
            log.info("Batch was sent");
            responseObserver.onCompleted();
        }


    }
}
