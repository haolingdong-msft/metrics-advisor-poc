// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.ai.metricsadvisor.models.FeedbackType;
import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

/** The MetricFeedbackFilter model. */
@Fluent
public final class MetricFeedbackFilter {
    /*
     * filter feedbacks by metric id
     */
    @JsonProperty(value = "metricId", required = true)
    private UUID metricId;

    /*
     * The dimensionFilter property.
     */
    @JsonProperty(value = "dimensionFilter")
    private FeedbackDimensionFilter dimensionFilter;

    /*
     * filter feedbacks by type
     */
    @JsonProperty(value = "feedbackType")
    private FeedbackType feedbackType;

    /*
     * start time filter under chosen time mode
     */
    @JsonProperty(value = "startTime")
    private OffsetDateTime startTime;

    /*
     * end time filter under chosen time mode
     */
    @JsonProperty(value = "endTime")
    private OffsetDateTime endTime;

    /*
     * time mode to filter feedback
     */
    @JsonProperty(value = "timeMode")
    private FeedbackQueryTimeMode timeMode;

    /**
     * Get the metricId property: filter feedbacks by metric id.
     *
     * @return the metricId value.
     */
    public UUID getMetricId() {
        return this.metricId;
    }

    /**
     * Set the metricId property: filter feedbacks by metric id.
     *
     * @param metricId the metricId value to set.
     * @return the MetricFeedbackFilter object itself.
     */
    public MetricFeedbackFilter setMetricId(UUID metricId) {
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
     * @return the MetricFeedbackFilter object itself.
     */
    public MetricFeedbackFilter setDimensionFilter(FeedbackDimensionFilter dimensionFilter) {
        this.dimensionFilter = dimensionFilter;
        return this;
    }

    /**
     * Get the feedbackType property: filter feedbacks by type.
     *
     * @return the feedbackType value.
     */
    public FeedbackType getFeedbackType() {
        return this.feedbackType;
    }

    /**
     * Set the feedbackType property: filter feedbacks by type.
     *
     * @param feedbackType the feedbackType value to set.
     * @return the MetricFeedbackFilter object itself.
     */
    public MetricFeedbackFilter setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
        return this;
    }

    /**
     * Get the startTime property: start time filter under chosen time mode.
     *
     * @return the startTime value.
     */
    public OffsetDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Set the startTime property: start time filter under chosen time mode.
     *
     * @param startTime the startTime value to set.
     * @return the MetricFeedbackFilter object itself.
     */
    public MetricFeedbackFilter setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Get the endTime property: end time filter under chosen time mode.
     *
     * @return the endTime value.
     */
    public OffsetDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Set the endTime property: end time filter under chosen time mode.
     *
     * @param endTime the endTime value to set.
     * @return the MetricFeedbackFilter object itself.
     */
    public MetricFeedbackFilter setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Get the timeMode property: time mode to filter feedback.
     *
     * @return the timeMode value.
     */
    public FeedbackQueryTimeMode getTimeMode() {
        return this.timeMode;
    }

    /**
     * Set the timeMode property: time mode to filter feedback.
     *
     * @param timeMode the timeMode value to set.
     * @return the MetricFeedbackFilter object itself.
     */
    public MetricFeedbackFilter setTimeMode(FeedbackQueryTimeMode timeMode) {
        this.timeMode = timeMode;
        return this;
    }
}
