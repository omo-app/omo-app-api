package com.findev.omo.common.security;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Arrays;
import java.util.Date;

public final class Jwt {

  private final String issuer;

  private final String clientSecret;

  private final int expirySeconds;

  private final Algorithm algorithm;

  private final JWTVerifier jwtVerifier;

  public Jwt(String issuer, String clientSecret, int expirySeconds) {
    this.issuer = issuer;
    this.clientSecret = clientSecret;
    this.expirySeconds = expirySeconds;
    this.algorithm = Algorithm.HMAC512(clientSecret);
    this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
      .withIssuer(issuer)
      .build();
  }

  public String newToken(Claims claims) {
    Date now = new Date();
    JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
    builder.withIssuer(issuer);
    builder.withIssuedAt(now);
    if (expirySeconds > 0) {
      builder.withExpiresAt(new Date(now.getTime() + expirySeconds * 1_000L));
    }
    builder.withClaim("memberId", claims.memberId);
    builder.withClaim("memberNickName", claims.memberNickName);
    builder.withArrayClaim("roles", claims.roles);
    return builder.sign(algorithm);
  }

  public String refreshToken(String token) throws JWTVerificationException {
    Claims claims = verify(token);
    claims.eraseIat();
    claims.eraseExp();
    return newToken(claims);
  }

  public Claims verify(String token) throws JWTVerificationException {
    return new Claims(jwtVerifier.verify(token));
  }

  public String getIssuer() {
    return issuer;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public int getExpirySeconds() {
    return expirySeconds;
  }

  public Algorithm getAlgorithm() {
    return algorithm;
  }

  public JWTVerifier getJwtVerifier() {
    return jwtVerifier;
  }

  static public class Claims {
    String memberId;
    String memberNickName;
    String[] roles;
    Date iat;
    Date exp;

    private Claims() {
    }

    Claims(DecodedJWT decodedJWT) {
      Claim memberId = decodedJWT.getClaim("memberId");
      if (!memberId.isNull())
        this.memberId = memberId.asString();
      Claim nickName = decodedJWT.getClaim("memberNickName");
      if (!nickName.isNull())
        this.memberNickName = nickName.asString();
      Claim roles = decodedJWT.getClaim("roles");
      if (!roles.isNull())
        this.roles = roles.asArray(String.class);
      this.iat = decodedJWT.getIssuedAt();
      this.exp = decodedJWT.getExpiresAt();
    }

    public static Claims of(String memberId, String nickName, String[] roles) {
      Claims claims = new Claims();
      claims.memberId = memberId;
      claims.memberNickName = nickName;
      claims.roles = roles;
      return claims;
    }

    long iat() {
      return iat != null ? iat.getTime() : -1;
    }

    long exp() {
      return exp != null ? exp.getTime() : -1;
    }

    void eraseIat() {
      iat = null;
    }

    void eraseExp() {
      exp = null;
    }

    @Override
    public String toString() {
      return "Claims{" +
        "userKey=" + memberId +
        ", name='" + memberNickName + '\'' +
        ", roles=" + Arrays.toString(roles) +
        ", iat=" + iat +
        ", exp=" + exp +
        '}';
    }
  }
}