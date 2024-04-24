package org.omilab.geocoder.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
import org.omilab.geocoder.model.Tour;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;

import java.awt.image.BufferedImage;

import io.swagger.annotations.Api;

/**
 */
@Path("/tourInterface")
// API is required, for the UI to recognize this class as annotated for swagger
@Api(tags = { "Tour Interface" })
public class DummyTour {

  public DummyTour() {
    super();
  }

  /**
   * The logger makes sure that asked information is written in a file. This eases
   * the debugging process and allows to check the output values.
   */
  private Logger log = LogManager.getLogger(DummyTour.class);

  /**
   * Operation to construct a grid and find the closest neighbor of a location. The grid is centered
   * 
   * @return String (JSON)
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("tourInformation")
  public String tourInformation() {
    return "{\n" + //
        "    \"tourId\": \"4c7b7f7b-47fd-479f-ba3b-898da050b04e\",\n" + //
        "    \"runningId\": \"8daf2e83-42a1-4c8d-925a-41c218d72c5a\",\n" + //
        "    \"name\": \"K\u00E4sekrainer Tour\",\n" + //
        "    \"route\": [\n" + //
        "      {\n" + //
        "        \"id\": \"7d8ce778-dbf6-47df-bbf4-34c5dad7a8e9\",\n" + //
        "        \"_long\": 16.373896,\n" + //
        "        \"lat\": 48.208088,\n" + //
        "        \"name\": \"Wilfrids Favorit\",\n" + //
        "        \"information\": \"Wilfrids Favorit, ein charmanter K\u00E4sekreinerstand am Fu\u00DF des majest\u00E4tischen Stephansdoms, ist ein belebter Treffpunkt, bekannt f\u00FCr seine k\u00F6stlichen, k\u00E4segef\u00FCllten W\u00FCrstchen und die warmherzige Pers\u00F6nlichkeit seines Besitzers. Ein k\u00F6stlicher Duft von ger\u00E4uchertem Fleisch, geschmolzenem K\u00E4se und frisch gebackenem Brot umh\u00FCllt den Stand, der durch sein schelmisches, Kochhut tragendes Ferkel auff\u00E4llt. Der Besitzer, Wilfrid, mit seinem lebhaften Lachen und unz\u00E4hligen Anekdoten, brutzelt die perfekt gegrillten K\u00E4sekrainer mit einer liebevollen Handfertigkeit. Wilfrids Favorit ist nicht nur ein Ort f\u00FCr hervorragendes Essen, sondern auch ein Zentrum der Gemeinschaft und Gastfreundschaft, das sowohl Einheimische als auch Touristen anzieht.\",\n" + //
        "        \"readableAddress\": \"Zum Wilfrid 1, 1010 Wien\",\n" + //
        "        \"startVisit\": \"2023-06-14T17:46:21.186817+02:00\",\n" + //
        "        \"endVisit\": \"2023-06-14T19:46:21.186836+02:00\"\n" + //
        "      },\n" + //
        "      {\n" + //
        "        \"id\": \"0b7086b6-760b-46b4-99b0-60748fe76935\",\n" + //
        "        \"_long\": 16.384663,\n" + //
        "        \"lat\": 48.20619,\n" + //
        "        \"name\": \"Alexanders Passion\",\n" + //
        "        \"information\": \"Alexanders Passion, ein avantgardistischer K\u00E4sekreinerstand, thront im Herzen der U-Bahn-Station Wien Mitte und strahlt mit seiner minimalistischen Architektur und modernem Design urbanen Chic aus. Mit seinem auff\u00E4lligen Logo und den hippen, recycelten Holzm\u00F6beln stellt dieser Stand eine innovative Neuinterpretation der traditionellen Wiener Wurstkultur dar. Der junge und dynamische Betreiber, Alexander, serviert mit Leidenschaft handwerklich zubereitete K\u00E4sekrainer, die sich durch ihre biologischen Zutaten und einzigartigen Geschmackskombinationen auszeichnen. Alexanders Passion verbindet so auf kreative Weise die Tradition der Wiener Wurstst\u00E4nde mit einem modernen, umweltbewussten und hippen Lebensstil, der Einheimische und Besucher gleicherma\u00DFen begeistert.\\n" + //
        "\",\n" + //
        "        \"readableAddress\": \"Landstra\u00DFer Hauptstra\u00DFe 1B, 1030 Wien\",\n" + //
        "        \"startVisit\": \"2023-06-14T20:16:21.186875+02:00\",\n" + //
        "        \"endVisit\": \"2023-06-14T22:46:21.186894+02:00\"\n" + //
        "      }\n" + //
        "    ],\n" + //
        "    \"startDate\": \"2023-06-14T17:46:21.185562+02:00\",\n" + //
        "    \"endDate\": \"2023-06-14T22:46:21.18627+02:00\",\n" + //
        "    \"participants\": [\n" + //
        "      {\n" + //
        "        \"id\": \"2fc719de-36f3-485d-8296-7e85a84e0147\",\n" + //
        "        \"name\": \"Lucia Anu\",\n" + //
        "        \"touristType\": \"REAL\"\n" + //
        "      },\n" + //
        "      {\n" + //
        "        \"id\": \"b6d3a76a-3bf9-46e6-995e-a87c98e175c8\",\n" + //
        "        \"name\": \"Valerija Pisti\",\n" + //
        "        \"touristType\": \"VIRTUAL\"\n" + //
        "      }\n" + //
        "    ]\n" + //
        "  }";
  }
}
