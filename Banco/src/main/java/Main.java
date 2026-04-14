public class Main {
    static void main() {
        Banco banco = new Banco();

        // Cuenta Corriente 1
        Cliente cliente1 = new Cliente("Martino","1111","Calle 1");
        Cuenta cuentaCorriente1 = new CuentaCorriente(cliente1,100000);
        cuentaCorriente1.acreditar(50000);
        // Cuenta Corriente 2
        Cliente cliente2 = new Cliente("Simon","2222","Calle 2");
        Cuenta cuentaCorriente2 = new CuentaCorriente(cliente2,100000);
        cuentaCorriente2.acreditar(60000);

        // transferencia entre cuentaCorriente1 a cuentaCorriente2
        banco.transferir(cuentaCorriente1,cuentaCorriente2,20000);
        //cuentaCorriente1.transferirA(cuentaCorriente2,20000);

        // Visitor para generar reporte
        ReporteDeCuentaVisitor reporteDeCuentaVisitor = new ReporteDeCuentaVisitor();
        cuentaCorriente1.aceptar(reporteDeCuentaVisitor);
        cuentaCorriente2.aceptar(reporteDeCuentaVisitor);



    }
}
