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

    }
}
