 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codigo;

import java.util.Scanner;
/**
 *
 * @author Jocelyn 
 */
public class Codigo {

  

    public static void imprimirNumeros() {
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();    
    }
    
    
    
    public static void MatrizConForAnidado() {
  
        int filas = 5; 
        int columnas = 5; 

       
        int[][] matriz = new int[filas][columnas]; 
        int contador = 1; 

        for (int i = 0; i < filas; i++) {   
            for (int j = 0; j < columnas; j++) { 
                matriz[i][j] = contador;  
                contador++;
            }
        }

       
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println(); 
        }
    
}
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecciona la operación:");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        
        System.out.print("Introduce tu elección (1/2/3/4): ");
        int eleccion = scanner.nextInt();
        
        if (eleccion >= 1 && eleccion <= 4) {
            System.out.print("Introduce el primer número: ");
            double num1 = scanner.nextDouble();
            System.out.print("Introduce el segundo número: ");
            double num2 = scanner.nextDouble();
            
            switch (eleccion) {
                case 1:
                    System.out.println(num1 + " + " + num2 + " = " 
                            + (num1 + num2));
                    break;
                case 2:
                    System.out.println(num1 + " - " + num2 + " = " 
                            + (num1 - num2));
                    break;
                case 3:
                    System.out.println(num1 + " * " + num2 + " = " 
                            + (num1 * num2));
                    break;
                case 4:
                    if (num2 != 0) {
                        System.out.println(num1 + " / " + num2 + " = "
                                + (num1 / num2));
                    } else {
                        System.out.println("Error: División por cero "
                                + "no permitida.");
                    }
                    break;
                default:
                    System.out.println("Elección no válida. Por favor, "
                            + "elige una opción del 1 al 4.");
                    break;
            }
        } else {
            System.out.println("Elección no válida. Por favor, elige una"
                    + "opción del 1 al 4.");
        }
        
        scanner.close();
        imprimirNumeros();
        MatrizConForAnidado();
       
    }
   
}


