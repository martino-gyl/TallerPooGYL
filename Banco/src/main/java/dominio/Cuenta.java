package dominio;

public class Cuenta {
    private String numero;
    private String password;
    private Sucursal sucursal;
    private double saldo;
    private TipoCuenta tipo;

    // Datos del titular dentro de la cuenta
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;

    public Cuenta(
            String numero,
            String password,
            Sucursal sucursal,
            TipoCuenta tipo,
            String dni,
            String nombre,
            String apellido,
            String email,
            String direccion
    ) {
        this.numero = numero;
        this.password = password;
        this.sucursal = sucursal;
        this.tipo = tipo;
        this.saldo = 0;

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public String getPassword() {
        return password;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public double getSaldo() {
        return saldo;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        saldo += monto;
    }

    public void extraer(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }

        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        saldo -= monto;
    }

    public void sumarSaldo(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        this.saldo += monto;
    }
}