// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

module com.azure.ai.metricsadvisor {
    requires transitive com.azure.core;

    exports com.azure.ai.metricsadvisor;
    exports com.azure.ai.metricsadvisor.models;
    exports com.azure.ai.metricsadvisor.administration.models;

    opens com.azure.ai.metricsadvisor.models to
            com.fasterxml.jackson.databind;
}
