package cz.muni.fi.pb112.project.geometry;

/** Enum representing standard colors for geometric shapes
 * @author Adam Trepáč
 */
public enum Color {
    WHITE, YELLOW, ORANGE, RED, GREEN, BLUE, BLACK;

    /** Method for getting the string representation of the color
     * @return string representation of the color in lowercase letters
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
