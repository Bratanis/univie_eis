package org.omilab.portal_service;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DatabaseConnection {

    public DatabaseConnection(){}

    public Connection connect() {
        String jdbcUrl = "jdbc:mysql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/" + System.getenv("DB_NAME");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try {
            System.out.println("BEFORE CONNECTION!");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");

            System.out.println("AFTER CONNECTION!");
            // Perform database operations
            return connection;
        } catch (SQLException e) {
            throw  new RuntimeException();
        }
    }

    public void showDatabases(Connection con){

        String sql = "SHOW DATABASES";

        try {
            // Create a PreparedStatement object with the SQL statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Execute the PreparedStatement to retrieve the results
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the retrieved data
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Database"));
            }

            // Close the ResultSet and PreparedStatement
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential SQLException
        }
    }

    public void showTables(Connection con) {
        String sql = "SHOW TABLES";

        try {
            // Set the desired database context
            String useDatabaseSQL = "USE portal_database";
            Statement useDatabaseStatement = con.createStatement();
            useDatabaseStatement.execute(useDatabaseSQL);

            // Create a Statement object with the SQL statement to show tables
            Statement statement = con.createStatement();

            // Execute the statement to retrieve the tables
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("portal_database: ");
            // Process the retrieved data
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)); // Assuming one column in result set
            }

            // Close the ResultSet and Statement
            resultSet.close();
            statement.close();
            useDatabaseStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential SQLException
        }
    }

