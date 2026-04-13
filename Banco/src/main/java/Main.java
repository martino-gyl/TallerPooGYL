public class Main {
    static void main() {
        // Cuenta Corriente 1
        Cliente cliente1 = new Cliente("Martino","1111","Calle 1");
        Cuenta cuentaCorriente1 = new CuentaCorriente(cliente1,100000);
        cuentaCorriente1.acreditar(50000);
        // Cuenta Corriente 2
        Cliente cliente2 = new Cliente("Simon","2222","Calle 2");
        Cuenta cuentaCorriente2 = new CuentaCorriente(cliente2,100000);
        cuentaCorriente2.acreditar(60000);

        // transferencia entre cuentaCorriente1 a cuentaCorriente2
        cuentaCorriente1.transferirA(cuentaCorriente2,20000);
        cuentaCorriente1.estadoDeCuenta();
        cuentaCorriente2.estadoDeCuenta();

        cuentaCorriente1.imprimirDatosDeCuenta();
        cuentaCorriente2.imprimirDatosDeCuenta();
    }
}
