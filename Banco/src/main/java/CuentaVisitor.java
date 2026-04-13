public interface CuentaVisitor {
    void visitarCajaDeAhorro(CajaDeAhorro unaCajaDeAhorro);
    void visitarCuentaCorriente(CuentaCorriente unaCuentaCorriente);
}
