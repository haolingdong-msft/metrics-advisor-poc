// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.administration.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/** Defines values for IngestionStatusType. */
public final class IngestionStatusType extends ExpandableStringEnum<IngestionStatusType> {
    /** Static value NotStarted for IngestionStatusType. */
    public static final IngestionStatusType NOT_STARTED = fromString("NotStarted");

    /** Static value Scheduled for IngestionStatusType. */
    public static final IngestionStatusType SCHEDULED = fromString("Scheduled");

    /** Static value Running for IngestionStatusType. */
    public static final IngestionStatusType RUNNING = fromString("Running");

    /** Static value Succeeded for IngestionStatusType. */
    public static final IngestionStatusType SUCCEEDED = fromString("Succeeded");

    /** Static value Failed for IngestionStatusType. */
    public static final IngestionStatusType FAILED = fromString("Failed");

    /** Static value NoData for IngestionStatusType. */
    public static final IngestionStatusType NO_DATA = fromString("NoData");

    /** Static value Error for IngestionStatusType. */
    public static final IngestionStatusType ERROR = fromString("Error");

    /** Static value Paused for IngestionStatusType. */
    public static final IngestionStatusType PAUSED = fromString("Paused");

    /**
     * Creates or finds a IngestionStatusType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding IngestionStatusType.
     */
    @JsonCreator
    public static IngestionStatusType fromString(String name) {
        return fromString(name, IngestionStatusType.class);
    }

    /** @return known IngestionStatusType values. */
    public static Collection<IngestionStatusType> values() {
        return values(IngestionStatusType.class);
    }
}
