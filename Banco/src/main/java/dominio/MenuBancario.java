package dominio;

import java.util.List;
import java.util.Scanner;

public class MenuBancario {
    private final Scanner scanner;
    private final AplicacionBanco aplicacion;

    public MenuBancario(Banco banco) {
        this.scanner = new Scanner(System.in);
        this.aplicacion = new AplicacionBanco(banco);
    }

    public void iniciar() {
        boolean salir = false;

        System.out.println("=== SISTEMA BANCARIO ===");

        while (!salir) {
            try {
                mostrarMenuPrincipal();
                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> panelBanco();
                    case 2 -> panelAdmin();
                    case 3 -> panelCuenta();
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

    private void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Panel Banco");
        System.out.println("2. Panel Admin de Sucursal");
        System.out.println("3. Panel Cuenta");
        System.out.println("4. Salir");
        System.out.print("Opción: ");
    }

    private void panelBanco() {
        boolean volver = false;

        while (!volver) {
            try {
                System.out.println("\n--- PANEL BANCO ---");
                System.out.println("1. Listar sucursales");
                System.out.println("2. Crear sucursal");
                System.out.println("3. Asignar admin a sucursal");
                System.out.println("4. Ver balance total del banco");
                System.out.println("5. Ver balance de una sucursal");
                System.out.println("6. Volver");
                System.out.print("Opción: ");

                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> System.out.println("\n" + aplicacion.listarSucursalesTexto());
                    case 2 -> accionCrearSucursal();
                    case 3 -> accionAsignarAdmin();
                    case 4 -> System.out.println(aplicacion.balanceBancoTexto());
                    case 5 -> accionBalanceSucursal();
                    case 6 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void panelAdmin() {
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
                    case 1 -> System.out.println("\n" + aplicacion.listarCuentasTexto(admin));
                    case 2 -> accionCrearCuenta(admin);
                    case 3 -> accionEliminarCuenta(admin);
                    case 4 -> System.out.println(aplicacion.balanceSucursalTexto(admin));
                    case 5 -> accionResumenCuentaPorNumero();
                    case 6 -> accionTransferir(admin);
                    case 7 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void panelCuenta() {
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
                    case 1 -> accionDepositar(cuenta);
                    case 2 -> accionExtraer(cuenta);
                    case 3 -> System.out.println(aplicacion.saldoTexto(cuenta));
                    case 4 -> System.out.println("\n" + aplicacion.resumenCuenta(cuenta));
                    case 5 -> volver = true;
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

    private Cuenta loginCuenta() {
        System.out.println("\n--- LOGIN CUENTA ---");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = leerTexto();

        System.out.print("Contraseña: ");
        String password = leerTexto();

        return aplicacion.loginCuenta(numeroCuenta, password);
    }

    private void accionCrearSucursal() {
        System.out.print("Código sucursal: ");
        String codigo = leerTexto();

        System.out.print("Nombre sucursal: ");
        String nombre = leerTexto();

        System.out.print("Dirección sucursal: ");
        String direccion = leerTexto();

        System.out.println(aplicacion.crearSucursal(codigo, nombre, direccion));
    }

    private void accionAsignarAdmin() {
        System.out.print("Código sucursal: ");
        String codigo = leerTexto();

        System.out.print("Nombre admin: ");
        String nombre = leerTexto();

        System.out.print("Usuario admin: ");
        String usuario = leerTexto();

        System.out.print("Contraseña admin: ");
        String password = leerTexto();

        System.out.println(aplicacion.asignarAdmin(codigo, nombre, usuario, password));
    }

    private void accionBalanceSucursal() {
        System.out.print("Código sucursal: ");
        String codigo = leerTexto();

        System.out.println(aplicacion.balanceSucursalTexto(codigo));
    }

    private void accionCrearCuenta(Admin admin) {
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

        System.out.println(
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
                )
        );
    }

    private void accionEliminarCuenta(Admin admin) {
        System.out.print("Número de cuenta a eliminar: ");
        String numeroCuenta = leerTexto();

        System.out.println(aplicacion.eliminarCuenta(admin, numeroCuenta));
    }

    private void accionTransferir(Admin admin) {
        System.out.print("Número de cuenta origen: ");
        String numeroOrigen = leerTexto();

        System.out.print("Número de cuenta destino: ");
        String numeroDestino = leerTexto();

        System.out.print("Monto a transferir: ");
        double monto = leerDouble();

        System.out.println(aplicacion.transferir(admin, numeroOrigen, numeroDestino, monto));
    }

    private void accionDepositar(Cuenta cuenta) {
        System.out.print("Monto a depositar: ");
        double monto = leerDouble();

        System.out.println(aplicacion.depositar(cuenta, monto));
    }

    private void accionExtraer(Cuenta cuenta) {
        System.out.print("Monto a extraer: ");
        double monto = leerDouble();

        System.out.println(aplicacion.extraer(cuenta, monto));
    }

    private void accionResumenCuentaPorNumero() {
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