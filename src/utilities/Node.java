package utilities;

/**
 *
 * @author Lexynux
 * @param <T> Este atributo sera reemplazado por cualquier objeto en tiempo de compilacion.
 */
public class Node<T> {
    
    private final T object;
    private Node nextElement;
    
    public Node(T object) {
        this.object = object;
        this.nextElement = null;
    }
    
    public T getObject() {
        return object;
    }

    public Node getNextElement() {
        return nextElement;
    }

    public void setNextElement(Node nextElement) {
        this.nextElement = nextElement;
    }
    
}
