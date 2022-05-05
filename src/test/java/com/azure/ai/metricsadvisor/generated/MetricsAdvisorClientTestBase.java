// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.generated;

import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClientBuilder;
import com.azure.ai.metricsadvisor.MetricsAdvisorClient;
import com.azure.ai.metricsadvisor.MetricsAdvisorClientBuilder;
import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestBase;
import com.azure.core.test.TestMode;
import com.azure.core.util.Configuration;
import com.azure.identity.DefaultAzureCredentialBuilder;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

class MetricsAdvisorClientTestBase extends TestBase {
    protected MetricsAdvisorClient metricsAdvisorClient;

    protected MetricsAdvisorAdministrationClient metricsAdvisorAdministrationClient;

    @Override
    protected void beforeTest() {
        MetricsAdvisorClientBuilder metricsAdvisorClientbuilder =
                new MetricsAdvisorClientBuilder()
                        .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            metricsAdvisorClientbuilder
                    .httpClient(interceptorManager.getPlaybackClient())
                    .credential(request -> Mono.just(new AccessToken("this_is_a_token", OffsetDateTime.MAX)));
        } else if (getTestMode() == TestMode.RECORD) {
            metricsAdvisorClientbuilder
                    .addPolicy(interceptorManager.getRecordPolicy())
                    .credential(new DefaultAzureCredentialBuilder().build());
        } else if (getTestMode() == TestMode.LIVE) {
            metricsAdvisorClientbuilder.credential(new DefaultAzureCredentialBuilder().build());
        }
        metricsAdvisorClient = metricsAdvisorClientbuilder.buildClient();

        MetricsAdvisorAdministrationClientBuilder metricsAdvisorAdministrationClientbuilder =
                new MetricsAdvisorAdministrationClientBuilder()
                        .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
                        .httpClient(HttpClient.createDefault())
                        .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            metricsAdvisorAdministrationClientbuilder
                    .httpClient(interceptorManager.getPlaybackClient())
                    .credential(request -> Mono.just(new AccessToken("this_is_a_token", OffsetDateTime.MAX)));
        } else if (getTestMode() == TestMode.RECORD) {
            metricsAdvisorAdministrationClientbuilder
                    .addPolicy(interceptorManager.getRecordPolicy())
                    .credential(new DefaultAzureCredentialBuilder().build());
        } else if (getTestMode() == TestMode.LIVE) {
            metricsAdvisorAdministrationClientbuilder.credential(new DefaultAzureCredentialBuilder().build());
        }
        metricsAdvisorAdministrationClient = metricsAdvisorAdministrationClientbuilder.buildClient();
    }
}
