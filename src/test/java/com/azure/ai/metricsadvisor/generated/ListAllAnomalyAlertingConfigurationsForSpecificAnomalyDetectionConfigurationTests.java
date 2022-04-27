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

public final class ListAllAnomalyAlertingConfigurationsForSpecificAnomalyDetectionConfigurationTests
        extends MetricsAdvisorClientTestBase {
    @Test
    @Disabled
    public void testListAllAnomalyAlertingConfigurationsForSpecificAnomalyDetectionConfigurationTests() {
        RequestOptions requestOptions = new RequestOptions();
        PagedIterable<BinaryData> response =
                metricsAdvisorAdministrationClient.getAnomalyAlertingConfigurationsByAnomalyDetectionConfiguration(
                        "33333333-3333-3333-3333-000000000001", requestOptions);
        Assertions.assertEquals(200, response.iterableByPage().iterator().next().getStatusCode());
        Assertions.assertEquals(
                BinaryData.fromString(
                                "{\"name\":\"alerting configuration name\",\"description\":\"this is an anomaly alerting configuration\",\"anomalyAlertingConfigurationId\":\"44444444-4444-4444-4444-000000000001\",\"crossMetricsOperator\":\"AND\",\"hookIds\":[\"00000000-0000-0000-0000-000000000001\"],\"metricAlertingConfigurations\":[{\"anomalyDetectionConfigurationId\":\"33333333-3333-3333-3333-000000000001\",\"anomalyScopeType\":\"All\",\"negationOperation\":false,\"severityFilter\":{\"maxAlertSeverity\":\"High\",\"minAlertSeverity\":\"Medium\"},\"snoozeFilter\":{\"autoSnooze\":0,\"onlyForSuccessive\":true,\"snoozeScope\":\"Series\"}},{\"anomalyDetectionConfigurationId\":\"33333333-3333-3333-3333-000000000002\",\"anomalyScopeType\":\"Dimension\",\"dimensionAnomalyScope\":{\"dimension\":{\"city\":\"Beijing\"}},\"negationOperation\":false,\"severityFilter\":{\"maxAlertSeverity\":\"High\",\"minAlertSeverity\":\"Low\"},\"snoozeFilter\":{\"autoSnooze\":0,\"onlyForSuccessive\":true,\"snoozeScope\":\"Series\"},\"valueFilter\":{\"direction\":\"Both\",\"lower\":0,\"upper\":1000}}]}")
                        .toObject(Object.class),
                response.iterator().next().toObject(Object.class));
    }
}
