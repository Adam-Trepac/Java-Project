package cz.muni.fi.pb112.project.geometry;

/** Class representing a Snowman in 2d space
 * @author Adam Trepáč
 */
public class Snowman {

    public static final int COUNT = 3;
    private static final double DEFAULT_REDUCTION_FACTOR = 0.8;

    private final RegularPolygon[] balls = new RegularPolygon[COUNT];

    /** Constructor for creation of Snowman
     * @param regularPolygon -  obj implemented with RegularPolygon
     * @param redFact - reduction factor of the balls in Snowman
     */
    public Snowman(RegularPolygon regularPolygon, double redFact) {
        double validFactor = validateFactor(redFact);
        buildSnowman(regularPolygon, validFactor);
    }

    /** Private Method for validating the reduction factor
     * @param factor - factor for validating
     * @return - factor if valid, if not returns deafult_reduction_factor
     */
    private double validateFactor(double factor) {
        if (factor <= 0 || factor > 1) {
            return DEFAULT_REDUCTION_FACTOR;
        }
        return factor;
    }

    /** Private Method for creation of balls of the snowman with the reducing factor
     * @param baseBall - the base ball of a snowman
     * @param factor - factor with which the balls will be reduced
     */
    private void buildSnowman(RegularPolygon baseBall, double factor) {
        balls[0] = baseBall;
        for (int i = 1; i < COUNT; i++) {
            balls[i] = createNextBall(balls[i - 1], factor);
        }
    }

    /** Private Method for creation of the following ball
     * @param previousBall - the ball that was previously created
     * @param factor - factor for reducing the balls
     * @return - newly created ball of the snowman
     */
    private RegularPolygon createNextBall(RegularPolygon previousBall, double factor) {
        double newRadius = previousBall.getRadius() * factor;
        double newY = previousBall.getCenter().getY() + previousBall.getRadius() + newRadius;

        Vertex2D newCenter = new Vertex2D(previousBall.getCenter().getX(), newY);

        return new GeneralRegularPolygon(newCenter, previousBall.getNumEdges(), newRadius);
    }

    /** Method for getting the array of balls
     * @return the balls array
     */
    public RegularPolygon[] getBalls() {
        return balls;
    }
}