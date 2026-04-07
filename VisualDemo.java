package cz.muni.fi.pb112.project;

import cz.muni.fi.pb112.project.geometry.*;
import cz.muni.fi.pb112.project.geometry.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

/**
 * A graphical demonstration bridging the geometric logic with Java Swing.
 */
public class VisualDemo extends JPanel {

    private final Paper paper;
    private Snowman snowman;
    private final int FLOOR_Y = 600; 

    public VisualDemo() {
        this.paper = new Paper();
        setupScene();
    }

    private void setupScene() {
        Vertex2D v1 = new Vertex2D(50, 100);
        Vertex2D v2 = new Vertex2D(250, 100);
        Vertex2D v3 = new Vertex2D(150, 273); 
        Triangle fractalTriangle = new Triangle(v1, v2, v3, 3); 
        
        paper.changeColor(Color.GREEN);
        drawTriangleRecursive(fractalTriangle);
        
        // Green House Base
        Vertex2D[] houseBase = {
            new Vertex2D(300, 100), new Vertex2D(500, 100), 
            new Vertex2D(500, 300), new Vertex2D(300, 300)
        };
        paper.changeColor(Color.GREEN);
        paper.drawPolygon(new ArrayPolygon(houseBase));

        // Black Roof
        Vertex2D r1 = new Vertex2D(300, 300);
        Vertex2D r2 = new Vertex2D(500, 300);
        Vertex2D r3 = new Vertex2D(400, 400);
        paper.changeColor(Color.BLACK);
        paper.drawPolygon(new Triangle(r1, r2, r3));

        // Red Windows
        paper.changeColor(Color.RED);
        Vertex2D[] win1 = {
            new Vertex2D(330, 200), new Vertex2D(380, 200), 
            new Vertex2D(380, 250), new Vertex2D(330, 250)
        };
        paper.drawPolygon(new ArrayPolygon(win1));
        
        Vertex2D[] win2 = {
            new Vertex2D(420, 200), new Vertex2D(470, 200), 
            new Vertex2D(470, 250), new Vertex2D(420, 250)
        };
        paper.drawPolygon(new ArrayPolygon(win2));

        // Blue Door
        paper.changeColor(Color.BLUE);
        Vertex2D[] door = {
            new Vertex2D(375, 100), new Vertex2D(425, 100), 
            new Vertex2D(425, 180), new Vertex2D(375, 180)
        };
        paper.drawPolygon(new ArrayPolygon(door));

        // --- 3. Create the Snowman ---
        Circle baseBall = new Circle(new Vertex2D(650, 170), 70); 
        this.snowman = new Snowman(baseBall, 0.7);
        RegularPolygon[] balls = snowman.getBalls(); 

        // Accessories
        RegularPolygon headBall = balls[balls.length - 1];
        Vertex2D headCenter = headBall.getCenter();
        double headR = headBall.getRadius();

        // Red Helmet
        paper.changeColor(Color.RED); 
        Vertex2D h1 = new Vertex2D(headCenter.getX() - headR * 0.6, headCenter.getY() + headR * 0.9);
        Vertex2D h2 = new Vertex2D(headCenter.getX() + headR * 0.6, headCenter.getY() + headR * 0.9);
        Vertex2D h3 = new Vertex2D(headCenter.getX() + headR * 0.4, headCenter.getY() + headR * 1.6);
        Vertex2D h4 = new Vertex2D(headCenter.getX() - headR * 0.4, headCenter.getY() + headR * 1.6);
        paper.drawPolygon(new CollectionPolygon(List.of(h1, h2, h3, h4)));

        // Orange Carrot Nose
        paper.changeColor(Color.ORANGE);
        Vertex2D n1 = new Vertex2D(headCenter.getX(), headCenter.getY() + headR * 0.2);
        Vertex2D n2 = new Vertex2D(headCenter.getX() + headR * 0.3, headCenter.getY());
        Vertex2D n3 = new Vertex2D(headCenter.getX() + headR * 0.8, headCenter.getY() + headR * 0.1);
        paper.drawPolygon(new CollectionPolygon(List.of(n1, n2, n3)));
    }

    private void drawTriangleRecursive(Triangle t) {
        if (!t.isDivided()) {
            paper.drawPolygon(t);
        } else {
            drawTriangleRecursive(t.getSubTriangle(0));
            drawTriangleRecursive(t.getSubTriangle(1));
            drawTriangleRecursive(t.getSubTriangle(2));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- Render Paper Polygons (House, Triangle, Accessories) ---
        for (ColoredPolygon coloredPoly : paper.getAllDrawnPolygons()) {
            Polygon basePoly = coloredPoly.getPolygon();
            g2d.setColor(mapColor(coloredPoly.getColor()));
            
            int numVertices = basePoly.getNumVertices();
            int[] xPoints = new int[numVertices];
            int[] yPoints = new int[numVertices];
            
            for (int i = 0; i < numVertices; i++) {
                Vertex2D v = basePoly.getVertex(i);
                xPoints[i] = (int) v.getX();
                yPoints[i] = (int) (FLOOR_Y - v.getY()); // Flip Y for computer graphics
            }
            g2d.drawPolygon(xPoints, yPoints, numVertices);
        }

        // --- Render Snowman ---
        g2d.setColor(java.awt.Color.BLUE); 
        for (RegularPolygon ball : snowman.getBalls()) {
            Vertex2D mathCenter = ball.getCenter();
            double screenCenterY = FLOOR_Y - mathCenter.getY(); 
            
            int x = (int) (mathCenter.getX() - ball.getRadius());
            int y = (int) (screenCenterY - ball.getRadius());
            int diameter = (int) (ball.getRadius() * 2);
            g2d.drawOval(x, y, diameter, diameter);
        }
    }

    private java.awt.Color mapColor(cz.muni.fi.pb112.project.geometry.Color myColor) {
        if (myColor == null) return java.awt.Color.BLACK;
        switch (myColor) {
            case RED: return java.awt.Color.RED;
            case GREEN: return java.awt.Color.GREEN;
            case BLUE: return java.awt.Color.BLUE;
            case YELLOW: return java.awt.Color.YELLOW;
            case ORANGE: return java.awt.Color.ORANGE;
            case WHITE: return java.awt.Color.WHITE;
            default: return java.awt.Color.BLACK;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Geometry Visualizer Showcase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 700);
        frame.add(new VisualDemo());
        frame.setVisible(true);
    }
}
