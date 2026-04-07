package cz.muni.fi.pb112.project.geometry;

/** Class representing a regular octagon in a 2d space
 * @author Adam Trepáč
 */
public class RegularOctagon extends GeneralRegularPolygon {

    /** Constructor for creating a regular octagon
     * The number of edges is strictly set to 8.
     * @param center - the center vertex of the octagon
     * @param radius - the radius of the circumscribed circle
     */
    public RegularOctagon(Vertex2D center, double radius) {
        super(center, 8, radius);
    }
}