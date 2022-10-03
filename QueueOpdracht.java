import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QueueOpdracht implements Queue {
    List<Integer> Queue = new ArrayList<>();

    public void add(int value) {
        Queue.add(value);
    }

    public int remove() {
        int removed = Queue.get(0);
        Queue.remove(0);
        return removed;
    }

    public int peek() {
        return Queue.get(0);
    }

    public boolean isEmpty() {
        return Queue.size() == 0;
    }

    public int size() {
        return Queue.size();
    }

    public void print() {
        System.out.println(Queue);
    }

    public void clear() {
        if (Queue.size() > 0) {
            Queue.subList(0, Queue.size()).clear();
        }
    }

    public void clear(int n) {
        if (n > 0) {
            Queue.subList(0, n).clear();
        }
    }

    public void printReverse() {
        System.out.print("[");
        for (int i = Queue.size() - 1; i >= 0; i--) {
            System.out.print(Queue.get(i) + ", ");
        }
        System.out.println("\b\b]");
    }

    public void jumpTheQueue(int n, int value) {
        Queue.add(n, value);
    }

    @Override
    public String toString() {
        return "" + Queue;
    }

    @Override
    public boolean equals(Queue q) {
        if (this == q) return true;
        if (!(q instanceof QueueOpdracht that)) return false;
        return Objects.equals(Queue, that.Queue);
    }

    public int indexOf(int value) {
        return Queue.indexOf(value);
    }

    public int lastIndexOf(int value) {
        return Queue.lastIndexOf(value);
    }
}

interface Queue {
    // voeg een item toe aan de FIFO queue
    void add(int value);

    // verwijder een item uit de FIFO queue
    int remove();

    // geef het eerste item in de FIFO queue terug, maar haal het er niet uit
    int peek();

    // geef aan of de FIFO queue leeg is
    boolean isEmpty();

    // geef de lengte van de FIFO queue terug
    int size();

    // Print de inhoud van de FIFO queue
    void print();

    // verwijder alle items uit de FIFO queue
    void clear();

    // verwijder de eerste n items uit de FIFO queue
    void clear(int n);

    // print de inhoud van de FIFO queue in omgekeerde volgorde
    void printReverse();

    // plaats een element op een bepaalde positie in de FIFO queue
    void jumpTheQueue(int n, int value);

    // Zet de FIFO queue om naar een String
    String toString();

    // Kijk of de FIFO queue gelijk is aan een andere FIFO queue
    boolean equals(Queue q);

    // Bepaal de index van een bepaalde waarde in de FIFO queue
    int indexOf(int value);

    // bepaal de laatste index van een bepaalde waarde in de FIFO queue
    int lastIndexOf(int value);
}

