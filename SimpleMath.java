package cz.muni.fi.pb112.project.utils;

import cz.muni.fi.pb112.project.geometry.Polygon;

/** Class for min and max methods
 * @author Adam Trepáč
 */
public class SimpleMath {

    /** Calculates the min value of X in triangle vertexes
     * @param polygon polygon obj
     * @return min x value of vertexes
     */
    public static double minX(Polygon polygon){
        double minimalX = polygon.getVertex(0).getX();

        for (int i = 1; i < polygon.getNumVertices(); i++){
            if (minimalX > polygon.getVertex(i).getX()) {
                minimalX = polygon.getVertex(i).getX();
            }
        }
        return minimalX;
    }

    /** Calculates the min value of Y in triangle vertexes
     * @param polygon polygon obj
     * @return min y value of vertexes
     */
    public static double minY(Polygon polygon){
        double minimalY = polygon.getVertex(0).getY();

        for (int i = 1; i < polygon.getNumVertices(); i++){
            if (minimalY > polygon.getVertex(i).getY()) {
                minimalY = polygon.getVertex(i).getY();
            }
        }
        return minimalY;
    }

    /** Calculates the max value of X in triangle vertexes
     * @param polygon polygon obj
     * @return max x value of vertexes
     */
    public static double maxX(Polygon polygon){
        double maximalX = polygon.getVertex(0).getX();

        for (int i = 1; i < polygon.getNumVertices(); i++){
            if (maximalX < polygon.getVertex(i).getX()) {
                maximalX = polygon.getVertex(i).getX();
            }
        }
        return maximalX;
    }

    /** Calculates the max value of Y in triangle vertexes
     * @param polygon polygon obj
     * @return max y value of vertexes
     */
    public static double maxY(Polygon polygon) {
        double maximalY = polygon.getVertex(0).getY();

        for (int i = 1; i < polygon.getNumVertices(); i++) {
            if (maximalY < polygon.getVertex(i).getY()) {
                maximalY = polygon.getVertex(i).getY();
            }
        }
        return maximalY;
    }
}
