public abstract class Cuenta {
    int saldo;
    private Cliente cliente;

    // las cuentas inician con saldo 0
    public Cuenta(Cliente unCliente){
        this.cliente = unCliente;
        this.saldo = 0;
    }

    public void transferirA(Cuenta unaCuenta, int unMonto){
        if (this.esLaMismaCuenta(unaCuenta)) {
            throw new IllegalArgumentException("ERROR: no se puede transferir a la misma cuenta");
        } else {
            this.debitar(unMonto);
            unaCuenta.acreditar(unMonto);
        }
    }

    public void debitar(int unMonto){
        if (unMonto <= 0) {
            throw new IllegalArgumentException("ERROR: no se puede debitar un monto menor o igual a cero");
        }
        this.saldo -= unMonto;
    }

    public void acreditar(int unMonto){
        if (unMonto <= 0) {
            throw new IllegalArgumentException("ERROR: no se puede acreditar un monto menor o igual a cero");
        }
        this.saldo += unMonto;
    }

    public boolean esLaMismaCuenta(Cuenta unaCuenta){
        return cliente.esElMismoCliente(unaCuenta.cliente) && this.getTipoDeCuenta() == unaCuenta.getTipoDeCuenta();
    }

    public abstract TipoDeCuenta getTipoDeCuenta();

    public int getSaldo() {
        return saldo;
    }


    public Cliente getCliente(){
        return this.cliente;
    }

    public abstract void aceptar(CuentaVisitor unVisitor);
}


