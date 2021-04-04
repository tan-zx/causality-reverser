import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Font;
import java.awt.event.KeyEvent;

public class CausalityReverser {
    private static final double RADIUS = 0.3;
    private static final double CIRCLE_X = 0.5;
    private static final double CIRCLE_Y = 0.5;
    private static final double TEXT_X = 0.5;
    private static final double TEXT_Y = 0.1;
    private static final int BEFORE_PAUSE = 300; // in milliseconds
    private static final int AFTER_PAUSE = 200; // in milliseconds
    private static final int ODDS = 35; // in percentage %
    private static final int SAFE_NUM = 10;


    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        drawInitCircle();
        Font font = new Font("Monospaced", Font.BOLD, 36);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(TEXT_X, TEXT_Y, "Press this button.");
        StdDraw.show();
    }

    public static void drawInitCircle() {
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(CIRCLE_X, CIRCLE_Y, RADIUS);
    }

    public static void drawTriggeredCircle() {
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(CIRCLE_X, CIRCLE_Y, RADIUS);
    }

    public static double square(double input) {
        return input * input;
    }

    public static boolean clickInCircle(double x, double y) {
        double dx = Math.abs(x - CIRCLE_X);
        double dy = Math.abs(y - CIRCLE_Y);
        if (dx > RADIUS) return false;
        if (dy > RADIUS) return false;
        if (dx + dy <= RADIUS) return true;
        // lines above for optimization; line below is sufficient
        return square(dx) + square(dy) <= square(RADIUS);

    }

    public static boolean lucky(int counter) {
        if (counter < SAFE_NUM) return false;
        else {
            double random = StdRandom.uniform();
            double odds = (double) ODDS / 100.0;
            if (random <= odds) {
                return true;
            }
            else return false;
        }
    }

    public static void main(String[] args) {
        initialize();
        int counter = 0;
        double mouseX = 0, mouseY = 0;
        // insert sth to break out of while loop? will it end the program?
        while (true) {
            drawInitCircle();
            StdDraw.show();
            if (StdDraw.isMousePressed() || StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                if (StdDraw.isMousePressed()) {
                    mouseX = StdDraw.mouseX();
                    mouseY = StdDraw.mouseY();
                }
                if (clickInCircle(mouseX, mouseY) || StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                    counter++;
                    if (lucky(counter)) {
                        // show circle immediately!
                        drawTriggeredCircle();
                        StdDraw.show();
                        StdDraw.pause(AFTER_PAUSE);
                        counter = SAFE_NUM - StdRandom.uniform(5, 10);
                    }
                    else {
                        drawTriggeredCircle();
                        StdDraw.pause(BEFORE_PAUSE);
                        StdDraw.show();
                        StdDraw.pause(AFTER_PAUSE);
                    }
                }
            }
            StdDraw.pause(20);
        }
    }
}
