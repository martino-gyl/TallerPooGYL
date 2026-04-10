package org.example;

public class Persona {
    // Atributos característicos
    public String nombre;
    public int edad;
    public boolean camina;
    public boolean vuela = false;

    // Metodos / acciones
    public void mostrarPersona() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
    public void puedeCaminar(){
        if(this.camina){
            System.out.println("Si puede caminar");
        } else {
            System.out.println("No puede caminar");
        }
    }
    public void puedeVolar() {
        if (this.vuela) {
            System.out.println("Si puede volar");
        } else {
            System.out.println("No puede volar");
        }
    }
}
