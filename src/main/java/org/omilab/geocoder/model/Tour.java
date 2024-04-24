package org.omilab.geocoder.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Tour {
    String tourId;
    String runningId;
    String name;
    Collection<POI> route;

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm:ss", Locale.ENGLISH);

    public Tour(String tourId, String runningId, String name) {
        this.tourId = tourId;
        this.runningId = runningId;
        this.name = name;
        this.route = Collections.EMPTY_LIST;

    }

    private void addPOI(String id, Double _long, Double lat, String name, String information, String readableAddress,
            Date startVisit, Date endVisit) {
        this.route.add(new POI(id, _long, lat, name, information, readableAddress, startVisit, endVisit));
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getRunningId() {
        return runningId;
    }

    public void setRunningId(String runningId) {
        this.runningId = runningId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<POI> getRoute() {
        return route;
    }

    public void setRoute(List<POI> route) {
        this.route = route;
    }

    public static Tour getDummyTour() throws ParseException {
        Tour tour = new Tour("4c7b7f7b-47fd-479f-ba3b-898da050b04e", "8daf2e83-42a1-4c8d-925a-41c218d72c5a",
                "Käsekrainer Tour");
        tour.addPOI("7d8ce778-dbf6-47df-bbf4-34c5dad7a8e9", 16.373896, 48.208088, "Wilfrids Favorit",
                "Wilfrids Favorit, ein charmanter Käsekreinerstand am Fuß des majestätischen Stephansdoms, ist ein belebter Treffpunkt, bekannt für seine köstlichen, käsegefüllten Würstchen und die warmherzige Persönlichkeit seines Besitzers. Ein köstlicher Duft von geräuchertem Fleisch, geschmolzenem Käse und frisch gebackenem Brot umhüllt den Stand, der durch sein schelmisches, Kochhut tragendes Ferkel auffällt. Der Besitzer, Wilfrid, mit seinem lebhaften Lachen und unzähligen Anekdoten, brutzelt die perfekt gegrillten Käsekrainer mit einer liebevollen Handfertigkeit. Wilfrids Favorit ist nicht nur ein Ort für hervorragendes Essen, sondern auch ein Zentrum der Gemeinschaft und Gastfreundschaft, das sowohl Einheimische als auch Touristen anzieht.",
                "Zum Wilfrid 1, 1010 Wien", formatter.parse("2023-06-14T17:46"), formatter.parse("2023-06-14T19:46:21"));
        tour.addPOI("0b7086b6-760b-46b4-99b0-60748fe76935", 16.384663, 48.20619, "Alexanders Passion",
                "Alexanders Passion, ein avantgardistischer Käsekreinerstand, thront im Herzen der U-Bahn-Station Wien Mitte und strahlt mit seiner minimalistischen Architektur und modernem Design urbanen Chic aus. Mit seinem auffälligen Logo und den hippen, recycelten Holzmöbeln stellt dieser Stand eine innovative Neuinterpretation der traditionellen Wiener Wurstkultur dar. Der junge und dynamische Betreiber, Alexander, serviert mit Leidenschaft handwerklich zubereitete Käsekrainer, die sich durch ihre biologischen Zutaten und einzigartigen Geschmackskombinationen auszeichnen. Alexanders Passion verbindet so auf kreative Weise die Tradition der Wiener Wurststände mit einem modernen, umweltbewussten und hippen Lebensstil, der Einheimische und Besucher gleichermaßen begeistert.\n",
                "Landstraßer Hauptstraße 1B, 1030 Wien", formatter.parse("2023-06-14T20:16:21"),
                formatter.parse("2023-06-14T22:46:21"));
        return tour;
    }
}