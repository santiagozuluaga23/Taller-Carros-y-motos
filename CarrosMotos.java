import java.util.Scanner;

public class CarrosMotos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Por favor ingrese la capacidad máxima del taller: ");
        int capacidadMaxima = scanner.nextInt();
        scanner.nextLine(); 

        String[][][] trabajos = new String[capacidadMaxima][2][4];

        System.out.print("Por favor ingrese el número de trabajos a registrat: ");
        int numeroTrabajos = scanner.nextInt();
        scanner.nextLine(); 

        if (numeroTrabajos > capacidadMaxima) {
            System.out.println("El número de trabajos supera la capacidad máxima del taller");
            return;
        }

        for (int i = 0; i < numeroTrabajos; i++) {
            System.out.print("Por favor ingrese el nombre del empleado: ");
            String empleado = scanner.nextLine();

            System.out.print("Por favor ingrese el tipo de vehículo (Carro/Moto): ");
            String tipoVehiculo = scanner.nextLine().toLowerCase();
            int tipo = tipoVehiculo.equals("moto") ? 0 : 1;

            System.out.print("Por favor ingrese la marca: ");
            String marca = scanner.nextLine();

            System.out.print("Por favor ingrese el modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Por favor ingrese el año: ");
            String ano = scanner.nextLine();

            System.out.print("Por favor ingrese el estado (Pendiente, En reparación, Reparado): ");
            String estado = scanner.nextLine();

            trabajos[i][tipo][0] = marca;
            trabajos[i][tipo][1] = modelo;
            trabajos[i][tipo][2] = ano;
            trabajos[i][tipo][3] = estado;
        }

        mostrarTrabajos(trabajos, capacidadMaxima);
        contarVehiculosPorEstado(trabajos, capacidadMaxima);

        System.out.print("\n¿Quiere buscar un vehículo? (si/no): ");
        String respuestaBuscar = scanner.nextLine();
        if (respuestaBuscar.equalsIgnoreCase("si")) {
            buscarVehiculo(trabajos, capacidadMaxima);
        }

        System.out.print("\n¿Quiere actualizar el estado de un vehículo? (si/no): ");
        String respuestaActualizar = scanner.nextLine();
        if (respuestaActualizar.equalsIgnoreCase("si")) {
            actualizarEstadoVehiculo(trabajos, capacidadMaxima);
        }

        scanner.close();
    }

    public static void mostrarTrabajos(String[][][] trabajos, int capacidadMaxima) {
        System.out.println("\nAgenda de trabajos:");
        System.out.println("| Tipo  | Marca    | Modelo  | Año  | Estado     |");
        System.out.println("|-------|----------|---------|------|------------|");

        for (int i = 0; i < capacidadMaxima; i++) {
            for (int j = 0; j < 2; j++) {
                if (trabajos[i][j][0] != null) {
                    String tipo = j == 0 ? "Moto" : "Carro";
                    System.out.printf("| %-5s | %-8s | %-7s | %-4s | %-10s |\n",
                            tipo, trabajos[i][j][0], trabajos[i][j][1], trabajos[i][j][2], trabajos[i][j][3]);
                }
            }
        }
    }

    public static void contarVehiculosPorEstado(String[][][] trabajos, int capacidadMaxima) {
        int pendiente = 0, enReparacion = 0, reparado = 0;

        for (int i = 0; i < capacidadMaxima; i++) {
            for (int j = 0; j < 2; j++) {
                if (trabajos[i][j][3] != null) {
                    switch (trabajos[i][j][3].toLowerCase()) {
                        case "pendiente":
                            pendiente++;
                            break;
                        case "en reparación":
                            enReparacion++;
                            break;
                        case "reparado":
                            reparado++;
                            break;
                    }
                }
            }
        }

        System.out.println("\nConteo de vehículos por estado:");
        System.out.println("Pendiente: " + pendiente);
        System.out.println("En reparación: " + enReparacion);
        System.out.println("Reparado: " + reparado);
    }

    public static void buscarVehiculo(String[][][] trabajos, int capacidadMaxima) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPor favor ingrese la marca del vehículo a buscar: ");
        String marca = scanner.nextLine();
        System.out.print("Por favor ingrese el modelo del vehículo a buscar: ");
        String modelo = scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < capacidadMaxima; i++) {
            for (int j = 0; j < 2; j++) {
                if (trabajos[i][j][0] != null &&
                        trabajos[i][j][0].equalsIgnoreCase(marca) &&
                        trabajos[i][j][1].equalsIgnoreCase(modelo)) {
                    String tipo = j == 0 ? "Moto" : "Carro";
                    System.out.printf("\nVehículo encontrado: | %-5s | %-8s | %-7s | %-4s | %-10s |\n",
                            tipo, trabajos[i][j][0], trabajos[i][j][1], trabajos[i][j][2], trabajos[i][j][3]);
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Vehículo no encontrado.");
        }
    }

    public static void actualizarEstadoVehiculo(String[][][] trabajos, int capacidadMaxima) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPor favor ingrese la marca del vehículo a actualizar: ");
        String marca = scanner.nextLine();
        System.out.print("Por favor ingrese el modelo del vehículo a actualizar: ");
        String modelo = scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < capacidadMaxima; i++) {
            for (int j = 0; j < 2; j++) {
                if (trabajos[i][j][0] != null &&
                        trabajos[i][j][0].equalsIgnoreCase(marca) &&
                        trabajos[i][j][1].equalsIgnoreCase(modelo)) {
                    System.out.print("Por favor ingrese el nuevo estado: ");
                    String nuevoEstado = scanner.nextLine();
                    trabajos[i][j][3] = nuevoEstado;
                    System.out.println("Estado actualizado correctamente.");
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Vehículo no encontrado.");
        }
    }
}
