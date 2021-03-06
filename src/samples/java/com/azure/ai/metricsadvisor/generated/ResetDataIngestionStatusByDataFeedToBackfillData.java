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

public class ResetDataIngestionStatusByDataFeedToBackfillData {
    public static void main(String[] args) {
        MetricsAdvisorAdministrationClient metricsAdvisorAdministrationClient =
                new MetricsAdvisorAdministrationClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        // BEGIN:com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationresetdatafeedingestionstatus.resetdataingestionstatusbydatafeedtobackfilldata
        BinaryData body =
                BinaryData.fromString(
                        "{\"endTime\":\"2020-02-01T00:00:00.000Z\",\"startTime\":\"2020-01-01T00:00:00.000Z\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response =
                metricsAdvisorAdministrationClient.resetDataFeedIngestionStatusWithResponse(
                        "01234567-8901-2345-6789-012345678901", body, requestOptions);
        // END:com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationresetdatafeedingestionstatus.resetdataingestionstatusbydatafeedtobackfilldata
    }
}
