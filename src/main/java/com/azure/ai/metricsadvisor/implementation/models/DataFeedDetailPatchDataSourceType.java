// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Collection;

/** Defines values for DataFeedDetailPatchDataSourceType. */
public final class DataFeedDetailPatchDataSourceType extends ExpandableStringEnum<DataFeedDetailPatchDataSourceType> {
    /** Static value AzureApplicationInsights for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType AZURE_APPLICATION_INSIGHTS =
            fromString("AzureApplicationInsights");

    /** Static value AzureBlob for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType AZURE_BLOB = fromString("AzureBlob");

    /** Static value AzureCosmosDB for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType AZURE_COSMOS_DB = fromString("AzureCosmosDB");

    /** Static value AzureDataExplorer for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType AZURE_DATA_EXPLORER = fromString("AzureDataExplorer");

    /** Static value AzureDataLakeStorageGen2 for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType AZURE_DATA_LAKE_STORAGE_GEN2 =
            fromString("AzureDataLakeStorageGen2");

    /** Static value AzureTable for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType AZURE_TABLE = fromString("AzureTable");

    /** Static value Elasticsearch for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType ELASTICSEARCH = fromString("Elasticsearch");

    /** Static value HttpRequest for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType HTTP_REQUEST = fromString("HttpRequest");

    /** Static value InfluxDB for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType INFLUX_DB = fromString("InfluxDB");

    /** Static value MongoDB for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType MONGO_DB = fromString("MongoDB");

    /** Static value MySql for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType MY_SQL = fromString("MySql");

    /** Static value PostgreSql for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType POSTGRE_SQL = fromString("PostgreSql");

    /** Static value SqlServer for DataFeedDetailPatchDataSourceType. */
    public static final DataFeedDetailPatchDataSourceType SQL_SERVER = fromString("SqlServer");

    /**
     * Creates or finds a DataFeedDetailPatchDataSourceType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding DataFeedDetailPatchDataSourceType.
     */
    @JsonCreator
    public static DataFeedDetailPatchDataSourceType fromString(String name) {
        return fromString(name, DataFeedDetailPatchDataSourceType.class);
    }

    /** @return known DataFeedDetailPatchDataSourceType values. */
    public static Collection<DataFeedDetailPatchDataSourceType> values() {
        return values(DataFeedDetailPatchDataSourceType.class);
    }
}
