import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class DrawXYScale extends JFrame {


    final int width = 1000;
    final int height = 1000;

    public DrawXYScale() {

        super("Cartesian Plane");

        setSize(width, height);
        setBackground(Color.WHITE);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        super.paint(g);
        g2.setColor(Color.BLACK);
        // drawing X and Y axis
        g2.draw(new Line2D.Double((width / 2), 31, (width / 2), height));
        g2.draw(new Line2D.Double(0, (height / 2), width, (height / 2)));
        g2.setColor(Color.GRAY);
        // along X axis ----->
        for (double i = (width / 2); i < width; i += 20) {
            // drawing vertical lines
            g2.draw(new Line2D.Double(i, 0, i, height));
        }
        // along X axis <------
        for (double i = (width / 2); i > width; i -= 20) {
            // drawing vertical lines
            g2.draw(new Line2D.Double(i, 0, i, height));
        }
        // along Y axis down
        for (double i = (height / 2); i < height; i += 20) {
            // drawing horizontal lines
            g2.draw(new Line2D.Double(0, i, width, i));
        }
        // along Y axis up
        for (double i = (height / 2); i < height; i -= 20) {
            // drawing horizontal lines
            g2.draw(new Line2D.Double(0, i, width, i));
        }
    }

    public static void main(String args[]) {

        new DrawXYScale();
    }

}