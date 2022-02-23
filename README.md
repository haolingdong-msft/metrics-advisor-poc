Manual classes (including models and utils):
I put all the manual classes from existing released code to this POC using the same namespace with the existing code. I did not change any of those code.
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/implementation/models
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/implementation/util
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/models

Model Convert:
1. PagedFlux: https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L336-L336
2. Mono: https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L623-L623

Customized Authentication:
Interface to set metricsAdvisorKeyCredential:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L148-L162
Handling part:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/MetricsAdvisorClientBuilder.java#L274-L278
Authentication part is also verified in LIVE test.

Exception Convert:
1. PagedFlux
Util function:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/implementation/util/PagedConverter.java#L66-L74
Client code:
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L286-L293
I found the deprecated .mapPage() interface has some issue when iterate with break inside the iteration.e.g.
for (PagedResponse<DataFeed> dataFeedPagedResponse : client.listDataFeeds(
                new ListDataFeedOptions().setListDataFeedFilter(new ListDataFeedFilter().setDataFeedStatus(ACTIVE)),
                Context.NONE)
                .iterableByPage()) {
            dataFeedPagedResponse.getValue().forEach((dataFeed -> assertEquals(ACTIVE, dataFeed.getStatus())));
            pageCount++;
            if (pageCount > 4) {
                // Stop after 4 pages since there can be large number of feeds.
                break;
            }
        }
This code will continue getting page after pageCount 4. So I used PagedConverter util.
2. Mono
https://github.com/haolingdong-msft/metrics-advisor-poc/blob/fdbfdb6f85b59042a559e8871b7c3c935fbaa365/src/main/java/com/azure/ai/metricsadvisor/administration/MetricsAdvisorAdministrationAsyncClient.java#L410-L417

Two Clients thing:
As swagger does not contains operation group yet (Isabella is updating swagger). Currently I just manually create MetricsAdvisorAdministrationAsyncClient, MetricsAdvisorAdministrationClient and MetricsAdvisorAdministrationClientBuilder. Move some administration related methods to the administration client.

API Test
Below APIs are tested. Please let me know if more APIs need to be tested.
I also do both PLAYBACK and LIVE tests, all tests are passed now.
| API                                     | Client                                  | Path                                                                          | Method | Return value |
|-----------------------------------------|-----------------------------------------|-------------------------------------------------------------------------------|--------|--------------|
| getAlertsByAnomalyAlertingConfiguration | MetricsAdvisorAsyncClient               | /enrichment/anomalyDetection/configurations/{configurationId}/anomalies/query | POST   | PagedFlux    |
| getDataFeed                             | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | GET    | Mono         |
| createDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | POST   | Mono         |
| updateDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | PATCH  | Mono         |
| listDataFeed                            | MetricsAdvisorAdministrationAsyncClient | /dataFeeds                                                                    | GET    | PagedFlux    |
| deleteDataFeed                          | MetricsAdvisorAdministrationAsyncClient | /dataFeeds/{dataFeedId}                                                       | DELETE | Mono         |