package utilities;

/**
 *
 * @author Lexynux
 * @param <T> Tipo de objetos que la lista debera soportar. Integer, Double,
 */
public class LmxList<T> {
    
    // Reference to the firs element of the list
    private Node head;
    // Reference to the last element of the list
    private Node tail;
    // Couter for the length of the list
    private int length;

    public LmxList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public LmxList(Node object) {
        this.head = new Node(object);
        this.tail = new Node(object);
        this.length = 1;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * @return Retorna la longitud guardada en la PROPIEDAD length
     */
    public int getLength() {
        return length;
    }

    /**
     * To add a new element to the list, it must have a value, gotten as a
     * parameter and its nextElement property must to point to null
     *
     * @param object Ingrese un objeto del mismo tipo que haya declarado la
     * Lista.
     */
    public void addLast(T object) {
        Node newElement = new Node(object);
        newElement.setNextElement(null);

        if (this.isEmpty()) {
            // if the list is empty, HEAD & TAIL must to point to the only element (nuevo)
            this.setHead(newElement);
            this.setTail(newElement);
            this.length = 1;
        } else {
            /* if the list is NOT empty, the nextElement property of the TAIL node must to
             point to the new NODE (nuevo) in order to not to lose the list. After that the
             TAIL (the last one) now become to be the new NODE added (nuevo)
             */
            this.getTail().setNextElement(newElement);
            this.setTail(newElement);
            this.length++;
        }
    }

    public void addFirst(T object) {
        Node newElement = new Node(object);

        if (this.isEmpty()) {
            // if the list is empty, HEAD & TAIL must to point to the only element (nuevo)
            this.setHead(newElement);
            this.setTail(newElement);
            this.length = 1;
        } else {
            /* if the list is NOT empty, the nextElement property of the newElement node must to
             point to the HEAD NODE in order to not to lose the list. After that the
             newElement now become to be the new HEAD NODE
             */
            newElement.setNextElement(this.getHead());
            this.setHead(newElement);
            this.length++;
        }
    }

    /**
     * Imprime todos los elementos de la lista con un ciclo 'while' The print
     * method must start from the first one NODE so we make the temp NODE to
     * point to the HEAD node of the entery list
     */
    public void printElements() {
        /* We need to go over the list until the nextElement property of the actual node points
         to null, that means that the list has endded
         */
        for (Node aux = this.getHead(); aux != null; aux = aux.getNextElement()) {
            // Print the value on the node
            System.out.print("{" + aux.getObject().toString() + "}");
        }
    }

    public void clearList() {
        this.setHead(null);
        this.setTail(null);
        this.length = 0;
    }

    public boolean isEmpty() {
        return (this.getHead() == null && this.getTail() == null);
    }

    public void deleteFirst() {
        if (!this.isEmpty()) {
            /* If the list is NOT empty, when it has one element it is goig to be empty,
             so HEAD and TAIL must point to null. When it has more than one element the
             nextElement property of the HEAD become to be the HEAD, so HEAD node must
             to point to its own NextElement in order to not to lose the list.
             */
            if (this.getLength() == 1) {
                this.clearList();
            } else {
                this.setHead(this.getHead().getNextElement());
                this.length--;
            }
        }
    }

    /**
     * Deletes the las element from the list
     */
    public void deleteLast() {
        if (!this.isEmpty()) {
            /* If the list is NOT empty, when it has JUST ONE element it is goig to be empty,
             so execute clearList() method. When it has more than one element we
             need two pointers (previous, actual) one forward to the other and when the
             ACTUAL is the last it means that's the one we have to delete, so the PREVIOUS
             must to point to NULL and the TAIL must to point to the PREVIOUS so it become to be the last.
             */
            if (this.getLength() == 1) {
                this.clearList();
            } else {
                Node previous = null;
                Node actual = this.getHead();

                while (actual.getNextElement() != null) {
                    previous = actual;
                    actual = actual.getNextElement();
                }

                previous.setNextElement(null);
                this.setTail(previous);
                this.length--;
            }
        }
    }

    /**
     * Delete an element in the given position starting indexes from 1
     * @param position Index of the element to delete
     */
    public void deleteByPosition(int position) {
        if (!this.isEmpty()) {
            /* If the list is NOT empty, when the element to be deleted is the
             FIRST or the LAST, just execute deleteFirst() or deleteLast() method.
             OtherwiseWhen we need two pointers (previous, actual), one forward to the
             other, and one counter to control when to stop during the travel all along
             the list. When the counter has the same value as the position, ACTUAL is
             the one to be deleted, so the PREVIOUS must to point to the ACTUAL.nextElement()
             pointer and the ACTUAL.nextElement() set to null.
             */
            if (position > 0 && position <= this.getLength()) {
                if (position == 1) {
                    this.deleteFirst();
                } else if (position == this.getLength()) {
                    this.deleteLast();
                } else {
                    Node previous = this.getHead();
                    Node actual = this.getHead();

                    for (int k = 1; k < position; k++) {
                        previous = actual;
                        actual = actual.getNextElement();
                    }

                    previous.setNextElement(actual.getNextElement());
                    actual.setNextElement(null);
                    this.length--;
                }
            } else {
                System.out.println("Error: No existe la posicion: " + position);
            }
        }
    }
    
}
