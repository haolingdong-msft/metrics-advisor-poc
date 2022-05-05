// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/** The AlertResult model. */
@Immutable
public final class AlertResult {
    /*
     * alert id
     */
    @JsonProperty(value = "alertId", access = JsonProperty.Access.WRITE_ONLY)
    private String alertId;

    /*
     * anomaly time
     */
    @JsonProperty(value = "timestamp", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime timestamp;

    /*
     * created time
     */
    @JsonProperty(value = "createdTime", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime createdTime;

    /*
     * modified time
     */
    @JsonProperty(value = "modifiedTime", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime modifiedTime;

    /**
     * Get the alertId property: alert id.
     *
     * @return the alertId value.
     */
    public String getAlertId() {
        return this.alertId;
    }

    /**
     * Get the timestamp property: anomaly time.
     *
     * @return the timestamp value.
     */
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Get the createdTime property: created time.
     *
     * @return the createdTime value.
     */
    public OffsetDateTime getCreatedTime() {
        return this.createdTime;
    }

    /**
     * Get the modifiedTime property: modified time.
     *
     * @return the modifiedTime value.
     */
    public OffsetDateTime getModifiedTime() {
        return this.modifiedTime;
    }
}
