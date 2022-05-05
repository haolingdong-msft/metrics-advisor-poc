// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

/** The DetectionSeriesQuery model. */
@Fluent
public final class DetectionSeriesQuery {
    /*
     * This is inclusive. The maximum number of data points (series number *
     * time range) is 10000.
     */
    @JsonProperty(value = "startTime", required = true)
    private OffsetDateTime startTime;

    /*
     * This is exclusive. The maximum number of data points (series number *
     * time range) is 10000.
     */
    @JsonProperty(value = "endTime", required = true)
    private OffsetDateTime endTime;

    /*
     * The series to be queried. The identity must be able to define one single
     * time series instead of a group of time series. The maximum number of
     * series is 100.
     */
    @JsonProperty(value = "series", required = true)
    private List<SeriesIdentity> series;

    /**
     * Get the startTime property: This is inclusive. The maximum number of data points (series number * time range) is
     * 10000.
     *
     * @return the startTime value.
     */
    public OffsetDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Set the startTime property: This is inclusive. The maximum number of data points (series number * time range) is
     * 10000.
     *
     * @param startTime the startTime value to set.
     * @return the DetectionSeriesQuery object itself.
     */
    public DetectionSeriesQuery setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Get the endTime property: This is exclusive. The maximum number of data points (series number * time range) is
     * 10000.
     *
     * @return the endTime value.
     */
    public OffsetDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Set the endTime property: This is exclusive. The maximum number of data points (series number * time range) is
     * 10000.
     *
     * @param endTime the endTime value to set.
     * @return the DetectionSeriesQuery object itself.
     */
    public DetectionSeriesQuery setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Get the series property: The series to be queried. The identity must be able to define one single time series
     * instead of a group of time series. The maximum number of series is 100.
     *
     * @return the series value.
     */
    public List<SeriesIdentity> getSeries() {
        return this.series;
    }

    /**
     * Set the series property: The series to be queried. The identity must be able to define one single time series
     * instead of a group of time series. The maximum number of series is 100.
     *
     * @param series the series value to set.
     * @return the DetectionSeriesQuery object itself.
     */
    public DetectionSeriesQuery setSeries(List<SeriesIdentity> series) {
        this.series = series;
        return this;
    }
}
