/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Model;

/**
 *
 * @author molka
 */
public class UtilisateurCreationException extends Exception{

    /**
     * Creates a new instance of <code>UtilisateurCreationException</code>
     * without detail message.
     */
    public UtilisateurCreationException() {
    }

    /**
     * Constructs an instance of <code>UtilisateurCreationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UtilisateurCreationException(String msg) {
        super(msg);
    }
}
