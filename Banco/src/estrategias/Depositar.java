package estrategias;

import dominio.Banco;
import dominio.Cuenta;

public class Depositar implements OperacionStrategy {
    @Override
    public void ejecutar(Banco banco, int idCuentaOrigen, int idCuentaDestino, double monto) {
        if (monto > 0) {
            Cuenta cuentaOrigen = banco.obtenerCuentaPorID(idCuentaOrigen);
            cuentaOrigen.sumarSaldo(monto);
            System.out.println("Se ha depositado $" + monto + " en la cuenta de " + cuentaOrigen.getIdentificacionCliente());
        }
    }
}


