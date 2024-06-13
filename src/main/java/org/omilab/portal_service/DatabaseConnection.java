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
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");

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


    public void insertAttractionData(Connection con) {

        List<String> insertStatements = Arrays.asList(
                "insert into Visitor (Age, Gender, Nationality) values (63, 'Female', 'Bolivia');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (16, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (42, 'Agender', 'Bosnia and Herzegovina');",
                "insert into Visitor (Age, Gender, Nationality) values (16, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (9, 'Non-binary', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (86, 'Female', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (35, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (7, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Non-binary', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Male', 'Albania');",
                "insert into Visitor (Age, Gender, Nationality) values (71, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (47, 'Male', 'Gambia');",
                "insert into Visitor (Age, Gender, Nationality) values (46, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Male', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (43, 'Male', 'Jamaica');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Male', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Non-binary', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Male', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (34, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (2, 'Female', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (52, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Male', 'Tanzania');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (42, 'Female', 'Dominican Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (81, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (97, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (32, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (90, 'Female', 'Bangladesh');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Male', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Male', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Female', 'Azerbaijan');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (7, 'Male', 'Hungary');",
                "insert into Visitor (Age, Gender, Nationality) values (42, 'Male', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (24, 'Bigender', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (42, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Male', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (18, 'Male', 'Tunisia');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Female', 'Syria');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (23, 'Female', 'Botswana');",
                "insert into Visitor (Age, Gender, Nationality) values (47, 'Male', 'Dominican Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (68, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Male', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (78, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Genderfluid', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (34, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (39, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (54, 'Male', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (24, 'Male', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (19, 'Female', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (39, 'Male', 'Madagascar');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Polygender', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (90, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (41, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (59, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (98, 'Male', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (3, 'Male', 'Guatemala');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (68, 'Female', 'Afghanistan');",
                "insert into Visitor (Age, Gender, Nationality) values (35, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Bigender', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Male', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (46, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (21, 'Female', 'Paraguay');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Female', 'Greenland');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Non-binary', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (43, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Female', 'Pakistan');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Male', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (55, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (91, 'Non-binary', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (87, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Genderfluid', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Female', 'Dominican Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (16, 'Male', 'South Korea');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (8, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (2, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Male', 'Kyrgyzstan');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Male', 'South Africa');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (23, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (66, 'Male', 'United Kingdom');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Female', 'Vietnam');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (21, 'Genderfluid', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (47, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (52, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Male', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (51, 'Female', 'Marshall Islands');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (98, 'Female', 'Jordan');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Female', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Male', 'Guatemala');",
                "insert into Visitor (Age, Gender, Nationality) values (63, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Female', 'Madagascar');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (44, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (90, 'Female', 'Sri Lanka');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Genderqueer', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Non-binary', 'Ivory Coast');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Female', 'Namibia');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Genderqueer', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Female', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (87, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Bigender', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (51, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (74, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Male', 'Armenia');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Male', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (39, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (41, 'Genderfluid', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (32, 'Genderqueer', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (73, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (68, 'Male', 'Benin');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Female', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (68, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (93, 'Genderqueer', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Female', 'Nepal');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Agender', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (39, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Genderfluid', 'Madagascar');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Male', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (66, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Male', 'Yemen');",
                "insert into Visitor (Age, Gender, Nationality) values (54, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Agender', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Female', 'Malaysia');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Female', 'Cyprus');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Female', 'Tunisia');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (19, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (3, 'Male', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (16, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Polygender', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Male', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (88, 'Female', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (74, 'Male', 'Papua New Guinea');",
                "insert into Visitor (Age, Gender, Nationality) values (90, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (78, 'Agender', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Male', 'Paraguay');",
                "insert into Visitor (Age, Gender, Nationality) values (44, 'Male', 'South Korea');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Polygender', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (52, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (87, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (68, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (90, 'Female', 'Germany');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Female', 'Pakistan');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Genderqueer', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (54, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (87, 'Male', 'Sierra Leone');",
                "insert into Visitor (Age, Gender, Nationality) values (63, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Polygender', 'Chile');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (24, 'Female', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (81, 'Polygender', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (21, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Genderqueer', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Male', 'Bosnia and Herzegovina');",
                "insert into Visitor (Age, Gender, Nationality) values (71, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Female', 'Nicaragua');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (93, 'Male', 'Haiti');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Male', 'Madagascar');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (19, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Male', 'Belarus');",
                "insert into Visitor (Age, Gender, Nationality) values (12, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (2, 'Female', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (82, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Male', 'Finland');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Female', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Female', 'Sierra Leone');",
                "insert into Visitor (Age, Gender, Nationality) values (34, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (41, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (44, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (91, 'Male', 'Chad');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (46, 'Male', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (9, 'Female', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (41, 'Male', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Agender', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (88, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (43, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Agender', 'Croatia');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (61, 'Male', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Male', 'Paraguay');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (57, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Non-binary', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Male', 'Cameroon');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Polygender', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (65, 'Female', 'Pakistan');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Agender', 'Bangladesh');",
                "insert into Visitor (Age, Gender, Nationality) values (16, 'Female', 'Macedonia');",
                "insert into Visitor (Age, Gender, Nationality) values (44, 'Polygender', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (47, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (9, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (20, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (65, 'Female', 'Estonia');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Agender', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Male', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (54, 'Male', 'Cuba');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Female', 'Germany');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Male', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (65, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (97, 'Female', 'Jamaica');",
                "insert into Visitor (Age, Gender, Nationality) values (71, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (23, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (42, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (59, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Female', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (80, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Polygender', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Female', 'Tunisia');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Genderqueer', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (18, 'Male', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (74, 'Female', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (32, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Male', 'Kenya');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Bigender', 'Serbia');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (87, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (9, 'Female', 'South Korea');",
                "insert into Visitor (Age, Gender, Nationality) values (19, 'Female', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Male', 'Guatemala');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Non-binary', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (2, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Female', 'Palestinian Territory');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (68, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (47, 'Male', 'San Marino');",
                "insert into Visitor (Age, Gender, Nationality) values (65, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (52, 'Male', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Female', 'Afghanistan');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Bigender', 'Madagascar');",
                "insert into Visitor (Age, Gender, Nationality) values (20, 'Agender', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Male', 'Chile');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Female', 'Botswana');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Female', 'Tajikistan');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (63, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Agender', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (66, 'Female', 'Vietnam');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Male', 'Egypt');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (55, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (82, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Male', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Female', 'Gambia');",
                "insert into Visitor (Age, Gender, Nationality) values (80, 'Agender', 'Vietnam');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Female', 'Kyrgyzstan');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (21, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (52, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Male', 'Cyprus');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (35, 'Female', 'Morocco');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Genderfluid', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Genderfluid', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (80, 'Male', 'Armenia');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (89, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (21, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Male', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Female', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (51, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (98, 'Female', 'Lithuania');",
                "insert into Visitor (Age, Gender, Nationality) values (61, 'Female', 'Tajikistan');",
                "insert into Visitor (Age, Gender, Nationality) values (8, 'Female', 'Belarus');",
                "insert into Visitor (Age, Gender, Nationality) values (3, 'Female', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Polygender', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Female', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (82, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (65, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (21, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Female', 'Hungary');",
                "insert into Visitor (Age, Gender, Nationality) values (40, 'Female', 'Saudi Arabia');",
                "insert into Visitor (Age, Gender, Nationality) values (24, 'Male', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (7, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (28, 'Female', 'Venezuela');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Male', 'Palestinian Territory');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Male', 'Panama');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Female', 'Azerbaijan');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Male', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (65, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (59, 'Bigender', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (57, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (32, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Male', 'Taiwan');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (8, 'Female', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Agender', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (73, 'Polygender', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Male', 'Pakistan');",
                "insert into Visitor (Age, Gender, Nationality) values (62, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Non-binary', 'Gambia');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (70, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (17, 'Non-binary', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (66, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (66, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (91, 'Male', 'Slovenia');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Male', 'Chile');",
                "insert into Visitor (Age, Gender, Nationality) values (24, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (23, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (20, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (44, 'Non-binary', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (46, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (15, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Female', 'Jordan');",
                "insert into Visitor (Age, Gender, Nationality) values (8, 'Genderfluid', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Male', 'Germany');",
                "insert into Visitor (Age, Gender, Nationality) values (35, 'Female', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (32, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (35, 'Male', 'Costa Rica');",
                "insert into Visitor (Age, Gender, Nationality) values (71, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (93, 'Female', 'Egypt');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (12, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Female', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (98, 'Agender', 'Honduras');",
                "insert into Visitor (Age, Gender, Nationality) values (88, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (91, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (80, 'Female', 'Germany');",
                "insert into Visitor (Age, Gender, Nationality) values (55, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (57, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (82, 'Bigender', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (46, 'Genderfluid', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Male', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Male', 'Mauritania');",
                "insert into Visitor (Age, Gender, Nationality) values (71, 'Male', 'Cameroon');",
                "insert into Visitor (Age, Gender, Nationality) values (71, 'Female', 'Micronesia');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (86, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (57, 'Male', 'Uganda');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (23, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Male', 'Pakistan');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Female', 'Azerbaijan');",
                "insert into Visitor (Age, Gender, Nationality) values (74, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (11, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Female', 'Palestinian Territory');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Female', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Male', 'Bulgaria');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (32, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'Albania');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (34, 'Male', 'Greece');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Female', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (74, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (12, 'Male', 'Guinea');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (9, 'Male', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Female', 'Ethiopia');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Male', 'Kosovo');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Female', 'Netherlands');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (73, 'Female', 'Venezuela');",
                "insert into Visitor (Age, Gender, Nationality) values (33, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (12, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Male', 'Cuba');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Female', 'Myanmar');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Male', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (36, 'Female', 'Canada');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Bigender', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (19, 'Male', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (75, 'Female', 'Panama');",
                "insert into Visitor (Age, Gender, Nationality) values (35, 'Female', 'Ireland');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (79, 'Bigender', 'Jordan');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (33, 'Male', 'Serbia');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (52, 'Genderqueer', 'Vietnam');",
                "insert into Visitor (Age, Gender, Nationality) values (67, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (54, 'Male', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Male', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (66, 'Female', 'Saudi Arabia');",
                "insert into Visitor (Age, Gender, Nationality) values (30, 'Female', 'Estonia');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Genderqueer', 'Georgia');",
                "insert into Visitor (Age, Gender, Nationality) values (42, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (78, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (73, 'Female', 'Vietnam');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Genderfluid', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (69, 'Genderqueer', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (33, 'Male', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (9, 'Male', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Male', 'Czech Republic');",
                "insert into Visitor (Age, Gender, Nationality) values (24, 'Male', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (53, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (91, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Female', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (77, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (92, 'Female', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Female', 'Rwanda');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Male', 'South Africa');",
                "insert into Visitor (Age, Gender, Nationality) values (50, 'Female', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (90, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (74, 'Female', 'Guatemala');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Agender', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (29, 'Female', 'Jordan');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (58, 'Polygender', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (56, 'Male', 'Spain');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Male', 'Croatia');",
                "insert into Visitor (Age, Gender, Nationality) values (28, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Bigender', 'Belgium');",
                "insert into Visitor (Age, Gender, Nationality) values (14, 'Male', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (94, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (49, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (54, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (61, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (25, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (37, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Female', 'Sweden');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Male', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (8, 'Female', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (60, 'Genderfluid', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (51, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (7, 'Genderqueer', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (10, 'Female', 'Colombia');",
                "insert into Visitor (Age, Gender, Nationality) values (80, 'Female', 'Ghana');",
                "insert into Visitor (Age, Gender, Nationality) values (55, 'Male', 'Chile');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Female', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (7, 'Male', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (84, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (86, 'Female', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (87, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (27, 'Female', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (48, 'Male', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (26, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (63, 'Female', 'North Korea');",
                "insert into Visitor (Age, Gender, Nationality) values (96, 'Male', 'Finland');",
                "insert into Visitor (Age, Gender, Nationality) values (5, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (1, 'Bigender', 'Portugal');",
                "insert into Visitor (Age, Gender, Nationality) values (76, 'Female', 'Sri Lanka');",
                "insert into Visitor (Age, Gender, Nationality) values (64, 'Female', 'Ireland');",
                "insert into Visitor (Age, Gender, Nationality) values (83, 'Female', 'Finland');",
                "insert into Visitor (Age, Gender, Nationality) values (8, 'Male', 'Mexico');",
                "insert into Visitor (Age, Gender, Nationality) values (82, 'Female', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (13, 'Female', 'Japan');",
                "insert into Visitor (Age, Gender, Nationality) values (100, 'Female', 'Argentina');",
                "insert into Visitor (Age, Gender, Nationality) values (31, 'Male', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (45, 'Male', 'Russia');",
                "insert into Visitor (Age, Gender, Nationality) values (20, 'Female', 'Brazil');",
                "insert into Visitor (Age, Gender, Nationality) values (72, 'Female', 'Thailand');",
                "insert into Visitor (Age, Gender, Nationality) values (4, 'Female', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (38, 'Female', 'United Kingdom');",
                "insert into Visitor (Age, Gender, Nationality) values (18, 'Genderqueer', 'Poland');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Genderfluid', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (22, 'Female', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (6, 'Female', 'Peru');",
                "insert into Visitor (Age, Gender, Nationality) values (16, 'Male', 'France');",
                "insert into Visitor (Age, Gender, Nationality) values (85, 'Genderfluid', 'Philippines');",
                "insert into Visitor (Age, Gender, Nationality) values (47, 'Male', 'Ukraine');",
                "insert into Visitor (Age, Gender, Nationality) values (18, 'Female', 'Nigeria');",
                "insert into Visitor (Age, Gender, Nationality) values (59, 'Male', 'United States');",
                "insert into Visitor (Age, Gender, Nationality) values (95, 'Female', 'China');",
                "insert into Visitor (Age, Gender, Nationality) values (23, 'Female', 'Indonesia');",
                "insert into Visitor (Age, Gender, Nationality) values (99, 'Male', 'Indonesia');"

                );

        try (Connection conn = con) {
            conn.setAutoCommit(false); // Begin transaction

            for (String insertStatement : insertStatements) {
                PreparedStatement stmt = conn.prepareStatement(insertStatement);
                stmt.executeUpdate();
                stmt.close();
            }

            conn.commit(); // Commit transaction
            System.out.println("Visitor data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDistricts(Connection con){

        String sql = "select * from portal_database.Visitor;";

        try {
            // Create a PreparedStatement object with the SQL statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Execute the PreparedStatement to retrieve the results
            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            // Process the retrieved data
            while (resultSet.next()) {
                count++;
                if (count <= 15){
                    System.out.println(resultSet.getString("Nationality"));
                }
            }

            // Close the ResultSet and PreparedStatement
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential SQLException
        }
    }


    /*
    public void insertAttractionData(Connection con) {

        List<String> insertStatements = Arrays.asList(
                "insert into Attraction (Name, Address, DistrictNr) values ('Carousel', 'Prater Hauptallee', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Haunted House', 'Schönbrunner Schloßstraße', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Ferris Wheel', 'Riesenradplatz', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Roller Coaster', 'Prater', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Water Slide', 'Böhmischer Prater', 10);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Museum of Technology', 'Mariahilfer Straße 212', 15);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Zoo', 'Maxingstraße 13b', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Botanical Garden', 'Rennweg 14', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Belvedere Palace', 'Prinz Eugen-Straße 27', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Art History Museum', 'Maria-Theresien-Platz', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Natural History Museum', 'Burgring 7', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Leopold Museum', 'Museumsplatz 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Albertina Museum', 'Albertinaplatz 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Schönbrunn Palace', 'Schönbrunner Schloßstraße 47', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna State Opera', 'Opernring 2', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Hundertwasser House', 'Kegelgasse 34-38', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Danube Tower', 'Donauturmstraße 4', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Zentralfriedhof', 'Simmeringer Hauptstraße 234', 11);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Neptune Fountain at Schönbrunn', 'Schönbrunner Schloßstraße 47', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Mariahilfer Straße Shopping Street', 'Mariahilfer Straße', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('MuseumsQuartier', 'Museumsplatz 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Lainzer Tiergarten', 'Lainzer Tor', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Kurpark Oberlaa', 'Kurbadstraße 10', 10);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Augarten', 'Obere Augartenstraße 1', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Prater Museum', 'Oswald-Thomas-Platz 1', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('House of the Sea', 'Fritz-Grünbaum-Platz 1', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Seegrotte Hinterbrühl', 'Grutschgasse 2a', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Thermalbad Vösendorf', 'Seepark Vösendorf', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Burg Liechtenstein', 'Am Hausberg 2', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Observatory', 'Türkenschanzstraße 17', 18);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Johann Strauss Monument', 'Stadtpark', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Heeresgeschichtliches Museum', 'Arsenal Objekt 1', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('MUMOK (Museum of Modern Art)', 'Museumsplatz 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('House of Music', 'Seilerstätte 30', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Beethoven Pasqualatihaus', 'Mölker Bastei 8', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Technical Museum Vienna', 'Mariahilfer Straße 212', 15);",
                "insert into Attraction (Name, Address, DistrictNr) values ('The Third Man Museum', 'Pressgasse 25', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Ernst Fuchs Museum', 'Hüttelbergstraße 26', 14);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Schmetterlinghaus (Butterfly House)', 'Burggarten', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Crime Museum', 'Große Sperlgasse 24', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Chocolate Museum Vienna', 'Willendorfer Gasse 2-8', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Gasometer City', 'Guglgasse 6', 11);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Bank Austria Kunstforum', 'Freyung 8', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Liesing River Path', 'Liesingbachweg', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Liesing Business Park', 'Liesinger-Platz 1', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Ottakring Brewery', 'Ottakringer Platz 1', 16);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Donaupark', 'Arbeiterstrandbadstraße', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Perchtoldsdorf Castle', 'Marktplatz 11', 23);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Sigmund Freud Museum', 'Berggasse 19', 9);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Schoenbrunn Orangery', 'Schönbrunner Schloßstraße', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Karl Marx-Hof', 'Heiligenstädter Straße 82', 19);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna City Beach Club', 'Neue Donau Mitte', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Woods', 'Wienerwald', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Neue Donau', 'Neue Donau', 21);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Alte Donau', 'Arbeiterstrandbadstraße', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Kahlenberg', 'Josefsdorf 38', 19);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Cobenzl', 'Am Cobenzl 94', 19);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Burg Kreuzenstein', 'Kreuzensteiner Straße', 21);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Lainzer Tiergarten', 'Hermesvilla', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Volksgarten', 'Dr.-Karl-Renner-Ring', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Stadtpark', 'Parkring 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Prater', 'Prater', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Karlskirche', 'Kreuzherrengasse 1', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Augarten Contemporary', 'Obere Augartenstraße 1e', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Gloriette Schönbrunn', 'Schönbrunner Schloßstraße 47', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Danube Tower', 'Donauturmstraße 4', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Belvedere 21', 'Arsenalstraße 1', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Museum of Illusions', 'Wallnerstraße 4', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Donau Zentrum', 'Wagramer Straße 94', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Flakturm Museum', 'Esterházypark', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Wiener Riesenrad', 'Riesenradplatz 1', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Porcelain Manufactory Augarten', 'Obere Augartenstraße 1', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Klimt Villa', 'Feldmühlgasse 11', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Geymüllerschlössel', 'Pötzleinsdorfer Straße 102', 18);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Raimund Theater', 'Wallgasse 18-20', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Theater in der Josefstadt', 'Josefstädter Straße 26', 8);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Volkstheater', 'Neustiftgasse 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Burgtheater', 'Universitätsring 2', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Theater Akzent', 'Theresianumgasse 18', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Ronacher Theater', 'Seilerstätte 9', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Hofburg Palace', 'Michaelerkuppel', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Schönbrunn Palace', 'Schönbrunner Schloßstraße 47', 13);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Belvedere Palace', 'Prinz Eugen-Straße 27', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Hundertwasserhaus', 'Kegelgasse 36-38', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('St. Stephen Cathedral', 'Stephansplatz', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Hundertwasser Village', 'Kegelgasse 36-38', 3);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Karlskirche', 'Karlsplatz', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna State Opera', 'Opernring 2', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Spanish Riding School', 'Michaelerplatz 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Albertina Museum', 'Albertinaplatz 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Leopold Museum', 'Museumsplatz 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Kunsthistorisches Museum', 'Maria-Theresien-Platz', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Natural History Museum', 'Burgring 7', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Prater Hauptallee', 'Prater Hauptallee', 2);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Naschmarkt', 'Naschmarkt', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna University of Technology', 'Karlsplatz 13', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('University of Vienna', 'Universitätsring 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Technical University of Vienna', 'Karlsplatz 13', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna City Hall', 'Friedrich-Schmidt-Platz 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Austrian Parliament Building', 'Dr.-Karl-Renner-Ring 3', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Haus des Meeres', 'Fritz-Grünbaum-Platz 1', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Museum Quarter', 'Museumsplatz 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Danube Island', 'Donauinsel', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Heldenplatz', 'Heldenplatz', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Volksgarten', 'Burgring 1', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('St. Charles Church', 'Karlsplatz', 4);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Jewish Museum Vienna', 'Dorotheergasse 11', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Museum of Modern Art', 'Museumsplatz 1', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Technical Museum', 'Mariahilfer Straße 212', 15);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna House of the Sea', 'Fritz-Grünbaum-Platz 1', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Museum of Natural History', 'Burgring 7', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Museum of Applied Arts, Vienna', 'Stubenring 5', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Danube Park', 'Arbeiterstrandbadstraße', 22);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Stephansplatz', 'Stephansplatz', 1);",
                "insert into Attraction (Name, Address, DistrictNr) values ('House of the Sea Vienna', 'Fritz-Grünbaum-Platz 1', 6);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Vienna Technical Museum', 'Mariahilfer Straße 212', 15);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Kunsthistorisches Museum Vienna', 'Maria-Theresien-Platz', 7);",
                "insert into Attraction (Name, Address, DistrictNr) values ('Hofburg Vienna', 'Michaelerkuppel', 1);"
                );

        try (Connection conn = con) {
            conn.setAutoCommit(false); // Begin transaction

            for (String insertStatement : insertStatements) {
                PreparedStatement stmt = conn.prepareStatement(insertStatement);
                stmt.executeUpdate();
                stmt.close();
            }

            conn.commit(); // Commit transaction
            System.out.println("Attraction data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/

    /*
    public void insertDistrictData(Connection con) {

        List<String> insertStatements = Arrays.asList(
                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(1, 'Innere Stadt', 'Innere Stadt, the historic heart of Vienna, is a district steeped in grandeur and elegance. It boasts many of the city''s most iconic landmarks, including the majestic St. Stephen''s Cathedral, the imperial Hofburg Palace, and the world-renowned Vienna State Opera. Visitors can wander through narrow medieval streets and grand boulevards, discovering charming squares like Stephansplatz and elegant shopping streets such as Graben and Kohlmarkt. The district is also home to world-class museums like the Albertina, the Kunsthistorisches Museum, and the Naturhistorisches Museum, making it a cultural epicenter. Cafes, restaurants, and luxury boutiques abound, offering visitors a taste of Vienna''s rich cultural heritage and sophisticated lifestyle.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(2, 'Leopoldstadt', 'Leopoldstadt is a vibrant district known for its rich Jewish heritage and abundant green spaces. The district''s centerpiece is the famous Prater park, which features the iconic Giant Ferris Wheel and offers a mix of amusement rides, open meadows, and wooded areas perfect for leisure and recreation. Leopoldstadt also includes the Danube Island, a favorite spot for outdoor activities like cycling, swimming, and boating, as well as hosting summer festivals and events. The Karmelitermarkt adds to the district''s charm with its diverse food stalls, cafes, and artisan shops, reflecting the area''s multicultural character. The district''s lively and diverse atmosphere makes it a dynamic part of Vienna.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(3, 'Landstraße', 'Landstraße seamlessly blends history with modernity, featuring some of Vienna''s most remarkable landmarks. The baroque Belvedere Palace, with its stunning gardens and impressive art collections, including works by Gustav Klimt, is a major highlight. The district also houses the contemporary Hundertwasserhaus, an architectural marvel designed by Friedensreich Hundertwasser, known for its vibrant colors and organic forms. Landstraße is home to cultural institutions like the Konzerthaus and the Schwarzenbergplatz, offering a rich array of artistic and musical experiences. The district''s mix of historic charm and modern innovation makes it a fascinating area to explore.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(4, 'Wieden', 'Wieden is a dynamic and trendy district, combining historic charm with a bohemian atmosphere. It is home to the magnificent Karlskirche, an architectural masterpiece with its impressive baroque design. The bustling Naschmarkt, Vienna''s most famous market, is located here, offering a vibrant mix of exotic foods, local delicacies, and unique shops. The area around the Technical University is lively and full of student life, with numerous cafes, boutiques, and cultural venues reflecting its youthful and creative spirit. Wieden''s blend of history, culture, and modern urban life makes it a vibrant and engaging district.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(5, 'Margareten', 'Margareten is a diverse and rapidly developing district known for its mix of historic and modern architecture. It boasts charming local markets, such as the Schlossquadrat, and numerous green spaces where residents and visitors can relax and enjoy the outdoors. Margareten has a vibrant cultural scene with theaters, galleries, and an emerging culinary landscape. The district is increasingly popular for its dynamic atmosphere and community feel, making it an attractive area for both residents and visitors.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(6, 'Mariahilf', 'Mariahilf is a lively shopping and cultural district, famous for Mariahilfer Straße, Vienna’s longest and busiest shopping street. This district also features the Haus des Meeres, an aquarium located in a historic flak tower, and the Theater an der Wien, known for its opera productions. Mariahilf offers a bustling mix of shopping, dining, and cultural experiences, making it a central hub of activity in Vienna.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(7, 'Neubau', 'Neubau is known for its creative and artistic vibe, housing the MuseumsQuartier, one of the largest cultural complexes in Europe. This district is dotted with independent boutiques, cafes, and galleries, attracting artists, designers, and young professionals. The Spittelberg area is particularly charming with its narrow streets, historic buildings, and vibrant nightlife. Neubau exudes a trendy and modern atmosphere, making it a favorite for those seeking a lively cultural experience.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(8, 'Josefstadt', 'Josefstadt, the smallest district in Vienna, is known for its elegant residential areas and cultural charm. It features the historic Theater in der Josefstadt, numerous small parks, and quaint squares. The district offers a calm, community-focused atmosphere with a blend of historic and modern architecture. Josefstadt is ideal for visitors looking to explore local Viennese culture in a peaceful and intimate setting.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(9, 'Alsergrund', 'Alsergrund is a district rich in medical and academic history, hosting the Sigmund Freud Museum and several historic hospitals. The district also features beautiful parks, the stunning Votive Church, and the University of Vienna campus. Alsergrund has a vibrant atmosphere, combining educational institutions, cultural sites, and lively cafes and bars. The mix of students and long-time residents creates a dynamic and engaging community.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(10, 'Favoriten', 'Favoriten is a diverse and populous district known for its multicultural vibe and modern developments. It features the recreational area of Wienerberg, the Therme Wien spa, and the Böhmischer Prater amusement park. Favoriten is a mix of residential zones, shopping streets, and green spaces, reflecting the dynamic and evolving character of Vienna''s urban life. The district''s vibrant community and diverse cultural offerings make it an exciting area to explore.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(11, 'Simmering', 'Simmering combines industrial heritage with green recreational areas. The district is home to the Central Cemetery, where many famous Viennese are buried, and the Gasometer, a complex of repurposed gas tanks now housing shops, apartments, and event spaces. Simmering also features the lively Simmeringer Hauptstraße shopping street and the peaceful Herderpark. The district offers a unique blend of history, modern development, and community life, making it a distinctive part of Vienna.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(12, 'Meidling', 'Meidling is a primarily residential district with a friendly, local atmosphere. It features attractions such as the Schönbrunn Zoo, one of the oldest and most renowned zoos in the world, and the historic Meidlinger Markt, a vibrant local market. Meidling is known for its local charm, community feel, and accessibility, making it a pleasant area for both residents and visitors to explore.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(13, 'Hietzing', 'Hietzing is an elegant district best known for the magnificent Schönbrunn Palace and its expansive gardens. The district boasts affluent residential areas, beautiful parks, and historic villas. Hietzing offers a peaceful environment with easy access to some of Vienna''s most famous cultural landmarks, such as the Tiergarten Schönbrunn, the oldest zoo in the world, and the Schönbrunn Orangery. The district combines regal history with tranquil living, making it a desirable area for both tourists and residents.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(14, 'Penzing', 'Penzing is a spacious district offering a blend of urban and natural attractions. It features the Technical Museum, the Otto Wagner Villa, and the expansive Lainzer Tiergarten wildlife reserve. The district is family-friendly, with a mix of residential areas, parks, and historical sites, making it an ideal spot for those seeking a balance between city life and nature. Penzing''s diverse offerings cater to a wide range of interests, from cultural enthusiasts to outdoor adventurers.');",
                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(15, 'Rudolfsheim-Fünfhaus', 'Rudolfsheim-Fünfhaus is a vibrant and multicultural district known for its dynamic community and cultural diversity. It features popular shopping destinations like Mariahilfer Straße and major event venues such as the Stadthalle. The district is also home to the Westbahnhof, one of Vienna''s main railway stations, and the lively Reindorfgasse with its cafes and shops. Rudolfsheim-Fünfhaus offers a lively mix of cultural events, diverse dining options, and modern urban living, reflecting its youthful and energetic spirit.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(16, 'Ottakring', 'Ottakring is known for its brewery, multicultural atmosphere, and rich local culture. The district includes the picturesque Wilhelminenberg Palace, the lively Brunnenmarkt, and the Ottakringer Brauerei, a historic brewery offering tours and tastings. Ottakring offers a unique blend of traditional Viennese culture and modern diversity, making it an exciting area to explore. The district''s vibrant nightlife, cultural festivals, and diverse communities contribute to its dynamic character.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(17, 'Hernals', 'Hernals combines urban living with ample green spaces, such as the Schwarzenbergpark and the historic Kalvarienberg Church. It is a residential district with charming old buildings, local markets, and a serene atmosphere. Hernals provides a community-oriented environment, ideal for families and those seeking a quieter side of Vienna. The district''s mix of green areas, cultural sites, and residential neighborhoods offers a balanced and peaceful lifestyle.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(18, 'Währing', 'Währing is an upscale residential district known for its beautiful parks, like the Türkenschanzpark, and elegant villas. It offers a peaceful environment with a mix of historic and modern architecture. Währing is a tranquil area with excellent schools, cultural sites such as the Geymüllerschlössel, and a variety of amenities, making it a desirable district for families and professionals. The district''s serene atmosphere and picturesque streetscapes make it a lovely place to live and visit.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(19, 'Döbling', 'Döbling is an affluent district renowned for its vineyards, scenic views, and luxurious residential areas. It includes the picturesque Grinzing village, famous for its wine taverns, and the Kahlenberg hill, offering stunning panoramas of Vienna. Döbling also features beautiful parks, like the Setagayapark, and the historic Karl-Marx-Hof. The district combines nature, wine culture, and upscale living, making it a sought-after area for both locals and tourists.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(20, 'Brigittenau', 'Brigittenau is a densely populated district with a rich industrial history and a vibrant community. It features the Millennium Tower, one of Vienna''s tallest buildings, and the recreational area of Augarten, known for its beautiful gardens and historic porcelain factory. The district is characterized by its accessible location along the Danube River and its dynamic urban lifestyle. Brigittenau''s mix of residential areas, cultural sites, and green spaces offers a well-rounded and lively environment.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(21, 'Floridsdorf', 'Floridsdorf is a spacious district with a suburban feel, offering green areas like the Donaupark and the Old Danube. It is a family-friendly area with numerous recreational options, including boating, cycling, and swimming along the Danube. Floridsdorf provides a balance between urban amenities and natural beauty, making it a pleasant residential district. The district''s parks, schools, and community centers contribute to its appeal as a peaceful and welcoming area.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(22, 'Donaustadt', 'Donaustadt is Vienna’s largest district, known for its extensive green spaces and modern developments. The district features the Danube Island, a hub for outdoor activities and festivals, and the UNO City, a center for international business. Donaustadt also includes the modern Seestadt Aspern development, showcasing innovative urban planning and sustainable living. The district combines vast natural areas with contemporary architecture, offering a dynamic and versatile environment for both residents and visitors.');",

                "INSERT INTO District (Number, Name, Description) VALUES " +
                        "(23, 'Liesing', 'Liesing is a diverse district with a mix of industrial areas, residential neighborhoods, and green spaces like the Wienerwald. It is known for its traditional wine taverns and the architecturally unique Wotruba Church. Liesing offers suburban tranquility with the convenience of urban living, making it an attractive area for families and professionals alike. The district''s blend of history, nature, and modern amenities creates a well-rounded and appealing environment.');"
        );


        try (Connection conn = con) {
            conn.setAutoCommit(true); // Begin transaction

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
*/
}

