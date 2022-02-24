This project is the experiment project on describing Data plane Codegen(EPG) grow up story for Java.

The process of the experiment:

 
1. Run DPG to generate code with partial-update
   Please refer to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the configuration
2. See the gaps between generated code and released code
   
In order to grow up on top of generated code, we first need to see the gaps between generated code and released code. The gaps are:

* models/utils classes are not in generated code
* There are two clients, i.e. `MetricsAdvisorAdministrationClient` and `MetricsAdvisorClient` in released code. 
  
Because Swagger spec does not define operation group in `operation_id`, only one client `MetricsAdvisorClient` is generated. I've brought up this issue in LLC group, currently we agree that it is better to modify Swagger directly to generate two clients. Since we know for sure that adding operation group in `operation_id` will generate two clients, I did not spend time to modify swagger for now as this is not first priority for our experiment. I just create `MetricsAdvisorAdministrationClient` and related `Builder` manually and move the APIs we want to test into `MetricsAdvisorAdministrationClient`. 

* Released code supports customized authentication using `MetricsAdvisorKeyCredential`.
* Released clients have APIs with high level model where DPG generated code does not have.

We need to add those APIs released clients have. The way is to add convenient method having the same method signature with released clients APIs. Then add implementation which underline calls generated protocol methods and do model transformation.


* Released code has customized exception type `MetricsAdvisorResponseException` with  `MetricsAdvisorError`. But DPG generated code has exception type `HttpE`
I've brought up this issue within Java DPG group and we also discussed it in LLC group. For Java, `azure-core` already has exception type `HttpResponseException` with `ResponseError`, those two types provide more information than MA's customized types. So we will use the exception types in `azure-core`. But customer is also able to customize the exception if they want.
  
3. Pick a list of Metrics Advisor's APIs to do experiment

As there are many APIs, working on the full list of APIs and adding convenient methods can be time-consuming. To prove the concept of grow up story, we choose a list if APIs to test based on below criteria:

* Contains APIs in both MetricsAdvisorAdministrationAsyncClient and MetricsAdvisorAsyncClient
* Contains all the four methods appeared in swagger:  GET, POST, DELETE, PATCH
* Contains create, delete, update, get and list operations.
* Contains both paging and non-paging operation. (I checked there is no long-running operation in MetricsAdvisor)

This is the list of APIs we choose:

| API                                     | Client                                  | Path                                                                          | Method | Return value |
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|
| getAlertsByAnomalyAlertingConfiguration | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |

4. Add models/utils/other manual codes to the project

In this project, I put all the manual classes from existing released code to this POC using the same namespace with the existing code. I did not change any of those code. 

Below are the manual codes (including models and utils) added:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/models
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/util
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/models

4. Customized Authentication 
   
DPG supports AzureAD OAuth2 authentication and API key authentication. Refer [here](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md) for more detail.

I added `MetricsAdvisorKeyCredential` authentication in `MetricsAdvisorClientBuilder`:

First provide an interface which accepts a `MetricsAdvisorKeyCredential`. 
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/83d2c4d1e5bcf83c929e20e25c2a88b2c3ee650f/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L147-L162

Then modify `createHttpPipeline()` to handle `MetricsAdvisorKeyCredential`, putting keys to header.

https://github.com/haolingdong-msft/metrics-advisor-poc/blob/83d2c4d1e5bcf83c929e20e25c2a88b2c3ee650f/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L274-L278

5. Add Convenient methods

I added convenient methods for the chosen APIs and run the unit tests to test those APIs. Since we keep the public method signature the same as released one, we can reuse the **existing unit test** to verify our grow-up code. We run the unit test in PLAYBACK as well LIVE mode.

Convenient methods in `MetricsAdvisorClient` and `MetricsAdvisorAsyncClient`:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L65-L100
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClient.java#L65-L93

Convenient methods in `MetricsAdvisorAdministrationClient` and `MetricsAdvisorAdministrationAsyncClient`:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L74-L692
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationClient.java#L44-L380

In convenient methods, basically you will need to add query parameters(if applicable), convert request body
to `BinaryData`, call underline protocol methods and convert response to model.

* Request Model Convert:
  
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L85-L85

* Response Model Convert:

1. PagedFlux: https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L336-L336
2. Mono: https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L623-L623

This is an example of convenient method:

https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L65-L89

