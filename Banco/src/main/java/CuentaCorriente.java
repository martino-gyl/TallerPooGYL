public class CuentaCorriente extends Cuenta{
    private int descubierto;

    public CuentaCorriente(Cliente unCliente, int unMontoDeDescubierto){
        super(unCliente);
        this.descubierto = unMontoDeDescubierto;
    }
}
