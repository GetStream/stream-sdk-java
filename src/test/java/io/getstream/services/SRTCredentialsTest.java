package io.getstream.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.getstream.exceptions.StreamException;
import io.getstream.models.framework.SRTCredentials;
import io.getstream.services.framework.TokenBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SRTCredentialsTest {
  private static final String API_KEY = "srt-test-key";
  private static final String SECRET = "a-test-secret-that-is-long-enough-for-hs256";
  private static final Pattern TOKEN = Pattern.compile("streamid=[^/]+/(.+)$");

  private static String template(String callType, String callID) {
    return "srt://ingress.stream-io-video.com:10080?mode=caller&passphrase={passphrase}&streamid="
        + API_KEY
        + "."
        + callType
        + "."
        + callID
        + "/{token}";
  }

  private static SRTCredentials build(String callID, String userID, Duration validity)
      throws StreamException {
    return Call.buildSRTCredentials(
        template("livestream", callID),
        new TokenBuilder(SECRET),
        userID,
        "livestream:" + callID,
        validity);
  }

  private static String tokenOf(SRTCredentials credentials) {
    var matcher = TOKEN.matcher(credentials.getAddress());
    assertTrue(matcher.find());
    return matcher.group(1);
  }

  private static Claims claimsOf(String token) {
    return Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Test
  void embedsCallScopedTokenThatExpiresAfterTheDefaultValidity() throws StreamException {
    var credentials = build("my-call", "john", Call.DEFAULT_SRT_TOKEN_VALIDITY);
    var token = tokenOf(credentials);
    var claims = claimsOf(token);

    assertEquals(Duration.ofHours(24), Call.DEFAULT_SRT_TOKEN_VALIDITY);
    assertEquals("john", claims.get("user_id"));
    assertEquals("livestream:my-call", claims.get("scoped_call_cid"));
    assertNull(claims.get("call_cids"));
    assertEquals(credentials.getExpiresAt(), claims.getExpiration().toInstant());
    var lifetime =
        Duration.between(claims.getIssuedAt().toInstant(), claims.getExpiration().toInstant());
    assertTrue(lifetime.compareTo(Duration.ofHours(24)) >= 0, lifetime.toString());
    assertTrue(lifetime.compareTo(Duration.ofHours(24).plusSeconds(10)) <= 0, lifetime.toString());
    assertTrue(credentials.getAddress().contains("passphrase=" + token.split("\\.")[2] + "&"));
  }

  @Test
  void acceptsCustomValidity() throws StreamException {
    var claims = claimsOf(tokenOf(build("my-call", "john", Duration.ofHours(3))));
    var lifetime =
        Duration.between(claims.getIssuedAt().toInstant(), claims.getExpiration().toInstant());

    assertTrue(lifetime.compareTo(Duration.ofHours(3)) >= 0, lifetime.toString());
    assertTrue(lifetime.compareTo(Duration.ofHours(3).plusSeconds(10)) <= 0, lifetime.toString());
  }

  @ParameterizedTest
  @ValueSource(longs = {0, -1, 999})
  void rejectsValidityUnderOneSecond(long millis) {
    assertThrows(
        IllegalArgumentException.class, () -> build("my-call", "john", Duration.ofMillis(millis)));
  }
}
