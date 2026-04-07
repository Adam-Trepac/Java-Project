package cz.muni.fi.pb112.project.geometry;

/** Class representing Square rotated by 45 degrees in a 2d space
 * @author Adam Trepáč
 */
public class Square extends GeneralRegularPolygon{

    /** Constructor for a circle around the square
     * @param center - vertex value of a circle
     * @param diameter - value of the diameter
     */
    public Square(Vertex2D center, double diameter){
        super(center, 4, diameter / 2.0);
    }

    /** Constructor that creates an obj based on another circular obj properties
     * @param circular - object implemented by Circular
     */
    public Square(Circular circular){
        this(circular.getCenter(), circular.getRadius() * 2.0);
    }

    /** Prints out String with vertex values of a square
     * @return string with vertex values
     */
    @Override
    public String toString(){
        return "Square: vertices=" + this.getVertex(0) + " "
                + this.getVertex(1) + " "
                + this.getVertex(2) + " "
                + this.getVertex(3);
    }
}
