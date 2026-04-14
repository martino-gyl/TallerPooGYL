package dominio;

public class Banco {
    public void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, int unMonto){
        if (cuentaOrigen == null || cuentaDestino == null){
            throw new IllegalArgumentException("Las cuentas no pueden ser null");
        } else if (cuentaOrigen.esLaMismaCuenta(cuentaDestino)) {
            throw new IllegalArgumentException("ERROR: no se puede transferir a la misma cuenta");
        } else {
            cuentaOrigen.debitar(unMonto);
            cuentaDestino.acreditar(unMonto);
        }
    }
}
