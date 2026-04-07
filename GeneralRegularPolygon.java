package cz.muni.fi.pb112.project.geometry;

/** Class representing a general regular polygon in a 2d space
 * @author Adam Trepáč
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored {

    private final Vertex2D center;
    private final int numEdges;
    private final double radius;
    private Color color;

    /** Constructor for creating a regular polygon
     * @param center - the center vertex of the polygon
     * @param numEdges - the number of edges of the polygon
     * @param radius - the radius of the circumscribed circle
     */
    public GeneralRegularPolygon(Vertex2D center, int numEdges, double radius) {
        this.center = center;
        this.numEdges = numEdges;
        this.radius = radius;
        this.color = Color.BLACK;
    }

    /** Gets the color of the polygon
     * @return the current color
     */
    @Override
    public Color getColor() {
        return color;
    }

    /** Sets a new color for the polygon
     * @param color - the new color to be set
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /** Gets the center vertex of the polygon
     * @return the center vertex
     */
    public Vertex2D getCenter() {
        return center;
    }

    /** Gets the radius of the circumscribed circle
     * @return the radius value
     */
    public double getRadius() {
        return radius;
    }

    /** Gets the number of edges of the polygon
     * @return the number of edges
     */
    public int getNumEdges() {
        return numEdges;
    }

    /** Gets the approximate width of the polygon
     * @return the width value (diameter of circumscribed circle)
     */
    public double getWidth() {
        return 2 * radius;
    }

    /** Gets the approximate height of the polygon
     * @return the height value (diameter of circumscribed circle)
     */
    public double getHeight() {
        return 2 * radius;
    }

    /** Calculates the length of a single edge of the polygon
     * @return the edge length
     */
    public double getEdgeLength() {
        return 2 * radius * Math.sin(Math.PI / numEdges);
    }

    /** Calculates the coordinates of the i-th vertex
     * Supports out-of-bounds indices by wrapping around (modulo)
     * @param index - the desired vertex index
     * @return the calculated vertex coordinates
     */
    public Vertex2D getVertex(int index) {

        if (index < 0) {
            index = (index % numEdges + numEdges) % numEdges;
        } else {
            index = index % numEdges;
        }

        double x = getCenter().getX() - getRadius() * Math.cos(2 * Math.PI * index / numEdges);
        double y = getCenter().getY() - getRadius() * Math.sin(2 * Math.PI * index / numEdges);

        return new Vertex2D(x, y);
    }

    /** Method for getting the string representation of the regular polygon
     * @return string representation containing number of edges, center, radius, and color
     */
    @Override
    public String toString() {
        return numEdges + "-gon: center=" + center.toString() +
                ", radius=" + radius +
                ", color=" + color.toString();
    }
}