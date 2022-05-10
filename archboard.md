# Arch Board Review


## Overview of the experiment

Metrics Advisor is a released package. We use this [released code](https://apiview.dev/Assemblies/Review/8567acee5f8a44cab9ac67b09c2820a8) as our target grow up code.

This experiment introduces how to grow up from pure DPG code to the target code.

## Configuration for generating DPG code

Please refer
to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the detailed configuration.

```yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/f9a5bf06925934b7841bdc95c14e9b70379b426b/specification/cognitiveservices/data-plane/MetricsAdvisor/stable/v1.0/MetricsAdvisor.json
output-folder: ../
java: true
partial-update: true
data-plane: true
security: AADToken
security-scopes: https://cognitiveservices.azure.com/.default
```

Highlight a few codegen properties:

* **data-plane**: this enables data-plane codegen
* **partial-update**: with this flag, user manually added/updated code will not be deleted in next codegen.

DPG generated code like this: 

API View: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?revisionId=6919fc2319694d039069cd9e777e9d8b&doc=False

code: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/6b602c187bba2dec122da35837cbc4aedf051858

## Add models/utils/other manual codes to the project

Developers can add customized manual codes to the project, e.g. models and utils. Those manual codes need to pass arch board review. In this experiment, as MA is released and we already have our target SDK. For convenient purpose. I put all the manual classes from existing released code (target grow up codes) to this POC using the same namespace with the
existing code. I did not change any of those code.

Git commit for manual codes:
https://github.com/haolingdong-msft/metrics-advisor-poc/commit/7f0554c1a47e58757b4da9cdfcdff59dc69308ee

## Customized Authentication

DPG supports AzureAD OAuth2 authentication and API key authentication.
Refer [here](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md) for more detail.

I added `MetricsAdvisorKeyCredential` authentication in `MetricsAdvisorClientBuilder`:

First provide a method which accepts a `MetricsAdvisorKeyCredential`. Then modify `createHttpPipeline()` to
handle `MetricsAdvisorKeyCredential`, putting keys to header.

API View: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=603cb6f7e7ad41499b6768cb2044c791&doc=False&diffOnly=False&revisionId=dff270dd1f75419b86e43eff6f2dcac1#com.azure.ai.metricsadvisor.MetricsAdvisorAdministrationClientBuilder

Code: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/79dbb93fb8ec395a6f79ea5971d6b4b2d090c765

## Add Convenient methods

We add a set of APIs to DPG code. Those methods are convenient to users, e.g. it can accept customized model as input value or return value.

* Example of convenient method that returns a customized model (getDataFeed):

  **API View**: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=dff270dd1f75419b86e43eff6f2dcac1&doc=False&diffOnly=False&revisionId=11c5d413369e4f099e6e8db7157bebd5
  
  **Code**: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/b86c9353a90c3cbf5709cb3c982b2ff175dc59d9

With pure DPG code, getting data feed is like below, we need to pass in requestOptions even we don't need it, in the response, we also need to convert `BinaryData` to `DataFeed` by ourselves:

```
RequestOptions requestOptions = new RequestOptions();
Response<BinaryData> response =
        metricsAdvisorAdministrationClient.getDataFeedByIdWithResponse(
                "01234567-8901-2345-6789-012345678901", requestOptions);
BinaryData data = response.getValue();
// Transformations to convert BinaryData to DataFeed object
DataFeedDetail dataFeedDeetail = data.toObject(DataFeedDetail.class);
DataFeed dataFeed = DataFeedTransforms.fromInner(dataFeedDetail);
// use datafeed
```


With convenient method, getting data feed is like below, we only need to pass in the data feed ID, and we can get data feed directly from response:

```
metricsAdvisorAdministrationClient = metricsAdvisorAdministrationClientbuilder.buildClient();
Response<DataFeed> response =
        metricsAdvisorAdministrationClient.getDataFeedWithResponse(
                "01234567-8901-2345-6789-012345678901");
DataFeed dataFeed = response.getValue();
//use datafeed
```

* Example of convenient method that takes a customized model and returns a customized model (createDataFeed):

  **API View**: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=11c5d413369e4f099e6e8db7157bebd5&doc=False&diffOnly=False&revisionId=6a47cc424fd842539135795a8f6c9863
  
  **Code**: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/acb4235e8c2f62bf497130cce7fc147818551565
  
  
With pure DPG code, when creating data feed, we need to create a `BinaryData` object first like using `BinaryData.fromString()` and pass it to the method, if we want to get the data feed being created, we also need to call `getDataFeed()` to get the data feed.
  
  ```
  BinaryData body =
                BinaryData.fromString(
                        "{\"allUpIdentification\":\"__SUM__\",\"authenticationType\":\"Basic\",\"dataFeedDescription\":\"This is a sample data feed.\",\"dataFeedName\":\"Sample - cost/revenue - city/category\",\"dataSourceParameter\":{\"connectionString\":\"Server=PlaceholderSqlServer,1433;Initial Catalog=PlaceholderDatabase;User ID=PlaceholderUserName;Password=PlaceholderPassword;\",\"query\":\"select * from your_table where timestamp = @StartTime\"},\"dataSourceType\":\"SqlServer\",\"dataStartFrom\":\"2020-01-01T00:00:00.000Z\",\"dimension\":[{\"dimensionDisplayName\":\"category\",\"dimensionName\":\"category\"},{\"dimensionDisplayName\":\"city\",\"dimensionName\":\"city\"}],\"fillMissingPointType\":\"SmartFilling\",\"granularityName\":\"Daily\",\"maxConcurrency\":5,\"metrics\":[{\"metricDisplayName\":\"cost\",\"metricName\":\"cost\"},{\"metricDisplayName\":\"revenue\",\"metricName\":\"revenue\"}],\"minRetryIntervalInSeconds\":3600,\"needRollup\":\"NeedRollup\",\"rollUpMethod\":\"Sum\",\"startOffsetInSeconds\":86400,\"stopRetryAfterInSeconds\":604800,\"timestampColumn\":\"timestamp\",\"viewMode\":\"Private\"}");
        
RequestOptions requestOptions = new RequestOptions();

Response<Void> response = metricsAdvisorAdministrationClient.createDataFeedWithResponse(body, requestOptions);

Response<DataFeed> response =
        metricsAdvisorAdministrationClient.getDataFeedWithResponse(
                "01234567-8901-2345-6789-012345678901");
DataFeed dataFeed = response.getValue();
// use datafeed 
```
  
With concvenient method, we can create a `DataFeed` object directly, instead of creating it from JSON string. We can also get the created data feed directly from response.
```
DataFeed dataFeed = new DataFeed();
// set the properties of the dataFeed
dataFeed.dataFeedDescription("This is a sample data feed.");

Response<DataFeed> response = metricsAdvisorAdministrationClient.createDataFeedWithResponse(dataFeed);
DataFeed dataFeed = response.getValue();
//use datafeed
```

* Example of convenient method that returns pageble object (listDataFeeds):

  **API View**: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=6a47cc424fd842539135795a8f6c9863&doc=False&diffOnly=False&revisionId=7384d93fddd84ffd86645da221a54392
  
  **Code**: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/effbe61a64dde133d47779b4787f17d91809e495

With pure DPG code, we need to add query parameters to `RequestOptions` by ourselves. In the `PagedIterable` response, when iterating each page, we get the `BinaryData` object, we need to tranform it to `DataFeed` object by ourselves.
 
```
 RequestOptions requestOptions = new RequestOptions();
 requestOptions.addQueryParam("creator", "demo@microsoft.com");
 requestOptions.addQueryParam("dataFeedName", "name_prefix");
 PagedIterable<BinaryData> response = metricsAdvisorAdministrationClient.listDataFeeds(requestOptions);
 BinaryData data = response.iterator().next();
// Transformations to convert BinaryData to DataFeed object
DataFeedDetail dataFeedDeetail = data.toObject(DataFeedDetail.class);
DataFeed dataFeed = DataFeedTransforms.fromInner(dataFeedDetail);
 // use dataFeed
```
 
With conveniment methods, users don't need to know details about how to add query parameters into `RequestOptions`, they just need to build ListDataFeedOptions model. They also don't need to convert the response to `DataFeed`.
 
 ```
 ListDataFeedOptions listDataFeedOptions = new ListDataFeedOptions();
 ListDataFeedFilter listDataFeedFilter = new ListDataFeedFilter();
 listDataFeedFilter.setDataFeedName("name_prefix");
 listDataFeedFilter.setCreator("demo@microsoft.com");
 listDataFeedOptions.setListDataFeedFilter()
 PagedIterable<DataFeed> response = metricsAdvisorAdministrationClient.listDataFeeds();
 DataFeed dataFeed = response.iterator().next();
 //use dataFeed
 ```
 
### Add Query Parameters

Use `requestOptions.addQueryParam()` to add query parameters.

Example:

```
requestOptions.addQueryParam("dataFeedName", "name");
```

Refer [listDataFeeds](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/effbe61a64dde133d47779b4787f17d91809e495) for example.

### Request Model Convert:

Client model needs to be converted to `BinaryData` to call underline protocol methods. We use `BinaryData.fromObject()`
to convert model to `BinaryData`.

Refer [createDataFeed](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/acb4235e8c2f62bf497130cce7fc147818551565) for example.

### Response Model Convert:

Response needs to be converted from `BinaryData` to client model. Below are the sample codes for model conversion
for `PagedFlux` and `Mono`.

* PagedFlux:
  Refer [listDataFeeds](https://github.com/haolingdong-msft/metrics-advisor-poc/commit/effbe61a64dde133d47779b4787f17d91809e495)
* Mono:
  Refer [createDataFeed]( https://github.com/haolingdong-msft/metrics-advisor-poc/commit/acb4235e8c2f62bf497130cce7fc147818551565)
  
