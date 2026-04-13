public class CajaDeAhorro extends Cuenta{

    public String tipoDeCuenta = "Caja de Ahorro";

    public CajaDeAhorro(Cliente unCliente){
        super(unCliente);
    }

    @Override
    public TipoDeCuenta getTipoDeCuenta(){
        return TipoDeCuenta.CUENTA_CORRIENTE;
    }
}
