package estrategias;

import dominio.Banco;
import dominio.Cuenta;

public class Retirar implements OperacionStrategy {
    @Override
    public void ejecutar(Banco banco, int idCuentaOrigen, int idCuentaDestino, double monto) {
        Cuenta cuentaOrigen = banco.obtenerCuentaPorID(idCuentaOrigen);
        if (monto > 0 && monto <= cuentaOrigen.getSaldo()) {
            cuentaOrigen.restarSaldo(monto);
            System.out.println("Se ha retirado $" + monto + " de la cuenta de " + cuentaOrigen.getIdentificacionCliente());
        } else {
            System.out.println("Saldo insuficiente para realizar el retiro.");
        }
    }
}
