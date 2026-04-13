public class Cliente {
    String nombre;
    String domicilio;
    String DNI;

    public Cliente (String nombre, String DNI, String domicilio){
        this.nombre = nombre;
        this.DNI = DNI;
        this.domicilio = domicilio;
    }

    public boolean esElMismoCliente(Cliente unClient) {
        return this.DNI.equals(unClient.DNI) && this.nombre.equals(unClient.nombre);
    }

}
