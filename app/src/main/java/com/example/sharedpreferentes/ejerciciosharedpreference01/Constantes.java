package com.example.sharedpreferentes.ejerciciosharedpreference01;

public class Constantes {
    public static final String user = "USUARIO";
    public static final String correo = "CORREO";
    public static final String password = "PASSWORD";
    public static final String correoCorrecto = "alumno@cieep.com";
    public static final String passwordCorrecto = "1234a";
    public static final String datos_persistencia = "datos";
    public static final String datos = "DATOS";

    public static String codificaPassword(String password){
        StringBuilder codificado = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            codificado.append((char) (password.charAt(i) + 1));
        }

        return codificado.toString();
    }

    public static String decodificaPassword(String password){
        StringBuilder codificado = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            codificado.append((char) (password.charAt(i) - 1));
        }

        return codificado.toString();
    }
}
