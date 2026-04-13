public class CajaDeAhorro extends Cuenta{

    public String tipoDeCuenta = "Caja de Ahorro";

    public CajaDeAhorro(Cliente unCliente){
        super(unCliente);
    }

    @Override
    public TipoDeCuenta getTipoDeCuenta(){
        return TipoDeCuenta.CAJA_DE_AHORRO;
    }

    @Override
    public void aceptar(CuentaVisitor unVisitor){
        unVisitor.visitarCajaDeAhorro(this);
    }
}
