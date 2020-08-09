package com.unam.mismascotas.notificaciones.restApi.model;

public class Response {

    private String id;
    private String token;
    private String animal;

    public Response(String id, String token, String animal) {
        this.id = id;
        this.token = token;
        this.animal = animal;
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

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
