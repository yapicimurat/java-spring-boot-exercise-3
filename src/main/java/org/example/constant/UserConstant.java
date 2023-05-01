package org.example.constant;

public final class UserConstant {
    private UserConstant() {
        throw new AssertionError();
    }
    public static final String CONTROLLER_PREFIX = "/api/user";
    public static final String LOGIN_URL = "/api/user/login";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_ALREADY_EXIST = "User is already exist";
    public static final String FETCH_SUCCESSFULLY = "User(s) successfully fetched.";
    public static final String CREATE_SUCCESSFULLY = "User successfully created.";
    public static final String UPDATE_SUCCESSFULLY = "User successfully updated.";
    public static final String AUTHENTICATE_SUCCESSFULLY = "User successfully authenticated.";
}
