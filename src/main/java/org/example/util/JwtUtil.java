package org.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.constant.SecurityConstant;

import java.util.Date;

public final class JwtUtil {
    private JwtUtil() {
        throw new AssertionError();
    }

    private static Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.SECRET_KEY);

    public static String generateJWT(final String username) {
        final int dayInMilliSeconds = 1000 * 60 * 60 * 24;

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (SecurityConstant.TOKEN_PERIOD_OF_AVAILABILITY_IN_DAY * dayInMilliSeconds)))
                .sign(algorithm);
    }

    public static DecodedJWT decodedJWT(final String token) throws JWTVerificationException {
        final JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);
    }
}
