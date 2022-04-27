# Azure Cognitive Service - Metric Advisor for Java

> see https://aka.ms/autorest

### Setup
```ps
Fork and clone https://github.com/Azure/autorest.java
git checkout main
git submodule update --init --recursive
mvn package -Dlocal
npm install
npm install -g autorest
```

### Generation
```ps
autorest <your-path-to-swagger-readme> --version=3.6.1 --use=./
```

### Code generation settings

```yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/f9a5bf06925934b7841bdc95c14e9b70379b426b/specification/cognitiveservices/data-plane/MetricsAdvisor/stable/v1.0/MetricsAdvisor.json
output-folder: ../
java: true
regenerate-pom: false
partial-update: true
model-override-setter-from-superclass: true
use-default-http-status-code-to-exception-type-mapping: true
generate-sync-async-clients: true
generate-client-as-impl: true
models-subpackage: implementation.models
generate-client-interfaces: false
generate-builder-per-client: true
add-context-parameter: true
generate-tests: true
artifact-id: azure-ai-metricsadvisor
low-level-client: true
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


### Generated types renamed and moved to model
