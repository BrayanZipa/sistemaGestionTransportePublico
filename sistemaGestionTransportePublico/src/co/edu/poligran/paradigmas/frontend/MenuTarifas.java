package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
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
            System.out.println("3. Obtener tarifa por código");
            System.out.println("4. Actualizar tarifa");
            System.out.println("5. Eliminar tarifa");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");
       
            try {
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
                	obtenerTarifa(); 
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

            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido.");
                sc.nextLine();
            }

        } while (opcion != 6);
    }

    /**
     * Crea una nueva tarifa solicitando los datos al usuario.
     */
    private void crearTarifa() {
        System.out.println("\n=== CREAR TARIFA ===");

        try {
            int codigo = 0;

            do {
                System.out.print("Código: ");
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("El código no puede estar vacío.");
                    continue;
                }

                try {
                    codigo = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                    codigo = 0;
                }

            } while (codigo <= 0);

            double valor = 0;

            do {
                System.out.print("Valor (mínimo 1.000): ");
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("El valor no puede estar vacío.");
                    continue;
                }

                try {
                    // Permite escribir 3.000 o 3000
                    String limpio = input.replace(".", "");

                    valor = Double.parseDouble(limpio);

                    if (valor < 1000) {
                        System.out.println("El valor debe ser mínimo 1.000.");
                        valor = 0;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido (ej: 3000 o 3.000).");
                    valor = 0;
                }

            } while (valor < 1000); 

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

        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private void listarTarifas() {

        System.out.println("\n=== LISTADO DE TARIFAS ===");

        for (TarifasVO t : tarifasManager.obtenerTarifas()) {
            System.out.println(t);
        }
    }
    
    private void obtenerTarifa() {

        int codigo = 0;

        do {
            System.out.print("Ingrese el código de la tarifa: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("El código no puede estar vacío.");
                continue;
            }

            try {
                codigo = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                codigo = 0;
            }

        } while (codigo <= 0);

        TarifasVO tarifa = tarifasManager.buscarTarifaPorCodigo(codigo);

        if (tarifa != null) {
            System.out.println("\n=== TARIFA ENCONTRADA ===");
            System.out.println(tarifa);
        } else {
            System.out.println("Tarifa no encontrada.");
        }
    }
    

    private void actualizarTarifa() {

        int codigo = 0;

        do {
            System.out.print("Ingrese el código de la tarifa a actualizar: ");
            String input = sc.nextLine().trim();

            try {
                codigo = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                codigo = 0;
            }

        } while (codigo <= 0);

        TarifasVO tarifa = tarifasManager.buscarTarifaPorCodigo(codigo);

        if (tarifa != null) {

            System.out.print("Nuevo valor (" + tarifa.getValor() + "): ");
            String nuevoValor = sc.nextLine().trim();

            if (!nuevoValor.isEmpty()) {
                try {
                    //puntos (permite 3.000)
                    String limpio = nuevoValor.replace(".", "");
                    double valor = Double.parseDouble(limpio);

                    
                    if (valor < 1000) {
                        System.out.println("El valor debe ser mínimo 1.000. Se conserva el valor anterior.");
                    } else {
                        tarifa.setValor(valor);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Se conserva el anterior.");
                }
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
        try {
            int codigo = 0;

            do {
                System.out.print("Ingrese el código de la tarifa a eliminar: ");
                String input = sc.nextLine().trim();

                try {
                    codigo = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                    codigo = 0;
                }

            } while (codigo <= 0);

            boolean eliminado = tarifasManager.eliminarTarifaPorCodigo(codigo);

            if (eliminado) {
                System.out.println("Tarifa eliminada correctamente.");
            } else {
                System.out.println("Tarifa no encontrada.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
}