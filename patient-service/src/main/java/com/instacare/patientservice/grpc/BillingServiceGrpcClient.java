package com.instacare.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String grpcServerAddress,
            @Value("${billing.service.grpc.port:6565}") int grpcPort) {
        log.info("Billing GRPC server connected at {}:{}", grpcServerAddress, grpcPort);

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(grpcServerAddress, grpcPort)
                .usePlaintext()
                .build();
        stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email,
                                                String mobileNumber, String gender) {
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .setMobileNumber(mobileNumber)
                .setGender(gender)
                .build();

        try {
            BillingResponse response = stub.createBillingAccount(request);
            log.info("Response from billing service is {}", response);
            return response;
        } catch (Exception ex) {
            log.error("Exception while creating billing account", ex);
            return null;
        }
    }

}
