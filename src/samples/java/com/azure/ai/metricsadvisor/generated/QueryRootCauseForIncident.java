// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.ai.metricsadvisor.MetricsAdvisorClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorClientBuilder;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class QueryRootCauseForIncident {
    public static void main(String[] args) {
        // BEGIN:
        // com.azure.ai.metricsadvisor.generated.metricsadvisorgetrootcauseofincidentbyanomalydetectionconfiguration.queryrootcauseforincident
        MetricsAdvisorClient metricsAdvisorClient =
                new MetricsAdvisorClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response =
                metricsAdvisorClient.getRootCauseOfIncidentByAnomalyDetectionConfigurationWithResponse(
                        "33333333-3333-3333-3333-000000000001", "iiiiiiiiiiii", requestOptions);
        // END:
        // com.azure.ai.metricsadvisor.generated.metricsadvisorgetrootcauseofincidentbyanomalydetectionconfiguration.queryrootcauseforincident
    }
}
