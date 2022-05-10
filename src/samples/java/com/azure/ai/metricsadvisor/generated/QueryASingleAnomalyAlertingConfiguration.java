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

public class QueryASingleAnomalyAlertingConfiguration {
    public static void main(String[] args) {
        MetricsAdvisorClient metricsAdvisorClient =
                new MetricsAdvisorClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        // BEGIN:com.azure.ai.metricsadvisor.generated.metricsadvisorgetanomalyalertingconfiguration.queryasingleanomalyalertingconfiguration
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response =
                metricsAdvisorClient.getAnomalyAlertingConfigurationWithResponse(
                        "44444444-4444-4444-4444-000000000001", requestOptions);
        // END:com.azure.ai.metricsadvisor.generated.metricsadvisorgetanomalyalertingconfiguration.queryasingleanomalyalertingconfiguration
    }
}
