package cz.muni.fi.pb112.project.geometry;

/** Interface for objects that have a color property
 * @author Adam Trepáč
 */
public interface Colored {

    /** Gets the color of the object
     * @return the current color
     */
    Color getColor();

    /** Sets a new color for the object
     * @param color - the new color to be set
     */
    void setColor(Color color);
}