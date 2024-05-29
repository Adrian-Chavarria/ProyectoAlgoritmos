/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chava
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Procedimiento {
    String nombre;
    String complejidad;

    public Procedimiento(String nombre, String complejidad) {
        this.nombre = nombre;
        this.complejidad = complejidad;
    }
}

class ListaCircular {
    private Nodo inicio;

    private class Nodo {
        Procedimiento procedimiento;
        Nodo siguiente;

        Nodo(Procedimiento p) {
            this.procedimiento = p;
        }
    }

    public void agregarAlFinal(Procedimiento p) {
        Nodo nuevoNodo = new Nodo(p);
        if (inicio == null) {
            inicio = nuevoNodo;
            inicio.siguiente = inicio;
        } else {
            Nodo temp = inicio;
            while (temp.siguiente != inicio) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
            nuevoNodo.siguiente = inicio;
        }
    }

    public void mostrarProcedimientos() {
        if (inicio == null) {
            System.out.println("No hay procedimientos almacenados.");
        } else {
            Nodo temp = inicio;
            do {
                System.out.println("Nombre: " + temp.procedimiento.nombre + ", Complejidad: " + temp.procedimiento.complejidad);
                temp = temp.siguiente;
            } while (temp != inicio);
        }
    }
}

class LeerFile {

    public static void main(String[] args) {
        String rutaArchivo = "C:\\Users\\chava\\OneDrive\\Documentos\\NetBeansProjects\\Codigo\\src\\codigo\\Codigo.java";
        ListaCircular listaProcedimientos = leerMetodos(rutaArchivo);
        if (listaProcedimientos != null) {
            mostrarMenu(listaProcedimientos);
        }
    }

    public static ListaCircular leerMetodos(String rutaArchivo) {
        ListaCircular listaProcedimientos = new ListaCircular();
        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo especificado no existe.");
            return null;
        }

        // Leer el archivo Java
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Buscar definiciones de métodos y calcular complejidad
                if (linea.contains("public static void")) {
                    String nombre = extraerNombreMetodo(linea);
                    String complejidad = calcularComplejidadMetodo(br, linea);
                    Procedimiento procedimiento = new Procedimiento(nombre, complejidad);
                    listaProcedimientos.agregarAlFinal(procedimiento);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return listaProcedimientos;
    }

    public static String extraerNombreMetodo(String linea) {
        String[] partes = linea.split("\\s+");
        for (int i = 0; i < partes.length; i++) {
            if (partes[i].equals("void") && i + 1 < partes.length && partes[i + 1].contains("(")) {
                return partes[i + 1].substring(0, partes[i + 1].indexOf("("));
            }
        }
        return null;
    }

    public static String calcularComplejidadMetodo(BufferedReader br, String primeraLinea) throws IOException {
        StringBuilder metodoContenido = new StringBuilder();
        String linea;
        boolean dentroMetodo = false;
        String complejidad = "O(1)";

        // Imprimir la primera línea del método
        metodoContenido.append(primeraLinea).append("\n");

        while ((linea = br.readLine()) != null) {
            metodoContenido.append(linea).append("\n");

            if (linea.contains("{")) {
                dentroMetodo = true;
            } else if (linea.contains("}")) {
                dentroMetodo = false;
                break; // Si llegamos al final del método, detenemos el cálculo
            }

            if (dentroMetodo) {
                complejidad = ajustarComplejidad(complejidad, calcularComplejidadLinea(linea));
            }
        }

        return complejidad;
    }

    public static String calcularComplejidadLinea(String linea) {
        // Aquí iría la lógica para calcular la complejidad de la línea, según tus criterios.
        // Por simplicidad, asumiremos que siempre es O(1).
        return "O(1)";
    }

    public static String ajustarComplejidad(String complejidadActual, String complejidadLinea) {
        // Aquí iría la lógica para ajustar la complejidad, según tus criterios.
        // Por simplicidad, devolvemos la complejidad actual.
        return complejidadActual;
    }

    public static void mostrarMenu(ListaCircular listaProcedimientos) {
        System.out.println("==== Menú de Procedimientos ====");
        System.out.println("Lista de procedimientos y su complejidad computacional:");

        listaProcedimientos.mostrarProcedimientos();
    }
}
