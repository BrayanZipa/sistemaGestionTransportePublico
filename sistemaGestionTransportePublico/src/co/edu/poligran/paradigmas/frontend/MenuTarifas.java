package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import co.edu.poligran.paradigmas.backend.vo.TarifasVO;

public class MenuTarifas {

    static Scanner sc = new Scanner(System.in);
    private GestionTarifasManager tarifasManager;
    
    /**
     * Constructor de la clase MenuTarifas.
     * @param tarifasManager gestor encargado de las operaciones relacionadas con tarifas
     */
    public MenuTarifas(GestionTarifasManager tarifasManager) {
        this.tarifasManager = tarifasManager;
    }
    
    /**
     * Muestra el menú principal del módulo de tarifas y gestiona las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE TARIFAS ===");
            System.out.println("1. Crear tarifa");
            System.out.println("2. Listar tarifas");
            System.out.println("3. Buscar tarifa por código");
            System.out.println("4. Actualizar tarifa");
            System.out.println("5. Eliminar tarifa");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearTarifa();
                    break;
                case 2:
                    listarTarifas();
                    break;
                case 3:
                    buscarTarifa();
                    break;
                case 4:
                    actualizarTarifa();
                    break;
                case 5:
                    eliminarTarifa();
                    break;
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    private void crearTarifa() {

        System.out.println("\n=== CREAR TARIFA ===");

        System.out.print("Código: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        String tipo;
        do {
            System.out.print("Tipo de tarifa: ");
            tipo = sc.nextLine().trim();

            if (tipo.isEmpty()) {
                System.out.println("El tipo no puede estar vacío.");
            }
        } while (tipo.isEmpty());

        TarifasVO tarifa = new TarifasVO(
                codigo,
                LocalDateTime.now(),
                valor,
                tipo
        );

        tarifasManager.agregarTarifa(tarifa);

        System.out.println("Tarifa creada correctamente.");
    }

    private void listarTarifas() {

        System.out.println("\n=== LISTADO DE TARIFAS ===");

        for (TarifasVO t : tarifasManager.obtenerTarifas()) {
            System.out.println(t);
        }
    }

    private void buscarTarifa() {

        System.out.print("Ingrese el código de la tarifa: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        TarifasVO tarifa = tarifasManager.buscarTarifaPorCodigo(codigo);

        if (tarifa != null) {
            System.out.println("\n=== TARIFA ENCONTRADA ===");
            System.out.println(tarifa);
        } else {
            System.out.println("Tarifa no encontrada.");
        }
    }

    private void actualizarTarifa() {

        System.out.print("Ingrese el código de la tarifa a actualizar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        TarifasVO tarifa = tarifasManager.buscarTarifaPorCodigo(codigo);

        if (tarifa != null) {

            System.out.print("Nuevo valor (" + tarifa.getValor() + "): ");
            String nuevoValor = sc.nextLine().trim();

            if (!nuevoValor.isEmpty()) {
                tarifa.setValor(Double.parseDouble(nuevoValor));
            }

            System.out.print("Nuevo tipo (" + tarifa.getTipoTarifa() + "): ");
            String nuevoTipo = sc.nextLine().trim();

            if (!nuevoTipo.isEmpty()) {
                tarifa.setTipoTarifa(nuevoTipo);
            }

            tarifasManager.actualizarTarifaPorCodigo(codigo, tarifa);

            System.out.println("Tarifa actualizada correctamente.");

        } else {
            System.out.println("Tarifa no encontrada.");
        }
    }

    private void eliminarTarifa() {

        System.out.print("Ingrese el código de la tarifa a eliminar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        boolean eliminado = tarifasManager.eliminarTarifaPorCodigo(codigo);

        if (eliminado) {
            System.out.println("Tarifa eliminada correctamente.");
        } else {
            System.out.println("Tarifa no encontrada.");
        }
    }
}
