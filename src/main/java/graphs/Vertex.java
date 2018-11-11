package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by rpurigella on 11/3/18.
 */
public class Vertex {
    int label;
    List<Vertex> neighbors;

    public Vertex() {
    }

    public Vertex(int label) {
        this.label = label;
        neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label == vertex.label &&
                Objects.equals(neighbors, vertex.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, neighbors);
    }
}
