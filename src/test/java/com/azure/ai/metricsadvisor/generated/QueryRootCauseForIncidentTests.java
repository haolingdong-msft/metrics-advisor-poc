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

public final class QueryRootCauseForIncidentTests extends MetricsAdvisorClientTestBase {
    @Test
    @Disabled
    public void testQueryRootCauseForIncidentTests() {
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response =
                metricsAdvisorClient.getRootCauseOfIncidentByAnomalyDetectionConfigurationWithResponse(
                        "33333333-3333-3333-3333-000000000001", "iiiiiiiiiiii", requestOptions);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(
                BinaryData.fromString(
                                "{\"value\":[{\"path\":[\"city\"],\"description\":\"city = Beijing contributes the most to this anomaly.\",\"rootCause\":{\"dimension\":{\"city\":\"Beijing\"}},\"score\":0.67}]}")
                        .toObject(Object.class),
                response.getValue().toObject(Object.class));
    }
}
