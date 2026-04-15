package servicios;

import dominio.Banco;

import java.util.Scanner;

public class MenuBancario {
    private final Scanner scanner;
    private final AplicacionBanco aplicacion;
    private final PanelBanco panelBanco;
    private final PanelAdmin panelAdmin;
    private final PanelCuenta panelCuenta;

    public MenuBancario(Banco banco) {
        this.scanner = new Scanner(System.in);
        this.aplicacion = new AplicacionBanco(banco);
        this.panelBanco = new PanelBanco(scanner, aplicacion);
        this.panelAdmin = new PanelAdmin(scanner, aplicacion);
        this.panelCuenta = new PanelCuenta(scanner, aplicacion);
    }

    public void iniciar() {
        boolean salir = false;

        System.out.println("=== SISTEMA BANCARIO ===");

        while (!salir) {
            try {
                System.out.println("\n--- MENÚ PRINCIPAL ---");
                System.out.println("1. Panel Banco");
                System.out.println("2. Panel Admin de Sucursal");
                System.out.println("3. Panel Cuenta");
                System.out.println("4. Salir");
                System.out.print("Opción: ");

                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> panelBanco.mostrar();
                    case 2 -> panelAdmin.mostrar();
                    case 3 -> panelCuenta.mostrar();
                    case 4 -> {
                        salir = true;
                        System.out.println("Hasta luego.");
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado.");
            }
        }

        scanner.close();
    }

    private int leerInt() {
        return Integer.parseInt(scanner.nextLine());
    }
}