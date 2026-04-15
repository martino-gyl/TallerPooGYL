package dominio;

import java.util.List;

public class AplicacionBanco {
    private final Banco banco;

    public AplicacionBanco(Banco banco) {
        this.banco = banco;
    }

    public Admin loginAdmin(String codigoSucursal, String usuario, String password) {
        Admin admin = banco.autenticarAdmin(codigoSucursal, usuario, password);

        if (admin == null) {
            throw new IllegalArgumentException("Credenciales de administrador inválidas.");
        }

        return admin;
    }

    public Cuenta loginCuenta(String numeroCuenta, String password) {
        Cuenta cuenta = banco.autenticarCuenta(numeroCuenta, password);

        if (cuenta == null) {
            throw new IllegalArgumentException("Credenciales de cuenta inválidas.");
        }

        return cuenta;
    }

    public String listarSucursalesTexto() {
        List<Sucursal> sucursales = banco.getSucursales();

        if (sucursales.isEmpty()) {
            return "No hay sucursales registradas.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- SUCURSALES ---\n");

        for (Sucursal sucursal : sucursales) {
            String admin = (sucursal.getAdmin() == null)
                    ? "Sin admin"
                    : sucursal.getAdmin().getUsuario();

            sb.append("Código: ").append(sucursal.getCodigo())
                    .append(" | Nombre: ").append(sucursal.getNombre())
                    .append(" | Dirección: ").append(sucursal.getDireccion())
                    .append(" | Admin: ").append(admin)
                    .append("\n");
        }

        return sb.toString();
    }

    public String crearSucursal(String codigo, String nombre, String direccion) {
        banco.agregarSucursal(new Sucursal(codigo, nombre, direccion));
        return "Sucursal creada correctamente.";
    }

    public String asignarAdmin(String codigoSucursal, String nombreAdmin, String usuarioAdmin, String passwordAdmin) {
        banco.asignarAdminASucursal(
                codigoSucursal,
                new Admin(nombreAdmin, usuarioAdmin, passwordAdmin)
        );
        return "Admin asignado correctamente.";
    }

    public String balanceBancoTexto() {
        return "Balance total del banco: $" + banco.calcularBalanceTotal();
    }

    public String balanceSucursalTexto(String codigoSucursal) {
        Sucursal sucursal = banco.buscarSucursalPorCodigo(codigoSucursal);

        if (sucursal == null) {
            throw new IllegalArgumentException("Sucursal inexistente.");
        }

        return "Balance sucursal: $" + sucursal.calcularBalanceSucursal();
    }

    public String balanceSucursalTexto(Admin admin) {
        return "Balance sucursal: $" + admin.verBalanceSucursal();
    }

    public String listarCuentasTexto(Admin admin) {
        List<Cuenta> cuentas = admin.listarCuentas();

        if (cuentas.isEmpty()) {
            return "No hay cuentas en la sucursal.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- CUENTAS DE LA SUCURSAL ---\n");

        for (Cuenta cuenta : cuentas) {
            sb.append("Número: ").append(cuenta.getNumero())
                    .append(" | Tipo: ").append(cuenta.getTipo())
                    .append(" | Titular: ").append(cuenta.getNombreCompleto())
                    .append(" | DNI: ").append(cuenta.getDni())
                    .append(" | Saldo: $").append(cuenta.getSaldo())
                    .append("\n");
        }

        return sb.toString();
    }

    public String crearCuenta(
            Admin admin,
            String numeroCuenta,
            String passwordCuenta,
            TipoCuenta tipo,
            String dni,
            String nombre,
            String apellido,
            String email,
            String direccion
    ) {
        admin.darAltaCuenta(
                numeroCuenta,
                passwordCuenta,
                tipo,
                dni,
                nombre,
                apellido,
                email,
                direccion
        );

        return "Cuenta creada correctamente.";
    }

    public String eliminarCuenta(Admin admin, String numeroCuenta) {
        admin.darBajaCuenta(numeroCuenta);
        return "Cuenta eliminada correctamente.";
    }

    public String transferir(Admin admin, String numeroOrigen, String numeroDestino, double monto) {
        admin.transferir(numeroOrigen, numeroDestino, monto);
        return "Transferencia realizada correctamente.";
    }

    public String depositar(Cuenta cuenta, double monto) {
        cuenta.depositar(monto);
        return "Depósito realizado.";
    }

    public String extraer(Cuenta cuenta, double monto) {
        cuenta.extraer(monto);
        return "Extracción realizada.";
    }

    public String saldoTexto(Cuenta cuenta) {
        return "Saldo actual: $" + cuenta.getSaldo();
    }

    public String resumenCuenta(String numeroCuenta) {
        Cuenta cuenta = banco.buscarCuentaPorNumero(numeroCuenta);

        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta inexistente.");
        }

        return resumenCuenta(cuenta);
    }

    public String resumenCuenta(Cuenta cuenta) {
        return "=== BALANCE CUENTA ===\n" +
                "Número: " + cuenta.getNumero() + "\n" +
                "Tipo: " + cuenta.getTipo() + "\n" +
                "Titular: " + cuenta.getNombreCompleto() + "\n" +
                "DNI: " + cuenta.getDni() + "\n" +
                "Email: " + cuenta.getEmail() + "\n" +
                "Dirección: " + cuenta.getDireccion() + "\n" +
                "Saldo: $" + cuenta.getSaldo() + "\n" +
                "Sucursal: " + cuenta.getSucursal().getNombre();
    }
}