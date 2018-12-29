package sorting;

/**
 * Emergency room using indexed Max PQ
 * @author rpurigella
 */
public class IndexedMaxPQClient {

    static class Patient implements Comparable<Patient> {
        String name;
        int priority;

        public Patient(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Patient o) {
            return Integer.compare(this.priority, o.priority);
        }

        @Override
        public String toString() {
            return "Patient{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }

    public static void main(String[] args) {
        IndexedMaxPQ<Patient> pq = new IndexedMaxPQ<Patient>(100);
        pq.insert(0, new Patient("A", 7));
        pq.insert(1, new Patient("B", 27));
        pq.insert(2, new Patient("C", 1));
        pq.insert(3, new Patient("D", 77));
        pq.insert(4, new Patient("E", 3));
        pq.insert(5, new Patient("F", 30));

        print(pq);
        pq.delMax();
        print(pq);
        pq.changeKey(2, new Patient("C", 55));
        print(pq);
        pq.delete(0);
        print(pq);
        pq.changeKey(1, new Patient("B", 1));
        print(pq);
    }

    static void print(IndexedMaxPQ<Patient> pq) {
        for (int index : pq) {
            System.out.println(pq.keyOf(index) + " ");
        }
        System.out.println();
    }
}

