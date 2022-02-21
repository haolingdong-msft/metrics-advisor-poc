// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.ai.metricsadvisor;

import com.azure.ai.metricsadvisor.implementation.MetricsAdvisorClientImpl;
import com.azure.ai.metricsadvisor.models.MetricsAdvisorKeyCredential;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpPipelinePosition;
import com.azure.core.http.policy.AddHeadersPolicy;
import com.azure.core.http.policy.BearerTokenAuthenticationPolicy;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.HttpPolicyProviders;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JacksonAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/** A builder for creating a new instance of the MetricsAdvisorClient type. */
@ServiceClientBuilder(serviceClients = {MetricsAdvisorClient.class, MetricsAdvisorAsyncClient.class})
public final class MetricsAdvisorClientBuilder {

    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    static final String OCP_APIM_SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";

    static final String API_KEY = "x-api-key";

    @Generated
    private static final String[] DEFAULT_SCOPES = new String[] {"https://cognitiveservices.azure.com/.default"};

    @Generated private final Map<String, String> properties = new HashMap<>();

    /** Create an instance of the MetricsAdvisorClientBuilder. */
    @Generated
    public MetricsAdvisorClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * Supported Cognitive Services endpoints (protocol and hostname, for
     * example: https://<resource-name>.cognitiveservices.azure.com).
     */
    @Generated private String endpoint;

    /**
     * Sets Supported Cognitive Services endpoints (protocol and hostname, for example:
     * https://&lt;resource-name&gt;.cognitiveservices.azure.com).
     *
     * @param endpoint the endpoint value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /*
     * The HTTP pipeline to send requests through
     */
    @Generated private HttpPipeline pipeline;

    /**
     * Sets The HTTP pipeline to send requests through.
     *
     * @param pipeline the pipeline value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /*
     * The HTTP client used to send the request.
     */
    @Generated private HttpClient httpClient;

    /**
     * Sets The HTTP client used to send the request.
     *
     * @param httpClient the httpClient value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /*
     * The configuration store that is used during construction of the service
     * client.
     */
    @Generated private Configuration configuration;

    /**
     * Sets The configuration store that is used during construction of the service client.
     *
     * @param configuration the configuration value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * The TokenCredential used for authentication.
     */
    @Generated private TokenCredential tokenCredential;

    /**
     * Sets The TokenCredential used for authentication.
     *
     * @param tokenCredential the tokenCredential value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder credential(TokenCredential tokenCredential) {
        this.tokenCredential = tokenCredential;
        return this;
    }

    /** Key credential for MetricsAdvisor */
    private MetricsAdvisorKeyCredential metricsAdvisorKeyCredential;

    /**
     * Sets the {@link MetricsAdvisorKeyCredential} to use when authenticating HTTP requests for this
     * MetricsAdvisorClientBuilder.
     *
     * @param metricsAdvisorKeyCredential {@link MetricsAdvisorKeyCredential} API key credential
     * @return The updated MetricsAdvisorClientBuilder object.
     * @throws NullPointerException If {@code metricsAdvisorKeyCredential} is null.
     */
    public MetricsAdvisorClientBuilder credential(MetricsAdvisorKeyCredential metricsAdvisorKeyCredential) {
        this.metricsAdvisorKeyCredential =
                Objects.requireNonNull(metricsAdvisorKeyCredential, "'metricsAdvisorKeyCredential' cannot be null.");
        return this;
    }

    /*
     * The logging configuration for HTTP requests and responses.
     */
    @Generated private HttpLogOptions httpLogOptions;

    /**
     * Sets The logging configuration for HTTP requests and responses.
     *
     * @param httpLogOptions the httpLogOptions value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
        return this;
    }

    /*
     * The retry policy that will attempt to retry failed requests, if
     * applicable.
     */
    @Generated private RetryPolicy retryPolicy;

    /**
     * Sets The retry policy that will attempt to retry failed requests, if applicable.
     *
     * @param retryPolicy the retryPolicy value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /*
     * The list of Http pipeline policies to add.
     */
    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /*
     * The client options such as application ID and custom headers to set on a
     * request.
     */
    @Generated private ClientOptions clientOptions;

    /**
     * Sets The client options such as application ID and custom headers to set on a request.
     *
     * @param clientOptions the clientOptions value.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder clientOptions(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        return this;
    }

    /**
     * Adds a custom Http pipeline policy.
     *
     * @param customPolicy The custom Http pipeline policy to add.
     * @return the MetricsAdvisorClientBuilder.
     */
    @Generated
    public MetricsAdvisorClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        pipelinePolicies.add(customPolicy);
        return this;
    }

