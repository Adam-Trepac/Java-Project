package cz.muni.fi.pb112.project.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Adam Trepáč
 * This class represents a polygon whose vertices are stored in a {@link List} collection.
 * It extends {@link SimplePolygon} and provides functionality for manipulating
 * a collection-based polygon, including removing its leftmost vertices.
 */
public class CollectionPolygon extends SimplePolygon {

    private final List<Vertex2D> vertices;

    /**
     * Constructs a new {@code CollectionPolygon} from an array of vertices.
     *
     * @param vertices an array of {@link Vertex2D} objects representing the polygon's vertices
     */
    public CollectionPolygon(Vertex2D[] vertices) {
        super(vertices);
        this.vertices = new ArrayList<>(Arrays.asList(vertices));
    }

    /**
     * Constructs a new {@code CollectionPolygon} from a list of vertices.
     *
     * @param vertices a {@link List} of {@link Vertex2D} objects representing the polygon's vertices
     */
    public CollectionPolygon(List<Vertex2D> vertices) {
        super(vertices == null ? null : vertices.toArray(new Vertex2D[0]));
        this.vertices = new ArrayList<>(vertices);
    }

    /**
     * Returns the number of vertices of the polygon.
     *
     * @return number of vertices
     */
    @Override
    public int getNumVertices() {
        return this.vertices.size();
    }

    /**
     * Returns the vertex at the given index modulo the number of indices.
     *
     * @param index vertex index, must not be a negative number
     * @return vertex at the given index modulo the number of indices
     * @throws IllegalArgumentException if the index is negative
     */
    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Neplatny index vrcholu (nemôže byť záporný).");
        }

        int realIndex = index % this.vertices.size();

        return this.vertices.get(realIndex);
    }

    /**
     * Compares this polygon to the specified object. The result is {@code true} if and only if
     * the argument is not {@code null} and is a {@code CollectionPolygon} object that
     * contains the same vertices in the same order.
     *
     * @param o the object to compare this {@code CollectionPolygon} against
     * @return {@code true} if the given object represents a {@code CollectionPolygon}
     * equivalent to this polygon, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        CollectionPolygon that = (CollectionPolygon) o;
        return Objects.equals(vertices, that.vertices);
    }

    /**
     * Returns a hash code for this {@code CollectionPolygon}.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(vertices);
    }

    /**
     * Creates and returns a new {@code CollectionPolygon} containing all vertices of this polygon
     * except the ones with the minimum X coordinate (the leftmost vertices).
     * If the resulting polygon would be empty (i.e., all vertices were the leftmost ones),
     * this method returns {@code null}.
     *
     * @return a new {@code CollectionPolygon} without the leftmost vertices, or {@code null}
     * if the resulting polygon would be empty
     */
    public CollectionPolygon withoutLeftmostVertices() {
        if (this.vertices.isEmpty()) {
            return null;
        }

        double minX = cz.muni.fi.pb112.project.utils.SimpleMath.minX(this);

        List<Vertex2D> remainingVertices = new ArrayList<>();

        for (Vertex2D v : this.vertices) {
            if (v.getX() > minX) {
                remainingVertices.add(v);
            }
        }

        if (remainingVertices.isEmpty()) {
            return null;
        }

        return new CollectionPolygon(remainingVertices.toArray(new Vertex2D[0]));
    }
}