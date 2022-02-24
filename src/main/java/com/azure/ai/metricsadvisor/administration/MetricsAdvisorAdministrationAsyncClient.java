// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.ai.metricsadvisor.administration;

import com.azure.ai.metricsadvisor.administration.models.DataFeed;
import com.azure.ai.metricsadvisor.administration.models.DataFeedGranularity;
import com.azure.ai.metricsadvisor.administration.models.DataFeedIngestionSettings;
import com.azure.ai.metricsadvisor.administration.models.DataFeedMissingDataPointFillSettings;
import com.azure.ai.metricsadvisor.administration.models.DataFeedMissingDataPointFillType;
import com.azure.ai.metricsadvisor.administration.models.DataFeedOptions;
import com.azure.ai.metricsadvisor.administration.models.DataFeedRollupSettings;
import com.azure.ai.metricsadvisor.administration.models.DataFeedSchema;
import com.azure.ai.metricsadvisor.administration.models.ListDataFeedFilter;
import com.azure.ai.metricsadvisor.administration.models.ListDataFeedOptions;
import com.azure.ai.metricsadvisor.implementation.MetricsAdvisorClientImpl;
import com.azure.ai.metricsadvisor.implementation.models.DataFeedDetail;
import com.azure.ai.metricsadvisor.implementation.models.DataFeedDetailPatch;
import com.azure.ai.metricsadvisor.implementation.models.EntityStatus;
import com.azure.ai.metricsadvisor.implementation.models.FillMissingPointType;
import com.azure.ai.metricsadvisor.implementation.models.Granularity;
import com.azure.ai.metricsadvisor.implementation.models.NeedRollupEnum;
import com.azure.ai.metricsadvisor.implementation.models.RollUpMethod;
import com.azure.ai.metricsadvisor.implementation.models.ViewMode;
import com.azure.ai.metricsadvisor.implementation.util.DataFeedTransforms;
import com.azure.ai.metricsadvisor.implementation.util.PagedConverter;
import com.azure.ai.metricsadvisor.models.MetricsAdvisorError;
import com.azure.ai.metricsadvisor.models.MetricsAdvisorResponseException;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.models.ResponseError;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

import static com.azure.ai.metricsadvisor.administration.models.DataFeedGranularityType.CUSTOM;
import static com.azure.ai.metricsadvisor.implementation.util.Utility.parseOperationId;
import static com.azure.core.util.FluxUtil.monoError;
import static com.azure.core.util.FluxUtil.withContext;
import static com.azure.core.util.tracing.Tracer.AZ_TRACING_NAMESPACE_KEY;

/**
 * Initializes a new instance of the asynchronous MetricsAdvisorAdministrationAsyncClient type.
 */
@ServiceClient(builder = MetricsAdvisorAdministrationClientBuilder.class, isAsync = true)
public final class MetricsAdvisorAdministrationAsyncClient {
    private static final String METRICS_ADVISOR_TRACING_NAMESPACE_VALUE = "Microsoft.CognitiveServices";
    private final ClientLogger logger = new ClientLogger(MetricsAdvisorAdministrationAsyncClient.class);

    @Generated
    private final MetricsAdvisorClientImpl serviceClient;

