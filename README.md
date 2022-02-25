# Metrics Advisor Grow Up Story Experiment

## Overview of Project

This project is the experiment project on describing Data plane Codegen(DPG) grow up story for Java, it uses Metrics Advisor as example resource provider. 

The project includes manual added codes such as client models/utils, code sample of DPG configuration, customized authentication, convenient methods including model conversion.

## Data-plane Codegen Configuration

Please refer to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the configuration

## Customized Authentication

DPG supports AzureAD OAuth2 authentication and API key authentication. Refer [here](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md) for more detail.

I added `MetricsAdvisorKeyCredential` authentication in `MetricsAdvisorClientBuilder`:

First provide an [interface](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/83d2c4d1e5bcf83c929e20e25c2a88b2c3ee650f/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L147-L162
) which accepts a `MetricsAdvisorKeyCredential`.
Then modify `createHttpPipeline()` to handle `MetricsAdvisorKeyCredential`, putting keys to header, codes [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/83d2c4d1e5bcf83c929e20e25c2a88b2c3ee650f/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L274-L278
).

## Manual Added Codes

In this project, I put all the manual classes from existing [released code](https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/metricsadvisor) to this POC using the same namespace with the existing code. I did not change any of those code.

Below are the manual codes (including models and utils) added:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/models
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/implementation/util
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/models

## Convenient Methods

We choose a set of APIs to add convenient methods. Below table shows the APIs we choose, and the corresponding convenient methods.

| API                                     | Client                                  | Path                                                                          | Method | Return value | Code Reference in Async Client|
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|---------------|
| getAlertsByAnomalyAlertingConfiguration | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/7348da524916ab84edf6f38a45883c888e001020/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L65-L89)|
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/7348da524916ab84edf6f38a45883c888e001020/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L448-L478)|
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/7348da524916ab84edf6f38a45883c888e001020/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L273-L384)|
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/7348da524916ab84edf6f38a45883c888e001020/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L523-L613)|
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/7348da524916ab84edf6f38a45883c888e001020/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L155-L205)|
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |[code](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/7348da524916ab84edf6f38a45883c888e001020/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L621-L648)|

In convenient methods, basically you will need to add query parameters(if applicable), convert request body to `BinaryData`, call underline protocol methods and convert response to model.

### Request Model Convert:

Client model needs to be converted to `BinaryData` to call underline protocol methods. We use `BinaryData.fromObject()` to convert model to `BinaryData`. Example code [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/6f20629db66e5eec153b33c7d3932ba5057ee81e/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorAsyncClient.java#L85-L85)

### Response Model Convert:

Response needs to be converted from `BinaryData` to client model. Below are the sample codes for model conversion for `PagedFlux` and `Mono`.

* PagedFlux: 
  Example code [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L336-L336)
* Mono: 
  Example code [here](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L623-L623)
  
