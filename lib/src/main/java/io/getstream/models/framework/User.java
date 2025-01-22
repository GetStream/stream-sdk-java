package io.getstream.models.framework;

import io.getstream.services.framework.StreamSDKClient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class User {
  private static StreamSDKClient client = StreamSDKClient.getInstance();

  @NotNull
  public static String createToken(
      @NotNull String userId, @Nullable Instant expiresAt, @Nullable Instant issuedAt) {
    return createToken(client.getApiSecret(), userId, expiresAt, issuedAt);
  }

  @NotNull
  public static String createToken(
      @NotNull String apiSecret,
      @NotNull String userId,
      @Nullable Instant expiresAt,
      @Nullable Instant issuedAt) {
    if (issuedAt == null) {
      issuedAt = Instant.now().minusSeconds(5);
    }

    var key = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
        .claim("user_id", userId)
        .expiration(Date.from(expiresAt))
        .issuedAt(Date.from(issuedAt))
        .issuer("Stream Java SDK")
        .subject("Stream Java SDK")
        .signWith(key, Jwts.SIG.HS256)
        .compact();
  }

  public static String createCallToken(@NotNull CallTokenClaims claims) {
    return createCallToken(client.getApiSecret(), claims);
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
        .issuer("Stream Java SDK")
        .subject("Stream Java SDK")
        .signWith(key, Jwts.SIG.HS256)
        .compact();
  }
}
