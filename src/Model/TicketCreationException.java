/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Model;

/**
 *
 * @author molka
 */
public class TicketCreationException extends Exception{

    /**
     * Creates a new instance of <code>TicketCreationException</code> without
     * detail message.
     */
    public TicketCreationException() {
    }

    /**
     * Constructs an instance of <code>TicketCreationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TicketCreationException(String msg) {
        super(msg);
    }
}
