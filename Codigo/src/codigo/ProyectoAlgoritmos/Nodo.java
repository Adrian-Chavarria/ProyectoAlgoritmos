/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo.ProyectoAlgoritmos;
/**
 * Class that represents a node in a linked data structure.
 * Each node contains information about a procedure, including
 * your name, method content, complexity, and counters
 * various operations within the method.
 * 
 * @author Jocelyn Abarca 
 * @author Adrian Chavarria
 */
public class Nodo {

    private String nombreProcedimiento;
    private String contenidoMetodo;

    private int complejidad;
    private int contadorDeclaracion;
    private int contadorSuma;
    private int contadorResta;
    private int complejidadN;

    private Nodo siguiente;

    /**
     * 
     * @param nombreProcedimiento  The name of the procedure.
     * @param complejidad           The complexity of the procedure.
     * @param contenidoMetodo       The content of the procedure method.
     * @param contadorDeclaracion   The statement counter in the procedure.
     * @param contadorSuma          The counter of addition operations in the procedure.
     * @param contadorResta         The subtraction operations counter in the procedure.
     * @param complejidadN          The complexity N of the procedure.
     */
    public Nodo(String nombreProcedimiento,
            int complejidad, String contenidoMetodo, 
            int contadorDeclaracion, int contadorSuma,
            int contadorResta, int complejidadN) {
        
        this.complejidadN = complejidadN;
        this.contenidoMetodo = contenidoMetodo;
        this.contadorDeclaracion = contadorDeclaracion; 
        this.contadorSuma = contadorSuma;
        this.contadorResta = contadorResta; 
        this.nombreProcedimiento = nombreProcedimiento;
        this.complejidad = complejidad;
        this.siguiente = null;

    }
/**
     * Gets the complexity N of the procedure.
     * 
     * @return The N complexity.
     */
    public int getComplejidadN() {
        return complejidadN;
    }
    
    /**
     * Sets the complexity N of the procedure.
     * 
     * @param complejidadN The new complexity N.
     */

    public void setComplejidadN(int complejidadN) {
        this.complejidadN = complejidadN;
    }
    /**
     * Gets the counter of statements in the procedure.
     * 
     * @return The statement counter.
     */
    public int getContadorDeclaracion() {
        return contadorDeclaracion;
    }
    /**
     * Sets the statement counter in the procedure.
     * 
     * @param contadorDeclaracion The new declaration counter.
     */

    public void setContadorDeclaracion(int contadorDeclaracion) {
        this.contadorDeclaracion = contadorDeclaracion;
    }
/**
     * Gets the counter of addition operations in the procedure.
     * 
     * @return The counter for addition operations.
     */
    public int getContadorSuma() {
        return contadorSuma;
    }
/**
     * Sets the addition operations counter in the procedure.
     * 
     * @param contadorSuma The new counter for addition operations.
     */
    
    public void setContadorSuma(int contadorSuma) {
        this.contadorSuma = contadorSuma;
    }
/**
     * Gets the counter of subtraction operations in the procedure.
     * 
     * @return The counter for subtraction operations.
     */
    public int getContadorResta() {
        return contadorResta;
    }
/**
     * Sets the subtraction operations counter in the procedure.
     * 
     * @param contadorResta The new counter for subtraction operations.
     */
    public void setContadorResta(int contadorResta) {
        this.contadorResta = contadorResta;
    }
    /**
     * Gets the content of the procedure method.
     * 
     * @return The content of the method.
     */

    public String getCuerpoMetodo() {
        return contenidoMetodo;
    }
/**
     * Sets the content of the procedure method.
     * 
     * @param cuerpoMetodo The new content of the method.
     */
    public void setCuerpoMetodo(String cuerpoMetodo) {
        this.contenidoMetodo = cuerpoMetodo;
    }
    /**
     * Gets the name of the procedure.
     * 
     * @return The name of the procedure.
     */

    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }
/**
     * Sets the name of the procedure.
     * 
     * @param nombreProcedimiento The new name of the procedure.
     */
    public void setNombreProcedimiento(String nombreProcedimiento) {
        this.nombreProcedimiento = nombreProcedimiento;
    }
/**
     * Gets the complexity of the procedure.
     * 
     * @return The complexity.
     */
    public int getComplejidad() {
        return complejidad;
    }
/**
     * Establishes the complexity of the procedure.
     * 
     * @param complejidad The new complexity.
     */
    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }
/**
     * Gets the next node in the linked structure.
     * 
     * @return The next node.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Sets the next node in the linked structure.
     * 
     * @param siguiente The new next node.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}
