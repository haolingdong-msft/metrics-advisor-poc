### Code generation settings
```yaml
input-file: https://github.com/Azure/azure-rest-api-specs/blob/main/specification/keyvault/data-plane/Microsoft.KeyVault/stable/7.2/certificates.json
java: true
output-folder: ../
regenerate-pom: false
title: KeyVaultCertificateClient
generate-sync-async-clients: true
generate-client-as-impl: true
generate-client-interfaces: false
add-context-parameter: true
artifact-id: azure-keyvault-certificate
low-level-client: true
sync-methods: all
generate-samples: true
license-header: MICROSOFT_MIT_SMALL
namespace: haoling.keyvault
service-name: KeyVaultCertificate
context-client-method-parameter: true
azure-arm: false
partial-update: true
credential-types: tokencredential
credential-scopes: https://cognitiveservices.azure.com/.default
service-versions:
  - 'v1.0'
```