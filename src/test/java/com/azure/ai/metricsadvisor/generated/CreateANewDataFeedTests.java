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

public final class CreateANewDataFeedTests extends MetricsAdvisorClientTestBase {
    @Test
    @Disabled
    public void testCreateANewDataFeedTests() {
        BinaryData body =
                BinaryData.fromString(
                        "{\"allUpIdentification\":\"__SUM__\",\"authenticationType\":\"Basic\",\"dataFeedDescription\":\"This is a sample data feed.\",\"dataFeedName\":\"Sample - cost/revenue - city/category\",\"dataSourceParameter\":{\"connectionString\":\"Server=PlaceholderSqlServer,1433;Initial Catalog=PlaceholderDatabase;User ID=PlaceholderUserName;Password=PlaceholderPassword;\",\"query\":\"select * from your_table where timestamp = @StartTime\"},\"dataSourceType\":\"SqlServer\",\"dataStartFrom\":\"2020-01-01T00:00:00.000Z\",\"dimension\":[{\"dimensionDisplayName\":\"category\",\"dimensionName\":\"category\"},{\"dimensionDisplayName\":\"city\",\"dimensionName\":\"city\"}],\"fillMissingPointType\":\"SmartFilling\",\"granularityName\":\"Daily\",\"maxConcurrency\":5,\"metrics\":[{\"metricDisplayName\":\"cost\",\"metricName\":\"cost\"},{\"metricDisplayName\":\"revenue\",\"metricName\":\"revenue\"}],\"minRetryIntervalInSeconds\":3600,\"needRollup\":\"NeedRollup\",\"rollUpMethod\":\"Sum\",\"startOffsetInSeconds\":86400,\"stopRetryAfterInSeconds\":604800,\"timestampColumn\":\"timestamp\",\"viewMode\":\"Private\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = metricsAdvisorAdministrationClient.createDataFeedWithResponse(body, requestOptions);
        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertEquals("", response.getHeaders().get("Location").getValue());
    }
}
