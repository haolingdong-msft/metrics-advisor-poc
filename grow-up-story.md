# Grow Up Story

## Run Data-plane Codegen to generate SDK

You will need to add `partial-update` option in `swagger/README.md` and run Data-plane Codegen (DPG) to generated SDK. 
Please refer [here](https://github.com/Azure/azure-sdk-for-java/wiki/Protocol-Methods-Quickstart-with-AutoRest) for more details about how to generate SDK please 
```yaml
partial-update: true
```

If the generated code does not satisfy your use case. For example, you want to generate two clients. Modify swagger and generate code again.

## Customized Authentication

DPG supports AzureAD OAuth2 authentication and API key authentication.
Refer [here](https://github.com/Azure/autorest/blob/main/docs/generate/authentication.md) for more detail.

If you want to have other customized authentication, you can add customized code in `Builder`.

For example, below code adds a customized authentication. First provide an [interface](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/83d2c4d1e5bcf83c929e20e25c2a88b2c3ee650f/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L147-L162
) which accepts a `MetricsAdvisorKeyCredential`.


Then modify [`createHttpPipeline()`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/83d2c4d1e5bcf83c929e20e25c2a88b2c3ee650f/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L274-L278) to handle `MetricsAdvisorKeyCredential`, putting keys to header.


## Add grow up codes (models/utils/other manual classes)

You can add your own codes, such as models/utils, to the SDK.

## Provide convenient methods with model

As protocol methods use `BinaryData` as request body, sometimes it is hard for end users to create `BinaryData` and call protocol methods, especially for operations like create/update. So we allow service to add convenient methods with models, which allow user to call with model and return model as response. Convenient methods will call protocol methods underline. In convenient methods, basically you will need to add query parameters(if applicable), convert request body
to `BinaryData`, call underline protocol methods and convert response to model.

### Add query paramaters

```java
RequestOptions requestOptions=new RequestOptions();
        requestOptions.addQueryParam("key","value");
```

### Convert to `BinaryData` when sending request

If you have created a model as the request body, you can just simply convert the model to `BinaryData`
using `BinaryDate.fromObject()`. Below is an example of setting values for `AlertingResultQuery` object and convert the
object to `BinaryData`.

```java
AlertingResultQuery alertingResultQuery=new AlertingResultQuery();
        alertingResultQuery.setStartTime(startTime);
        alertingResultQuery.setEndTime(endTime);
        alertingResultQuery.setTimeMode(options.getTimeMode());
        BinaryData body=BinaryData.fromObject(alertingResultQuery);
```

If you don't have model, and want to create `BinaryData` from scratch. You can use `BinaryData.fromString()`. If you don't want to concat json string, you can use [`ObjectNode`](https://fasterxml.github.io/jackson-databind/javadoc/2.7/com/fasterxml/jackson/databind/node/ObjectNode.html). Below is an example.
.

```java
   ObjectNode objectNode=JsonNodeFactory.instance.objectNode();
        objectNode.put("key","value");
        BinaryData body=BinaryData.fromString(objectNode.toString());
```

### Convert `BinaryData` to model when receiving response

#### PagedFlux

Use below `mapPage()` method in `PagedConverter` to convert `PagedFlux<BinaryData>` response to `PagedFlux<YourModel>`.

```java
public static <T, S> PagedFlux<S> mapPage(PagedFlux<T> pagedFlux, Function<T, S> mapper)
```

Example Code:
```java
PagedConverter.mapPage(pagedFluxResponse, binaryData -> binaryData.toObject(YourModel.class));
```

#### PagedIterable 
   
Use below `mapPage()` method in `PagedConverter` to convert `PagedIterable<BinaryData>` response to `PagedIterable<YourModel>`.

```java
public static <T, S> PagedIterable<S> mapPage(PagedIterable<T> pageIterable, Function<T, S> mapper) 
```

Example Code:
```java
PagedConverter.mapPage(pagedIterableResponse, binaryData -> binaryData.toObject(YourModel.class));
```

#### SyncPoller


#### PollerFlux


#### Mono

Use `map()` method in `Mono` to convert model.

```java
return monoResponse.map(binaryData -> {
    return binaryData.toObject(DataFeedDetail.class);
});
```

### Exception Handling

The protocol method will throw `HttpResponseException`. If the request is rejected by server. `HttpResponseException`
has `getValue()` API which returns the deserialized HTTP response value.

### An Example of Convenient Method

This is a sample convenient method. Here `listAlerts` is a convenient method which takes four
parameters. `getAlertsByAnomalyAlertingConfiguration` is the protocol method which takes required
parameter `alertConfigurationId`, `BinaryData` as body and a `RequestOptions`.

Firstly we add query parameters. Secondly we need to create request body, we create `AlertingResultQuery` and convert it
to `BinaryData` using `BinaryData.fromObject(alertingResultQuery)`. Thirdly, we call underline protocol method to get
response. Lastly, we convert the response from `PagedFlux<BinaryData>` to `PagedFlux<AnomalyAlert>`.

```java
@ServiceMethod(returns = ReturnType.COLLECTION)
public PagedIterable<AnomalyAlert> listAlerts(
        String alertConfigurationId,
        OffsetDateTime startTime,
        OffsetDateTime endTime,
        ListAlertOptions options,
        Context context) {
        Objects.requireNonNull(alertConfigurationId, "'alertConfigurationId' is required.");
        Objects.requireNonNull(startTime, "'startTime' is required.");
        Objects.requireNonNull(endTime, "'endTime' is required.");
        RequestOptions requestOptions = new RequestOptions();
        if (options.getMaxPageSize() != null) {
            requestOptions.addQueryParam("$maxpagesize", options.getMaxPageSize().toString());
        }
        if (options.getSkip() != null) {
            requestOptions.addQueryParam("$skip", options.getSkip().toString());
        }

        AlertingResultQuery alertingResultQuery = new AlertingResultQuery();
        alertingResultQuery.setStartTime(startTime);
        alertingResultQuery.setEndTime(endTime);
        alertingResultQuery.setTimeMode(options.getTimeMode());
        BinaryData body = BinaryData.fromObject(alertingResultQuery);
        if (context != null) {
            requestOptions.setContext(context);
        }
        PagedIterable<BinaryData> response = this.getAlertsByAnomalyAlertingConfiguration(alertConfigurationId, body, requestOptions);
        return PagedConverter.mapPage(response, binaryData -> binaryData.toObject(AnomalyAlert.class));
}
```



