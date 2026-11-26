package com.esfocar.backend.dto;

public class PasswordUpdateDto {

    private String nuevaPassword;

    public PasswordUpdateDto() {
    }

    public PasswordUpdateDto(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }

    public String getNuevaPassword() {
        return nuevaPassword;
    }

    public void setNuevaPassword(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }
}
