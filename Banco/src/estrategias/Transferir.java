package estrategias;

import dominio.Banco;
import dominio.Cuenta;

public class Transferir implements OperacionStrategy{
    @Override
    public void ejecutar(Banco banco, int idCuentaOrigen, int idCuentaDestino, double monto) {
        Cuenta cuentaOrigen = banco.obtenerCuentaPorID(idCuentaOrigen);
        Cuenta cuentaDestino = banco.obtenerCuentaPorID(idCuentaDestino);
        if (monto > 0 && monto <= cuentaOrigen.getSaldo()) {
            cuentaOrigen.restarSaldo(monto);
            cuentaDestino.sumarSaldo(monto);
            System.out.println("Se ha transferido $" + monto + " de la cuenta de " + cuentaOrigen.getIdentificacionCliente() + " a " + cuentaDestino.getIdentificacionCliente());
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia.");
        }
    }
}
