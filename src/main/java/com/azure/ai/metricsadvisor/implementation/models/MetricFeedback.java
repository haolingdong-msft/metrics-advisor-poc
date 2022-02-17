// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.OffsetDateTime;
import java.util.UUID;

/** The MetricFeedback model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "feedbackType",
        defaultImpl = MetricFeedback.class)
@JsonTypeName("MetricFeedback")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Anomaly", value = AnomalyFeedback.class),
    @JsonSubTypes.Type(name = "ChangePoint", value = ChangePointFeedback.class),
    @JsonSubTypes.Type(name = "Comment", value = CommentFeedback.class),
    @JsonSubTypes.Type(name = "Period", value = PeriodFeedback.class)
})
@Fluent
public class MetricFeedback {
    /*
     * feedback unique id
     */
    @JsonProperty(value = "feedbackId", access = JsonProperty.Access.WRITE_ONLY)
    private UUID feedbackId;

    /*
     * feedback created time
     */
    @JsonProperty(value = "createdTime", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime createdTime;

    /*
     * user who gives this feedback
     */
    @JsonProperty(value = "userPrincipal", access = JsonProperty.Access.WRITE_ONLY)
    private String userPrincipal;

    /*
     * metric unique id
     */
    @JsonProperty(value = "metricId", required = true)
    private UUID metricId;

    /*
     * The dimensionFilter property.
     */
    @JsonProperty(value = "dimensionFilter", required = true)
    private FeedbackDimensionFilter dimensionFilter;

    /**
     * Get the feedbackId property: feedback unique id.
     *
     * @return the feedbackId value.
     */
    public UUID getFeedbackId() {
        return this.feedbackId;
    }

    /**
     * Get the createdTime property: feedback created time.
     *
     * @return the createdTime value.
     */
    public OffsetDateTime getCreatedTime() {
        return this.createdTime;
    }

    /**
     * Get the userPrincipal property: user who gives this feedback.
     *
     * @return the userPrincipal value.
     */
    public String getUserPrincipal() {
        return this.userPrincipal;
    }

    /**
     * Get the metricId property: metric unique id.
     *
     * @return the metricId value.
     */
    public UUID getMetricId() {
        return this.metricId;
    }

    /**
     * Set the metricId property: metric unique id.
     *
     * @param metricId the metricId value to set.
     * @return the MetricFeedback object itself.
     */
    public MetricFeedback setMetricId(UUID metricId) {
        this.metricId = metricId;
        return this;
    }

    /**
     * Get the dimensionFilter property: The dimensionFilter property.
     *
     * @return the dimensionFilter value.
     */
    public FeedbackDimensionFilter getDimensionFilter() {
        return this.dimensionFilter;
    }

    /**
     * Set the dimensionFilter property: The dimensionFilter property.
     *
     * @param dimensionFilter the dimensionFilter value to set.
     * @return the MetricFeedback object itself.
     */
    public MetricFeedback setDimensionFilter(FeedbackDimensionFilter dimensionFilter) {
        this.dimensionFilter = dimensionFilter;
        return this;
    }
}
