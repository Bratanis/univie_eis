import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.omilab.geocoder.KdTree;
import org.omilab.geocoder.KdTree.XYZPoint;

public class TestKDTreeClosestNeighbor {
    @Test
    public void testKdTree() {
        int width = 8;
        int height = 8;


        //construct the array of items
        List<XYZPoint> points = new ArrayList<XYZPoint>();

        int x = 0;
        for(char alphabet = 'A'; alphabet <='Z'; alphabet ++ )
        {   
            if (x < width) {
                for (int y = 0; y< height; y++){
                    XYZPoint point = new XYZPoint(x-width/2, y-height/2, alphabet + "x" + String.valueOf(y));
                    points.add(point);
                    System.out.println("Create node: " + point);
                }
                x++;
            }
            
        }
        KdTree<XYZPoint> kdTree = new KdTree<XYZPoint>(points);

        XYZPoint searchPoint = new XYZPoint(20, 3, "search");

        Collection<XYZPoint> result = kdTree.nearestNeighbourSearch(1, searchPoint);
        System.out.println(result);
    }
}
