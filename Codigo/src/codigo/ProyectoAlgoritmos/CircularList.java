/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo.ProyectoAlgoritmos;

/**
 * This class represents a circular linked list, where each node links to the
 * next node, and the last node links back to the first node, forming a circle.
 *
 * @author ESTUDIANTE
 */
public class CircularList {

    private Nodo inicio;

    /**
     * Constructs an empty circular list.
     *
     */
    public CircularList() {
        inicio = null;
    }

    /**
     * Adds a new node to the circular list.
     *
     * @param nombreProcedimiento The name of the procedure.
     * @param complejidad The complexity of the procedure.
     * @param contenidoMetodo The content of the method.
     * @param contadorDeclaracion The declaration counter.
     * @param contadorSuma The sum counter.
     * @param contadorResta The subtraction counter.
     * @param complejidadN The complexity value N.
     */
    public void addNode(String nombreProcedimiento, int complejidad,
            String contenidoMetodo, int contadorDeclaracion, int contadorSuma,
            int contadorResta, int complejidadN) {

        Nodo nuevoNodo = new Nodo(nombreProcedimiento,
                complejidad, contenidoMetodo, contadorDeclaracion,
                contadorSuma, contadorResta, complejidadN);
        if (inicio == null) {
            nuevoNodo.setSiguiente(nuevoNodo);
            inicio = nuevoNodo;
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != inicio) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(inicio);
        }
    }

    /**
     *
     * Displays the name of each node in the circular list.
     *
     */

    public void showNode() {
        if (inicio == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = inicio;
        do {
            System.out.println("Metodo: " + "- "
                    + actual.getNombreProcedimiento());
            actual = actual.getSiguiente();
        } while (actual != inicio);
    }

     /**
     * Searches for a node by its procedure name.
     *
     * @param nombreProcedimiento The name of the procedure to search for.
     * @return The node with the matching procedure name, or null if not found.
     */
    public Nodo searchNode(String nombreProcedimiento) {
        if (inicio == null) {
            return null;
        }
        Nodo actual = inicio;
        do {
            if (actual.getNombreProcedimiento().equals(nombreProcedimiento)) {
                return actual;
            }
            actual = actual.getSiguiente();
        } while (actual != inicio);
        return null;
    }
}
