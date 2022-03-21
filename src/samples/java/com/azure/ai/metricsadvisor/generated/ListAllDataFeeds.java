// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClientBuilder;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class ListAllDataFeeds {
    public static void main(String[] args) {
        // BEGIN: com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationlistdatafeeds.listalldatafeeds
        MetricsAdvisorAdministrationClient metricsAdvisorAdministrationClient =
                new MetricsAdvisorAdministrationClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("creator", "demo@microsoft.com");
        requestOptions.addQueryParam("dataFeedName", "name_prefix");
        requestOptions.addQueryParam("dataSourceType", "SqlServer");
        requestOptions.addQueryParam("granularityName", "Daily");
        requestOptions.addQueryParam("status", "Active");
        PagedIterable<BinaryData> response = metricsAdvisorAdministrationClient.listDataFeeds(requestOptions);
        // END: com.azure.ai.metricsadvisor.generated.metricsadvisoradministrationlistdatafeeds.listalldatafeeds
    }
}
