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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProyectoAlgoritmos {

    public static void main(String[] args) {
        String rutaArchivo = "C:\\Users\\chava\\OneDrive\\Documentos\\NetBeansProjects\\Codigo\\src\\codigo\\Codigo.java";
        leerMetodos(rutaArchivo);
    }

    public static void leerMetodos(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo especificado no existe.");
            return;
        }

        // Leer el archivo Java
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Buscar definiciones de métodos y calcular complejidad
                if (linea.contains("metodo1")) {
                    System.out.println("Contenido de metodo1:");
                    String complejidad = calcularComplejidadMetodo(br, linea);
                    System.out.println("Complejidad total de metodo1: " + complejidad);
                    System.out.println("Fin del metodo1");
                } else if (linea.contains("metodo2")) {
                    System.out.println("Contenido de metodo2:");
                    String complejidad = calcularComplejidadMetodo(br, linea);
                    System.out.println("Complejidad total de metodo2: " + complejidad);
                    System.out.println("Fin del metodo2");
                } else if (linea.contains("metodo3")) {
                    System.out.println("Contenido de metodo3:");
                    String complejidad = calcularComplejidadMetodo(br, linea);
                    System.out.println("Complejidad total de metodo3: " + complejidad);
                    System.out.println("Fin del metodo3");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String calcularComplejidadMetodo(BufferedReader br, String primeraLinea) throws IOException {
        StringBuilder metodoContenido = new StringBuilder();
        String linea;
        boolean dentroMetodo = false;
        String complejidad = "O(1)";

        // Imprimir la primera línea del método
        System.out.println(primeraLinea);
        metodoContenido.append(primeraLinea).append("\n");

        while ((linea = br.readLine()) != null) {
            System.out.println(linea); // Imprimir cada línea del método
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
        // Definir patrones para detectar estructuras de control y operaciones
        Pattern forLoopPattern = Pattern.compile("for.*\\{"); // Patrón para bucle for
        Pattern whileLoopPattern = Pattern.compile("while.*\\{"); // Patrón para bucle while
        Pattern ifPattern = Pattern.compile("if.*\\{"); // Patrón para sentencia if

        // Buscar coincidencias con los patrones
        Matcher forLoopMatcher = forLoopPattern.matcher(linea);
        Matcher whileLoopMatcher = whileLoopPattern.matcher(linea);
        Matcher ifMatcher = ifPattern.matcher(linea);

        // Determinar la complejidad de la línea
        if (forLoopMatcher.find() || whileLoopMatcher.find()) {
            return "O(n)"; // Consideramos O(n) como la complejidad de un bucle for o while
        } else if (ifMatcher.find()) {
            return "O(n)"; // Consideramos O(n) como la complejidad de una condición
        } else {
            return "O(1)"; // Consideramos O(1) como la complejidad para otras líneas
        }
    }

    public static String ajustarComplejidad(String complejidadActual, String complejidadLinea) {
        if (complejidadActual.equals("O(1)") && complejidadLinea.equals("O(n)")) {
            return "O(n)";
        } else if (complejidadActual.equals("O(n)") && complejidadLinea.equals("O(1)")) {
            return "O(n)";
        } else if (complejidadActual.equals("O(n)") && complejidadLinea.equals("O(n)")) {
            return "O(n^2)";
        } else if (complejidadActual.equals("O(1)") && complejidadLinea.equals("O(log N)")) {
            return "O(log N)";
        } else if (complejidadActual.equals("O(A*B)") || complejidadLinea.equals("O(A*B)")) {
            return "O(A*B)";
        } else if (complejidadActual.equals("O(N*log N)") || complejidadLinea.equals("O(N*log N)")) {
            return "O(N*log N)";
        } else {
            return complejidadActual;
        }
    }
}
