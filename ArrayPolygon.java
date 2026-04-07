package cz.muni.fi.pb112.project.geometry;

import java.util.Arrays;

/**
 * Immutable class representing a polygon backed by an array of vertices.
 *
 * @author Adam Trepáč
 */
public class ArrayPolygon extends SimplePolygon {

    private final Vertex2D[] vertices;

    /**
     * Constructs a new ArrayPolygon from the provided array of vertices.
     * This constructor creates a shallow copy of the input array to maintain
     * encapsulation and ensure the polygon's immutability. A valid polygon
     * must consist of at least 3 vertices.
     *
     * @param vertices an array of Vertex2D objects defining the polygon's vertices
     * @throws IllegalArgumentException if the vertices array is null,
     * contains fewer than 3 elements, or if any individual
     * vertex within the array is null
     */
    public ArrayPolygon(Vertex2D[] vertices) {
        super(vertices);
        if (vertices.length < 3) {
            throw new IllegalArgumentException("ArrayPolygon vyzaduje aspon 3 vrcholy.");
        }
        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }

    /** * Returns the number of vertices in the given polygon.
     *
     * @return the length of the array, which represents the number of vertices
     */
    @Override
    public int getNumVertices() {
        return vertices.length;
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
            throw new IllegalArgumentException("Index je zaporny.");
        } else {
            index = index % this.getNumVertices();
        }

        return vertices[index];
    }

    /** * Compares this polygon to the specified object.
     *
     * @param obj object for comparison
     * @return true if they are of the same class and have the same vertices at the same indices
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ArrayPolygon that = (ArrayPolygon) obj;

        return Arrays.equals(this.vertices, that.vertices);
    }

    /** * Returns the hash code value for the polygon's vertices.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.vertices);
    }
}