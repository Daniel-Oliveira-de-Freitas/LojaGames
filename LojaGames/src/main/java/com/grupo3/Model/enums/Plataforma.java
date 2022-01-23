package com.grupo3.Model.enums;

public enum Plataforma {
	PC("PC"),
    PS5("Playstation"),
    PS4("Playstation"),
    PS3("Playstation"),
    XBOXONE("XBOX"),
    XBOX360("XBOX"),
    NITENDO("Nitendo");

    private String plataforma;
    private Plataforma(String plataforma){
        this.plataforma = plataforma;
    }
}
