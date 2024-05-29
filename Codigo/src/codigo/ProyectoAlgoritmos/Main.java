/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo.ProyectoAlgoritmos;

/**
 * * Main class that contains the `main` method to run the program.
 * This program reads a source code file, extracts the methods and
 * stored in a circular list for further processing.
 * @author Jocelyn Abarca
 * @author Adrian Chavarria
 */
public class Main {
    
  public static void main(String[] args) {
        String rutaArchivo = "D:\\Usuarios\\ESTUDIANTE\\Documents\\NetBeansProjects\\Algoritmos Proyecto\\Codigo\\src\\codigo\\Codigo.java";
        CircularList listaProcedimientos = new CircularList();
        LeerFile.readMethods(rutaArchivo, listaProcedimientos);
        LeerFile.showMenu(listaProcedimientos);
    }
}
