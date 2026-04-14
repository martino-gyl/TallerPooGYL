package dominio;

import java.util.Scanner;

public class MenuBancario {
    private Scanner scanner = new Scanner(System.in);
    private Banco banco;
    private ProcesadorDeOperaciones procesador;

    public MenuBancario(Banco banco) {
        this.banco = banco;
        this.procesador = new ProcesadorDeOperaciones();
    }

    public void iniciar() {
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Transferir");

        int opcion = Integer.parseInt(scanner.nextLine());

        System.out.print("Monto: ");
        double monto = Double.parseDouble(scanner.nextLine());

        Cuenta origen = ...;
        Cuenta destino = null;

        switch (opcion) {
            case 1 -> procesador.setEstrategia(new DepositarStrategy());
            case 2 -> procesador.setEstrategia(new RetirarStrategy());
            case 3 -> {
                procesador.setEstrategia(new TransferirStrategy());
                destino = ...;
            }
            default -> throw new IllegalArgumentException("Opción inválida");
        }

        procesador.procesar(banco, origen, destino, monto);
    }
}