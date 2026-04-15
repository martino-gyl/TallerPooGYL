package servicios;

import dominio.Cuenta;

import java.util.Scanner;

public class PanelCuenta {
    private final Scanner scanner;
    private final AplicacionBanco aplicacion;

    public PanelCuenta(Scanner scanner, AplicacionBanco aplicacion) {
        this.scanner = scanner;
        this.aplicacion = aplicacion;
    }

    public void mostrar() {
        Cuenta cuenta = loginCuenta();
        boolean volver = false;

        while (!volver) {
            try {
                System.out.println("\n--- PANEL CUENTA " + cuenta.getNumero() + " ---");
                System.out.println("1. Depositar");
                System.out.println("2. Extraer");
                System.out.println("3. Ver saldo");
                System.out.println("4. Ver balance");
                System.out.println("5. Volver");
                System.out.print("Opción: ");

                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> depositar(cuenta);
                    case 2 -> extraer(cuenta);
                    case 3 -> System.out.println("Saldo actual: $" + cuenta.getSaldo());
                    case 4 -> System.out.println("\n" + aplicacion.resumenCuenta(cuenta));
                    case 5 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private Cuenta loginCuenta() {
        System.out.println("\n--- LOGIN CUENTA ---");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = leerTexto();

        System.out.print("Contraseña: ");
        String password = leerTexto();

        return aplicacion.loginCuenta(numeroCuenta, password);
    }

    private void depositar(Cuenta cuenta) {
        System.out.print("Monto a depositar: ");
        double monto = leerDouble();

        aplicacion.depositar(cuenta, monto);
        System.out.println("Depósito realizado.");
    }

    private void extraer(Cuenta cuenta) {
        System.out.print("Monto a extraer: ");
        double monto = leerDouble();

        aplicacion.extraer(cuenta, monto);
        System.out.println("Extracción realizada.");
    }

    private int leerInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    private double leerDouble() {
        return Double.parseDouble(scanner.nextLine());
    }

    private String leerTexto() {
        return scanner.nextLine();
    }
}
