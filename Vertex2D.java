package cz.muni.fi.pb112.project.geometry;
import java.util.Objects;
/** @author Adam Trepáč
 */
public class Vertex2D {
    private final double x;
    private final double y;

    /** Constructor for the class Vertex2D
     * @param x horizontal value of the vertex
     * @param y vertical value of the vertex
     */
    public Vertex2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }

    /** Returns formated version of coordinates for a vector
    * @return the coordinates for a given vector
     */
    @Override
    public String toString(){
        return "[" + x + ", " + y + "]";
    }

    /** Returns vertex that is in the middle of this.vertex and given vertex
     * @param vertex
     * @return middle of two vertexes
     */
    public Vertex2D createMiddle(Vertex2D vertex){
        Vertex2D middle = new Vertex2D((vertex.getX() + this.x)/2,(vertex.getY() + this.y)/2);
        return middle;
    }

    /** Method for getting the distance of two vertexes
     * @param vertex wanted vertex for comparison
     * @return the distance between two vertexes
     */
    public double distance(Vertex2D vertex){
        if (vertex == null){
            return -1.0;
        }
        return Math.sqrt(Math.pow(this.x - vertex.x, 2) + Math.pow(this.y - vertex.y, 2));
    }

    /** Compares of two vertices are equal (if they have the same coordinates)
     * @param obj - object for comparison
     * @return true if the coordinates are equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vertex2D vertex = (Vertex2D) obj;

        return Double.compare(vertex.getX(), this.getX()) == 0 &&
                Double.compare(vertex.getY(), this.getY()) == 0;
    }

    /**
     * if two objects are equal they have to have the same hashCode.
     * @return number of hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
