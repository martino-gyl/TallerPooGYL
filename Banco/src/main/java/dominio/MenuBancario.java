package dominio;

import java.util.List;
import java.util.Scanner;

public class MenuBancario {
    private Scanner scanner;
    private Banco banco;

    public MenuBancario(Banco banco) {
        this.banco = banco;
        this.scanner = new Scanner(System.in);
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
                    case 1 -> menuBanco();
                    case 2 -> menuLoginAdmin();
                    case 3 -> menuLoginCuenta();
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

    private void menuBanco() {
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
                    case 1 -> listarSucursales();
                    case 2 -> crearSucursal();
                    case 3 -> asignarAdminASucursal();
                    case 4 -> verBalanceTotalBanco();
                    case 5 -> verBalanceSucursal();
                    case 6 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void menuLoginAdmin() {
        System.out.println("\n--- LOGIN ADMIN ---");
        System.out.print("Código de sucursal: ");
        String codigoSucursal = leerTexto();

        System.out.print("Usuario: ");
        String usuario = leerTexto();

        System.out.print("Contraseña: ");
        String password = leerTexto();

        Admin admin = banco.autenticarAdmin(codigoSucursal, usuario, password);

        if (admin == null) {
            System.out.println("Credenciales inválidas.");
            return;
        }

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigoSucursal);
        menuAdminSucursal(sucursal);
    }

    private void menuAdminSucursal(Sucursal sucursal) {
        boolean volver = false;

        while (!volver) {
            try {
                System.out.println("\n--- PANEL ADMIN SUCURSAL " + sucursal.getCodigo() + " ---");
                System.out.println("1. Listar cuentas");
                System.out.println("2. Crear cuenta");
                System.out.println("3. Eliminar cuenta");
                System.out.println("4. Ver balance de la sucursal");
                System.out.println("5. Ver balance de una cuenta");
                System.out.println("6. Volver");
                System.out.print("Opción: ");

                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> listarCuentasSucursal(sucursal);
                    case 2 -> crearCuentaEnSucursal(sucursal);
                    case 3 -> eliminarCuentaDeSucursal(sucursal);
                    case 4 -> System.out.println("Balance sucursal: $" + sucursal.calcularBalanceSucursal());
                    case 5 -> verBalanceCuenta();
                    case 6 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void menuLoginCuenta() {
        System.out.println("\n--- LOGIN CUENTA ---");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = leerTexto();

        System.out.print("Contraseña: ");
        String password = leerTexto();

        Cuenta cuenta = banco.autenticarCuenta(numeroCuenta, password);

        if (cuenta == null) {
            System.out.println("Credenciales inválidas.");
            return;
        }

        menuCuenta(cuenta);
    }

    private void menuCuenta(Cuenta cuenta) {
        boolean volver = false;

        while (!volver) {
            try {
                System.out.println("\n--- PANEL CUENTA " + cuenta.getNumero() + " ---");
                System.out.println("1. Depositar");
                System.out.println("2. Extraer");
                System.out.println("3. Transferir");
                System.out.println("4. Ver saldo");
                System.out.println("5. Ver balance");
                System.out.println("6. Volver");
                System.out.print("Opción: ");

                int opcion = leerInt();

                switch (opcion) {
                    case 1 -> depositar(cuenta);
                    case 2 -> extraer(cuenta);
                    case 3 -> transferir(cuenta);
                    case 4 -> System.out.println("Saldo actual: $" + cuenta.getSaldo());
                    case 5 -> verBalanceCuenta(cuenta);
                    case 6 -> volver = true;
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listarSucursales() {
        List<Sucursal> sucursales = banco.getSucursales();

        if (sucursales.isEmpty()) {
            System.out.println("No hay sucursales registradas.");
            return;
        }

        System.out.println("\n--- SUCURSALES ---");
        for (Sucursal sucursal : sucursales) {
            String admin = (sucursal.getAdmin() == null) ? "Sin admin" : sucursal.getAdmin().getUsuario();

            System.out.println(
                    "Código: " + sucursal.getCodigo() +
                            " | Nombre: " + sucursal.getNombre() +
                            " | Dirección: " + sucursal.getDireccion() +
                            " | Admin: " + admin
            );
        }
    }

    private void crearSucursal() {
        System.out.print("Código sucursal: ");
        String codigo = leerTexto();

        System.out.print("Nombre sucursal: ");
        String nombre = leerTexto();

        System.out.print("Dirección sucursal: ");
        String direccion = leerTexto();

        Sucursal sucursal = new Sucursal(codigo, nombre, direccion);
        banco.agregarSucursal(sucursal);

        System.out.println("Sucursal creada correctamente.");
    }

    private void asignarAdminASucursal() {
        System.out.print("Código sucursal: ");
        String codigo = leerTexto();

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigo);
        if (sucursal == null) {
            throw new IllegalArgumentException("Sucursal inexistente.");
        }

        System.out.print("Nombre admin: ");
        String nombre = leerTexto();

        System.out.print("Usuario admin: ");
        String usuario = leerTexto();

        System.out.print("Contraseña admin: ");
        String password = leerTexto();

        Admin admin = new Admin(nombre, usuario, password);
        banco.asignarAdminASucursal(codigo, admin);

        System.out.println("Admin asignado correctamente.");
    }

    private void verBalanceTotalBanco() {
        System.out.println("Balance total del banco: $" + banco.calcularBalanceTotal());
    }

    private void verBalanceSucursal() {
        System.out.print("Código sucursal: ");
        String codigo = leerTexto();

        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigo);
        if (sucursal == null) {
            throw new IllegalArgumentException("Sucursal inexistente.");
        }

        System.out.println("Balance sucursal: $" + sucursal.calcularBalanceSucursal());
    }

    private void listarCuentasSucursal(Sucursal sucursal) {
        List<Cuenta> cuentas = sucursal.getCuentas();

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

    private void crearCuentaEnSucursal(Sucursal sucursal) {
        System.out.print("DNI titular: ");
        String dni = leerTexto();

        if (buscarCuentaPorDni(dni) != null) {
            throw new IllegalArgumentException("Ya existe una cuenta asociada a ese DNI.");
        }

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

        if (banco.buscarCuentaPorNumero(numeroCuenta) != null) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese número.");
        }

        System.out.print("Contraseña cuenta: ");
        String passwordCuenta = leerTexto();

        System.out.println("Tipo de cuenta:");
        System.out.println("1. Caja de ahorro");
        System.out.println("2. Cuenta corriente");
        System.out.print("Opción: ");
        int opcionTipo = leerInt();

        TipoCuenta tipo;
        if (opcionTipo == 1) {
            tipo = TipoCuenta.CAJA_DE_AHORRO;
        } else if (opcionTipo == 2) {
            tipo = TipoCuenta.CUENTA_CORRIENTE;
        } else {
            throw new IllegalArgumentException("Tipo de cuenta inválido.");
        }

        Cuenta cuenta = new Cuenta(
                numeroCuenta,
                passwordCuenta,
                sucursal,
                tipo,
                dni,
                nombre,
                apellido,
                email,
                direccion
        );

        sucursal.registrarCuenta(cuenta);
        System.out.println("Cuenta creada correctamente.");
    }

    private void eliminarCuentaDeSucursal(Sucursal sucursal) {
        System.out.print("Número de cuenta a eliminar: ");
        String numeroCuenta = leerTexto();

        sucursal.eliminarCuenta(numeroCuenta);
        System.out.println("Cuenta eliminada si existía.");
    }

    private void depositar(Cuenta cuenta) {
        System.out.print("Monto a depositar: ");
        double monto = leerDouble();

        cuenta.depositar(monto);
        System.out.println("Depósito realizado.");
    }

    private void extraer(Cuenta cuenta) {
        System.out.print("Monto a extraer: ");
        double monto = leerDouble();

        cuenta.extraer(monto);
        System.out.println("Extracción realizada.");
    }

    private void transferir(Cuenta cuentaOrigen) {
        System.out.print("Número de cuenta destino: ");
        String numeroDestino = leerTexto();

        Cuenta cuentaDestino = banco.buscarCuentaPorNumero(numeroDestino);
        if (cuentaDestino == null) {
            throw new IllegalArgumentException("Cuenta destino inexistente.");
        }

        System.out.print("Monto a transferir: ");
        double monto = leerDouble();

        banco.transferir(cuentaOrigen, cuentaDestino, monto);
        System.out.println("Transferencia realizada.");
    }

    private void verBalanceCuenta() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = leerTexto();

        Cuenta cuenta = banco.buscarCuentaPorNumero(numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta inexistente.");
        }

        verBalanceCuenta(cuenta);
    }

    private void verBalanceCuenta(Cuenta cuenta) {
        System.out.println("\n=== BALANCE CUENTA ===");
        System.out.println("Número: " + cuenta.getNumero());
        System.out.println("Tipo: " + cuenta.getTipo());
        System.out.println("Titular: " + cuenta.getNombreCompleto());
        System.out.println("DNI: " + cuenta.getDni());
        System.out.println("Email: " + cuenta.getEmail());
        System.out.println("Dirección: " + cuenta.getDireccion());
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("Sucursal: " + cuenta.getSucursal().getNombre());
    }

    private Cuenta buscarCuentaPorDni(String dni) {
        for (Sucursal sucursal : banco.getSucursales()) {
            for (Cuenta cuenta : sucursal.getCuentas()) {
                if (cuenta.getDni().equals(dni)) {
                    return cuenta;
                }
            }
        }
        return null;
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