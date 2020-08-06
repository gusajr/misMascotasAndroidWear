package com.unam.mismascotas.notificaciones.restApi.model;

public class Response {

    private String id;
    private String token;

    public Response(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public Response() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
