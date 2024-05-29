/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 */
package codigo.ProyectoAlgoritmos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class contains methods to read Java methods from a file, analyze their complexity,
 * and manage them within a circular linked list.
 * @author Jocelyn Abarca
 * @author Adrian Chavarria
 */
public class LeerFile {

     /**
     * Reads methods from a specified file and adds them to a circular list.
     *
     * @param rutaArchivo          The path to the file containing Java methods.
     * @param listaProcedimientos  The circular list to store the methods.
     */
    public static void readMethods(String rutaArchivo, CircularList listaProcedimientos) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo especificado no existe.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            StringBuilder cuerpoMetodo = new StringBuilder();
            String nombreMetodo = null;
            boolean leyendoMetodo = false;
            int balanceLlaves = 0;
            int contadorDeclaracion = 0;
            int contadorSuma = 0;
            int contadorResta= 0;

           while ((linea = br.readLine()) != null) {
               
                String posibleNombreMetodo = extractNameMethod(linea);
                if (posibleNombreMetodo != null) {
                    nombreMetodo = posibleNombreMetodo;
                    cuerpoMetodo.setLength(0);
                    leyendoMetodo = true;
                    balanceLlaves = 0;
                    contadorDeclaracion = 0;
                    contadorSuma = 0;
                    contadorResta = 0;
                }

                if (leyendoMetodo) {
                    cuerpoMetodo.append(linea).append("\n");
                    balanceLlaves += countKeys(linea);

                    contadorDeclaracion += countStatement(linea);
                    contadorSuma += excludeSum(linea, "+");
                    contadorResta += excludeSum(linea, "-");

                    if (balanceLlaves == 0 && cuerpoMetodo.length() > 0) {
                        int complejidad = calculateComplexityM(nombreMetodo, cuerpoMetodo.toString());
                        int complejidadN = calculateComplexityN(contadorDeclaracion, contadorSuma,contadorResta);
                        listaProcedimientos.addNode(nombreMetodo, complejidad,cuerpoMetodo.toString(),contadorDeclaracion,contadorSuma,contadorResta,complejidadN);

                        
                        leyendoMetodo = false;
                        nombreMetodo = null;
                    }
                }
            }
           
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     /**
     * Excludes sums within quoted strings and counts occurrences of a substring.
     *
     * @param cadena    The string to search within.
     * @param subcadena The substring to count.
     * @return The count of non-quoted occurrences of the substring.
     */

    public static int excludeSum(String cadena, String subcadena) {
        int contador = 0;
        boolean dentroDeComillas = false;
        int startIndex = 0;

        while (startIndex < cadena.length()) {
            int printIndex = cadena.indexOf("System.out.print", startIndex);
            if (printIndex == -1) {
                printIndex = cadena.length();
            }
            String subCadenaAAnalizar = cadena.substring(startIndex, printIndex);

            for (int i = 0; i < subCadenaAAnalizar.length(); i++) {
                char c = subCadenaAAnalizar.charAt(i);

                if (c == '"') {
                    dentroDeComillas = !dentroDeComillas;
                }

                if (!dentroDeComillas && subCadenaAAnalizar.startsWith(subcadena, i)) {
                    contador++;
                }
            }

            if (printIndex < cadena.length()) {
                int endIndex = cadena.indexOf(";", printIndex);
                if (endIndex == -1) {
                    endIndex = cadena.length();
                }
                startIndex = endIndex + 1;
            } else {
                startIndex = printIndex;
            }
        }

        return contador;
    }

     /**
     * Counts variable declarations in a line of code.
     *
     * @param linea The line of code to analyze.
     * @return The count of variable declarations.
     */
   
public static int countStatement(String linea) {
    int contador = 0;
    
    Pattern pattern = Pattern.compile("\\b(?:int|double|String|boolean|char|float|long|short|byte)\\s+\\w+(?:\\[\\])?(?:\\s*=\\s*[^;]+)?;");
    Matcher matcher = pattern.matcher(linea);

    
    while (matcher.find()) {
        contador++;
    }

    return contador;
}
 /**
     * Extracts the name of a method from a line of code.
     *
     * @param linea The line of code to analyze.
     * @return The name of the method, or null if no method is found.
     */
    
   public static String extractNameMethod(String linea) {
    if (linea.contains("System.out.println")) {
        return null;
    }
    Pattern pattern = Pattern.compile("\\b(?:public|private|protected)?\\s*(?:static)?\\s*(?:final)?\\s*\\w+\\s+(\\w+)\\s*\\([^\\)]*\\)\\s*\\{?");
    Matcher matcher = pattern.matcher(linea);

    if (matcher.find()) {
        return matcher.group(1); 
    }

    return null;
}

