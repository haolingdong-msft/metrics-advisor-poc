// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClientBuilder;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class GetADataSourceCredential {
    public static void main(String[] args) {
        MetricsAdvisorAdministrationClient metricsAdvisorAdministrationClient =
                new MetricsAdvisorAdministrationClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        // BEGIN:com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationgetcredential.getadatasourcecredential
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response =
                metricsAdvisorAdministrationClient.getCredentialWithResponse(
                        "01234567-8901-2345-6789-012345678901", requestOptions);
        // END:com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationgetcredential.getadatasourcecredential
    }
}
