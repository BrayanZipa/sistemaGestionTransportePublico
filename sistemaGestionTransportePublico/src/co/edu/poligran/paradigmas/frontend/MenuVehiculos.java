package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;

public class MenuVehiculos {
	
    static Scanner sc = new Scanner(System.in);
    static GestionVehiculosManager vehiculoManager = new GestionVehiculosManager();

    /**
     * Muestra el menú principal del módulo de vehículos y maneja las opciones del usuario.
     */
    public void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE VEHICULOS ===");
            System.out.println("1. Crear vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Obtener vehículo por placa");
            System.out.println("4. Actualizar vehículo por placa");
            System.out.println("5. Eliminar vehículo por placa");
            System.out.println("6. Cambiar disponibilidad de un vehículo");
            System.out.println("7. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                	obtenerVehiculo();
                    break;
                case 4:
                	actualizarVehiculo();
                    break;
                case 5:
                    eliminarVehiculo();
                    break;
                case 6:
                	cambiarDisponibilidad();
                    break;
                case 7:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }

    /**
     * Crea un nuevo vehículo solicitando los datos al usuario.
     */
    private void crearVehiculo() {
        System.out.println("\n=== CREAR VEHICULO ===");

        String placa;
        
        do {
            System.out.print("Ingrese la placa: ");
            placa = sc.nextLine().trim();

            if (placa.isEmpty()) {
                System.out.println("La placa no puede estar vacía.");
            }
        } while (placa.isEmpty());

        String modelo;
        
        do {
            System.out.print("Ingrese el modelo: ");
            modelo = sc.nextLine().trim();

            if (modelo.isEmpty()) {
                System.out.println("El modelo no puede estar vacío.");
            }
        } while (modelo.isEmpty());

        int capacidad = 0;
        boolean capacidadValida = false;

        while (!capacidadValida) {
            System.out.print("Ingrese la capacidad de pasajeros: ");

            if (sc.hasNextInt()) {
                capacidad = sc.nextInt();
                sc.nextLine();

                if (capacidad > 5) {
                    capacidadValida = true;
                } else {
                    System.out.println("La capacidad debe ser mayor a 5.");
                }
            } else {
                System.out.println("Debe ingresar un número válido.");
                sc.nextLine();
            }
        }

        boolean estado = false;
        boolean estadoValido = false;

        while (!estadoValido) {
            System.out.print("¿Está disponible? (true/false): ");

            String entrada = sc.nextLine().trim().toLowerCase();

            if (entrada.equals("true") || entrada.equals("false")) {
                estado = Boolean.parseBoolean(entrada);
                estadoValido = true;
            } else {
                System.out.println("Ingrese únicamente true o false.");
            }
        }

        VehiculoVO v = new VehiculoVO(placa, modelo, capacidad, estado);
        vehiculoManager.agregarVehiculo(v);

        System.out.println("Vehículo agregado correctamente.");
    }

    /**
     * Lista todos los vehículos registrados en el sistema.
     */
    private void listarVehiculos() {
        System.out.println("\n=== LISTADO DE VEHICULOS ===");
        for (VehiculoVO v : vehiculoManager.obtenerVehiculos()) {
            System.out.println(v);
        }
    }
    
    /**
     * Busca y muestra un vehículo específico por su placa.
     */
    private void obtenerVehiculo() {
        System.out.print("Ingrese la placa del vehiculo a buscar: ");
        String placa = sc.nextLine();
        VehiculoVO vehiculo = vehiculoManager.buscarVehiculoPorPlaca(placa);

        if (vehiculo != null) {
            System.out.println("\n=== VEHICULO ENCONTRADO ===");
            System.out.println(vehiculo);
        } else {
            System.out.println("Vehiculo no encontrado.");
        }
    }
    
    private void actualizarVehiculo() {
        System.out.print("Ingrese la placa del vehículo a actualizar: ");
        String placa = sc.nextLine();

        VehiculoVO v = vehiculoManager.buscarVehiculoPorPlaca(placa);

        if (v != null) {
            System.out.print("Nuevo modelo (" + v.getModelo() + "): ");
            String nuevoModelo = sc.nextLine().trim();

            if (!nuevoModelo.isEmpty()) {
                v.setModelo(nuevoModelo);
            }

            System.out.print("Nueva capacidad de pasajeros (" + v.getCapacidadPasajeros() + "): ");
            String nuevaCapacidad = sc.nextLine().trim();

            if (!nuevaCapacidad.isEmpty()) {
                try {
                    int capacidad = Integer.parseInt(nuevaCapacidad);

                    if (capacidad > 5) {
                        v.setCapacidadPasajeros(capacidad);
                    } else {
                        System.out.println("La capacidad debe ser mayor a 5. Se conserva el valor anterior.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Se conserva la capacidad anterior.");
                }
            }

            System.out.print("Nuevo estado (" + v.isEstadoDisponibilidad() + ") [true/false]: ");
            String nuevoEstado = sc.nextLine().trim().toLowerCase();

            if (!nuevoEstado.isEmpty()) {
                if (nuevoEstado.equals("true") || nuevoEstado.equals("false")) {
                    v.setEstadoDisponibilidad(Boolean.parseBoolean(nuevoEstado));
                } else {
                    System.out.println("Estado inválido. Se conserva el valor anterior.");
                }
            }

            vehiculoManager.actualizarVehiculoPorPlaca(placa, v);
            System.out.println("Vehículo actualizado correctamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    /**
     * Elimina un vehículo del sistema identificado por su placa.
     */
    private void eliminarVehiculo() {
        System.out.print("Ingrese la placa del vehiculo a eliminar: ");
        String placa = sc.nextLine();
        boolean eliminado = vehiculoManager.eliminarVehiculoPorPlaca(placa);

        if (eliminado) {
            System.out.println("Vehiculo eliminado correctamente.");
        } else {
            System.out.println("Vehiculo no encontrado.");
        }
    }

    /**
     * Cambia el estado de disponibilidad de un vehículo identificado por su placa.
     */
    private void cambiarDisponibilidad() {
        System.out.print("Ingrese la placa del vehiculo: ");
        String placa = sc.nextLine();
        System.out.print("Nuevo estado de disponibilidad (true/false): ");
        boolean estado = sc.nextBoolean();
        sc.nextLine();

        boolean actualizado = vehiculoManager.cambiarDisponibilidad(placa, estado);
        if (actualizado) {
            System.out.println("Disponibilidad actualizada correctamente.");
        } else {
            System.out.println("Vehiculo no encontrado.");
        }
    }
}