    /**
     * Initializes an instance of MetricsAdvisorAdministrationAsyncClient client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    MetricsAdvisorAdministrationAsyncClient(MetricsAdvisorClientImpl serviceClient) {
        this.serviceClient = serviceClient;
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
     * @return the paginated response with {@link PagedFlux}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listDataFeeds(RequestOptions requestOptions) {
        return this.serviceClient.listDataFeedsAsync(requestOptions);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DataFeed> listDataFeeds() {
        return listDataFeeds(new ListDataFeedOptions());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<DataFeed> listDataFeeds(ListDataFeedOptions listDataFeedOptions) {
        return listDataFeeds(listDataFeedOptions, Context.NONE);
    }

    PagedFlux<DataFeed> listDataFeeds(ListDataFeedOptions options, Context context) {
        options = options != null ? options : new ListDataFeedOptions();
        final ListDataFeedFilter dataFeedFilter =
                options.getListDataFeedFilter() != null ? options.getListDataFeedFilter() : new ListDataFeedFilter();
        final Context withTracing = context.addData(AZ_TRACING_NAMESPACE_KEY, METRICS_ADVISOR_TRACING_NAMESPACE_VALUE);

        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.setContext(withTracing);
        if (dataFeedFilter.getName() != null) {
            requestOptions.addQueryParam("dataFeedName", dataFeedFilter.getName());
        }
        if (dataFeedFilter.getSourceType() != null) {
            requestOptions.addQueryParam("dataSourceType", dataFeedFilter.getSourceType().toString());
        }
        if (dataFeedFilter.getGranularityType() != null) {
            requestOptions.addQueryParam("granularityName", dataFeedFilter.getGranularityType().toString());
        }
        if (dataFeedFilter.getStatus() != null) {
            requestOptions.addQueryParam("status", dataFeedFilter.getStatus().toString());
        }
        if (dataFeedFilter.getCreator() != null) {
            requestOptions.addQueryParam("creator", dataFeedFilter.getCreator());
        }
        if (options.getSkip() != null) {
            requestOptions.addQueryParam("$skip", options.getSkip().toString(), true);
        }
        if (options.getMaxPageSize() != null) {
            requestOptions.addQueryParam("$maxpagesize", options.getMaxPageSize().toString(), true);
        }

        return PagedConverter.mapPage(this.listDataFeeds(requestOptions), response -> {
            DataFeedDetail dataFeedDetail = response.toObject(DataFeedDetail.class);
            return DataFeedTransforms.fromInner(dataFeedDetail);
        });
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> createDataFeedWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.createDataFeedWithResponseAsync(body, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DataFeed> createDataFeed(DataFeed dataFeed) {
        return createDataFeedWithResponse(dataFeed).flatMap(FluxUtil::toMono);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DataFeed>> createDataFeedWithResponse(DataFeed dataFeed) {
        try {
            return withContext(context -> createDataFeedWithResponse(dataFeed, context));
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    Mono<Response<DataFeed>> createDataFeedWithResponse(DataFeed dataFeed, Context context) {

        Objects.requireNonNull(dataFeed, "'dataFeed' is required and cannot be null.");
        Objects.requireNonNull(dataFeed.getSource(), "'dataFeedSource' is required and cannot be null.");
        Objects.requireNonNull(dataFeed.getName(), "'dataFeedName' cannot be null or empty.");

        final DataFeedSchema dataFeedSchema = dataFeed.getSchema();
        final DataFeedGranularity dataFeedGranularity = dataFeed.getGranularity();
        final DataFeedIngestionSettings dataFeedIngestionSettings = dataFeed.getIngestionSettings();

        if (dataFeedSchema == null) {
            throw logger.logExceptionAsError(
                    new NullPointerException("'dataFeedSchema.metrics' cannot be null or empty."));
        } else {
            Objects.requireNonNull(dataFeedSchema.getMetrics(),
                    "'dataFeedSchema.metrics' cannot be null or empty.");
        }

        if (dataFeedGranularity == null) {
            throw logger.logExceptionAsError(
                    new NullPointerException("'dataFeedGranularity.granularityType' is required and cannot be null."));

        } else {
            Objects.requireNonNull(dataFeedGranularity.getGranularityType(),
                    "'dataFeedGranularity.granularityType' is required.");
            if (CUSTOM.equals(dataFeedGranularity.getGranularityType())) {
                Objects.requireNonNull(dataFeedGranularity.getCustomGranularityValue(),
                        "'dataFeedGranularity.customGranularityValue' is required when granularity type is CUSTOM");
            }
        }

        if (dataFeedIngestionSettings == null) {
            throw logger.logExceptionAsError(
                    new NullPointerException(
                            "'dataFeedIngestionSettings.ingestionStartTime' is required and cannot be null."));
        } else {
            Objects.requireNonNull(dataFeedIngestionSettings.getIngestionStartTime(),
                    "'dataFeedIngestionSettings.ingestionStartTime' is required and cannot be null.");
        }

        final DataFeedOptions finalDataFeedOptions = dataFeed.getOptions() == null
                ? new DataFeedOptions() : dataFeed.getOptions();
        final DataFeedRollupSettings dataFeedRollupSettings = finalDataFeedOptions.getRollupSettings() == null
                ? new DataFeedRollupSettings() : finalDataFeedOptions.getRollupSettings();
        final DataFeedMissingDataPointFillSettings dataFeedMissingDataPointFillSettings =
                finalDataFeedOptions.getMissingDataPointFillSettings() == null
                        ? new DataFeedMissingDataPointFillSettings() : finalDataFeedOptions.getMissingDataPointFillSettings();
        final Context withTracing = context.addData(AZ_TRACING_NAMESPACE_KEY, METRICS_ADVISOR_TRACING_NAMESPACE_VALUE);

        DataFeedDetail dataFeedDetail = DataFeedTransforms.toDataFeedDetailSource(dataFeed.getSource())
                .setDataFeedName(dataFeed.getName())
                .setDataFeedDescription(finalDataFeedOptions.getDescription())
                .setGranularityName(Granularity.fromString(dataFeedGranularity.getGranularityType() == null
                        ? null : dataFeedGranularity.getGranularityType().toString()))
                .setGranularityAmount(dataFeedGranularity.getCustomGranularityValue())
                .setDimension(DataFeedTransforms.toInnerDimensionsListForCreate(dataFeedSchema.getDimensions()))
                .setMetrics(DataFeedTransforms.toInnerMetricsListForCreate(dataFeedSchema.getMetrics()))
                .setTimestampColumn(dataFeedSchema.getTimestampColumn())
                .setDataStartFrom(dataFeedIngestionSettings.getIngestionStartTime())
                .setStartOffsetInSeconds(dataFeedIngestionSettings.getIngestionStartOffset() == null
                        ? null : dataFeedIngestionSettings.getIngestionStartOffset().getSeconds())
                .setMaxConcurrency(dataFeedIngestionSettings.getDataSourceRequestConcurrency())
                .setStopRetryAfterInSeconds(dataFeedIngestionSettings.getStopRetryAfter() == null
                        ? null : dataFeedIngestionSettings.getStopRetryAfter().getSeconds())
                .setMinRetryIntervalInSeconds(dataFeedIngestionSettings.getIngestionRetryDelay() == null
                        ? null : dataFeedIngestionSettings.getIngestionRetryDelay().getSeconds())
                .setRollUpColumns(dataFeedRollupSettings.getAutoRollupGroupByColumnNames())
                .setRollUpMethod(RollUpMethod.fromString(dataFeedRollupSettings
                        .getDataFeedAutoRollUpMethod() == null
                        ? null : dataFeedRollupSettings.getDataFeedAutoRollUpMethod().toString()))
                .setNeedRollup(NeedRollupEnum.fromString(dataFeedRollupSettings.getRollupType() == null
                        ? null : dataFeedRollupSettings.getRollupType().toString()))
                .setAllUpIdentification(dataFeedRollupSettings.getRollupIdentificationValue())
                .setFillMissingPointType(FillMissingPointType.fromString(
                        dataFeedMissingDataPointFillSettings.getFillType() == null
                                ? null : dataFeedMissingDataPointFillSettings.getFillType().toString()))
                .setFillMissingPointValue(dataFeedMissingDataPointFillSettings.getCustomFillValue())
                .setViewMode(ViewMode.fromString(finalDataFeedOptions.getAccessMode() == null
                        ? null : finalDataFeedOptions.getAccessMode().toString()))
                .setViewers(finalDataFeedOptions.getViewers())
                .setAdmins(finalDataFeedOptions.getAdmins())
                .setActionLinkTemplate(finalDataFeedOptions.getActionLinkTemplate());

        BinaryData body = BinaryData.fromObject(dataFeedDetail);
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.setContext(withTracing);

        return this.createDataFeedWithResponse(body, requestOptions).flatMap(createDataFeedResponse -> {
            final String dataFeedId =
                    parseOperationId(createDataFeedResponse.getHeaders().getValue("Location"));
            return getDataFeedWithResponse(dataFeedId);
        });
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
     * @return a data feed by its id along with {@link Response} on successful completion of {@link Mono}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDataFeedByIdWithResponse(String dataFeedId, RequestOptions requestOptions) {
        return this.serviceClient.getDataFeedByIdWithResponseAsync(dataFeedId, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DataFeed>> getDataFeedWithResponse(String dataFeedId) {
        try {
            return withContext(context -> getDataFeedWithResponse(dataFeedId, context));
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DataFeed> getDataFeed(String dataFeedId) {
        return getDataFeedWithResponse(dataFeedId).flatMap(FluxUtil::toMono);
    }

    Mono<Response<DataFeed>> getDataFeedWithResponse(String dataFeedId, Context context) {
        Objects.requireNonNull(dataFeedId, "'dataFeedId' cannot be null.");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);

        UUID.fromString(dataFeedId);
        return this.getDataFeedByIdWithResponse(dataFeedId, requestOptions).map(response -> {
            DataFeedDetail dataFeedDetail = response.getValue().toObject(DataFeedDetail.class);
            return new SimpleResponse<>(response, DataFeedTransforms.fromInner(dataFeedDetail));
        });
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
     * @return the {@link Response} on successful completion of {@link Mono}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> updateDataFeedWithResponse(
            String dataFeedId, BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.updateDataFeedWithResponseAsync(dataFeedId, body, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DataFeed> updateDataFeed(DataFeed dataFeed) {
        return updateDataFeedWithResponse(dataFeed).flatMap(FluxUtil::toMono);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DataFeed>> updateDataFeedWithResponse(DataFeed dataFeed) {
        try {
            return withContext(context -> updateDataFeedWithResponse(dataFeed, context));
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    Mono<Response<DataFeed>> updateDataFeedWithResponse(DataFeed dataFeed, Context context) {

        final DataFeedIngestionSettings dataFeedIngestionSettings = dataFeed.getIngestionSettings();
        final DataFeedOptions dataFeedOptions = dataFeed.getOptions() == null
                ? new DataFeedOptions() : dataFeed.getOptions();
        final DataFeedRollupSettings dataFeedRollupSettings = dataFeedOptions.getRollupSettings() == null
                ? new DataFeedRollupSettings() : dataFeedOptions.getRollupSettings();
        final DataFeedMissingDataPointFillSettings dataFeedMissingDataPointFillSettings =
                dataFeedOptions.getMissingDataPointFillSettings() == null
                        ? new DataFeedMissingDataPointFillSettings() : dataFeedOptions.getMissingDataPointFillSettings();
        final Context withTracing = context.addData(AZ_TRACING_NAMESPACE_KEY, METRICS_ADVISOR_TRACING_NAMESPACE_VALUE);

        DataFeedDetailPatch dataFeedDetailPatch =
                DataFeedTransforms.toInnerForUpdate(dataFeed.getSource())
                        .setDataFeedName(dataFeed.getName())
                        .setDataFeedDescription(dataFeedOptions.getDescription())
                        .setTimestampColumn(dataFeed.getSchema() == null
                                ? null : dataFeed.getSchema().getTimestampColumn())
                        .setDataStartFrom(dataFeed.getIngestionSettings().getIngestionStartTime())
                        .setStartOffsetInSeconds(dataFeedIngestionSettings.getIngestionStartOffset() == null
                                ? null : dataFeedIngestionSettings.getIngestionStartOffset().getSeconds())
                        .setMaxConcurrency(dataFeedIngestionSettings.getDataSourceRequestConcurrency())
                        .setStopRetryAfterInSeconds(dataFeedIngestionSettings.getStopRetryAfter() == null
                                ? null : dataFeedIngestionSettings.getStopRetryAfter().getSeconds())
                        .setMinRetryIntervalInSeconds(dataFeedIngestionSettings.getIngestionRetryDelay() == null
                                ? null : dataFeedIngestionSettings.getIngestionRetryDelay().getSeconds())
                        .setNeedRollup(
                                dataFeedRollupSettings.getRollupType() != null
                                        ? NeedRollupEnum.fromString(dataFeedRollupSettings.getRollupType().toString())
                                        : null)
                        .setRollUpColumns(dataFeedRollupSettings.getAutoRollupGroupByColumnNames())
                        .setRollUpMethod(
                                dataFeedRollupSettings.getDataFeedAutoRollUpMethod() != null
                                        ? RollUpMethod.fromString(
                                        dataFeedRollupSettings.getDataFeedAutoRollUpMethod().toString())
                                        : null)
                        .setAllUpIdentification(dataFeedRollupSettings.getRollupIdentificationValue())
                        .setFillMissingPointType(
                                dataFeedMissingDataPointFillSettings.getFillType() != null
                                        ? FillMissingPointType.fromString(
                                        dataFeedMissingDataPointFillSettings.getFillType().toString())
                                        : null)
                        .setFillMissingPointValue(
                                // For PATCH send 'fill-custom-value' over wire only for 'fill-custom-type'.
                                dataFeedMissingDataPointFillSettings.getFillType() ==
                                        DataFeedMissingDataPointFillType.CUSTOM_VALUE
                                        ? dataFeedMissingDataPointFillSettings.getCustomFillValue()
                                        : null)
                        .setViewMode(
                                dataFeedOptions.getAccessMode() != null
                                        ? ViewMode.fromString(dataFeedOptions.getAccessMode().toString())
                                        : null)
                        .setViewers(dataFeedOptions.getViewers())
                        .setAdmins(dataFeedOptions.getAdmins())
                        .setStatus(
                                dataFeed.getStatus() != null
                                        ? EntityStatus.fromString(dataFeed.getStatus().toString())
                                        : null)
                        .setActionLinkTemplate(dataFeedOptions.getActionLinkTemplate());

        BinaryData body = BinaryData.fromObject(dataFeedDetailPatch);
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.setContext(withTracing);

        return this.updateDataFeedWithResponse(dataFeed.getId(), body, requestOptions).flatMap(updateDataFeedResponse -> {
            final String dataFeedId = dataFeed.getId();
            return getDataFeedWithResponse(dataFeedId);
        }).map(test -> test);

    }

    /**
     * Delete a data feed.
     *
     * @param dataFeedId     The data feed unique id.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the {@link Response} on successful completion of {@link Mono}.
     * @throws HttpResponseException thrown if the request is rejected by server.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteDataFeedWithResponse(String dataFeedId, RequestOptions requestOptions) {
        return this.serviceClient.deleteDataFeedWithResponseAsync(dataFeedId, requestOptions);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteDataFeed(String dataFeedId) {
        return deleteDataFeedWithResponse(dataFeedId).flatMap(FluxUtil::toMono);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteDataFeedWithResponse(String dataFeedId) {
        try {
            return withContext(context -> deleteDataFeedWithResponse(dataFeedId, context));
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    Mono<Response<Void>> deleteDataFeedWithResponse(String dataFeedId, Context context) {
        Objects.requireNonNull(dataFeedId, "'dataFeedId' cannot be null.");
        UUID.fromString(dataFeedId);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        return this.deleteDataFeedWithResponse(dataFeedId, requestOptions)
                .map(response -> new SimpleResponse<>(response, null));
    }
}
