package org.omilab.geocoder.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omilab.geocoder.GridDrawerUtil;
import org.omilab.geocoder.KdTree;
import org.omilab.geocoder.KdTree.XYZPoint;

import java.awt.image.BufferedImage;

import io.swagger.annotations.Api;

/**
 * The sensor resource is accessed with /sensor. This class provides various
 * operations for sensors.
 */
@Path("/geocode_static")
// API is required, for the UI to recognize this class as annotated for swagger
@Api(tags = { "Geocoder Static Operation" })
public class GeoCoderStatic {

  public GeoCoderStatic() {
    super();
  }

  /**
   * The logger makes sure that asked information is written in a file. This eases
   * the debugging process and allows to check the output values.
   */
  private Logger log = LogManager.getLogger(GeoCoderStatic.class);

  /**
   * Operation to construct a grid and find the closest neighbor of a location.
   * The grid is centered
   * 
   * @return String (JSON)
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("find_closest_node")
  public Collection<XYZPoint> findClosestNeigbor(@QueryParam("gridSize") int gridSize,
      @QueryParam("x") double positionX, @QueryParam("y") double positionY) {
    log.info("constructing the grid");
    KdTree<XYZPoint> kdTree = new KdTree<XYZPoint>(generateGrid(gridSize));
    XYZPoint searchPoint = new XYZPoint(positionX, positionY, "search");
    Collection<XYZPoint> result = kdTree.nearestNeighbourSearch(1, searchPoint);
    return result;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("foo")
  public String helloFoo(@QueryParam("input") String input) {
    return "Hello " + input;
  }

  @GET
  @Path("/display_closest_node")
  @Produces("image/png")
  public Response findClosestNeigborVisual(@QueryParam("gridSize") int gridSize, @QueryParam("x") double positionX,
      @QueryParam("y") double positionY) {
    List<XYZPoint> gridPoints = generateGrid(gridSize);
    KdTree<XYZPoint> kdTree = new KdTree<XYZPoint>(gridPoints);
    XYZPoint searchPoint = new XYZPoint(positionX, positionY, "search");
    Collection<XYZPoint> result = kdTree.nearestNeighbourSearch(1, searchPoint);
    BufferedImage image = GridDrawerUtil.drawGridResults(gridPoints, gridSize, searchPoint, result);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, "png", baos);
    } catch (IOException e) {
      e.printStackTrace();
    }
    byte[] imageData = baos.toByteArray();

    // uncomment line below to send non-streamed
    // return Response.ok(imageData).build();

    // uncomment line below to send streamed
    return Response.ok(new ByteArrayInputStream(imageData)).build();
  }

  private static List<XYZPoint> generateGrid(int gridSize) {
    int gridLeft = gridSize / 2 * -1;
    int gridTop = gridSize / 2 * -1;
    List<XYZPoint> grid = new ArrayList<XYZPoint>();
    for (int x = gridLeft; x < gridSize; x++) {
      for (int y = gridTop; y < gridSize; y++) {
        XYZPoint point = new XYZPoint(x, y, String.valueOf(x) + "x" + String.valueOf(y));
        grid.add(point);
      }
    }
    return grid;
  }

}
