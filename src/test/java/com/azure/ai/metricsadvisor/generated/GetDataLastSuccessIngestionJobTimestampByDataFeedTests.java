// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public final class GetDataLastSuccessIngestionJobTimestampByDataFeedTests extends MetricsAdvisorClientTestBase {
    @Test
    @Disabled
    public void testGetDataLastSuccessIngestionJobTimestampByDataFeedTests() {
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response =
                metricsAdvisorAdministrationClient.getIngestionProgressWithResponse(
                        "01234567-8901-2345-6789-012345678901", requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(
                BinaryData.fromString(
                                "{\"latestActiveTimestamp\":\"2020-01-31T00:00:00.000Z\",\"latestSuccessTimestamp\":\"2020-01-30T00:00:00.000Z\"}")
                        .toObject(Object.class),
                response.getValue().toObject(Object.class));
    }
}
