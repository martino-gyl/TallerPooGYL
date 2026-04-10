package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Persona objPersona1 = new Persona();
        objPersona1.nombre = "Lionel Messi";
        objPersona1.edad = 38;
        objPersona1.mostrarPersona();

        objPersona1.puedeCaminar();
        objPersona1.camina = true;
        objPersona1.puedeCaminar();

        objPersona1.nombre = "Lionel Andres Messi";
        objPersona1.mostrarPersona();
        objPersona1.puedeVolar();

        Persona objPersona2 = new Persona();
        objPersona2.nombre = "Emiliano Martinez";
        objPersona2.vuela = true;
        objPersona2.edad = 33;
        objPersona2.mostrarPersona();
        objPersona2.puedeVolar();


    }
}
