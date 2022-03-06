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

https://github.com/Azure/azure-rest-api-specs/blob/main/specification/cognitiveservices/data-plane/MetricsAdvisor/stable/v1.0/MetricsAdvisor.json

### Code generation settings
```yaml
input-file: ./metricsadvisor_haoling.json
java: true
output-folder: ../
regenerate-pom: false
title: MetricsAdvisorClient
generate-sync-async-clients: true
generate-client-as-impl: true
generate-client-interfaces: false
add-context-parameter: true
artifact-id: azure-ai-metricsadvisor
low-level-client: true
sync-methods: all
generate-samples: true
license-header: MICROSOFT_MIT_SMALL
namespace: com.azure.ai.metricsadvisor
service-name: MetricsAdvisor
context-client-method-parameter: true
azure-arm: false
partial-update: true
credential-types: tokencredential
credential-scopes: https://cognitiveservices.azure.com/.default
service-versions:
  - 'v1.0'
```
### Generated types renamed and moved to model
