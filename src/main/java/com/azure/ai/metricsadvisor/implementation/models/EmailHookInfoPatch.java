// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The EmailHookInfoPatch model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "hookType")
@JsonTypeName("Email")
@Fluent
public final class EmailHookInfoPatch extends HookInfoPatch {
    /*
     * The hookParameter property.
     */
    @JsonProperty(value = "hookParameter")
    private EmailHookParameterPatch hookParameter;

    /**
     * Get the hookParameter property: The hookParameter property.
     *
     * @return the hookParameter value.
     */
    public EmailHookParameterPatch getHookParameter() {
        return this.hookParameter;
    }

    /**
     * Set the hookParameter property: The hookParameter property.
     *
     * @param hookParameter the hookParameter value to set.
     * @return the EmailHookInfoPatch object itself.
     */
    public EmailHookInfoPatch setHookParameter(EmailHookParameterPatch hookParameter) {
        this.hookParameter = hookParameter;
        return this;
    }
}
