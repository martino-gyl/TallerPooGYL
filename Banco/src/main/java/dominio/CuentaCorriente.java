package dominio;

public class CuentaCorriente extends Cuenta{
    private int descubierto;

    public CuentaCorriente(Cliente unCliente, int unMontoDeDescubierto){
        super(unCliente);
        this.descubierto = unMontoDeDescubierto;
    }

    @Override
    public TipoDeCuenta getTipoDeCuenta(){
        return TipoDeCuenta.CUENTA_CORRIENTE;
    }

    @Override
    public void debitar(int unMonto){
        if (unMonto <= 0) {
            throw new IllegalArgumentException("ERROR: no se puede debitar un monto menor o igual a cero");
        } else {
            int saldoActual = getSaldo();
            int saldoNuevo = saldoActual - unMonto;
            if (saldoNuevo < -descubierto) {
                throw new IllegalArgumentException("ERROR: no se puede debitar el monto: " + unMonto + " ya que supera al descubierto");
            } else {
                this.saldo = saldoNuevo;
            }
        }
    }

    @Override
    public void aceptar(CuentaVisitor unVisitor){
        unVisitor.visitarCuentaCorriente(this);
    }
}
