// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClientBuilder;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class DeleteAnomalyDetectionConfiguration {
    public static void main(String[] args) {
        MetricsAdvisorAdministrationClient metricsAdvisorAdministrationClient =
                new MetricsAdvisorAdministrationClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        // BEGIN:com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationdeleteanomalydetectionconfiguration.deleteanomalydetectionconfiguration
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response =
                metricsAdvisorAdministrationClient.deleteAnomalyDetectionConfigurationWithResponse(
                        "33333333-3333-3333-3333-000000000001", requestOptions);
        // END:com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationdeleteanomalydetectionconfiguration.deleteanomalydetectionconfiguration
    }
}
