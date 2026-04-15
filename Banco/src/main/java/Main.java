import dominio.*;
import servicios.MenuBancario;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Dino");

        Sucursal sucursal1 = new Sucursal("001", "Casa Central", "Av. Siempre Viva 123");
        Sucursal sucursal2 = new Sucursal("002", "Sucursal Norte", "Calle Norte 456");

        banco.agregarSucursal(sucursal1);
        banco.agregarSucursal(sucursal2);

        Admin admin1 = new Admin("Laura", "admin1", "1234");
        banco.asignarAdminASucursal("001", admin1);

        MenuBancario menu = new MenuBancario(banco);
        menu.iniciar();
    }
}