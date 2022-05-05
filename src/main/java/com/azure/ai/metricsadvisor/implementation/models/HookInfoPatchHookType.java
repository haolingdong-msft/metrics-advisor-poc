// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/** Defines values for HookInfoPatchHookType. */
public final class HookInfoPatchHookType extends ExpandableStringEnum<HookInfoPatchHookType> {
    /** Static value Webhook for HookInfoPatchHookType. */
    public static final HookInfoPatchHookType WEBHOOK = fromString("Webhook");

    /** Static value Email for HookInfoPatchHookType. */
    public static final HookInfoPatchHookType EMAIL = fromString("Email");

    /**
     * Creates or finds a HookInfoPatchHookType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding HookInfoPatchHookType.
     */
    @JsonCreator
    public static HookInfoPatchHookType fromString(String name) {
        return fromString(name, HookInfoPatchHookType.class);
    }

    /** @return known HookInfoPatchHookType values. */
    public static Collection<HookInfoPatchHookType> values() {
        return values(HookInfoPatchHookType.class);
    }
}
