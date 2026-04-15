package servicios;

import dominio.Admin;
import dominio.Cuenta;
import dominio.TipoCuenta;

import java.util.List;
import java.util.Scanner;

public class PanelAdmin {
    private final Scanner scanner;
    private final AplicacionBanco aplicacion;

    public PanelAdmin(Scanner scanner, AplicacionBanco aplicacion) {
        this.scanner = scanner;
        this.aplicacion = aplicacion;
    }

    public void mostrar() {
        Admin admin = loginAdmin();
        boolean volver = false;

        while (!volver) {
            try {
                System.out.println("\n--- PANEL ADMIN SUCURSAL " + admin.getSucursal().getCodigo() + " ---");
                System.out.println("1. Listar cuentas");
                System.out.println("2. Crear cuenta");
                System.out.println("3. Eliminar cuenta");
                System.out.println("4. Ver balance de la sucursal");
                System.out.println("5. Ver balance de una cuenta");
                System.out.println("6. Transferir entre cuentas de la sucursal");
                System.out.println("7. Volver");
                System.out.print("Opción: ");

                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> listarCuentas(admin);
                    case 2 -> crearCuenta(admin);
                    case 3 -> eliminarCuenta(admin);
                    case 4 -> System.out.println("Balance sucursal: $" + aplicacion.balanceSucursal(admin));
                    case 5 -> verBalanceCuenta();
                    case 6 -> transferir(admin);
                    case 7 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private Admin loginAdmin() {
        System.out.println("\n--- LOGIN ADMIN ---");
        System.out.print("Código de sucursal: ");
        String codigoSucursal = leerTexto();

        System.out.print("Usuario: ");
        String usuario = leerTexto();

        System.out.print("Contraseña: ");
        String password = leerTexto();

        return aplicacion.loginAdmin(codigoSucursal, usuario, password);
    }

    private void listarCuentas(Admin admin) {
        List<Cuenta> cuentas = aplicacion.listarCuentas(admin);

        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas en la sucursal.");
            return;
        }

        System.out.println("\n--- CUENTAS DE LA SUCURSAL ---");
        for (Cuenta cuenta : cuentas) {
            System.out.println(
                    "Número: " + cuenta.getNumero() +
                            " | Tipo: " + cuenta.getTipo() +
                            " | Titular: " + cuenta.getNombreCompleto() +
                            " | DNI: " + cuenta.getDni() +
                            " | Saldo: $" + cuenta.getSaldo()
            );
        }
    }

    private void crearCuenta(Admin admin) {
        System.out.print("DNI titular: ");
        String dni = leerTexto();

        System.out.print("Nombre titular: ");
        String nombre = leerTexto();

        System.out.print("Apellido titular: ");
        String apellido = leerTexto();

        System.out.print("Email titular: ");
        String email = leerTexto();

        System.out.print("Dirección titular: ");
        String direccion = leerTexto();

        System.out.print("Número de cuenta: ");
        String numeroCuenta = leerTexto();

        System.out.print("Contraseña cuenta: ");
        String passwordCuenta = leerTexto();

        TipoCuenta tipo = leerTipoCuenta();

        aplicacion.crearCuenta(
                admin,
                numeroCuenta,
                passwordCuenta,
                tipo,
                dni,
                nombre,
                apellido,
                email,
                direccion
        );

        System.out.println("Cuenta creada correctamente.");
    }

    private void eliminarCuenta(Admin admin) {
        System.out.print("Número de cuenta a eliminar: ");
        String numeroCuenta = leerTexto();

        aplicacion.eliminarCuenta(admin, numeroCuenta);
        System.out.println("Cuenta eliminada correctamente.");
    }

    private void transferir(Admin admin) {
        System.out.print("Número de cuenta origen: ");
        String numeroOrigen = leerTexto();

        System.out.print("Número de cuenta destino: ");
        String numeroDestino = leerTexto();

        System.out.print("Monto a transferir: ");
        double monto = leerDouble();

        aplicacion.transferir(admin, numeroOrigen, numeroDestino, monto);
        System.out.println("Transferencia realizada correctamente.");
    }

    private void verBalanceCuenta() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = leerTexto();

        System.out.println("\n" + aplicacion.resumenCuenta(numeroCuenta));
    }

    private TipoCuenta leerTipoCuenta() {
        System.out.println("Tipo de cuenta:");
        System.out.println("1. Caja de ahorro");
        System.out.println("2. Cuenta corriente");
        System.out.print("Opción: ");

        int opcionTipo = leerInt();

        if (opcionTipo == 1) {
            return TipoCuenta.CAJA_DE_AHORRO;
        }
        if (opcionTipo == 2) {
            return TipoCuenta.CUENTA_CORRIENTE;
        }

        throw new IllegalArgumentException("Tipo de cuenta inválido.");
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
