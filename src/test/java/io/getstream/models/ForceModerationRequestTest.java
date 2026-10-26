package io.getstream.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ForceModerationRequestTest {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void addActivityRequestSerializesForceModeration() throws Exception {
    assertForceModeration(
        AddActivityRequest.builder().type("post").forceModeration(true).build(),
        AddActivityRequest.builder().type("post").build());
  }

  @Test
  public void addCommentRequestSerializesForceModeration() throws Exception {
    assertForceModeration(
        AddCommentRequest.builder().forceModeration(true).build(),
        AddCommentRequest.builder().build());
  }

  @Test
  public void updateActivityRequestSerializesForceModeration() throws Exception {
    assertForceModeration(
        UpdateActivityRequest.builder().forceModeration(true).build(),
        UpdateActivityRequest.builder().build());
  }

  @Test
  public void updateActivityPartialRequestSerializesForceModeration() throws Exception {
    assertForceModeration(
        UpdateActivityPartialRequest.builder().forceModeration(true).build(),
        UpdateActivityPartialRequest.builder().build());
  }

  @Test
  public void updateCommentRequestSerializesForceModeration() throws Exception {
    assertForceModeration(
        UpdateCommentRequest.builder().forceModeration(true).build(),
        UpdateCommentRequest.builder().build());
  }

  @Test
  public void upsertActivitiesRequestSerializesForceModeration() throws Exception {
    assertForceModeration(
        UpsertActivitiesRequest.builder().forceModeration(true).build(),
        UpsertActivitiesRequest.builder().build());
  }

  @Test
  public void addCommentsBatchCarriesForceModerationOnEachComment() throws Exception {
    AddCommentsBatchRequest request =
        AddCommentsBatchRequest.builder()
            .comments(List.of(AddCommentRequest.builder().forceModeration(true).build()))
            .build();

    JsonNode json = objectMapper.readTree(objectMapper.writeValueAsBytes(request));
    assertEquals(true, json.get("comments").get(0).get("force_moderation").asBoolean());
  }

  private void assertForceModeration(Object enabled, Object unset) throws Exception {
    JsonNode enabledJson = objectMapper.readTree(objectMapper.writeValueAsBytes(enabled));
    assertEquals(true, enabledJson.get("force_moderation").asBoolean());

    JsonNode unsetJson = objectMapper.readTree(objectMapper.writeValueAsBytes(unset));
    assertTrue(unsetJson.get("force_moderation").isNull());
  }
}
