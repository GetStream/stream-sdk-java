package io.getstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.models.ActivitySelectorConfig;
import io.getstream.models.ActivitySelectorConfigResponse;
import io.getstream.models.GetOrCreateFeedResponse;
import io.getstream.services.framework.StreamHTTPClient;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SelectorTimeBudgetTest {

  private final ObjectMapper mapper =
      new StreamHTTPClient("apiKey", "012345678901234567890123456789ab").getObjectMapper();

  @Test
  void activitySelectorConfigSerializesTimeBudgetMs() throws Exception {
    ActivitySelectorConfig config =
        ActivitySelectorConfig.builder().type("proximity").timeBudgetMs(250).build();

    JsonNode payload = mapper.readTree(mapper.writeValueAsString(config));

    assertEquals("proximity", payload.get("type").asText());
    assertEquals(250, payload.get("time_budget_ms").asInt());
  }

  @Test
  void activitySelectorConfigResponseReadsTimeBudgetMs() throws Exception {
    ActivitySelectorConfigResponse config =
        mapper.readValue(
            "{\"type\":\"proximity\",\"time_budget_ms\":250}",
            ActivitySelectorConfigResponse.class);

    assertEquals("proximity", config.getType());
    assertEquals(250, config.getTimeBudgetMs());
  }

  @Test
  void getOrCreateFeedResponseReadsDegradedSelectors() throws Exception {
    GetOrCreateFeedResponse response =
        mapper.readValue("{\"degraded_selectors\":[\"proximity\"]}", GetOrCreateFeedResponse.class);

    assertEquals(List.of("proximity"), response.getDegradedSelectors());

    GetOrCreateFeedResponse complete = mapper.readValue("{}", GetOrCreateFeedResponse.class);
    assertNull(complete.getDegradedSelectors());
  }
}
