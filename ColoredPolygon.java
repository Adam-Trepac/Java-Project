package cz.muni.fi.pb112.project.geometry;

import java.util.Objects;

/**
 * A decorator class that adds color to an existing {@link Polygon}.
 * This class implements the {@link Polygon} interface by delegating
 * geometric operations to the underlying polygon while maintaining
 * a specific color attribute.
 *
 * @author Adam Trepáč
 */
public class ColoredPolygon implements Polygon {

    private final Polygon polygon;
    private final Color color;

    /**
     * Constructs a new {@code ColoredPolygon} wrapping the specified polygon
     * with the given color.
     *
     * @param polygon the underlying polygon to be colored
     * @param color the color to be applied to the polygon
     * @throws IllegalArgumentException if either the polygon or the color is {@code null}
     */
    public ColoredPolygon(Polygon polygon, Color color) {
        if (polygon == null) {
            throw new IllegalArgumentException("Polygon nesmie byt null.");
        }
        if (color == null) {
            throw new IllegalArgumentException("Farba nesmie byt null.");
        }

        this.polygon = polygon;
        this.color = color;
    }

    /**
     * Gets the underlying uncolored polygon.
     *
     * @return the wrapped {@link Polygon}
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     * Gets the color of this polygon.
     *
     * @return the {@link Color} of the polygon
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the vertex at the given index by delegating the call to the underlying polygon.
     *
     * @param index vertex index
     * @return vertex at the specified index
     */
    @Override
    public Vertex2D getVertex(int index) {
        return this.polygon.getVertex(index);
    }

    /**
     * Returns the number of vertices by delegating the call to the underlying polygon.
     *
     * @return the number of vertices
     */
    @Override
    public int getNumVertices() {
        return this.polygon.getNumVertices();
    }

    /**
     * Returns the width by delegating the call to the underlying polygon.
     *
     * @return the width of the polygon
     */
    @Override
    public double getWidth() {
        return this.polygon.getWidth();
    }

    /**
     * Returns the height by delegating the call to the underlying polygon.
     *
     * @return the height of the polygon
     */
    @Override
    public double getHeight() {
        return this.polygon.getHeight();
    }

    /**
     * Compares this colored polygon to the specified object. The result is {@code true}
     * if and only if the argument is not {@code null}, is a {@code ColoredPolygon} object,
     * and contains an equivalent underlying polygon and the same color.
     *
     * @param o the object to compare this {@code ColoredPolygon} against
     * @return {@code true} if the given object is equivalent to this colored polygon, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ColoredPolygon that = (ColoredPolygon) o;
        return Objects.equals(this.polygon, that.polygon) && this.color == that.color;
    }

    /**
     * Returns a hash code value for this {@code ColoredPolygon} based on its
     * underlying polygon and color.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.polygon, this.color);
    }
}