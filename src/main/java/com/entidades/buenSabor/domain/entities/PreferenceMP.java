package com.entidades.buenSabor.domain.entities;

public class PreferenceMP {
    private String id;
    private int statusCode;

    public PreferenceMP() {
    }

    public PreferenceMP(String id, int statusCode) {
        this.id = id;
        this.statusCode = statusCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
