// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.ai.metricsadvisor.administration;

import com.azure.ai.metricsadvisor.administration.models.DataFeed;
import com.azure.ai.metricsadvisor.administration.models.ListDataFeedOptions;
import com.azure.ai.metricsadvisor.implementation.MetricsAdvisorClientImpl;
import com.azure.ai.metricsadvisor.models.AnomalyAlert;
import com.azure.ai.metricsadvisor.models.ListAlertOptions;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.OffsetDateTime;

/**
 * Initializes a new instance of the synchronous MetricsAdvisorClient type.
 */
@ServiceClient(builder = MetricsAdvisorAdministrationClientBuilder.class)
public final class MetricsAdvisorAdministrationClient {
    final MetricsAdvisorAdministrationAsyncClient client;
    @Generated
    private final MetricsAdvisorClientImpl serviceClient;

    /**
     * Initializes an instance of MetricsAdvisorAdministrationClient client.
     *
     * @param serviceClient the service client implementation.
     */
    MetricsAdvisorAdministrationClient(MetricsAdvisorClientImpl serviceClient, MetricsAdvisorAdministrationAsyncClient client) {
        this.serviceClient = serviceClient;
        this.client = client;
    }

    /**
     * List all data feeds.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dataFeedName</td><td>String</td><td>No</td><td>filter data feed by its name</td></tr>
     *     <tr><td>dataSourceType</td><td>String</td><td>No</td><td>filter data feed by its source type</td></tr>
     *     <tr><td>granularityName</td><td>String</td><td>No</td><td>filter data feed by its granularity</td></tr>
     *     <tr><td>status</td><td>String</td><td>No</td><td>filter data feed by its status</td></tr>
     *     <tr><td>creator</td><td>String</td><td>No</td><td>filter data feed by its creator</td></tr>
     *     <tr><td>skip</td><td>String</td><td>No</td><td>for paging, skipped number</td></tr>
     *     <tr><td>maxpagesize</td><td>String</td><td>No</td><td>the maximum number of items in one page</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     nextLink: String
     *     value: [
     *         {
     *             dataFeedId: String
     *             dataFeedName: String
     *             dataFeedDescription: String
     *             granularityName: String(Yearly/Monthly/Weekly/Daily/Hourly/Minutely/Secondly/Custom)
     *             granularityAmount: Integer
     *             metrics: [
     *                 {
     *                     metricId: String
     *                     metricName: String
     *                     metricDisplayName: String
     *                     metricDescription: String
     *                 }
     *             ]
     *             dimension: [
     *                 {
     *                     dimensionName: String
     *                     dimensionDisplayName: String
     *                 }
     *             ]
     *             timestampColumn: String
     *             dataStartFrom: String
     *             startOffsetInSeconds: Long
     *             maxConcurrency: Integer
     *             minRetryIntervalInSeconds: Long
     *             stopRetryAfterInSeconds: Long
     *             needRollup: String(NoRollup/NeedRollup/AlreadyRollup)
     *             rollUpMethod: String(None/Sum/Max/Min/Avg/Count)
     *             rollUpColumns: [
     *                 String
     *             ]
     *             allUpIdentification: String
     *             fillMissingPointType: String(SmartFilling/PreviousValue/CustomValue/NoFilling)
     *             fillMissingPointValue: Double
     *             viewMode: String(Private/Public)
     *             admins: [
     *                 String
     *             ]
     *             viewers: [
     *                 String
     *             ]
     *             isAdmin: Boolean
     *             creator: String
     *             status: String(Active/Paused)
     *             createdTime: String
     *             actionLinkTemplate: String
     *             authenticationType: String(Basic/ManagedIdentity/AzureSQLConnectionString/DataLakeGen2SharedKey/ServicePrincipal/ServicePrincipalInKV)
     *             credentialId: String
     *         }
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the paginated response with {@link PagedIterable}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listDataFeeds(RequestOptions requestOptions) {
        return this.serviceClient.listDataFeeds(requestOptions);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DataFeed> listDataFeeds() {
        return listDataFeeds(null, Context.NONE);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<DataFeed> listDataFeeds(ListDataFeedOptions options, Context context) {
        return new PagedIterable<>(client.listDataFeeds(options, context));
    }

    /**
     * Create a new data feed.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     dataFeedId: String
     *     dataFeedName: String
     *     dataFeedDescription: String
     *     granularityName: String(Yearly/Monthly/Weekly/Daily/Hourly/Minutely/Secondly/Custom)
     *     granularityAmount: Integer
     *     metrics: [
     *         {
     *             metricId: String
     *             metricName: String
     *             metricDisplayName: String
     *             metricDescription: String
     *         }
     *     ]
     *     dimension: [
     *         {
     *             dimensionName: String
     *             dimensionDisplayName: String
     *         }
     *     ]
     *     timestampColumn: String
     *     dataStartFrom: String
     *     startOffsetInSeconds: Long
     *     maxConcurrency: Integer
     *     minRetryIntervalInSeconds: Long
     *     stopRetryAfterInSeconds: Long
     *     needRollup: String(NoRollup/NeedRollup/AlreadyRollup)
     *     rollUpMethod: String(None/Sum/Max/Min/Avg/Count)
     *     rollUpColumns: [
     *         String
     *     ]
     *     allUpIdentification: String
     *     fillMissingPointType: String(SmartFilling/PreviousValue/CustomValue/NoFilling)
     *     fillMissingPointValue: Double
     *     viewMode: String(Private/Public)
     *     admins: [
     *         String
     *     ]
     *     viewers: [
     *         String
     *     ]
     *     isAdmin: Boolean
     *     creator: String
     *     status: String(Active/Paused)
     *     createdTime: String
     *     actionLinkTemplate: String
     *     authenticationType: String(Basic/ManagedIdentity/AzureSQLConnectionString/DataLakeGen2SharedKey/ServicePrincipal/ServicePrincipalInKV)
     *     credentialId: String
     * }
     * }</pre>
     *
     * @param body           parameters to create a data feed.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the {@link Response}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> createDataFeedWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.createDataFeedWithResponse(body, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DataFeed createDataFeed(DataFeed dataFeed) {
        return createDataFeedWithResponse(dataFeed, Context.NONE).getValue();
    }


    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DataFeed> createDataFeedWithResponse(DataFeed dataFeed, Context context) {
        return client.createDataFeedWithResponse(dataFeed, context).block();
    }

    /**
     * Get a data feed by its id.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     dataFeedId: String
     *     dataFeedName: String
     *     dataFeedDescription: String
     *     granularityName: String(Yearly/Monthly/Weekly/Daily/Hourly/Minutely/Secondly/Custom)
     *     granularityAmount: Integer
     *     metrics: [
     *         {
     *             metricId: String
     *             metricName: String
     *             metricDisplayName: String
     *             metricDescription: String
     *         }
     *     ]
     *     dimension: [
     *         {
     *             dimensionName: String
     *             dimensionDisplayName: String
     *         }
     *     ]
     *     timestampColumn: String
     *     dataStartFrom: String
     *     startOffsetInSeconds: Long
     *     maxConcurrency: Integer
     *     minRetryIntervalInSeconds: Long
     *     stopRetryAfterInSeconds: Long
     *     needRollup: String(NoRollup/NeedRollup/AlreadyRollup)
     *     rollUpMethod: String(None/Sum/Max/Min/Avg/Count)
     *     rollUpColumns: [
     *         String
     *     ]
     *     allUpIdentification: String
     *     fillMissingPointType: String(SmartFilling/PreviousValue/CustomValue/NoFilling)
     *     fillMissingPointValue: Double
     *     viewMode: String(Private/Public)
     *     admins: [
     *         String
     *     ]
     *     viewers: [
     *         String
     *     ]
     *     isAdmin: Boolean
     *     creator: String
     *     status: String(Active/Paused)
     *     createdTime: String
     *     actionLinkTemplate: String
     *     authenticationType: String(Basic/ManagedIdentity/AzureSQLConnectionString/DataLakeGen2SharedKey/ServicePrincipal/ServicePrincipalInKV)
     *     credentialId: String
     * }
     * }</pre>
     *
     * @param dataFeedId     The data feed unique id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return a data feed by its id along with {@link Response}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDataFeedByIdWithResponse(String dataFeedId, RequestOptions requestOptions) {
        return this.serviceClient.getDataFeedByIdWithResponse(dataFeedId, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DataFeed getDataFeed(String dataFeedId) {
        return getDataFeedWithResponse(dataFeedId, Context.NONE).getValue();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DataFeed> getDataFeedWithResponse(String dataFeedId, Context context) {
        return client.getDataFeedWithResponse(dataFeedId, context).block();
    }

    /**
     * Update a data feed.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     dataFeedName: String
     *     dataFeedDescription: String
     *     timestampColumn: String
     *     dataStartFrom: String
     *     startOffsetInSeconds: Long
     *     maxConcurrency: Integer
     *     minRetryIntervalInSeconds: Long
     *     stopRetryAfterInSeconds: Long
     *     needRollup: String(NoRollup/NeedRollup/AlreadyRollup)
     *     rollUpMethod: String(None/Sum/Max/Min/Avg/Count)
     *     rollUpColumns: [
     *         String
     *     ]
     *     allUpIdentification: String
     *     fillMissingPointType: String(SmartFilling/PreviousValue/CustomValue/NoFilling)
     *     fillMissingPointValue: Double
     *     viewMode: String(Private/Public)
     *     admins: [
     *         String
     *     ]
     *     viewers: [
     *         String
     *     ]
     *     status: String(Active/Paused)
     *     actionLinkTemplate: String
     *     authenticationType: String(Basic/ManagedIdentity/AzureSQLConnectionString/DataLakeGen2SharedKey/ServicePrincipal/ServicePrincipalInKV)
     *     credentialId: String
     * }
     * }</pre>
     *
     * @param dataFeedId     The data feed unique id.
     * @param body           parameters to update a data feed.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the {@link Response}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> updateDataFeedWithResponse(
            String dataFeedId, BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.updateDataFeedWithResponse(dataFeedId, body, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DataFeed updateDataFeed(DataFeed dataFeed) {
        return updateDataFeedWithResponse(dataFeed, Context.NONE).getValue();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DataFeed> updateDataFeedWithResponse(DataFeed dataFeed, Context context) {
        return client.updateDataFeedWithResponse(dataFeed, context).block();
    }

    /**
     * Delete a data feed.
     *
     * @param dataFeedId     The data feed unique id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the {@link Response}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteDataFeedWithResponse(String dataFeedId, RequestOptions requestOptions) {
        return this.serviceClient.deleteDataFeedWithResponse(dataFeedId, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteDataFeed(String dataFeedId) {
        deleteDataFeedWithResponse(dataFeedId, Context.NONE);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteDataFeedWithResponse(String dataFeedId, Context context) {
        return client.deleteDataFeedWithResponse(dataFeedId, context).block();
    }

}
