package io.getstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.models.ActivityMarksConfig;
import io.getstream.models.CreateFeedGroupRequest;
import io.getstream.models.RankingConfig;
import io.getstream.services.framework.StreamHTTPClient;
import org.junit.jupiter.api.Test;

public class ActivityMarksRankingTest {

  @Test
  void createFeedGroupRequestSerializesActivityMarksAndIsSeenRanking() throws Exception {
    ObjectMapper mapper =
        new StreamHTTPClient("apiKey", "012345678901234567890123456789ab").getObjectMapper();

    CreateFeedGroupRequest request =
        CreateFeedGroupRequest.builder()
            .id("timeline")
            .activityMarks(ActivityMarksConfig.builder().trackSeen(true).trackRead(true).build())
            .ranking(RankingConfig.builder().type("expression").score("is_seen ? 0 : 100").build())
            .build();

    JsonNode payload = mapper.readTree(mapper.writeValueAsString(request));

    assertTrue(payload.get("activity_marks").get("track_seen").asBoolean());
    assertTrue(payload.get("activity_marks").get("track_read").asBoolean());
    assertEquals("expression", payload.get("ranking").get("type").asText());
    assertEquals("is_seen ? 0 : 100", payload.get("ranking").get("score").asText());
  }
}
