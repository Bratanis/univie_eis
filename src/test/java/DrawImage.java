import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.*;

import org.omilab.geocoder.KdTree.XYZPoint;

import java.awt.image.BufferedImage;
import java.io.File;

public class DrawImage {
    public static void main(String[] args) {
        try {
            int gridSize = 10;
            Dimension imgDim = new Dimension(1000,1000);
            int offset = 25;
            Dimension imgDimOffset = new Dimension(imgDim.width+2*offset, imgDim.height+2*offset);
            BufferedImage mazeImage = new BufferedImage(imgDimOffset.width, imgDimOffset.height, BufferedImage.TYPE_INT_RGB);
            
    
            Graphics2D g2d = mazeImage.createGraphics();
            g2d.setBackground(Color.WHITE);
            g2d.fillRect(0, 0, imgDimOffset.width, imgDimOffset.height);

         
            //draw grid lines
            g2d.setColor(Color.BLACK);
            Stroke dashed = new BasicStroke(0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                          0, new float[]{ 2f, 0f, 2f }, 2f);
            g2d.setStroke(dashed);
            int y = imgDim.height/2;
            while (y <= imgDim.height) {
                g2d.drawLine(0+offset, y+offset, imgDim.width+offset, y+offset);
                //inverse line
                g2d.drawLine(0+offset, y-imgDim.height/2+offset, imgDim.width+offset, y-imgDim.height/2+offset);
                y += imgDim.height/gridSize;
            }
            int x = imgDim.width/2;
            while (x <= imgDim.width) {
                g2d.drawLine(x+offset, offset, x+offset, imgDim.height+offset);                 
                //inverse lines
                g2d.drawLine(x-imgDim.width/2+offset, 0+offset, x-imgDim.width/2+offset, imgDim.height+offset);
                x+= imgDim.width/gridSize;
            }

            //draw center lines in red
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(1));
            //x-axis
            g2d.drawLine(offset-15, (imgDim.height)/2+offset, imgDim.width+offset+15, (imgDim.height)/2+offset);
            //y-axis
            g2d.drawLine((imgDim.width)/2+offset, offset-15, imgDim.width/2+offset, imgDim.height+offset+15);
            //label center point
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(1));
            g2d.setBackground(Color.RED);
            g2d.fillOval(imgDim.width/2-10/2+offset, imgDim.height/2-10/2+offset, 10, 10);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 8));         
            g2d.drawString("0/0", imgDim.width/2-10/2 + 10+offset, imgDim.height/2-10/2 + 20+offset);



            List<XYZPoint> points = generateGrid(gridSize);
            for (XYZPoint xyzPoint : points) {
                drawPoint(xyzPoint, g2d, imgDim, gridSize, offset);
            } 

            drawPoint(2.6,-4.5, "somename", g2d, imgDim, gridSize, offset, Color.BLUE);

            ImageIO.write(mazeImage, "PNG",
                    new File("/Users/wilfridutz/Documents/GitHub/geocoder/src/test/resources/yourImageName.PNG"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }

    private static void drawPoint (double x, double y, String name, Graphics2D g2d, Dimension imgDim, int gridSize, int offset, Color color) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(1));
        g2d.setBackground(Color.BLUE);
        int xPoint = (imgDim.width/2)+(int)x*imgDim.width/gridSize+offset;
        int xPointDouble = (imgDim.width/2) + (int)(x*imgDim.width/gridSize) +offset;
        int yPoint = (imgDim.height/2) + (int)y*-1*imgDim.height/gridSize+offset;
        int yPointDouble = (imgDim.width/2) + (int)(y*imgDim.width/gridSize) +offset;
        g2d.fillOval(xPointDouble-10/2, yPointDouble-10/2, 10, 10);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 8));         
        g2d.drawString(name, xPointDouble+10, yPointDouble + 10);
    }

    private static void drawPoint (XYZPoint point, Graphics2D g2d, Dimension imgDim, int gridSize, int offset) {
       drawPoint(point.getX(), point.getY(), point.getName(), g2d, imgDim, gridSize, offset, Color.GRAY);
    }

    private static List<XYZPoint> generateGrid(int gridSize) {
        int gridLeft = gridSize/2*-1;
        int gridTop = gridSize/2*-1;
        List<XYZPoint> grid = new ArrayList<XYZPoint>();
        for (int x = gridLeft; x<gridSize;x++) {
            for (int y = gridTop; y<gridSize;y++) {
                XYZPoint point = new XYZPoint(x, y, String.valueOf(x) + "x" + String.valueOf(y));
                grid.add(point);
            }
        }
        return grid;
    }
}
