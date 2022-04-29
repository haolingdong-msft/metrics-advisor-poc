# Arch Board Review

## Configuration for generating DPG code

Please refer to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the configuration

```yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/f9a5bf06925934b7841bdc95c14e9b70379b426b/specification/cognitiveservices/data-plane/MetricsAdvisor/stable/v1.0/MetricsAdvisor.json
output-folder: ../
java: true
regenerate-pom: false
*partial-update: true
model-override-setter-from-superclass: true
use-default-http-status-code-to-exception-type-mapping: true
generate-sync-async-clients: true
generate-client-as-impl: true
models-subpackage: implementation.models
generate-client-interfaces: false
*generate-builder-per-client: true
add-context-parameter: true
generate-tests: true
artifact-id: azure-ai-metricsadvisor
*data-plane: true
sync-methods: all
generate-samples: true
license-header: MICROSOFT_MIT_SMALL
client-logger: true
namespace: com.azure.ai.metricsadvisor
context-client-method-parameter: true
azure-arm: false
service-versions:
- '1.0'
security: AADToken
security-scopes: https://cognitiveservices.azure.com/.default
```

Highlight a few codegen properties:

* **data-plane**: this enables data-plane codegen
* **partial-update**: with this flag, user manually added/updated code will not be deleted in next codegen.
* **generate-builder-per-client**: with this flag, we will generate each builder to each client.

## Add models/utils/other manual codes to the project

In this project, I put all the manual classes from existing released code to this POC using the same namespace with the existing code. I did not change any of those code.

Below are the manual codes (including models and utils) added: 

https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/models 

https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/util 

https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/models 

https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/models

## Customized Authentication

DPG supports AzureAD OAuth2 authentication and API key authentication. Refer [here](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md) for more detail.

I added `MetricsAdvisorKeyCredential` authentication in `MetricsAdvisorClientBuilder`:

First provide an [interface](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L147-L162
) which accepts a `MetricsAdvisorKeyCredential`.
Then modify `createHttpPipeline()` to handle `MetricsAdvisorKeyCredential`, putting keys to header, codes [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L274-L278
).

## Add Convenient methods

We choose a set of APIs to add convenient methods. Below table shows the APIs we choose, and the corresponding convenient methods.

| API                                     | Client                                  | Path                                                                          | Method | Return value | Code Reference in Async Client|
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|---------------|
| listAlerts | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L61-L85)|
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1264)|
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1056)|
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |[code](hhttps://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1379)|
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L934)|
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1495)|

In convenient methods, basically you will need to add query parameters(if applicable), convert request body to `BinaryData`, call underline protocol methods and convert response to model.

### Add Query Parameters

Use `requestOptions.addQueryParam()` to add query parameters. 

Example:

```
requestOptions.addQueryParam("dataFeedName", "name");
```

Refer [listDataFeeds](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L956) for a real example in MA.

### Request Model Convert:

Client model needs to be converted to `BinaryData` to call underline protocol methods. We use `BinaryData.fromObject()` to convert model to `BinaryData`. 

Refer [listAlerts](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L81) for real example in MA.

### Response Model Convert:

Response needs to be converted from `BinaryData` to client model. Below are the sample codes for model conversion for `PagedFlux` and `Mono`.

* PagedFlux:
  Refer [listAlerts](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L84-L84) for real example in MA.
* Mono:
  Refer [getDataFeed](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1269-L1274) for real example for MA.
  
## API View Comparison

* Administration related clients and builders are moved from `com.azure.ai.metricsadvisor.administration` package to `com.azure.ai.metricsadvisor`.

| API                                     | Client                                  | Path                                                                          | Method | Return value | Code Reference in Async Client|
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|---------------|
| listAlerts | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L61-L85)|
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1264)|
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1056)|
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |[code](hhttps://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1379)|
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L934)|
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAdministrationAsyncClient.java#L1495)|