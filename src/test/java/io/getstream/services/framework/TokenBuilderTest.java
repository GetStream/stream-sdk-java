package io.getstream.services.framework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TokenBuilderTest {
  private static final String SECRET = "a-test-secret-that-is-long-enough-for-hs256";

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "  "})
  void createTokenRejectsMissingUserId(String userId) {
    var builder = new TokenBuilder(SECRET);
    assertThrows(IllegalArgumentException.class, () -> builder.createToken(userId));
    assertThrows(IllegalArgumentException.class, () -> builder.createToken(userId, 3600));
  }

  @Test
  void createTokenCarriesUserId() {
    var token = new TokenBuilder(SECRET).createToken("john");
    var claims =
        Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseSignedClaims(token)
            .getPayload();
    assertEquals("john", claims.get("user_id"));
  }

  @Test
  void createScopedCallTokenCarriesScopeAndExpiration() {
    var expiresAt = Instant.now().plusSeconds(3600).truncatedTo(ChronoUnit.SECONDS);
    var token = new TokenBuilder(SECRET).createScopedCallToken("john", "livestream:abc", expiresAt);
    var claims =
        Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseSignedClaims(token)
            .getPayload();
    assertEquals("john", claims.get("user_id"));
    assertEquals("livestream:abc", claims.get("scoped_call_cid"));
    assertNull(claims.get("call_cids"));
    assertEquals(expiresAt, claims.getExpiration().toInstant());
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "  "})
  void createScopedCallTokenRejectsMissingCallCID(String callCID) {
    var builder = new TokenBuilder(SECRET);
    assertThrows(
        IllegalArgumentException.class,
        () -> builder.createScopedCallToken("john", callCID, Instant.now().plusSeconds(60)));
  }
}
