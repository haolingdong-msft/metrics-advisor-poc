// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.metricsadvisor.util;

import com.azure.ai.metricsadvisor.MetricsAdvisorServiceVersion;
import com.azure.ai.metricsadvisor.models.AlertQueryTimeMode;
import com.azure.ai.metricsadvisor.models.AnomalyAlert;
import com.azure.ai.metricsadvisor.models.ListAlertOptions;
import com.azure.core.http.HttpClient;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

public abstract class AlertTestBase extends MetricsAdvisorClientTestBase {

    @Test
    public abstract void listAlerts(HttpClient httpClient, MetricsAdvisorServiceVersion serviceVersion);

    public static final String ALERT_CONFIG_ID = "1c1575d8-b09e-40c3-a3c0-d459c64d8382";

    // Pre-configured test resource.
    public static class ListAlertsInput {
        public static final ListAlertsInput INSTANCE = new ListAlertsInput();
        public final OffsetDateTime startTime = OffsetDateTime.parse("2021-10-10T00:00:00Z");
        public final OffsetDateTime endTime = OffsetDateTime.parse("2021-10-21T00:00:00Z");
        public final AlertQueryTimeMode timeMode = AlertQueryTimeMode.ANOMALY_TIME;
        public final ListAlertOptions options = new ListAlertOptions()
            .setAlertQueryTimeMode(timeMode)
            .setMaxPageSize(10);

        //        static {
//            System.out.println(INSTANCE.timeMode);
//        }
        public final String alertConfigurationId = ALERT_CONFIG_ID;
    }

    protected static class ListAlertsOutput {
        public static final ListAlertsOutput INSTANCE = new ListAlertsOutput();
        public final int expectedAlerts = 11;
    }

    protected void assertAlertOutput(AnomalyAlert anomalyAlert) {
        Assertions.assertNotNull(anomalyAlert);
        Assertions.assertNotNull(anomalyAlert.getId());
        Assertions.assertNotNull(anomalyAlert.getCreatedTime());
        Assertions.assertNotNull(anomalyAlert.getModifiedTime());
        Assertions.assertNotNull(anomalyAlert.getTimestamp());
        boolean isInRange = (anomalyAlert.getTimestamp().isEqual(ListAlertsInput.INSTANCE.startTime)
            || anomalyAlert.getTimestamp().isAfter(ListAlertsInput.INSTANCE.startTime))
            && (anomalyAlert.getTimestamp().isEqual(ListAlertsInput.INSTANCE.endTime)
            || anomalyAlert.getTimestamp().isBefore(ListAlertsInput.INSTANCE.endTime));
        Assertions.assertTrue(isInRange);
    }

    protected void assertAlertOutput(BinaryData anomalyAlert) {
        Assertions.assertNotNull(anomalyAlert);
//        Assertions.assertNotNull(anomalyAlert.getId());
//        Assertions.assertNotNull(anomalyAlert.getCreatedTime());
//        Assertions.assertNotNull(anomalyAlert.getModifiedTime());
//        Assertions.assertNotNull(anomalyAlert.getTimestamp());
//        boolean isInRange = (anomalyAlert.getTimestamp().isEqual(ListAlertsInput.INSTANCE.startTime)
//            || anomalyAlert.getTimestamp().isAfter(ListAlertsInput.INSTANCE.startTime))
//            && (anomalyAlert.getTimestamp().isEqual(ListAlertsInput.INSTANCE.endTime)
//            || anomalyAlert.getTimestamp().isBefore(ListAlertsInput.INSTANCE.endTime));
//        Assertions.assertTrue(isInRange);
    }
}
