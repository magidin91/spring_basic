package com.education.jwt;

/**
 * маппим в этот объект юзернейм и пассворд из получаемого при логине json
 */
public class AccountCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
