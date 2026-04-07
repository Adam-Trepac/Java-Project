package cz.muni.fi.pb112.project.geometry;

/** Class representing triangle in 2d space
 * @author Adam Trepáč
 */
public class Triangle extends ArrayPolygon {
    private final Triangle[] subTriangles = new Triangle[3];

    private static final double DELTA = 0.001;

    /** Constructor for triangle class
     * @param v1 - vertex num1
     * @param v2 - vertex num2
     * @param v3 - vertex num3
     */
    public Triangle(Vertex2D v1, Vertex2D v2,Vertex2D v3) {
        super(new Vertex2D[]{v1, v2, v3});
    }

    /** Constructor for creation of triangle with subdivision
     * @param v1 - vertex num1
     * @param v2 - vertex num2
     * @param v3 - vertex num3
     * @param depth - int value of the subdivision depth
     */
    public Triangle(Vertex2D v1, Vertex2D v2,Vertex2D v3, int depth) {
        this(v1, v2, v3);
        this.divide(depth);
    }

    /** Print out the vertices
     * @return - returns string with the vertices
     */
    @Override
    public String toString(){
        return "Triangle: vertices=" + getVertex(0).toString() + " "
                + getVertex(1).toString() + " " + getVertex(2).toString();
    }

    /** Checks if triangle has been already divided
     * @return true or false if triangle has been divided
     */
    public boolean isDivided() {
        return subTriangles[0] != null;
    }

    /** Divides the triangle with the createMiddle method into three smaller triangles
     * @return True if method sucessfully divided the triangle and False if it was already divided
     */
    public boolean divide() {
        if (isDivided()) {
            return false;
        }

        Vertex2D m01 = getVertex(0).createMiddle(getVertex(1));
        Vertex2D m12 = getVertex(1).createMiddle(getVertex(2));
        Vertex2D m02 = getVertex(0).createMiddle(getVertex(2));

        subTriangles[0] = new Triangle(getVertex(0), m01, m02);
        subTriangles[1] = new Triangle(getVertex(1), m01, m12);
        subTriangles[2] = new Triangle(getVertex(2), m02, m12);

        return true;
    }

    /** Gets the vertices of the sub triangle
     * @param index of desired sub triangle
     * @return vertices of chosen sub triangle
     */
    public Triangle getSubTriangle(int index) {
        if (index < 0 || index > 2 || !isDivided()) {
            return null;
        }
        return subTriangles[index];
    }

    /** Method for checking if the triangle is Equilateral
     * @return True if the triangle is Equilateral and False if not
     */
    public boolean isEquilateral(){
        double d01 = getVertex(0).distance(getVertex(1));
        double d12 = getVertex(1).distance(getVertex(2));
        double d20 = getVertex(2).distance(getVertex(0));

        return areDoublesSimilar(d01, d12) && areDoublesSimilar(d12, d20);
    }

    /** Method for checking if two distances are similar
     * with the difference being less than DELTA(0.001)
     * @param d1 - distance num1
     * @param d2 - distance num2
     * @return True or False based on the difference being
     * larger or smaller than DELTA
     */
    public boolean areDoublesSimilar(double d1, double d2){
        return Math.abs(d1 - d2) < DELTA;
    }

    /** Method for dividing the triangle
     * @param depth - num of subdivisons wanted in the triangle
     */
    void divide(int depth){
        if (depth <= 0 ){
            return;
        }
        this.divide();

        for (int i = 0; i < subTriangles.length; i++){
            subTriangles[i].divide(depth - 1);
        }
    }
}

