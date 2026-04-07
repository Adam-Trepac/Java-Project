package cz.muni.fi.pb112.project.geometry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A class representing a paper canvas on which colored polygons can be drawn.
 * It implements the {@link Drawable} interface. The drawing simulation works by
 * storing polygons as {@link ColoredPolygon} objects in a set to prevent duplicates.
 *
 * @author Adam Trepáč
 */
public class Paper implements Drawable {

    private Set<ColoredPolygon> polygons;
    private Color color;

    /**
     * Constructs a new empty {@code Paper}.
     * The internal collection for polygons is initialized and the default
     * drawing color is set to {@link Color#BLACK}.
     */
    public Paper() {
        this.polygons = new HashSet<>();
        this.color = Color.BLACK;
    }

    /**
     * Constructs a new {@code Paper} and copies all drawn polygons from
     * the provided {@link Drawable} object. The default drawing color
     * is set to {@link Color#BLACK}.
     *
     * @param drawable the {@link Drawable} object to copy polygons from;
     * if {@code null}, no polygons are copied
     */
    public Paper(Drawable drawable) {
        this();

        if (drawable != null) {
            this.polygons.addAll(drawable.getAllDrawnPolygons());
        }
    }

    /**
     * Changes the current pencil color for future drawn polygons.
     *
     * @param color the new color to be used
     */
    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * "Draws" (stores) a polygon on the paper using the current pencil color.
     * If the current color is {@link Color#WHITE}, the polygon is ignored
     * and not added to the paper. If an identical polygon with the same color
     * already exists on the paper, it is not duplicated.
     *
     * @param polygon the polygon to be drawn
     */
    @Override
    public void drawPolygon(Polygon polygon) {
        if (this.color != Color.WHITE) {
            this.polygons.add(new ColoredPolygon(polygon, this.color));
        }
    }

    /**
     * Erases (removes) a specific colored polygon from the paper.
     *
     * @param polygon the colored polygon to be removed
     */
    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        this.polygons.remove(polygon);
    }

    /**
     * Erases all drawn polygons from the paper.
     * The current pencil color remains unchanged.
     */
    @Override
    public void eraseAll() {
        this.polygons.clear();
    }

    /**
     * Returns a collection of all currently drawn polygons on the paper.
     * To maintain encapsulation, the returned collection is unmodifiable.
     *
     * @return an unmodifiable {@link Collection} of all drawn {@link ColoredPolygon} objects
     */
    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableSet(this.polygons);
    }

    /**
     * Calculates the total number of unique vertices across all polygons
     * currently drawn on the paper. Overlapping vertices with the exact
     * same coordinates are counted only once.
     *
     * @return the amount of distinct vertices
     */
    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> allUniqueVertices = new HashSet<>();

        for (ColoredPolygon polygon : this.polygons) {
            int verticesCount = polygon.getNumVertices();

            for (int i = 0; i < verticesCount; i++) {
                allUniqueVertices.add(polygon.getVertex(i));
            }
        }
        return allUniqueVertices.size();
    }
}