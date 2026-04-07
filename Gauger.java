package cz.muni.fi.pb112.project.utils;

import cz.muni.fi.pb112.project.geometry.Measurable;
import cz.muni.fi.pb112.project.geometry.Triangle;

/** Class for printing out the Measurements of objects
 * @author Adam Trepáč
 */
public class Gauger {

    /** Overflow method for printing out the width and height of a object
     * that is implemented by Measurable
     * @param measurable - obj implemented by measurable
     */
    public static void printMeasurement(Measurable measurable) {
        System.out.println("Width: " + measurable.getWidth());
        System.out.println("Height: " + measurable.getHeight());
    }

    /** Overflow method for printing out the width and height of a triangle
     * @param triangle triangle obj
     */
    public static void printMeasurement(Triangle triangle) {
        System.out.println(triangle.toString());
        printMeasurement((Measurable) triangle);
    }
}
