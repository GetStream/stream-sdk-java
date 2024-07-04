package io.getstream.models.framework;

import io.getstream.services.framework.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class User {
  @NotNull
  public static String createToken(
      @NotNull String userId, @Nullable Date expiresAt, @Nullable Date issuedAt) {
    return createToken(Client.getInstance().getApiSecret(), userId, expiresAt, issuedAt);
  }

  @NotNull
  public static String createToken(
      @NotNull String apiSecret,
      @NotNull String userId,
      @Nullable Date expiresAt,
      @Nullable Date issuedAt) {
    if (issuedAt == null) {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.add(Calendar.SECOND, -5);
      issuedAt = calendar.getTime();
    }

    var key = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
        .claim("user_id", userId)
        .expiration(expiresAt)
        .issuedAt(issuedAt)
        .issuer("Stream Java SDK")
        .subject("Stream Java SDK")
        .signWith(key, Jwts.SIG.HS256)
        .compact();
  }
}
