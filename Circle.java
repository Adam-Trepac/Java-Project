package cz.muni.fi.pb112.project.geometry;

/** Class representing Circle in a 2d space
 * @author Adam Trepáč
 */
public class Circle extends GeneralRegularPolygon {

    /** Constructor for class Circle with two parameters
     * @param center - center vertex
     * @param radius - radius value
     */
    public Circle(Vertex2D center, double radius) {
        super(center, Integer.MAX_VALUE, radius);
        this.setColor(Color.RED);
    }

    /** Constructor for new Circle with Center 0, 0 and radius 1
     */
    public Circle() {
        this(new Vertex2D(0.0, 0.0), 1.0);
    }

    /** Calculates the edge length of the circle
     * Overrides the default calculation since a circle has no straight edges.
     * @return 0.0
     */
    @Override
    public double getEdgeLength() {
        return 0.0;
    }

    /** Method for getting the string representation of the circle
     * @return string representation containing center and radius
     */
    @Override
    public String toString() {
        return "Circle: center=" + getCenter() + ", radius=" + getRadius();
    }
}