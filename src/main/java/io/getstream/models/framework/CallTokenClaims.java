package io.getstream.models.framework;

import java.time.Instant;
import java.util.List;

public class CallTokenClaims {
  private List<String> callCIDs;
  private String role;
  private String userID;
  private Instant expiresAt;
  private Instant issuedAt;

  public CallTokenClaims(String userID, String role, List<String> callCIDs, Integer expiresIn) {
    this.userID = userID;
    this.role = role;
    this.callCIDs = callCIDs;
    this.issuedAt = Instant.now().minusSeconds(5);
    this.expiresAt = Instant.now().plusSeconds(expiresIn);
  }

  public List<String> getCallCIDs() {
    return callCIDs;
  }

  public String getRole() {
    return role;
  }

  public String getUserID() {
    return userID;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public Instant getIssuedAt() {
    return issuedAt;
  }
}
