// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.ai.metricsadvisor.MetricsAdvisorClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorClientBuilder;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class ListDimensionFromCertainMetric {
    public static void main(String[] args) {
        // BEGIN: com.azure.ai.metricsadvisor.generated.metricsadvisorgetmetricdimension.listdimensionfromcertainmetric
        MetricsAdvisorClient metricsAdvisorClient =
                new MetricsAdvisorClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{endpoint}")
                        .buildClient();
        BinaryData body = BinaryData.fromString("{\"dimensionName\":\"city\"}");
        RequestOptions requestOptions = new RequestOptions();
        PagedIterable<BinaryData> response =
                metricsAdvisorClient.getMetricDimension("22222222-2222-2222-2222-000000000001", body, requestOptions);
        // END: com.azure.ai.metricsadvisor.generated.metricsadvisorgetmetricdimension.listdimensionfromcertainmetric
    }
}
