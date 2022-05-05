// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public final class DeleteADataFeedTests extends MetricsAdvisorClientTestBase {
    @Test
    @Disabled
    public void testDeleteADataFeedTests() {
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response =
                metricsAdvisorAdministrationClient.deleteDataFeedWithResponse(
                        "01234567-8901-2345-6789-012345678901", requestOptions);
        Assertions.assertEquals(204, response.getStatusCode());
    }
}
