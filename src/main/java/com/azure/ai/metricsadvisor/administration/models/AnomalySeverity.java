// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.administration.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/** Defines values for AnomalySeverity. */
public final class AnomalySeverity extends ExpandableStringEnum<AnomalySeverity> {
    /** Static value Low for AnomalySeverity. */
    public static final AnomalySeverity LOW = fromString("Low");

    /** Static value Medium for AnomalySeverity. */
    public static final AnomalySeverity MEDIUM = fromString("Medium");

    /** Static value High for AnomalySeveDrity. */
    public static final AnomalySeverity HIGH = fromString("High");

    /**
     * Creates or finds a AnomalySeverity from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding AnomalySeverity.
     */
    @JsonCreator
    public static AnomalySeverity fromString(String name) {
        return fromString(name, AnomalySeverity.class);
    }

    /** @return known AnomalySeverity values. */
    public static Collection<AnomalySeverity> values() {
        return values(AnomalySeverity.class);
    }
}
