package io.getstream.services.framework;

import io.getstream.models.framework.CallTokenClaims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TokenBuilder {
  String secret;

  public TokenBuilder(String secret) {
    this.secret = secret;
  }

  // create token without limits on validity
  public String createToken(@NotNull String userId) {
    return createToken(secret, userId, null);
  }

  @NotNull
  public String createToken(@NotNull String userId, @Nullable Integer validity) {
    return createToken(secret, userId, validity);
  }

  @NotNull
  public static String createToken(
      @NotNull String apiSecret, @NotNull String userId, @Nullable Integer validity) {
    // A secret-signed token without a user id authenticates as the server.
    if (userId == null || userId.isBlank()) {
      throw new IllegalArgumentException("userId is required");
    }
    var issuedAt = Instant.now().minusSeconds(5);

    var key = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8));

    var builder =
        Jwts.builder()
            .claim("user_id", userId)
            .issuedAt(Date.from(issuedAt))
            .signWith(key, Jwts.SIG.HS256);

    if (validity != null) {
      builder.expiration(Date.from(Instant.now().plusSeconds(validity)));
    }

    return builder.compact();
  }

  /**
   * Creates a token that can only join the call {@code callCID} (and read the app settings). The
   * backend refuses it on every other endpoint and on the websocket. Unlike {@code call_cids},
   * which grants the user a role in calls, this takes access away.
   *
   * @param userId the user the token authenticates
   * @param callCID the call the token is limited to, as {@code type:id}
   * @param expiresAt when the token expires
   */
  @NotNull
  public String createScopedCallToken(
      @NotNull String userId, @NotNull String callCID, @NotNull Instant expiresAt) {
    if (userId == null || userId.isBlank()) {
      throw new IllegalArgumentException("userId is required");
    }
    if (callCID == null || callCID.isBlank()) {
      throw new IllegalArgumentException("callCID is required");
    }
    var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder()
        .claim("user_id", userId)
        .claim("scoped_call_cid", callCID)
        .issuedAt(Date.from(Instant.now().minusSeconds(5)))
        .expiration(Date.from(expiresAt))
        .signWith(key, Jwts.SIG.HS256)
        .compact();
  }

  public String createCallToken(@NotNull CallTokenClaims claims) {
    return createCallToken(secret, claims);
  }

  public static String createCallToken(@NotNull String apiSecret, @NotNull CallTokenClaims claims) {
    var key = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
        .claims(
            Map.of(
                "user_id", claims.getUserID(),
                "role", claims.getRole(),
                "call_cids", claims.getCallCIDs()))
        .issuedAt(Date.from(claims.getIssuedAt()))
        .expiration(Date.from(claims.getExpiresAt()))
        .signWith(key, Jwts.SIG.HS256)
        .compact();
  }
}
