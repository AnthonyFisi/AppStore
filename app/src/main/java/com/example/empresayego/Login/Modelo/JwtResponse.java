package com.example.empresayego.Login.Modelo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

    @SerializedName("accessToken")
    @Expose
    private String accessToken;

    @SerializedName("tokenType")
    @Expose
    private String tokenType;

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("roles")
    @Expose
    private List<String> roles;

    @SerializedName("idubicacion")
    @Expose
    private int idubicacion;

    public JwtResponse(String accessToken,String tokenType, Long id, String username, String email, List<String> roles,int idubicacion) {
        this.accessToken = accessToken;
        this.tokenType=tokenType;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.idubicacion=idubicacion;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }
}
