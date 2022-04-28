// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public final class QueryAnomaliesUnderASpecificAlertTests extends MetricsAdvisorClientTestBase {
    @Test
    @Disabled
    public void testQueryAnomaliesUnderASpecificAlertTests() {
        RequestOptions requestOptions = new RequestOptions();
        PagedIterable<BinaryData> response =
                metricsAdvisorClient.getAnomaliesFromAlertByAnomalyAlertingConfiguration(
                        "44444444-4444-4444-4444-000000000001", "aaaaaaaaaaaa", requestOptions);
        Assertions.assertEquals(200, response.iterableByPage().iterator().next().getStatusCode());
        Assertions.assertEquals(
                BinaryData.fromString(
                                "{\"anomalyDetectionConfigurationId\":\"33333333-3333-3333-3333-000000000001\",\"createdTime\":\"2020-03-01T00:00:00.000Z\",\"dataFeedId\":\"11111111-1111-1111-1111-000000000001\",\"dimension\":{\"category\":\"__SUM__\",\"city\":\"Beijing\"},\"metricId\":\"22222222-2222-2222-2222-000000000001\",\"modifiedTime\":\"2020-03-01T00:00:00.000Z\",\"property\":{\"anomalySeverity\":\"High\",\"anomalyStatus\":\"Active\",\"expectedValue\":100,\"value\":1},\"timestamp\":\"2020-01-01T00:00:00.000Z\"}")
                        .toObject(Object.class),
                response.iterator().next().toObject(Object.class));
    }
}