// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/** Defines values for DataFeedDetailPatchViewMode. */
public final class DataFeedDetailPatchViewMode extends ExpandableStringEnum<DataFeedDetailPatchViewMode> {
    /** Static value Private for DataFeedDetailPatchViewMode. */
    public static final DataFeedDetailPatchViewMode PRIVATE = fromString("Private");

    /** Static value Public for DataFeedDetailPatchViewMode. */
    public static final DataFeedDetailPatchViewMode PUBLIC = fromString("Public");

    /**
     * Creates or finds a DataFeedDetailPatchViewMode from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding DataFeedDetailPatchViewMode.
     */
    @JsonCreator
    public static DataFeedDetailPatchViewMode fromString(String name) {
        return fromString(name, DataFeedDetailPatchViewMode.class);
    }

    /** @return known DataFeedDetailPatchViewMode values. */
    public static Collection<DataFeedDetailPatchViewMode> values() {
        return values(DataFeedDetailPatchViewMode.class);
    }
}
