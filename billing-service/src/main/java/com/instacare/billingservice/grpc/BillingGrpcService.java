package com.instacare.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {
        try {
            log.info("Creating Billing Account request received {}", billingRequest.toString());

            // Validate request
            if (billingRequest.getPatientId() == null || billingRequest.getPatientId().isEmpty()) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Patient ID is required")
                        .asRuntimeException());
                return;
            }

            BillingResponse billingResponse = BillingResponse
                    .newBuilder()
                    .setAccountId("111111")
                    .setStatus("SUCCESS")
                    .build();
            log.info("Sending response: {}", billingResponse);
            responseObserver.onNext(billingResponse);
            responseObserver.onCompleted();  // if we do not want to send any other response
        } catch (Exception e) {
            log.error("Error creating billing account", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("An unexpected error occurred: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }

    }
}