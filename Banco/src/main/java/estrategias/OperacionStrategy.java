package estrategias;

import dominio.Banco;
import dominio.Cuenta;

public interface OperacionStrategy {
    void ejecutar(Banco banco, int idCuentaOrigen, int idCuentaDestino, double unMonto);
}
