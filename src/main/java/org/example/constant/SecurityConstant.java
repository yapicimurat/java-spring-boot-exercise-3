package org.example.constant;

public final class SecurityConstant {
    private SecurityConstant() {
        throw new AssertionError();
    }
    public static final String SECRET_KEY = "PdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4";
    public static final String TOKEN_RESPONSE_KEY = "accessToken";
    public static final int TOKEN_PERIOD_OF_AVAILABILITY_IN_DAY = 7;
    public static final String VERIFY_ERROR_MESSAGE = "Unauthorized request.";
}
