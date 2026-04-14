package estrategias;

import dominio.Banco;
import dominio.Cuenta;

public class ProcesadorDeOperaciones {
    //El usuario elije la estrategia que quiere usar
    private OperacionStrategy estrategia;

    //La setea aca para que no se repita en cada metodo
    public void setEstrategia(OperacionStrategy estrategia) {
        this.estrategia = estrategia;
    }

    //Llama a la estrategia que elija y la procesa
    public void procesar(Banco banco, int idCuentaOrigen, int idCuentaDestino, double monto) {
        if (this.estrategia == null) {
            System.out.println("No se ha seleccionado una operación.");
            return;
        }
        this.estrategia.ejecutar(banco,idCuentaOrigen,idCuentaDestino,monto);
    }
}
