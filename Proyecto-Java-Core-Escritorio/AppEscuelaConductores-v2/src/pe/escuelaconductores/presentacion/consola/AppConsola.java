package pe.escuelaconductores.presentacion.consola;

import pe.escuelaconductores.bean.Entidad;
import pe.escuelaconductores.bean.EstadoEntidad;
import pe.escuelaconductores.bean.TipoEntidad;
import pe.escuelaconductores.bean.Ubigeo;
import pe.escuelaconductores.persistence.*;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class AppConsola {
    private static final String USER_INSERT = "admin";
    public static void main(String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        int menu;

        do {
            // Mostrar el menú
            System.out.println(" ");
            System.out.println("\033[1m OPCIONES DEL SISTEMA v1.0 \033[0m");
            System.out.println("1. Listar establecimientos con SQL");
            System.out.println("2. Agregar establecimientos con SQL");
            System.out.println("3. Agregar establecimiento con SP");
            System.out.println("4. Buscar establecimiento por Ubigeo");
            System.out.println("5. Buscar por Nombre establecimiento");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción:");

            menu = scanner.nextInt();

            switch(menu) {
                case 1:
                    listarEntidades();
                    break;
                case 2:
                    insertarEntidad();
                    break;
                case 3:
                     insertarEntidad_SP();
                    break;
                case 4:
                      buscarUbigeo_SP();
                    break;
                case 5:
                      buscarNombre_SP();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }

        } while(menu != 6);

        scanner.close();
    }

    public static void listarEntidades(){
        try{
            EntidadDB entidadDB = new EntidadDBImpl();
            System.out.println("");
            System.out.println("LISTA DE ESCUELAS CON CONSULTA SQL ");
            AtomicReference<Long> contador = new AtomicReference<>(1L);
            entidadDB.list().forEach(fila -> {
                System.out.println(contador.getAndSet(contador.get() + 1)
                        + ", RUC: " + fila.getRuc()
                        + ", Nombre: " + fila.getNombre()
                        + ", Ubicación: " + fila.getUbigeo().getNomDepartamento()
                        + ", / " + fila.getUbigeo().getNomProvincia()
                        + ", / " + fila.getUbigeo().getNomDistrito()
                        + ", Tipo: " + fila.getTipoEntidad().getNombreTipoEntidad()
                        + ", Estado: " + fila.getEstadoEntidad().getNombreEstadoEntidad());

            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarEntidad(){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("");
            /*
            System.out.print(" RUC (11 digitos): ");
            String ruc = scanner.nextLine();
            */

            String ruc;
            while (true) {
                System.out.print(" RUC (11 dígitos): ");
                ruc = scanner.nextLine().trim();
                if (ruc.matches("\\d{11}")) break;
                System.out.println("RUC inválido. Debe tener 11 dígitos numéricos.");
            }

            System.out.print(" Nombre de la escuela: ");
            String nombre = scanner.nextLine();

            System.out.print(" Dirección: ");
            String direccion = scanner.nextLine();
            /*
            System.out.print(" Correo: ");
            String correo = scanner.nextLine();
            */
            String correo;
            while (true) {
                System.out.print(" Correo: ");
                correo = scanner.nextLine().trim();
                if (correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) break;
                System.out.println("Correo inválido. Ingrese un correo válido.");
            }

            System.out.print(" Teléfono: ");
            String telefono = scanner.nextLine();

            /*listarUbigeos();
            System.out.print("   Ingrese la ubicación: ");
            Long idUbigeo = Long.valueOf(scanner.nextLine());
            */

            // Cargar la lista de Ubigeos desde la base de datos o repositorio
            UbigeoDB ubigeoDB = new UbigeoDBImpl();  // Asumiendo que tienes una implementación de UbigeoDB
            List<Ubigeo> ubigeos = ubigeoDB.listado(); // Obtener la lista de Ubigeos
            listarUbigeos();  // Mostrar todos los Ubigeos disponibles

            Long idUbigeo;  // Declarar la variable fuera del bucle para poder usarla en la lambda
            while (true) {
                System.out.print("   Ingrese la ubicación (ID numérico): ");
                String input = scanner.nextLine().trim();
                try {
                    idUbigeo = Long.valueOf(input);
                    final Long finalIdUbigeo = idUbigeo;
                    // Validar si el ID del Ubigeo existe
                    boolean existe = ubigeos.stream().anyMatch(u -> u.getIdUbigeo().equals(finalIdUbigeo));  // Cambia esto por el método correcto en la clase Ubigeo
                    if (existe) break;
                    else System.out.println("ID de Ubigeo no válido. Seleccione uno de la lista.");
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Ingrese un número.");
                }
            }

            /*
            listartiposentidad();
            System.out.print("  Ingrese el tipo establecimiento: ");
            Long idTipoEntidad = Long.valueOf(scanner.nextLine());
            */

            TipoEntidadDB tipoEntidadDB = new TipoEntidadDBImpl();
            List<TipoEntidad> tipos = tipoEntidadDB.listado();
            listartiposentidad();

            Long idTipoEntidad;  // Declaramos la variable fuera del bucle

            while (true) {
                System.out.print("  Ingrese el tipo establecimiento de la lista: ");
                String input = scanner.nextLine().trim();

                try {
                    idTipoEntidad = Long.valueOf(input); // Asignamos el valor a idTipoEntidad
                    final Long finalIdTipoEntidad = idTipoEntidad;  // Usar una variable final
                    boolean existe = tipos.stream().anyMatch(t -> t.getIdTipoEntidad().equals(finalIdTipoEntidad));  // Validación
                    if (existe) {
                        break;
                    } else {
                        System.out.println("Tipo establecimiento no válido. Seleccione uno de la lista.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un establecimiento válido.");
                }
            }

            /*
            listarestados();
            System.out.print("  Ingrese el estado del establecimiento: ");
            Long idTipoEstado = Long.valueOf(scanner.nextLine());
            */

            EstadoEntidadDB estadoEntidadDB = new EstadoEntidadDBImpl(); // Asumimos que tienes esta clase
            List<EstadoEntidad> estados = estadoEntidadDB.listado();
            listarestados();
            Long idTipoEstado;
            while (true) {
                System.out.print("  Ingrese el estado del establecimiento : ");
                String input = scanner.nextLine().trim();
                try {
                    idTipoEstado = Long.valueOf(input);
                    // Usamos la variable final para la expresión lambda
                    final Long finalIdTipoEstado = idTipoEstado;
                    // Validación si el ID ingresado existe en la lista de Estados
                    boolean existe = estados.stream().anyMatch(e -> e.getIdEstadoEntidad().equals(finalIdTipoEstado));
                    if (existe) break;
                    else System.out.println("Estado no válido. Seleccione uno de la lista.");
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Ingrese un número.");
                }
            }

            EntidadDB entidadDB = new EntidadDBImpl();

            Entidad entidad = new Entidad(ruc, nombre, direccion, correo, telefono, idUbigeo, idTipoEntidad, idTipoEstado);
            entidad.setUserCreate(USER_INSERT);

            if (entidadDB.insertar(entidad))
            {
                System.out.println("");
                System.out.print("\033[32m Exito! Escuela registrada. \033[0m");
                System.out.println("");
                listarEntidades();
            }
            else {
                System.out.println("");
                System.err.print("\033[31m Error al registrar la escuela. Intentelo nuevamente. \033[0m");
                System.out.println("");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void insertarEntidad_SP(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("");
            System.out.print(" RUC (11 digitos): ");
            String ruc = scanner.nextLine();

            System.out.print(" Nombre de la escuela: ");
            String nombre = scanner.nextLine();

            System.out.print(" Dirección: ");
            String direccion = scanner.nextLine();

            System.out.print(" Correo: ");
            String correo = scanner.nextLine();

            System.out.print(" Teléfono: ");
            String telefono = scanner.nextLine();

            listarUbigeos();
            System.out.print("   Ingrese la ubicación: ");
            Long idUbigeo = Long.valueOf(scanner.nextLine());

            listartiposentidad();
            System.out.print("  Ingrese el tipo establecimiento: ");
            Long idTipoEntidad = Long.valueOf(scanner.nextLine());

            listarestados();
            System.out.print("  Ingrese el estado del establecimiento: ");
            Long idTipoEstado = Long.valueOf(scanner.nextLine());

            EntidadDB entidadDB = new EntidadDBImpl();

            Entidad entidad = new Entidad(ruc, nombre, direccion, correo, telefono, idUbigeo, idTipoEntidad, idTipoEstado);

            if (entidadDB.insertar_SP(entidad))
            {
                System.out.println("");
                System.out.print("\033[32m Exito! Escuela registrada. \033[0m");
                System.out.println("");
                listarEntidades();
            }
            else {
                System.out.println("");
                System.err.print("\033[31m Error al registrar la escuela. Intentelo nuevamente. \033[0m");
                System.out.println("");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void buscarUbigeo_SP(){
        Scanner scanner = new Scanner(System.in);
        try {
            EntidadDB entidadDB = new EntidadDBImpl();
             /*
            System.out.print("   Ingrese el Departamento ");
            String depa = String.valueOf(scanner.nextLine());
            System.out.print("   Ingrese la Provincia ");
            String prov = String.valueOf(scanner.nextLine());
            System.out.print("   Ingrese la Provincia ");
            String dist = String.valueOf(scanner.nextLine()); */

            System.out.print("   Ingrese el Departamento: ");
            String depa = scanner.nextLine();

            System.out.print("   Ingrese la Provincia: ");
            String prov = scanner.nextLine();

            System.out.print("   Ingrese el Distrito: ");
            String dist = scanner.nextLine();

            AtomicReference<Long> contador = new AtomicReference<>(1L);
            entidadDB.buscarEntidadPorUbigeo(depa, prov, dist).forEach(fila -> {
                System.out.println(contador.getAndSet(contador.get() + 1)
                        + ", RUC: " + fila.getRuc()
                        + ", Nombre: " + fila.getNombre()
                        + ", Ubicación: " + fila.getUbigeo().getNomDepartamento()
                        + ", / " + fila.getUbigeo().getNomProvincia()
                        + ", / " + fila.getUbigeo().getNomDistrito()
                        + ", Tipo: " + fila.getTipoEntidad().getNombreTipoEntidad()
                        + ", Estado: " + fila.getEstadoEntidad().getNombreEstadoEntidad());

            });
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void buscarNombre_SP() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese nombre o parte del nombre: ");
        String nombre = scanner.nextLine();

        try {
            EntidadDB entidadDB = new EntidadDBImpl();
            AtomicReference<Long> contador = new AtomicReference<>(1L);
            entidadDB.buscarEntidadPorNombre(nombre).forEach(fila -> {
                System.out.println(contador.getAndSet(contador.get() + 1)
                        + ", RUC: " + fila.getRuc()
                        + ", Nombre: " + fila.getNombre()
                        + ", Ubicación: " + fila.getUbigeo().getNomDepartamento()
                        + ", / " + fila.getUbigeo().getNomProvincia()
                        + ", / " + fila.getUbigeo().getNomDistrito()
                        + ", Tipo: " + fila.getTipoEntidad().getNombreTipoEntidad()
                        + ", Estado: " + fila.getEstadoEntidad().getNombreEstadoEntidad());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void listarUbigeos(){
        try{
            UbigeoDB ubigeoDB = new UbigeoDBImpl();
            System.out.println("Ubicación:  ");
            ubigeoDB.listado().forEach(fila -> {
                System.out.println("   "+ fila.getIdUbigeo() +". " + fila.getNomDepartamento() + "/" + fila.getNomProvincia() + "/" + fila.getNomDistrito());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void listartiposentidad(){
        try{
            TipoEntidadDB tipoEntidadDB = new TipoEntidadDBImpl();
            System.out.println("Tipo Entidad:  ");
            tipoEntidadDB.listado().forEach(fila -> {
                System.out.println("   "+ fila.getIdTipoEntidad() +". " + fila.getNombreTipoEntidad());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarestados(){
        try{
            EstadoEntidadDB estados = new EstadoEntidadDBImpl();
            System.out.println("Estado establecimiento:  ");
            estados.listado().forEach(fila -> {
                System.out.println("   "+ fila.getIdEstadoEntidad() +". " + fila.getNombreEstadoEntidad());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
