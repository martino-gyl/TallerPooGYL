public abstract class Cuenta {
    private int saldo;
    private Cliente cliente;

    // las cuentas inician con saldo 0
    public Cuenta(Cliente unCliente){
        this.cliente = unCliente;
        this.saldo = 0;
    }
}


