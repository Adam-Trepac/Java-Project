package cz.muni.fi.pb112.project.geometry;

import static cz.muni.fi.pb112.project.utils.SimpleMath.minX;
import static cz.muni.fi.pb112.project.utils.SimpleMath.maxX;
import static cz.muni.fi.pb112.project.utils.SimpleMath.minY;
import static cz.muni.fi.pb112.project.utils.SimpleMath.maxY;

/**
 * Abstract class representing a simple polygon.
 * Provides default calculations for width and height based on vertices.
 *
 * @author Adam Trepáč
 */
public abstract class SimplePolygon implements Polygon {

    /**
     * Constructs a new SimplePolygon and validates the input array of vertices.
     *
     * @param vertices an array of Vertex2D objects defining the polygon
     * @throws IllegalArgumentException if the array is null, is empty,
     * or if any individual vertex is null
     */
    public SimplePolygon(Vertex2D[] vertices) {
        if ((vertices == null) || (vertices.length == 0)) {
            throw new IllegalArgumentException("Pole vrcholov nesmie byt null a nesmie byt prazdne.");
        }
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                throw new IllegalArgumentException("Vrchol na indexe " + i + " je null.");
            }
        }
    }

    /**
     * Gets the number of vertices of the polygon.
     *
     * @return the number of vertices
     */
    public abstract int getNumVertices();

    /**
     * Gets the vertex at the given index.
     *
     * @param index the index of the desired vertex
     * @return the vertex at the specified index
     */
    public abstract Vertex2D getVertex(int index);

    /**
     * Calculates the height of the polygon using min and max Y coordinates.
     *
     * @return the height of the polygon
     */
    @Override
    public double getHeight() {
        return maxY(this) - minY(this);
    }

    /**
     * Calculates the width of the polygon using min and max X coordinates.
     *
     * @return the width of the polygon
     */
    @Override
    public double getWidth() {
        return maxX(this) - minX(this);
    }

    /**
     * Returns a string representation of the polygon containing all its vertices.
     *
     * @return string representation containing all vertices
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Polygon: vertices =");

        for (int i = 0; i < this.getNumVertices(); i++) {
            result.append(" ").append(this.getVertex(i));
        }

        return result.toString();
    }
}