   /**
     * Calculates the total complexity (N) based on declarations, sums, and subtractions.
     *
     * @param contadorDeclaraciones The count of variable declarations.
     * @param contadorSumas         The count of sum operations.
     * @param contadorRestas        The count of subtraction operations.
     * @return The total complexity (N).
     */
   
public static int calculateComplexityN( int contadorDeclaraciones, int contadorSumas, int contadorRestas) {
       
        int complejidadN = contadorDeclaraciones + contadorSumas + contadorRestas;

        

        return complejidadN;
    }

  /**
     * Calculates the complexity (M) of a method based on loops.
     *
     * @param nombreMetodo The name of the method.
     * @param metodo       The content of the method.
     * @return The complexity (M) of the method.
     */

    public static int calculateComplexityM(String nombreMetodo, String metodo) {
        int cantidadBucles = countLoops(metodo);
        int complejidad = 0;

        if (cantidadBucles == 1) {
            complejidad += 1; 
        } else if (cantidadBucles > 1 && nestedLoop(metodo)) {
            complejidad += 2; 
        }

        return complejidad;
    }
      /**
     * Counts the number of loop statements (for, while, do-while) in a method.
     *
     * @param metodo The method content to analyze.
     * @return The count of loop statements.
     */

    public static int countLoops(String metodo) {
        int contador = 0;
        contador += countSubstring(metodo, "for");
        contador += countSubstring(metodo, "while");
        contador += countSubstring(metodo, "do");
        return contador;
    }
/**
     * Counts the occurrences of a substring within a string.
     *
     * @param cadena    The string to search within.
     * @param subcadena The substring to count.
     * @return The count of occurrences of the substring.
     */
    public static int countSubstring(String cadena, String subcadena) {
        int contador = 0;
        int index = 0;

        while ((index = cadena.indexOf(subcadena, index)) != -1) {
            contador++;
            index += subcadena.length();
        }

        return contador;
    }
      /**
     * Checks for nested loops within a method.
     *
     * @param metodo The method content to analyze.
     * @return True if nested loops are found, false otherwise.
     */

    public static boolean nestedLoop(String metodo) {
        int nivelAnidacion = 0;
        String[] lineas = metodo.split("\\n");

        for (String linea : lineas) {
            if (linea.contains("for") || linea.contains("while") || linea.contains("do")) {
                nivelAnidacion++;
                if (nivelAnidacion > 1) {
                    return true;
                }
            }

            if (linea.contains("}")) {
                nivelAnidacion = Math.max(0, nivelAnidacion - 1);
            }
        }

        return false;
    }

      /**
     * Counts the number of opening and closing braces in a line of code.
     *
     * @param linea The line of code to analyze.
     * @return The net count of braces (positive for more opening braces, negative for more closing braces).
     */
    public static int countKeys(String linea) {
        int contador = 0;
        for (char c : linea.toCharArray()) {
            if (c == '{') {
                contador++;
            } else if (c == '}') {
                contador--;
            }
        }
        return contador;
    }
 /**
     * Determines the Big-O complexity based on the number of loops.
     *
     * @param cantidadBucles The count of loops in a method.
     * @return The Big-O complexity as a string.
     */
    public static String calculateComplexityBigO(int cantidadBucles) {
    if (cantidadBucles == 0) {
        return "Constante: O(1)";
    } else if (cantidadBucles == 1) {
        return "Lineal: O(n)";
    } else if (cantidadBucles > 1) {
        return "Cuadratica: O(n^2)";
    } else {
        return "Complejidad no determinada";
    }
}

      /**
     * Displays a menu for the user to interact with the circular list of methods.
     *
     * @param listaProcedimientos The circular list containing the methods.
     */
    
    public static void showMenu(CircularList listaProcedimientos) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println();
            System.out.println("Menu de Metodos del .Java:");
            listaProcedimientos.showNode();
            System.out.println("Ingrese el nombre del procedimiento para ver su complejidad o 'salir' para terminar:");
            String input = scanner.nextLine();
            System.out.println("---------------------------------------------");
            if (input.equalsIgnoreCase("salir")) {
                break;
            } else {
                Nodo procedimiento = listaProcedimientos.searchNode(input);
                if (procedimiento != null) {
                    System.out.println("Metodo Seleccionado: " + procedimiento.getNombreProcedimiento());
                    System.out.println("Tipo de Complejidad Computacional: " + calculateComplexityBigO(procedimiento.getComplejidad()));
                    System.out.println("DESGLOSE");
                    System.out.println("Contador de declaraciones: "+ procedimiento.getContadorDeclaracion());
                    System.out.println("Contenido de sumas: " + procedimiento.getContadorSuma());
                    System.out.println("Contenido de Restas: " + procedimiento.getContadorResta());
                     System.out.println("Complejidad Computacional Total: " + procedimiento.getComplejidadN());
                    System.out.println("Contenido del Metodo: ");
                    System.out.println();
                    System.out.println(procedimiento.getCuerpoMetodo());

            
                } else {
                    System.out.println("Procedimiento no encontrado.");
                }
            }
        }
        scanner.close();
    }
}

