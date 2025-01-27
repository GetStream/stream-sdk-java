package io.getstream.models.framework;

import io.getstream.services.framework.StreamSDKClient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class User {
    private static StreamSDKClient client = StreamSDKClient.getInstance();

    @NotNull
    public static String createToken(
            @NotNull String userId, @Nullable Integer validity) {
        return createToken(client.getApiSecret(), userId, validity);
    }

    @NotNull
    public static String createToken(
            @NotNull String apiSecret,
            @NotNull String userId,
            @Nullable Integer validity) {
        var issuedAt = Instant.now().minusSeconds(5);

        var key = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8));

        var builder = Jwts.builder()
                .claim("user_id", userId)
                .issuedAt(Date.from(issuedAt))
                .issuer("Stream Java SDK")
                .subject("Stream Java SDK")
                .signWith(key, Jwts.SIG.HS256);

        if (validity != null) {
            builder.expiration(Date.from(Instant.now().plusSeconds(validity)));
        }

        return builder.compact();
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