//    public void createTables(Connection con) {
//        String createDistrictTable = "CREATE TABLE IF NOT EXISTS District (" +
//                "    Number INT PRIMARY KEY," +
//                "    Name VARCHAR(100)" +
//                ");";
//
//        String createAttractionTable = "CREATE TABLE IF NOT EXISTS Attraction (" +
//                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
//                "    Name VARCHAR(255)," +
//                "    Address VARCHAR(255)," +
//                "    DistrictNr INT," +
//                "    FOREIGN KEY (DistrictNr) REFERENCES District(Number)" +
//                ");";
//
//        String createVisitorTable = "CREATE TABLE IF NOT EXISTS Visitor (" +
//                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
//                "    Age INT CHECK (Age >= 0)," +
//                "    Gender VARCHAR(50)," +
//                "    Nationality VARCHAR(100)" +
//                ");";
//
//        String createEventTable = "CREATE TABLE IF NOT EXISTS Event (" +
//                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
//                "    Name VARCHAR(255)," +
//                "    Date DATE," +
//                "    Address VARCHAR(255)," +
//                "    Type VARCHAR(100)," +
//                "    DistrictNr INT," +
//                "    FOREIGN KEY (DistrictNr) REFERENCES District(Number)" +
//                ");";
//
//        String createVisitRecordTable = "CREATE TABLE IF NOT EXISTS VisitRecord (" +
//                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
//                "    AttractionID INT," +
//                "    VisitorID INT," +
//                "    VisitTime TIMESTAMP," +
//                "    MoneySpent DECIMAL(10, 2)," +
//                "    TimeSpent DECIMAL(10, 2) CHECK (TimeSpent >= 0 AND TimeSpent <= 24)," +
//                "    Rating DECIMAL(3, 1) CHECK (Rating >= 0 AND Rating <= 100)," +
//                "    FOREIGN KEY (AttractionID) REFERENCES Attraction(ID)," +
//                "    FOREIGN KEY (VisitorID) REFERENCES Visitor(ID)" +
//                ");";
//
//        try {
//            // Execute each table creation statement
//            executeUpdate(con, createAttractionTable);
//            executeUpdate(con, createVisitorTable);
//            executeUpdate(con, createEventTable);
//            executeUpdate(con, createVisitRecordTable);
//
//            System.out.println("Tables created successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void alterDistrictTable(Connection con) {
        // SQL statement to alter the District table
        String alterDistrictTableSQL = "ALTER TABLE District " +
                "ADD COLUMN Description VARCHAR(1500)";

        try {
            executeUpdate(con, alterDistrictTableSQL);
            System.out.println("District table altered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(Connection con, String sql) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }

    //FILL

    public void insertDistrictData(Connection con) {

        List<String> insertStatements = Arrays.asList(
                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(1, 'Innere Stadt', 'Innere Stadt, the historic heart of Vienna, is a district steeped in grandeur and elegance. It boasts many of the city''s most iconic landmarks, including the majestic St. Stephen''s Cathedral, the imperial Hofburg Palace, and the world-renowned Vienna State Opera. Visitors can wander through narrow medieval streets and grand boulevards, discovering charming squares like Stephansplatz and elegant shopping streets such as Graben and Kohlmarkt. The district is also home to world-class museums like the Albertina, the Kunsthistorisches Museum, and the Naturhistorisches Museum, making it a cultural epicenter. Cafes, restaurants, and luxury boutiques abound, offering visitors a taste of Vienna''s rich cultural heritage and sophisticated lifestyle.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(2, 'Leopoldstadt', 'Leopoldstadt is a vibrant district known for its rich Jewish heritage and abundant green spaces. The district''s centerpiece is the famous Prater park, which features the iconic Giant Ferris Wheel and offers a mix of amusement rides, open meadows, and wooded areas perfect for leisure and recreation. Leopoldstadt also includes the Danube Island, a favorite spot for outdoor activities like cycling, swimming, and boating, as well as hosting summer festivals and events. The Karmelitermarkt adds to the district''s charm with its diverse food stalls, cafes, and artisan shops, reflecting the area''s multicultural character. The district''s lively and diverse atmosphere makes it a dynamic part of Vienna.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(3, 'Landstraße', 'Landstraße seamlessly blends history with modernity, featuring some of Vienna''s most remarkable landmarks. The baroque Belvedere Palace, with its stunning gardens and impressive art collections, including works by Gustav Klimt, is a major highlight. The district also houses the contemporary Hundertwasserhaus, an architectural marvel designed by Friedensreich Hundertwasser, known for its vibrant colors and organic forms. Landstraße is home to cultural institutions like the Konzerthaus and the Schwarzenbergplatz, offering a rich array of artistic and musical experiences. The district''s mix of historic charm and modern innovation makes it a fascinating area to explore.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(4, 'Wieden', 'Wieden is a dynamic and trendy district, combining historic charm with a bohemian atmosphere. It is home to the magnificent Karlskirche, an architectural masterpiece with its impressive baroque design. The bustling Naschmarkt, Vienna''s most famous market, is located here, offering a vibrant mix of exotic foods, local delicacies, and unique shops. The area around the Technical University is lively and full of student life, with numerous cafes, boutiques, and cultural venues reflecting its youthful and creative spirit. Wieden''s blend of history, culture, and modern urban life makes it a vibrant and engaging district.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(5, 'Margareten', 'Margareten is a diverse and rapidly developing district known for its mix of historic and modern architecture. It boasts charming local markets, such as the Schlossquadrat, and numerous green spaces where residents and visitors can relax and enjoy the outdoors. Margareten has a vibrant cultural scene with theaters, galleries, and an emerging culinary landscape. The district is increasingly popular for its dynamic atmosphere and community feel, making it an attractive area for both residents and visitors.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(6, 'Mariahilf', 'Mariahilf is a lively shopping and cultural district, famous for Mariahilfer Straße, Vienna’s longest and busiest shopping street. This district also features the Haus des Meeres, an aquarium located in a historic flak tower, and the Theater an der Wien, known for its opera productions. Mariahilf offers a bustling mix of shopping, dining, and cultural experiences, making it a central hub of activity in Vienna.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(7, 'Neubau', 'Neubau is known for its creative and artistic vibe, housing the MuseumsQuartier, one of the largest cultural complexes in Europe. This district is dotted with independent boutiques, cafes, and galleries, attracting artists, designers, and young professionals. The Spittelberg area is particularly charming with its narrow streets, historic buildings, and vibrant nightlife. Neubau exudes a trendy and modern atmosphere, making it a favorite for those seeking a lively cultural experience.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(8, 'Josefstadt', 'Josefstadt, the smallest district in Vienna, is known for its elegant residential areas and cultural charm. It features the historic Theater in der Josefstadt, numerous small parks, and quaint squares. The district offers a calm, community-focused atmosphere with a blend of historic and modern architecture. Josefstadt is ideal for visitors looking to explore local Viennese culture in a peaceful and intimate setting.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(9, 'Alsergrund', 'Alsergrund is a district rich in medical and academic history, hosting the Sigmund Freud Museum and several historic hospitals. The district also features beautiful parks, the stunning Votive Church, and the University of Vienna campus. Alsergrund has a vibrant atmosphere, combining educational institutions, cultural sites, and lively cafes and bars. The mix of students and long-time residents creates a dynamic and engaging community.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(10, 'Favoriten', 'Favoriten is a diverse and populous district known for its multicultural vibe and modern developments. It features the recreational area of Wienerberg, the Therme Wien spa, and the Böhmischer Prater amusement park. Favoriten is a mix of residential zones, shopping streets, and green spaces, reflecting the dynamic and evolving character of Vienna''s urban life. The district''s vibrant community and diverse cultural offerings make it an exciting area to explore.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(11, 'Simmering', 'Simmering combines industrial heritage with green recreational areas. The district is home to the Central Cemetery, where many famous Viennese are buried, and the Gasometer, a complex of repurposed gas tanks now housing shops, apartments, and event spaces. Simmering also features the lively Simmeringer Hauptstraße shopping street and the peaceful Herderpark. The district offers a unique blend of history, modern development, and community life, making it a distinctive part of Vienna.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(12, 'Meidling', 'Meidling is a primarily residential district with a friendly, local atmosphere. It features attractions such as the Schönbrunn Zoo, one of the oldest and most renowned zoos in the world, and the historic Meidlinger Markt, a vibrant local market. Meidling is known for its local charm, community feel, and accessibility, making it a pleasant area for both residents and visitors to explore.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(13, 'Hietzing', 'Hietzing is an elegant district best known for the magnificent Schönbrunn Palace and its expansive gardens. The district boasts affluent residential areas, beautiful parks, and historic villas. Hietzing offers a peaceful environment with easy access to some of Vienna''s most famous cultural landmarks, such as the Tiergarten Schönbrunn, the oldest zoo in the world, and the Schönbrunn Orangery. The district combines regal history with tranquil living, making it a desirable area for both tourists and residents.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(14, 'Penzing', 'Penzing is a spacious district offering a blend of urban and natural attractions. It features the Technical Museum, the Otto Wagner Villa, and the expansive Lainzer Tiergarten wildlife reserve. The district is family-friendly, with a mix of residential areas, parks, and historical sites, making it an ideal spot for those seeking a balance between city life and nature. Penzing''s diverse offerings cater to a wide range of interests, from cultural enthusiasts to outdoor adventurers.');",
                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(15, 'Rudolfsheim-Fünfhaus', 'Rudolfsheim-Fünfhaus is a vibrant and multicultural district known for its dynamic community and cultural diversity. It features popular shopping destinations like Mariahilfer Straße and major event venues such as the Stadthalle. The district is also home to the Westbahnhof, one of Vienna''s main railway stations, and the lively Reindorfgasse with its cafes and shops. Rudolfsheim-Fünfhaus offers a lively mix of cultural events, diverse dining options, and modern urban living, reflecting its youthful and energetic spirit.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(16, 'Ottakring', 'Ottakring is known for its brewery, multicultural atmosphere, and rich local culture. The district includes the picturesque Wilhelminenberg Palace, the lively Brunnenmarkt, and the Ottakringer Brauerei, a historic brewery offering tours and tastings. Ottakring offers a unique blend of traditional Viennese culture and modern diversity, making it an exciting area to explore. The district''s vibrant nightlife, cultural festivals, and diverse communities contribute to its dynamic character.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(17, 'Hernals', 'Hernals combines urban living with ample green spaces, such as the Schwarzenbergpark and the historic Kalvarienberg Church. It is a residential district with charming old buildings, local markets, and a serene atmosphere. Hernals provides a community-oriented environment, ideal for families and those seeking a quieter side of Vienna. The district''s mix of green areas, cultural sites, and residential neighborhoods offers a balanced and peaceful lifestyle.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(18, 'Währing', 'Währing is an upscale residential district known for its beautiful parks, like the Türkenschanzpark, and elegant villas. It offers a peaceful environment with a mix of historic and modern architecture. Währing is a tranquil area with excellent schools, cultural sites such as the Geymüllerschlössel, and a variety of amenities, making it a desirable district for families and professionals. The district''s serene atmosphere and picturesque streetscapes make it a lovely place to live and visit.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(19, 'Döbling', 'Döbling is an affluent district renowned for its vineyards, scenic views, and luxurious residential areas. It includes the picturesque Grinzing village, famous for its wine taverns, and the Kahlenberg hill, offering stunning panoramas of Vienna. Döbling also features beautiful parks, like the Setagayapark, and the historic Karl-Marx-Hof. The district combines nature, wine culture, and upscale living, making it a sought-after area for both locals and tourists.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(20, 'Brigittenau', 'Brigittenau is a densely populated district with a rich industrial history and a vibrant community. It features the Millennium Tower, one of Vienna''s tallest buildings, and the recreational area of Augarten, known for its beautiful gardens and historic porcelain factory. The district is characterized by its accessible location along the Danube River and its dynamic urban lifestyle. Brigittenau''s mix of residential areas, cultural sites, and green spaces offers a well-rounded and lively environment.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(21, 'Floridsdorf', 'Floridsdorf is a spacious district with a suburban feel, offering green areas like the Donaupark and the Old Danube. It is a family-friendly area with numerous recreational options, including boating, cycling, and swimming along the Danube. Floridsdorf provides a balance between urban amenities and natural beauty, making it a pleasant residential district. The district''s parks, schools, and community centers contribute to its appeal as a peaceful and welcoming area.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(22, 'Donaustadt', 'Donaustadt is Vienna’s largest district, known for its extensive green spaces and modern developments. The district features the Danube Island, a hub for outdoor activities and festivals, and the UNO City, a center for international business. Donaustadt also includes the modern Seestadt Aspern development, showcasing innovative urban planning and sustainable living. The district combines vast natural areas with contemporary architecture, offering a dynamic and versatile environment for both residents and visitors.');",

                "INSERT INTO District (Number, DistrictName, Description) VALUES " +
                        "(23, 'Liesing', 'Liesing is a diverse district with a mix of industrial areas, residential neighborhoods, and green spaces like the Wienerwald. It is known for its traditional wine taverns and the architecturally unique Wotruba Church. Liesing offers suburban tranquility with the convenience of urban living, making it an attractive area for families and professionals alike. The district''s blend of history, nature, and modern amenities creates a well-rounded and appealing environment.');"
        );


        try (Connection conn = con) {
            conn.setAutoCommit(false); // Begin transaction

            for (String insertStatement : insertStatements) {
                PreparedStatement stmt = conn.prepareStatement(insertStatement);
                stmt.executeUpdate();
                stmt.close();
            }

            conn.commit(); // Commit transaction
            System.out.println("District data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDistricts(Connection con){

        String sql = "select * from portal_database.District;";

        try {
            // Create a PreparedStatement object with the SQL statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Execute the PreparedStatement to retrieve the results
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the retrieved data
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

            // Close the ResultSet and PreparedStatement
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential SQLException
        }
    }

}

