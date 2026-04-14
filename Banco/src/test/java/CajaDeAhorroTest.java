
import dominio.CajaDeAhorro;
import dominio.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CajaDeAhorroTest {

    @Test
    void acreditaDinero() {
        Cliente cliente = new Cliente("Martino", "1111", "Calle 1");
        CajaDeAhorro cuenta = new CajaDeAhorro(cliente);

        cuenta.acreditar(500);

        assertEquals(500, cuenta.getSaldo());
    }
}

