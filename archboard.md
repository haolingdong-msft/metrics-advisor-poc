# Arch Board Review

## Configuration to generate DPG code

Please refer to [`swagger/README.md`](https://github.com/haolingdong-msft/metrics-advisor-poc/blob/master/swagger/README.md) for the configuration

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
```
data-plane: true
```
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

## 