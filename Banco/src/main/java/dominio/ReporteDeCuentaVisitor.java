package dominio;

public class ReporteDeCuentaVisitor implements CuentaVisitor {
    @Override
    public void visitarCajaDeAhorro(CajaDeAhorro unaCajaDeAhorro) {
        System.out.println("--Caja de Ahorro--");
        this.reporteGeneralDeCuenta(unaCajaDeAhorro);
    }

    public void visitarCuentaCorriente(CuentaCorriente unaCuentaCorriente) {
        System.out.println("--dominio.Cuenta Corriente--");
        this.reporteGeneralDeCuenta(unaCuentaCorriente);
    }

    public void reporteGeneralDeCuenta(Cuenta unaCuenta){
        Cliente cliente = unaCuenta.getCliente();
        System.out.println(
            "Saldo: " + unaCuenta.getSaldo());
            System.out.println("Datos del cliente \n" +
            "Nombre: " + cliente.getNombre() + "\n" +
            "Domicilio: " + cliente.getDomicilio() + "\n" +
            "DNI: "+ cliente.getDNI() + "\n");
        };

}


