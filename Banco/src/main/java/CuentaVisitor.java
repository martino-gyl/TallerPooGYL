public interface CuentaVisitor {
    void visitarCajaDeAhorro(Cuenta unaCuenta);
    void visitarCuentaCorriente(Cuenta unaCuenta);
}
