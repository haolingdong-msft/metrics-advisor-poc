# Arch Board Review

## Configuration for generating DPG code

Please refer
to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the
configuration

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

Developers can add customized manual codes to the project, e.g. models and utils. Those manual codes need to pass arch board review.  For this experiment, for convenient purpose. I put all the manual classes from existing released code (target grow up codes) to this POC using the same namespace with the
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

* Example of convenient method that takes a customized model and returns a customized model (createDataFeed): 

  **API View**: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=11c5d413369e4f099e6e8db7157bebd5&doc=False&diffOnly=False&revisionId=6a47cc424fd842539135795a8f6c9863
  
  **Code**: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/acb4235e8c2f62bf497130cce7fc147818551565

* Example of convenient method that takes query parameter (listDataFeeds):

  **API View**: https://apiview.dev/Assemblies/Review/250323618f12485eaadcc4822c880f46?diffRevisionId=6a47cc424fd842539135795a8f6c9863&doc=False&diffOnly=False&revisionId=7384d93fddd84ffd86645da221a54392
  
  **Code**: https://github.com/haolingdong-msft/metrics-advisor-poc/commit/effbe61a64dde133d47779b4787f17d91809e495

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
  