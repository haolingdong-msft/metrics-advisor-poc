// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.administration.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/**
 * Defines values for DataFeedStatus.
 */
public final class DataFeedStatus extends ExpandableStringEnum<DataFeedStatus> {
    /**
     * Static value Active for DataFeedStatus.
     */
    public static final DataFeedStatus ACTIVE = fromString("Active");

    /**
     * Static value Paused for DataFeedStatus.
     */
    public static final DataFeedStatus PAUSED = fromString("Paused");

    /**
     * Creates or finds a DataFeedStatus from its string representation.
     *
     * @param name a name to look for.
     *
     * @return the corresponding DataFeedStatus.
     */
    @JsonCreator
    public static DataFeedStatus fromString(String name) {
        return fromString(name, DataFeedStatus.class);
    }

    /**
     * @return known DataFeedStatus values.
     */
    public static Collection<DataFeedStatus> values() {
        return values(DataFeedStatus.class);
    }
}
