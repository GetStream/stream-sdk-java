/*
 * ========================================================================
 * WARNING: GENERATED CODE -- DO NOT EDIT!
 * ========================================================================
 *
 * This file was auto-generated by GetStream internal OpenAPI
 *
 * Any modifications to this file will be lost upon regeneration.
 * To make changes, please modify the source templates and regenerate.
 *
 * ========================================================================
 */
package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallNotificationEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("members")
  private List<MemberResponse> members;

  @JsonProperty("call")
  private CallResponse call;

  @JsonProperty("user")
  private UserResponse user;

  @JsonProperty("type")
  private String type;
}
