package com.example.sharedpreferentes.ejerciciosharedpreference01;

public class ContactoMatricula {
    private String nombre;
    private String ciclo;
    private String email;
    private String telefono;

    public ContactoMatricula(String nombre, String ciclo, String email, String telefono) {
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.email = email;
        this.telefono = telefono;
    }

    public ContactoMatricula() {
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCiclo() {return ciclo;}
    public void setCiclo(String ciclo) {this.ciclo = ciclo;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
}
