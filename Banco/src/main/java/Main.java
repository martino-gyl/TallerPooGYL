public class Main {
    static void main() {
        Cliente cliente1 = new Cliente("Martino","1111","Call 1");
        System.out.println(cliente1.nombre);
        System.out.println(cliente1.esElMismoCliente(cliente1));
    }
}
