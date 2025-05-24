package io.github.ajudabrasil.apiajudabrasil.DTO;

public class LoginResponse {
    private String token;
    private String email;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoginResponse() {
    }


    public LoginResponse(String token, String email,  String id) {
        this.token = token;
        this.email = email;
        this.id =  id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
