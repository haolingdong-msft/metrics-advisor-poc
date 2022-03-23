## Process of the Experiment

This document explains the steps of the experiment.

### 1. Run DPG to generate code with partial-update

Please refer to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the configuration

### 2. See the gaps between generated code and released code

In order to grow up on top of generated code, we first need to see the gaps between generated code and [released code](https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/metricsadvisor/azure-ai-metricsadvisor/src). The gaps are:

* models/utils classes are not in generated code
* There are two clients, i.e. `MetricsAdvisorAdministrationClient` and `MetricsAdvisorClient` in released code.

  Because Swagger spec does not define operation group in `operation_id`, only one client `MetricsAdvisorClient` is generated. I've brought up this issue in LLC group, currently we agree that it is better to modify Swagger directly to generate two clients. I have updated swagger in a draft pr with operation group and use the spec in the pr to generate code. Spec pr is here: https://github.com/Azure/azure-rest-api-specs/pull/18107

* Released code supports customized authentication using `MetricsAdvisorKeyCredential`.
* Released clients have APIs with high level model where DPG generated code does not have.

  We need to add those APIs released clients have. The way is to add convenient method having the same method signature with released clients APIs. Then add implementation which underline calls generated protocol methods and do model transformation.

* Released code has customized exception type `MetricsAdvisorResponseException` with  `MetricsAdvisorError`

  Released code has customized exception type `MetricsAdvisorResponseException` with  `MetricsAdvisorError`, while DPG generated code has exception type `HttpResponseException` with `ResponseError`. I've brought up this issue within Java DPG group and we also discussed it in LLC group. For Java, we have different  exception types for different HTTP response code. Those types already provide more information than MA's customized exception type. This is the exception types we generated: https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/MetricsAdvisorsImpl.java#L63-L75. But customer is also able to customize the exception if they want.

* Released code use POST when retrieving next page in list paging operation.

  I also add POST operation when retrieving next page in the swagger spec draft pr: https://github.com/Azure/azure-rest-api-specs/pull/18107. And generated code is based on the pr.

### 3. Pick a list of Metrics Advisor's APIs to do experiment

As there are many APIs, working on the full list of APIs and adding convenient methods can be time-consuming. To prove the concept of grow up story, we choose a list if APIs to test based on below criteria:

* Contains APIs in both MetricsAdvisorAdministrationAsyncClient and MetricsAdvisorAsyncClient
* Contains all the four methods appeared in swagger:  GET, POST, DELETE, PATCH
* Contains create, delete, update, get and list operations.
* Contains both paging and non-paging operation. (I checked there is no long-running operation in MetricsAdvisor)

This is the list of APIs we choose and related code in AsyncClient:

| API                                     | Client                                  | Path                                                                          | Method | Return value |
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|
| getAlertsByAnomalyAlertingConfiguration | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |

### 4. Add models/utils/other manual codes to the project

Refer [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/README.md#L25-L25) on the models added.

### 5. Customized Authentication

Refer [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/README.md#L14-L14) on how to add customized authentication.

### 6. Add Convenient methods

Refer [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/README.md#L34-L34) on how to add convenient methods.

| API                                     | Client                                  | Path                                                                          | Method | Return value | Code Reference in Async Client|
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|---------------|
| getAlertsByAnomalyAlertingConfiguration | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L65-L89)|
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L448-L478)||
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L273-L384)|
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L523-L613)|
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L155-L205)|
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L621-L648)|

### 7. Test

Since we keep the public method signature the same as released one, we can reuse the existing [unit test](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/test/java/com/azure/ai/metricsadvisor) to verify our grow-up code. We run the unit test in PLAYBACK as well LIVE mode.