    /**
     * Builds an instance of MetricsAdvisorClientImpl with the provided parameters.
     *
     * @return an instance of MetricsAdvisorClientImpl.
     */
    @Generated
    private MetricsAdvisorClientImpl buildInnerClient() {
        if (pipeline == null) {
            this.pipeline = createHttpPipeline();
        }
        MetricsAdvisorClientImpl client =
                new MetricsAdvisorClientImpl(pipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint);
        return client;
    }

    private HttpPipeline createHttpPipeline() {
        Configuration buildConfiguration =
                (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;
        if (httpLogOptions == null) {
            httpLogOptions = new HttpLogOptions();
        }
        if (clientOptions == null) {
            clientOptions = new ClientOptions();
        }
        List<HttpPipelinePolicy> policies = new ArrayList<>();
        String clientName = properties.getOrDefault(SDK_NAME, "UnknownName");
        String clientVersion = properties.getOrDefault(SDK_VERSION, "UnknownVersion");
        String applicationId = CoreUtils.getApplicationId(clientOptions, httpLogOptions);
        policies.add(new UserAgentPolicy(applicationId, clientName, clientVersion, buildConfiguration));
        HttpHeaders headers = new HttpHeaders();
        clientOptions.getHeaders().forEach(header -> headers.set(header.getName(), header.getValue()));
        policies.addAll(
                this.pipelinePolicies.stream()
                        .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL)
                        .collect(Collectors.toList()));
        HttpPolicyProviders.addBeforeRetryPolicies(policies);
        policies.add(retryPolicy == null ? new RetryPolicy() : retryPolicy);
        policies.add(new CookiePolicy());
        if (tokenCredential != null) {
            policies.add(new BearerTokenAuthenticationPolicy(tokenCredential, DEFAULT_SCOPES));
        } else if (!CoreUtils.isNullOrEmpty(metricsAdvisorKeyCredential.getKeys().getSubscriptionKey())
                || !CoreUtils.isNullOrEmpty(metricsAdvisorKeyCredential.getKeys().getApiKey())) {
            headers.set(OCP_APIM_SUBSCRIPTION_KEY, metricsAdvisorKeyCredential.getKeys().getSubscriptionKey());
            headers.set(API_KEY, metricsAdvisorKeyCredential.getKeys().getApiKey());
        }
        if (headers.getSize() > 0) {
            policies.add(new AddHeadersPolicy(headers));
        }
        policies.addAll(
                this.pipelinePolicies.stream()
                        .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)
                        .collect(Collectors.toList()));
        HttpPolicyProviders.addAfterRetryPolicies(policies);
        policies.add(new HttpLoggingPolicy(httpLogOptions));
        HttpPipeline httpPipeline =
                new HttpPipelineBuilder()
                        .policies(policies.toArray(new HttpPipelinePolicy[0]))
                        .httpClient(httpClient)
                        .clientOptions(clientOptions)
                        .build();
        return httpPipeline;
    }

    /**
     * Builds an instance of MetricsAdvisorAsyncClient async client.
     *
     * @return an instance of MetricsAdvisorAsyncClient.
     */
    @Generated
    public MetricsAdvisorAsyncClient buildAsyncClient() {
        return new MetricsAdvisorAsyncClient(buildInnerClient());
    }

    /**
     * Builds an instance of MetricsAdvisorClient sync client.
     *
     * @return an instance of MetricsAdvisorClient.
     */
    @Generated
    public MetricsAdvisorClient buildClient() {
        return new MetricsAdvisorClient(buildInnerClient());
    }
}
