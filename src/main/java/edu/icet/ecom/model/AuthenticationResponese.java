package edu.icet.ecom.model;

public class AuthenticationResponese {

    private String token;

    public AuthenticationResponese(String token) {
        this.token= token;
    }

    public String getToken() {
        return token;
    }


}
