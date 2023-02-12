package com.blog.blog_api.security;

public class JWTAuthResponseDTO {
     private String tokenOfAcces;
     private String  typeToken = "Bearer";
      
    public JWTAuthResponseDTO(String tokenOfAcces) {
        super();
        this.tokenOfAcces = tokenOfAcces;
    }
    public JWTAuthResponseDTO(String tokenOfAcces, String typeToken) {
        super();
        this.tokenOfAcces = tokenOfAcces;
        this.typeToken = typeToken;
    }
    public String getTokenOfAcces() {
        return tokenOfAcces;
    }
    public void setTokenOfAcces(String tokenOfAcces) {
        this.tokenOfAcces = tokenOfAcces;
    }
    public String getTypeToken() {
        return typeToken;
    }
    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }
     
}
