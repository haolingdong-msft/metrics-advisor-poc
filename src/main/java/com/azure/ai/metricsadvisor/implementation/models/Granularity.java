// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/** Defines values for Granularity. */
public final class Granularity extends ExpandableStringEnum<Granularity> {
    /** Static value Yearly for Granularity. */
    public static final Granularity YEARLY = fromString("Yearly");

    /** Static value Monthly for Granularity. */
    public static final Granularity MONTHLY = fromString("Monthly");

    /** Static value Weekly for Granularity. */
    public static final Granularity WEEKLY = fromString("Weekly");

    /** Static value Daily for Granularity. */
    public static final Granularity DAILY = fromString("Daily");

    /** Static value Hourly for Granularity. */
    public static final Granularity HOURLY = fromString("Hourly");

    /** Static value Minutely for Granularity. */
    public static final Granularity MINUTELY = fromString("Minutely");

    /** Static value Custom for Granularity. */
    public static final Granularity CUSTOM = fromString("Custom");

    /**
     * Creates or finds a Granularity from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding Granularity.
     */
    @JsonCreator
    public static Granularity fromString(String name) {
        return fromString(name, Granularity.class);
    }

    /** @return known Granularity values. */
    public static Collection<Granularity> values() {
        return values(Granularity.class);
    }
}
