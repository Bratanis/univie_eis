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
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (19, 246, '2017-11-28', 649.09, 9.87, 52.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (90, 928, '2022-08-19', 503.36, 12.32, 30.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (95, 521, '2017-11-25', 214.33, 10.5, 44.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (60, 921, '2021-08-07', 570.8, 11.79, 55.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (75, 576, '2007-06-27', 489.18, 7.52, 27.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (41, 662, '2004-03-30', 867.58, 4.45, 71.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (10, 526, '2024-05-11', 140.67, 8.25, 19.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (94, 380, '2015-08-25', 882.95, 8.76, 94.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (49, 25, '2023-09-25', 336.69, 11.36, 77.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (77, 952, '2008-11-12', 252.93, 6.84, 89.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 174, '2018-02-27', 945.11, 11.31, 28.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (9, 31, '2012-11-10', 274.23, 5.59, 82.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (66, 971, '2020-11-15', 529.08, 10.55, 18.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (4, 72, '2023-10-14', 664.68, 2.14, 56.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (39, 384, '2008-09-28', 280.12, 12.57, 9.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 451, '2002-05-25', 309.72, 1.32, 98.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (29, 960, '2007-12-30', 312.63, 13.99, 70.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (53, 405, '2018-12-25', 608.11, 4.75, 77.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 776, '2000-12-23', 404.25, 6.73, 78.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 76, '2005-03-02', 286.48, 12.28, 9.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (44, 54, '2008-02-10', 226.12, 6.26, 10.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 501, '2013-09-25', 2.17, 3.92, 14.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (4, 90, '2003-04-19', 594.96, 2.92, 52.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (59, 230, '2018-05-06', 552.14, 15.54, 72.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (92, 241, '2014-02-09', 218.25, 1.47, 5.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (98, 525, '2008-04-15', 295.58, 4.71, 13.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 581, '2003-07-22', 910.36, 10.76, 76.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 231, '2018-04-21', 136.87, 5.32, 98.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (71, 503, '2011-06-29', 397.82, 14.93, 38.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (94, 545, '2017-11-21', 606.1, 15.72, 12.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (29, 536, '2017-08-22', 848.08, 6.9, 43.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 956, '2022-04-09', 283.5, 2.04, 11.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 658, '2000-02-02', 830.06, 4.47, 42.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (82, 458, '2014-06-18', 693.06, 6.15, 44.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 455, '2001-07-29', 417.06, 4.08, 80.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 269, '2006-03-17', 460.79, 7.99, 57.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 970, '2004-11-05', 348.19, 4.65, 83.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (33, 862, '2013-12-15', 757.89, 8.12, 87.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (83, 681, '2005-09-21', 427.93, 3.55, 33.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (96, 712, '2017-12-07', 297.67, 14.38, 99.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 133, '2003-04-02', 596.67, 13.03, 47.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (1, 249, '2011-07-11', 488.3, 5.97, 65.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 177, '2012-10-24', 370.91, 11.43, 81.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (62, 800, '2007-03-04', 957.62, 13.89, 60.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (24, 989, '2008-08-30', 713.4, 3.91, 61.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (84, 109, '2013-12-24', 160.35, 9.63, 10.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (17, 277, '2011-08-01', 544.42, 1.96, 68.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 648, '2009-06-09', 560.14, 12.62, 8.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 622, '2002-07-14', 407.13, 6.59, 48.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 998, '2007-06-16', 748.69, 6.23, 66.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (6, 771, '2010-09-25', 276.77, 13.18, 51.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (67, 188, '2004-04-30', 752.18, 14.58, 32.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 733, '2019-08-17', 775.84, 7.15, 18.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (81, 855, '2004-10-21', 423.65, 7.34, 96.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (20, 614, '2016-12-12', 293.93, 6.01, 87.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 555, '2005-07-02', 131.58, 11.09, 40.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 683, '2003-05-19', 640.7, 7.89, 18.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 790, '2015-08-13', 398.9, 12.04, 60.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (46, 377, '2016-09-28', 889.1, 13.86, 22.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 208, '2007-06-08', 166.69, 3.21, 63.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (40, 220, '2006-10-18', 783.89, 2.1, 89.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (4, 785, '2015-04-21', 139.41, 7.86, 16.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (70, 524, '2005-07-31', 361.42, 15.91, 19.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (3, 200, '2013-02-03', 881.26, 5.79, 8.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (92, 184, '2001-03-12', 733.01, 4.94, 61.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (91, 733, '2011-02-01', 467.66, 14.69, 94.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (34, 373, '2005-10-22', 330.66, 5.14, 34.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (14, 801, '2004-12-31', 693.43, 1.37, 84.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 455, '2001-07-29', 417.06, 4.08, 80.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 269, '2006-03-17', 460.79, 7.99, 57.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 970, '2004-11-05', 348.19, 4.65, 83.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (33, 862, '2013-12-15', 757.89, 8.12, 87.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (83, 681, '2005-09-21', 427.93, 3.55, 33.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (96, 712, '2017-12-07', 297.67, 14.38, 99.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 133, '2003-04-02', 596.67, 13.03, 47.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (1, 249, '2011-07-11', 488.3, 5.97, 65.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 177, '2012-10-24', 370.91, 11.43, 81.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (62, 800, '2007-03-04', 957.62, 13.89, 60.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (24, 989, '2008-08-30', 713.4, 3.91, 61.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (84, 109, '2013-12-24', 160.35, 9.63, 10.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (17, 277, '2011-08-01', 544.42, 1.96, 68.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 648, '2009-06-09', 560.14, 12.62, 8.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 622, '2002-07-14', 407.13, 6.59, 48.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 998, '2007-06-16', 748.69, 6.23, 66.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (6, 771, '2010-09-25', 276.77, 13.18, 51.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (67, 188, '2004-04-30', 752.18, 14.58, 32.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 733, '2019-08-17', 775.84, 7.15, 18.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (81, 855, '2004-10-21', 423.65, 7.34, 96.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (20, 614, '2016-12-12', 293.93, 6.01, 87.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 555, '2005-07-02', 131.58, 11.09, 40.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 683, '2003-05-19', 640.7, 7.89, 18.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 790, '2015-08-13', 398.9, 12.04, 60.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (46, 377, '2016-09-28', 889.1, 13.86, 22.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 208, '2007-06-08', 166.69, 3.21, 63.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (40, 220, '2006-10-18', 783.89, 2.1, 89.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (4, 785, '2015-04-21', 139.41, 7.86, 16.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (70, 524, '2005-07-31', 361.42, 15.91, 19.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (3, 200, '2013-02-03', 881.26, 5.79, 8.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (92, 184, '2001-03-12', 733.01, 4.94, 61.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (91, 733, '2011-02-01', 467.66, 14.69, 94.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (34, 373, '2005-10-22', 330.66, 5.14, 34.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (14, 801, '2004-12-31', 693.43, 1.37, 84.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (9, 856, '2003-09-17', 579.31, 8.76, 73.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 822, '2002-11-02', 307.45, 9.8, 12.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 409, '2008-12-20', 314.15, 11.47, 53.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (73, 841, '2015-06-14', 774.74, 10.68, 87.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (32, 314, '2018-02-09', 208.18, 6.27, 69.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (10, 342, '2002-09-25', 228.36, 12.42, 36.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (64, 581, '2006-10-14', 881.89, 4.05, 54.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (6, 606, '2004-05-28', 615.2, 2.54, 43.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (55, 165, '2012-08-31', 843.99, 8.17, 22.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (59, 311, '2019-04-22', 514.85, 14.3, 57.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (17, 609, '2008-06-27', 748.31, 2.55, 75.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (23, 887, '2017-03-09', 296.73, 11.08, 95.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (84, 646, '2003-06-16', 860.56, 4.36, 86.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (71, 933, '2015-01-19', 812.61, 8.02, 37.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (42, 143, '2004-09-29', 455.09, 11.92, 81.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (38, 248, '2010-04-18', 295.97, 6.18, 60.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (25, 965, '2002-01-01', 425.28, 9.61, 27.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (14, 299, '2012-11-22', 268.49, 11.75, 73.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (94, 641, '2016-04-06', 789.88, 10.23, 91.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (51, 143, '2017-08-07', 506.97, 13.33, 24.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (36, 980, '2003-07-17', 267.14, 10.44, 32.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (16, 772, '2014-12-25', 986.56, 12.34, 41.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (80, 570, '2005-05-14', 555.87, 4.87, 13.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (93, 210, '2010-09-23', 321.98, 12.67, 94.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (75, 960, '2008-02-12', 365.94, 13.56, 36.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (5, 124, '2007-04-30', 584.4, 3.78, 60.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 865, '2004-08-18', 618.65, 14.55, 50.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (49, 458, '2009-10-03', 684.48, 3.09, 78.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (86, 510, '2005-03-26', 831.99, 2.83, 90.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 147, '2013-09-10', 936.26, 4.66, 14.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (11, 687, '2018-07-01', 374.65, 9.58, 87.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (37, 876, '2014-02-08', 475.51, 15.47, 42.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (30, 511, '2006-05-13', 798.68, 5.65, 99.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (8, 300, '2002-12-07', 563.29, 2.56, 88.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 54, '2005-07-28', 658.89, 5.87, 52.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 310, '2019-05-05', 709.92, 11.78, 98.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (102, 207, '2016-08-12', 325.45, 8.21, 78.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 112, '2018-03-19', 421.67, 6.54, 85.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 419, '2020-10-02', 523.89, 9.87, 90.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 815, '2017-11-15', 632.75, 7.92, 65.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 613, '2023-01-04', 743.21, 12.45, 82.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 934, '2014-06-27', 845.36, 10.76, 77.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 772, '2015-09-18', 956.42, 14.28, 91.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (102, 428, '2018-12-30', 104.57, 3.45, 60.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 679, '2019-07-21', 265.89, 4.76, 73.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 193, '2016-04-14', 372.91, 6.98, 88.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 840, '2022-02-08', 489.76, 8.54, 70.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 567, '2017-05-30', 592.34, 9.87, 75.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 729, '2019-10-17', 605.98, 7.32, 69.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 327, '2018-08-25', 714.25, 11.45, 95.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (102, 517, '2020-01-12', 829.36, 13.87, 87.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 662, '2021-04-03', 942.48, 15.23, 94.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 875, '2015-12-07', 107.59, 2.98, 55.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 943, '2016-09-28', 214.67, 5.65, 68.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 225, '2017-03-14', 321.78, 7.34, 72.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 439, '2019-01-19', 456.89, 8.76, 79.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 826, '2022-06-22', 567.92, 10.89, 83.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (102, 723, '2020-08-10', 678.34, 12.76, 85.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 513, '2018-11-05', 789.45, 14.23, 92.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 637, '2017-07-29', 891.67, 16.54, 97.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 124, '2016-02-18', 956.78, 18.76, 99.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 965, '2021-09-03', 109.89, 3.54, 62.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 340, '2017-04-14', 210.92, 5.78, 67.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 781, '2019-08-28', 321.45, 7.21, 72.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (102, 594, '2016-10-11', 432.67, 9.54, 84.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 876, '2018-05-24', 543.78, 11.87, 89.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 219, '2020-02-19', 654.89, 13.76, 86.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 138, '2017-01-07', 765.92, 15.89, 93.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 453, '2019-04-30', 876.34, 17.54, 98.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (76, 56, '2023-12-09', 374.02, 4.09, 52.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 258, '2008-05-21', 281.99, 4.74, 30.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (14, 505, '2007-06-07', 17.67, 12.82, 47.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (26, 788, '2020-10-28', 361.16, 4.38, 21.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 997, '2010-07-02', 636.39, 4.98, 77.8)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (98, 861, '2024-12-12', 34.12, 7.24, 64.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (71, 511, '2004-08-03', 581.42, 9.07, 21.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (3, 380, '2016-09-29', 850.44, 11.37, 67.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (20, 660, '2018-05-10', 30.03, 2.5, 82.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (67, 270, '2001-09-14', 109.98, 9.8, 11.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (27, 314, '2020-01-31', 146.02, 10.75, 91.8)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 536, '2019-10-25', 156.2, 1.79, 68.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 240, '2003-12-12', 586.71, 9.85, 6.8)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (54, 969, '2006-10-30', 898.77, 9.8, 23.5)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 910, '2014-03-11', 760.76, 13.09, 41.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 919, '2018-01-07', 807.28, 1.13, 19.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (1, 686, '2019-10-26', 471.43, 13.18, 0.8)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (65, 91, '2004-08-25', 567.34, 9.99, 27.5)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (5, 178, '2004-03-16', 871.7, 8.78, 32.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (72, 407, '2023-04-10', 824.05, 11.43, 62.2)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 524, '2002-03-12', 392.02, 7.05, 62.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (76, 46, '2016-09-13', 785.57, 4.3, 82.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (77, 791, '2007-11-21', 877.79, 2.18, 92.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (4, 505, '2015-06-16', 773.36, 4.88, 92.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (80, 641, '2000-11-11', 994.29, 8.08, 72.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (32, 351, '2023-08-19', 20.07, 12.04, 38.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (1, 444, '2005-11-25', 705.61, 12.71, 56.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (12, 229, '2004-02-20', 990.53, 1.63, 7.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (4, 10, '2002-05-01', 169.41, 8.71, 94.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 769, '2009-08-05', 877.36, 7.59, 66.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (79, 860, '2012-08-10', 148.0, 9.21, 73.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (19, 261, '2019-12-15', 723.18, 6.95, 47.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (82, 141, '2021-01-23', 282.3, 13.52, 97.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (39, 965, '2014-07-09', 865.57, 3.14, 53.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (33, 468, '2024-07-20', 378.61, 11.03, 96.5)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (90, 907, '2014-06-15', 650.87, 12.09, 46.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (42, 223, '2017-02-14', 480.39, 8.78, 81.2)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 550, '2011-04-03', 145.71, 5.45, 16.5)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (22, 671, '2008-12-29', 498.21, 6.37, 99.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (54, 822, '2013-10-12', 158.76, 2.16, 87.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (67, 800, '2006-11-18', 908.6, 7.92, 79.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 163, '2002-09-25', 763.72, 5.33, 94.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (44, 691, '2010-07-31', 176.04, 7.98, 28.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (11, 153, '2017-05-15', 426.32, 4.21, 82.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (76, 369, '2015-08-22', 648.05, 11.58, 89.8)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (26, 507, '2003-06-07', 618.82, 11.44, 18.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 427, '2010-03-09', 510.96, 5.12, 45.8)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (59, 126, '2024-02-02', 16.32, 11.29, 23.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (2, 610, '2006-01-17', 390.08, 11.15, 85.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (41, 444, '2018-09-05', 171.12, 5.83, 39.5)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (13, 889, '2000-03-29', 214.09, 12.91, 26.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (34, 788, '2005-06-13', 741.62, 10.09, 65.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 340, '2016-04-20', 763.72, 4.33, 57.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (79, 500, '2013-11-08', 795.54, 13.77, 82.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (9, 692, '2001-07-26', 525.27, 13.12, 80.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (17, 816, '2004-12-02', 383.31, 13.29, 95.2)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (60, 812, '2024-01-14', 949.03, 5.4, 99.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (64, 950, '2019-05-09', 61.11, 6.9, 72.2)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (24, 630, '2008-10-29', 583.41, 10.1, 58.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (48, 190, '2012-12-08', 899.01, 7.54, 70.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (47, 388, '2006-02-22', 399.69, 10.65, 18.7)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (27, 88, '2003-11-05', 956.44, 2.5, 49.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (72, 233, '2004-04-20', 722.89, 1.77, 23.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (88, 403, '2016-05-26', 775.89, 11.77, 77.5)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (7, 290, '2009-12-07', 472.16, 10.72, 38.0)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (82, 645, '2015-02-17', 144.6, 4.82, 62.4)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (3, 975, '2012-05-24', 401.62, 5.7, 56.9)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (12, 127, '2004-06-30', 726.53, 8.1, 64.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 54, '2011-02-13', 155.32, 12.04, 14.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 366, '2023-11-17', 927.97, 1.62, 22.6)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (33, 914, '2005-10-01', 616.77, 12.96, 63.1)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 862, '2000-04-16', 456.51, 6.02, 19.3)",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (71, 872, '2016-05-18', 811.18, 5.69, 82.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (70, 382, '2020-01-18', 413.62, 5.38, 54.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (30, 311, '2005-11-22', 137.23, 11.93, 89.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 340, '2006-07-09', 419.18, 4.33, 99.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 989, '2003-10-07', 53.36, 9.76, 57.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (41, 654, '2000-06-24', 15.3, 15.3, 49.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 471, '2018-12-01', 347.46, 1.84, 98.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (3, 342, '2014-05-22', 234.7, 5.45, 29.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (92, 812, '2009-10-30', 59.74, 13.8, 2.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (65, 904, '2000-03-03', 858.31, 1.99, 3.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 253, '2004-09-16', 244.97, 3.36, 64.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (69, 413, '2023-03-16', 397.6, 11.89, 33.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 73, '2021-11-16', 563.35, 3.38, 27.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (20, 638, '2012-12-23', 694.24, 15.56, 21.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (53, 331, '2011-11-24', 357.37, 12.05, 11.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (37, 963, '2011-06-12', 601.77, 7.86, 47.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (88, 139, '2020-06-04', 921.04, 15.87, 31.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (49, 213, '2005-09-10', 910.85, 15.63, 51.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (22, 713, '2013-08-31', 21.81, 8.02, 31.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (24, 566, '2006-10-13', 554.37, 7.43, 32.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (75, 243, '2016-02-26', 22.38, 9.19, 39.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (25, 104, '2011-12-17', 684.85, 11.32, 19.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (69, 373, '2015-02-19', 30.02, 1.22, 49.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (40, 113, '2014-07-24', 417.91, 11.82, 34.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (89, 836, '2014-02-18', 602.35, 7.16, 68.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (16, 55, '2017-09-26', 687.69, 3.93, 96.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 725, '2000-01-20', 635.55, 13.99, 52.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (84, 718, '2007-09-03', 871.5, 7.56, 58.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 773, '2018-03-15', 320.22, 11.49, 58.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (69, 728, '2006-06-23', 118.82, 11.96, 13.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 87, '2002-04-12', 560.07, 1.15, 58.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (51, 953, '2024-11-21', 506.53, 10.51, 63.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (62, 150, '2011-06-27', 662.28, 5.47, 5.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 425, '2001-07-23', 203.49, 6.61, 34.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 851, '2008-03-12', 651.72, 3.16, 12.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (74, 602, '2004-09-18', 353.31, 2.24, 63.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (94, 782, '2008-05-05', 401.35, 1.5, 80.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (82, 189, '2003-12-17', 368.48, 10.39, 48.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (32, 902, '2010-01-14', 470.56, 13.97, 15.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 891, '2012-08-09', 389.42, 10.99, 44.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (70, 658, '2006-10-31', 14.85, 5.6, 8.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 877, '2013-06-26', 101.06, 15.7, 63.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (90, 753, '2011-05-03', 482.84, 15.92, 43.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (80, 571, '2010-11-19', 332.74, 8.84, 56.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (86, 326, '2009-10-01', 576.28, 1.34, 24.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (27, 127, '2019-12-29', 100.33, 13.34, 38.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (65, 611, '2007-03-22', 645.6, 15.78, 77.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (53, 241, '2002-05-14', 504.15, 14.05, 29.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 637, '2013-04-25', 17.29, 15.01, 60.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (38, 811, '2016-04-07', 599.43, 13.52, 94.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (39, 661, '2004-08-28', 148.66, 10.48, 66.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (58, 803, '2005-12-19', 632.22, 7.22, 79.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (92, 716, '2014-07-30', 828.8, 3.27, 81.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (95, 152, '2015-11-09', 648.28, 6.65, 69.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (70, 781, '2008-10-23', 538.49, 7.5, 78.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (64, 116, '2003-05-26', 280.18, 15.02, 37.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (24, 392, '2017-03-14', 547.04, 2.41, 72.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 991, '2014-12-05', 837.88, 7.06, 84.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 612, '2000-02-16', 233.93, 6.34, 48.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (64, 881, '2011-10-20', 832.03, 10.18, 69.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (68, 661, '2018-05-08', 796.25, 11.57, 30.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 862, '2004-01-11', 562.01, 4.03, 16.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (39, 674, '2015-07-29', 956.03, 9.72, 69.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 773, '2008-06-16', 360.95, 10.79, 94.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (27, 191, '2001-12-22', 801.34, 13.56, 7.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 562, '2017-01-18', 517.96, 11.31, 45.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (43, 132, '2005-04-30', 146.6, 13.02, 89.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (44, 412, '2004-07-13', 467.05, 8.08, 38.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (95, 971, '2006-03-07', 575.52, 10.13, 60.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 892, '2015-12-22', 680.11, 12.79, 40.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (67, 693, '2010-09-18', 299.01, 15.31, 45.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 234, '2003-07-30', 489.53, 4.89, 82.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (77, 711, '2002-10-23', 672.07, 13.67, 90.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (58, 936, '2001-05-05', 471.23, 4.43, 9.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (73, 647, '2005-11-03', 264.72, 11.14, 77.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (42, 593, '2018-08-09', 383.47, 8.7, 86.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (37, 361, '2016-09-29', 527.85, 8.54, 92.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 928, '2006-12-02', 711.68, 7.56, 73.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (80, 593, '2008-06-08', 531.7, 5.72, 46.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 458, '2003-09-23', 780.83, 1.47, 86.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 833, '2010-04-25', 570.01, 15.03, 65.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (93, 443, '2007-10-12', 307.42, 12.97, 67.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (60, 541, '2009-01-17', 463.54, 8.85, 86.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (55, 839, '2015-02-22', 343.18, 14.76, 31.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (71, 941, '2004-04-15', 466.13, 3.33, 2.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (83, 821, '2014-07-21', 177.86, 2.6, 58.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 532, '2013-12-09', 925.25, 3.69, 96.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (32, 612, '2010-10-17', 255.34, 13.02, 90.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (74, 241, '2000-01-30', 627.69, 7.48, 89.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (77, 103, '2011-08-03', 479.51, 8.52, 16.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (26, 247, '2003-06-25', 123.48, 4.18, 99.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (72, 469, '2007-11-11', 958.11, 7.33, 57.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (86, 154, '2016-09-24', 100.95, 13.17, 22.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (27, 714, '2014-04-26', 558.53, 12.17, 67.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (43, 871, '2009-10-29', 101.42, 10.11, 50.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 223, '2005-02-14', 461.15, 8.45, 79.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 186, '2008-07-19', 880.62, 6.93, 79.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (54, 602, '2011-03-15', 512.72, 11.03, 85.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (69, 356, '2019-05-02', 889.67, 2.62, 63.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (64, 262, '2002-11-08', 251.31, 3.04, 79.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 732, '2006-04-23', 837.12, 11.29, 11.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (46, 143, '2017-10-01', 291.26, 13.61, 35.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 392, '2009-09-03', 450.44, 11.16, 48.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (39, 572, '2005-03-13', 766.51, 6.07, 68.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 378, '2021-12-08', 620.32, 3.36, 85.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 341, '2001-03-05', 343.84, 5.85, 54.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (5, 460, '2015-06-20', 834.43, 8.36, 94.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (100, 621, '2013-01-27', 677.1, 7.4, 60.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (22, 777, '2012-04-05', 412.22, 13.74, 60.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (38, 511, '2018-06-29', 90.23, 6.74, 23.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 214, '2004-01-11', 230.82, 12.84, 17.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (22, 203, '2006-02-21', 539.03, 13.03, 71.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 337, '2015-10-21', 323.8, 7.72, 63.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (85, 469, '2001-04-29', 994.65, 3.03, 38.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (46, 91, '2024-10-26', 384.5, 1.98, 26.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 321, '2022-12-29', 333.98, 5.35, 9.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 89, '2004-01-04', 837.69, 15.07, 47.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (15, 627, '2016-11-16', 961.08, 4.02, 98.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 403, '2015-07-07', 56.55, 12.59, 99.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (59, 527, '2024-09-24', 512.48, 1.98, 59.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (13, 730, '2017-08-12', 259.9, 6.67, 5.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (12, 749, '2024-08-20', 950.29, 12.27, 37.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 156, '2008-07-23', 239.0, 13.17, 94.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (65, 627, '2021-02-16', 428.52, 11.93, 75.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (68, 776, '2020-02-23', 960.84, 9.42, 75.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 373, '2003-05-26', 636.01, 10.96, 30.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (32, 68, '2012-11-09', 409.16, 3.89, 78.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (74, 592, '2024-07-21', 800.67, 13.14, 79.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (15, 730, '2006-08-18', 500.24, 14.05, 97.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (94, 605, '2002-05-17', 132.2, 8.46, 51.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (1, 369, '2002-08-31', 879.6, 6.26, 4.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 799, '2004-06-01', 24.95, 14.44, 60.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (23, 155, '2015-07-10', 749.65, 3.13, 72.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (42, 862, '2003-12-31', 280.09, 1.16, 47.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (16, 699, '2016-10-10', 748.7, 14.95, 38.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (49, 282, '2007-05-27', 330.12, 6.37, 40.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (69, 946, '2002-12-20', 850.41, 8.62, 82.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (29, 346, '2012-04-25', 396.22, 4.91, 31.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (54, 711, '2001-09-15', 168.89, 3.01, 42.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (46, 854, '2020-07-27', 500.72, 10.49, 22.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (77, 637, '2007-04-04', 335.67, 11.03, 16.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (32, 654, '2013-03-12', 931.31, 13.42, 93.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (33, 88, '2005-11-18', 599.64, 2.46, 80.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (17, 238, '2022-01-08', 433.88, 9.35, 57.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (18, 799, '2001-02-17', 99.67, 12.47, 25.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (91, 118, '2009-06-22', 521.26, 11.08, 35.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (19, 571, '2019-09-05', 162.35, 4.03, 76.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (84, 479, '2008-08-11', 772.59, 4.22, 19.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (45, 951, '2007-07-18', 609.8, 14.77, 86.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (81, 623, '2010-11-30', 486.64, 11.57, 56.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (14, 888, '2006-10-14', 121.03, 5.45, 61.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (12, 211, '2003-12-19', 418.06, 6.83, 28.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 299, '2024-02-26', 881.16, 3.57, 79.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (26, 505, '2002-06-10', 274.95, 13.8, 65.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (47, 626, '2018-07-31', 676.11, 7.32, 86.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 348, '2015-04-28', 77.24, 13.43, 88.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (83, 191, '2010-01-02', 391.18, 1.71, 14.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (79, 82, '2016-03-11', 238.11, 8.3, 30.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (25, 98, '2017-12-24', 823.4, 3.64, 74.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (76, 490, '2005-11-20', 432.36, 10.79, 29.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (27, 336, '2004-09-13', 713.17, 4.12, 39.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (11, 867, '2023-08-14', 161.64, 9.38, 46.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (43, 183, '2003-01-30', 953.76, 2.32, 92.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (63, 98, '2006-02-17', 330.27, 12.46, 64.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (87, 973, '2011-07-21', 485.85, 7.02, 41.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (57, 409, '2014-09-05', 84.52, 14.88, 83.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 522, '2009-11-08', 448.17, 2.01, 13.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (59, 857, '2010-05-04', 541.44, 10.81, 99.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (24, 872, '2008-04-18', 189.29, 11.31, 65.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (31, 213, '2003-03-22', 584.12, 6.34, 80.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (65, 97, '2011-01-19', 275.93, 2.04, 75.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (88, 755, '2012-10-23', 614.56, 3.71, 47.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (29, 338, '2004-08-16', 123.68, 8.98, 83.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (50, 725, '2015-09-19', 894.87, 14.23, 15.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (47, 210, '2009-02-07', 460.78, 5.77, 61.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (56, 191, '2017-05-30', 315.12, 6.09, 87.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (23, 343, '2014-12-15', 984.55, 12.05, 33.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (35, 503, '2005-07-02', 752.74, 11.54, 52.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (15, 914, '2018-11-26', 109.42, 6.55, 74.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (61, 74, '2013-08-14', 398.97, 8.27, 84.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (21, 509, '2003-04-11', 724.86, 7.12, 69.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (78, 946, '2006-06-02', 831.94, 14.19, 55.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (82, 129, '2010-09-28', 662.99, 10.01, 34.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (42, 607, '2022-07-12', 367.25, 9.67, 78.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (43, 881, '2007-01-14', 112.78, 5.96, 22.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (25, 208, '2008-12-04', 759.45, 8.36, 90.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (67, 440, '2014-04-08', 505.16, 13.29, 43.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (86, 535, '2009-03-16', 622.31, 2.65, 63.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (22, 654, '2011-05-22', 992.74, 7.45, 48.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (34, 902, '2023-02-11', 490.1, 5.22, 66.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (66, 773, '2015-06-18', 868.94, 11.66, 86.1);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (51, 172, '2012-08-09', 394.62, 6.22, 95.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (58, 845, '2006-11-13', 126.61, 9.78, 12.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (39, 176, '2017-09-07', 779.67, 13.83, 29.5);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (28, 524, '2013-10-19', 387.16, 12.98, 54.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (44, 291, '2001-06-23', 682.68, 7.1, 83.3);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (16, 85, '2003-09-10', 953.29, 13.51, 76.4);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (37, 879, '2005-04-17', 239.33, 8.55, 39.6);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (76, 361, '2016-08-20', 682.76, 14.61, 67.2);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (52, 509, '2019-07-26', 860.28, 1.74, 94.9);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (48, 312, '2011-12-18', 594.19, 7.24, 32.8);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (55, 677, '2012-03-05', 429.85, 4.66, 88.7);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (13, 843, '2008-04-14', 763.92, 13.31, 23.0);",
                "insert into VisitRecord (AttractionID, VisitorID, VisitTime, MoneySpent, TimeSpent, Rating) values (77, 402, '2024-10-08', 114.12, 9.55, 89.1);"

                );

        try (Connection conn = con) {
            conn.setAutoCommit(false); // Begin transaction

            for (String insertStatement : insertStatements) {
                PreparedStatement stmt = conn.prepareStatement(insertStatement);
                stmt.executeUpdate();
                stmt.close();
            }

            conn.commit(); // Commit transaction
            System.out.println("VisitRecord data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void showDistricts(Connection con){

        String sql = "select * from portal_database.VisitRecord;";

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
                    System.out.println(resultSet.getString("TimeSpent"));
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
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Kunsthistorisches Museum Visit', '2023-06-01', 'Maria-Theresien-Platz', 'museum visit', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Open Air Jazz Concert', '2023-07-15', 'Donauinsel', 'music festival', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Schönbrunn Palace Outdoor Cinema', '2023-08-05', 'Schönbrunner Schloßstraße', 'movie screening', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Belvedere Art Exhibition', '2023-09-10', 'Prinz Eugen-Straße', 'art exhibition', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Philharmonic Park Concert', '2023-06-20', 'Musikvereinsplatz', 'classical concert', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Street Art Tour', '2023-07-25', 'Breite Gasse', 'art tour', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Naschmarkt Food Festival', '2023-08-12', 'Naschmarkt', 'food festival', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna State Opera Open Day', '2023-09-01', 'Opernring', 'open day', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('MuseumsQuartier Summer Lounge', '2023-07-03', 'Museumsplatz', 'cultural gathering', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Outdoor Film Festival at Rathausplatz', '2023-08-22', 'Rathausplatz', 'film festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Historic Tram Ride Event', '2023-09-18', 'Ringstraße', 'cultural event', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Literature Festival', '2023-06-30', 'Heldenplatz', 'literature festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Danube Island Kite Festival', '2023-07-12', 'Donauinsel', 'outdoor activity', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Volksgarten Classical Music Night', '2023-08-18', 'Volksgarten', 'classical music', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Mariahilfer Street Art Show', '2023-09-22', 'Mariahilfer Straße', 'art show', 6);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Jazz Floor Festival', '2023-06-28', 'Spittelberggasse', 'jazz festival', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopold Museum Private Viewing', '2023-07-08', 'Museumsplatz', 'private museum tour', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Grinzing Wine Tasting', '2023-08-01', 'Grinzinger Straße', 'wine tasting', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Classical Recital', '2023-09-05', 'Währinger Straße', 'classical concert', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hundertwasserhaus Tour', '2023-06-12', 'Kegelgasse', 'guided tour', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Karlsplatz Art Fair', '2023-07-14', 'Karlsplatz', 'art fair', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donaukanal Street Party', '2023-08-08', 'Donaukanal', 'street festival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Prater Festival of Lights', '2023-09-15', 'Prater', 'light festival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('St. Stephens Cathedral Choir Performance', '2023-06-20', 'Stephansplatz', 'choir concert', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Viennese Coffee House Culture Day', '2023-07-25', 'Freihausviertel', 'cultural celebration', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Augarten Porcelain Workshop', '2023-08-20', 'Obere Augartenstraße', 'craft workshop', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Bio Center Science Day', '2023-09-03', 'Dr.-Bohr-Gasse', 'science festival', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ringstrasse Architectural Tour', '2023-06-25', 'Ringstraße', 'guided walk', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Woods Hiking Day', '2023-07-18', 'Vienna Woods', 'hiking event', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Kahlenberg Sunset Concert', '2023-08-27', 'Kahlenberg', 'outdoor concert', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Zentralfriedhof Guided History Tour', '2023-09-09', 'Simmeringer Hauptstraße', 'historical tour', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Fashion Week Vienna', '2023-06-15', 'MuseumsQuartier', 'fashion event', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Outdoor Shakespeare Festival', '2023-07-29', 'Burggarten', 'theatre festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna International Film Festival', '2023-08-18', 'Gartenbaukino', 'film festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ferris Wheel Dinner Event', '2023-09-21', 'Prater', 'dining event', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Symphony Open Air', '2023-10-05', 'Schönbrunn Palace', 'classical music', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Albertina Modern Gallery Night', '2023-10-15', 'Karlsplatz 5', 'art exhibition', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Döbling Harvest Festival', '2023-10-22', 'Heiligenstädter Park', 'cultural festival', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Otto Wagner Villa Tour', '2023-11-01', 'Hüttelbergstraße', 'architecture tour', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Art Week', '2023-11-20', 'Various Locations', 'art week', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neubau Design Market', '2023-12-05', 'Neubaugasse', 'design market', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Wine and Jazz Evening', '2023-10-30', 'Landstraßer Hauptstraße', 'wine and jazz', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Chamber Orchestra Concert', '2023-11-10', 'Kalvarienberggasse', 'chamber music', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Favoriten Street Art and Food Tour', '2023-11-25', 'Quellenstraße', 'art and food tour', 10);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Film and History Night', '2023-12-15', 'Margaretenplatz', 'film night', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Christmas Market at Rathausplatz', '2023-12-01', 'Rathausplatz', 'christmas market', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Medieval Festival at Heldenplatz', '2023-10-08', 'Heldenplatz', 'medieval festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Floridsdorf Jazz Night', '2023-11-05', 'Am Spitz', 'jazz night', 21);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Penzing Classical Music Morning', '2023-10-21', 'Linzer Straße', 'classical music', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering Local Theater Performance', '2023-11-12', 'Simmeringer Hauptstraße', 'theater', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wieden Poetry Slam', '2023-12-10', 'Karlsplatz', 'poetry slam', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Brigittenau Festival of Lights', '2023-10-28', 'Handelskai', 'light festival', 20);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Meidling Autumn Arts Festival', '2023-11-15', 'Meidlinger Hauptstraße', 'arts festival', 12);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Kunst Haus Wien Green Day', '2023-12-05', 'Untere Weißgerberstraße', 'environmental event', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt Historical Reenactment', '2023-11-30', 'Prater Hauptallee', 'historical reenactment', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Opernring New Year’s Eve Concert', '2023-12-31', 'Opernring', 'new year concert', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Josefstadt Antique Book Fair', '2023-10-12', 'Josefstädter Straße', 'book fair', 8);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Student Film Festival', '2023-11-19', 'Währinger Straße', 'film festival', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hietzing Spring Festival', '2023-10-14', 'Maxingstraße', 'spring festival', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Rudolfsheim-Fünfhaus Art and Craft Fair', '2023-12-20', 'Reindorfgasse', 'craft fair', 15);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Millennium Art Exhibition', '2000-09-15', 'MuseumsQuartier', 'art exhibition', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wien River Music Festival', '2002-08-05', 'Wienflussweg', 'music festival', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna History Walk', '2004-06-12', 'Michaelerplatz', 'guided walk', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donaustadt Nature Walk', '2006-04-22', 'Lobau', 'nature walk', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Belvedere Spring Festival', '2008-05-09', 'Prinz Eugen-Straße', 'cultural festival', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Kahlenberg Jazz Nights', '2010-07-17', 'Kahlenberg', 'jazz night', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Viennese Ballroom Dancing', '2012-12-31', 'Hofburg Palace', 'ballroom dancing', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Summer Screenings in the Park', '2014-08-15', 'Volksgarten', 'film screening', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neue Donau Water Festival', '2016-09-03', 'Neue Donau', 'water sports', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Poetry Reading', '2018-11-25', 'Hernalser Hauptstraße', 'literature festival', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Design Week', '2020-10-01', 'Freyung', 'design week', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Museum of Natural History Night Tour', '2021-07-15', 'Burgring 7', 'night tour', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ottakring Craft Beer Festival', '2022-06-10', 'Ottakringer Platz', 'beer festival', 16);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Autumn Art Tour', '2023-09-21', 'Rennweg', 'art tour', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Christmas in Vienna', '2024-12-24', 'Stephansplatz', 'christmas celebration', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Marathon', '2001-05-06', 'Ringstraße', 'sports event', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Schubert Music Festival', '2003-11-19', 'Schubert Park', 'music festival', 18);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Literature Fair', '2005-03-15', 'Mariahilfer Straße', 'literature fair', 6);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Outdoor Sculpture Exhibition', '2007-07-23', 'Karlsplatz', 'art exhibition', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Culinary Nights', '2011-08-30', 'Margaretenplatz', 'food festival', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Light Festival', '2013-12-15', 'Donaukanal', 'light festival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Art Night', '2015-10-25', 'Währinger Straße', 'art night', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Film Festival on Rathausplatz', '2017-08-20', 'Rathausplatz', 'film festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering Spring Concert', '2019-05-15', 'Simmeringer Hauptstraße', 'concert', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Writers Festival', '2000-04-20', 'Burggasse', 'literature festival', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Währing Autumn Jazz Session', '2001-09-11', 'Gersthofer Straße', 'jazz festival', 18);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donauinsel Pop Concert', '2002-07-05', 'Donauinsel', 'pop concert', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Gourmet Festival Wien', '2003-05-25', 'Naschmarkt', 'gourmet festival', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Film Noir Festival', '2004-10-12', 'Burg Kino', 'film festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Floridsdorf Spring Flower Show', '2005-03-18', 'Am Spitz', 'flower show', 21);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Rudolfsheim-Fünfhaus Cultural Parade', '2006-06-30', 'Reindorfgasse', 'cultural parade', 15);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ottakring Art and Craft Market', '2007-12-07', 'Thaliastraße', 'craft market', 16);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Modern Dance Festival', '2008-11-15', 'MuseumsQuartier', 'dance festival', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donaustadt Dragon Boat Race', '2009-08-22', 'Alte Donau', 'boat race', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Classical Music Days', '2010-05-09', 'Hernalser Hauptstraße', 'classical music', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Literary Readings', '2011-04-14', 'Landstraßer Hauptstraße', 'literary reading', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering Summer Festival', '2012-07-17', 'Simmeringer Platz', 'summer festival', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Penzing Winter Markets', '2013-12-05', 'Hütteldorfer Straße', 'winter market', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Fashion Week', '2014-09-25', 'Margaretenstraße', 'fashion week', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt Circus Festival', '2015-06-12', 'Prater', 'circus festival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Historical Reenactment', '2016-08-03', 'Schönbrunn Palace', 'historical event', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Meidling Community Art Project', '2017-10-10', 'Meidlinger Markt', 'community art', 12);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Josefstadt Jazz Night', '2018-03-20', 'Josefstädter Straße', 'jazz night', 8);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Science Expo', '2019-02-15', 'Währinger Straße', 'science expo', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neubau International Art Festival', '2020-11-28', 'Neubaugasse', 'international art', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wieden Summer Opera', '2021-07-21', 'Karlsplatz', 'opera event', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hietzing Outdoor Gallery', '2022-04-15', 'Hietzinger Hauptstraße', 'outdoor gallery', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Favoriten Techno Parade', '2023-05-30', 'Quellenstraße', 'techno parade', 10);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Brigittenau Light Sculpture Exhibition', '2024-01-25', 'Brigittenauer Lände', 'light exhibition', 20);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Book Fair', '2000-10-21', 'Mariahilfer Straße', 'book fair', 6);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Prater Spring Carnival', '2001-03-15', 'Prater', 'carnival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Jazz Festival', '2002-05-10', 'Alser Straße', 'jazz festival', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt Art Walk', '2003-04-20', 'Prater Hauptallee', 'art walk', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Classical Music Marathon', '2004-11-11', 'Musikverein', 'classical music', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hietzing Harvest Festival', '2005-09-25', 'Hietzinger Hauptstraße', 'harvest festival', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Penzing Autumn Art Fair', '2006-10-08', 'Linzer Straße', 'art fair', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Döbling Wine and Art Festival', '2007-08-19', 'Grinzinger Straße', 'wine and art', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Währing Classical Music Days', '2008-06-06', 'Gersthofer Straße', 'classical music', 18);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donauinsel Film Festival', '2009-07-14', 'Donauinsel', 'film festival', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Fashion Show', '2010-02-27', 'Margaretenplatz', 'fashion show', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Favoriten Spring Parade', '2011-04-23', 'Quellenstraße', 'parade', 10);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neubau Photography Exhibition', '2012-03-08', 'Neubaugasse', 'photography exhibition', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ottakring Summer Dance', '2013-07-19', 'Thaliastraße', 'dance event', 16);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Meidling Cultural Festival', '2014-05-15', 'Meidlinger Hauptstraße', 'cultural festival', 12);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering History Days', '2015-09-10', 'Simmeringer Hauptstraße', 'history event', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Art and Jazz Night', '2016-08-21', 'Landstraßer Hauptstraße', 'art and jazz', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Literary Salon', '2017-01-12', 'Hernalser Hauptstraße', 'literary salon', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Rudolfsheim-Fünfhaus Music Festival', '2018-12-05', 'Reindorfgasse', 'music festival', 15);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Floridsdorf Community Art Days', '2019-10-20', 'Am Spitz', 'community art', 21);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Josefstadt Autumn Music Series', '2020-11-07', 'Josefstädter Straße', 'music series', 8);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wieden Artisan Market', '2021-06-15', 'Karlsplatz', 'artisan market', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Brigittenau Lantern Festival', '2022-02-11', 'Brigittenauer Lände', 'lantern festival', 20);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna International Arts Festival', '2023-07-20', 'Various Locations', 'arts festival', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt New Years Eve Party', '2024-12-31', 'Prater', 'new years eve', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Opera Ball', '2000-02-14', 'Wiener Staatsoper', 'ball', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donauinsel Rock Festival', '2001-07-08', 'Donauinsel', 'music festival', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wieden Winter Theatre Festival', '2002-12-11', 'Theater an der Wien', 'theatre festival', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Josefstadt Autumn Jazz Nights', '2003-09-20', 'Theater in der Josefstadt', 'jazz night', 8);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Biennale for Art and Technology', '2004-06-18', 'MuseumsQuartier', 'art and tech', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt Street Food Market', '2005-05-03', 'Prater Hauptallee', 'food market', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Döbling Garden Festival', '2006-04-14', 'Türkenschanzpark', 'garden festival', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Architecture Day', '2007-10-05', 'Belvedere Palace', 'architecture day', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ottakring Cultural Heritage Day', '2008-09-15', 'Ottakringer Brauerei', 'heritage day', 16);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Global Film Festival', '2009-11-10', 'Votiv Kino', 'film festival', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Harvest Day', '2010-10-12', 'Pezzlpark', 'harvest day', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neubau Culture and Art Days', '2011-08-22', 'Spittelberg', 'culture and art', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering Historical Fair', '2012-06-07', 'Simmeringer Hauptstraße', 'historical fair', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Meidling Spring Art Exhibition', '2013-03-28', 'Meidlinger Markt', 'art exhibition', 12);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Penzing Film Appreciation Night', '2014-07-15', 'Technisches Museum', 'film night', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Literary Weekend', '2015-04-10', 'Margaretenplatz', 'literature event', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ottakring Art and Craft Market', '2007-12-07', 'Thaliastraße', 'craft market', 16);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Modern Dance Festival', '2008-11-15', 'MuseumsQuartier', 'dance festival', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donaustadt Dragon Boat Race', '2009-08-22', 'Alte Donau', 'boat race', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Classical Music Days', '2010-05-09', 'Hernalser Hauptstraße', 'classical music', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Literary Readings', '2011-04-14', 'Landstraßer Hauptstraße', 'literary reading', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering Summer Festival', '2012-07-17', 'Simmeringer Platz', 'summer festival', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Penzing Winter Markets', '2013-12-05', 'Hütteldorfer Straße', 'winter market', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Fashion Week', '2014-09-25', 'Margaretenstraße', 'fashion week', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt Circus Festival', '2015-06-12', 'Prater', 'circus festival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Historical Reenactment', '2016-08-03', 'Schönbrunn Palace', 'historical event', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Meidling Community Art Project', '2017-10-10', 'Meidlinger Markt', 'community art', 12);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Josefstadt Jazz Night', '2018-03-20', 'Josefstädter Straße', 'jazz night', 8);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Science Expo', '2019-02-15', 'Währinger Straße', 'science expo', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neubau International Art Festival', '2020-11-28', 'Neubaugasse', 'international art', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wieden Summer Opera', '2021-07-21', 'Karlsplatz', 'opera event', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hietzing Outdoor Gallery', '2022-04-15', 'Hietzinger Hauptstraße', 'outdoor gallery', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Favoriten Techno Parade', '2023-05-30', 'Quellenstraße', 'techno parade', 10);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Brigittenau Light Sculpture Exhibition', '2024-01-25', 'Brigittenauer Lände', 'light exhibition', 20);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Book Fair', '2000-10-21', 'Mariahilfer Straße', 'book fair', 6);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Prater Spring Carnival', '2001-03-15', 'Prater', 'carnival', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Alsergrund Jazz Festival', '2002-05-10', 'Alser Straße', 'jazz festival', 9);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Leopoldstadt Art Walk', '2003-04-20', 'Prater Hauptallee', 'art walk', 2);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Vienna Classical Music Marathon', '2004-11-11', 'Musikverein', 'classical music', 1);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hietzing Harvest Festival', '2005-09-25', 'Hietzinger Hauptstraße', 'harvest festival', 13);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Penzing Autumn Art Fair', '2006-10-08', 'Linzer Straße', 'art fair', 14);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Döbling Wine and Art Festival', '2007-08-19', 'Grinzinger Straße', 'wine and art', 19);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Währing Classical Music Days', '2008-06-06', 'Gersthofer Straße', 'classical music', 18);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Donauinsel Film Festival', '2009-07-14', 'Donauinsel', 'film festival', 22);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Margareten Fashion Show', '2010-02-27', 'Margaretenplatz', 'fashion show', 5);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Favoriten Spring Parade', '2011-04-23', 'Quellenstraße', 'parade', 10);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Neubau Photography Exhibition', '2012-03-08', 'Neubaugasse', 'photography exhibition', 7);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Ottakring Summer Dance', '2013-07-19', 'Thaliastraße', 'dance event', 16);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Meidling Cultural Festival', '2014-05-15', 'Meidlinger Hauptstraße', 'cultural festival', 12);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Simmering History Days', '2015-09-10', 'Simmeringer Hauptstraße', 'history event', 11);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Landstraße Art and Jazz Night', '2016-08-21', 'Landstraßer Hauptstraße', 'art and jazz', 3);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Hernals Literary Salon', '2017-01-12', 'Hernalser Hauptstraße', 'literary salon', 17);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Rudolfsheim-Fünfhaus Music Festival', '2018-12-05', 'Reindorfgasse', 'music festival', 15);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Floridsdorf Community Art Days', '2019-10-20', 'Am Spitz', 'community art', 21);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Josefstadt Autumn Music Series', '2020-11-07', 'Josefstädter Straße', 'music series', 8);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Wieden Artisan Market', '2021-06-15', 'Karlsplatz', 'artisan market', 4);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Brigittenau Lantern Festival', '2022-02-11', 'Brigittenauer Lände', 'lantern festival', 20);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Autumn Crafts Fair', '2025-09-21', 'Ketzergasse', 'crafts fair', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Winter Arts Festival', '2026-12-01', 'Perchtoldsdorfer Straße', 'arts festival', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Spring Music Festival', '2027-04-15', 'Breitenfurter Straße', 'music festival', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Summer Theater', '2028-07-12', 'Atzgersdorfer Platz', 'summer theater', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Christmas Concert', '2029-12-10', 'Rodauner Straße', 'christmas concert', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Art Exhibition', '2030-11-08', 'Ketzergasse', 'art exhibition', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Film Screening', '2031-06-19', 'Liesingbachstraße', 'film screening', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Photography Workshop', '2032-08-22', 'Anton-Baumgartner-Straße', 'photography workshop', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Historical Tour', '2033-05-16', 'Alt-Erlaa', 'historical tour', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Community Fair', '2034-03-30', 'Liesing Park', 'community fair', 23);",
                "insert into Event (Name, Date, Address, Type, DistrictNr) values ('Liesing Outdoor Cinema', '2035-09-14', 'Maurer Hauptplatz', 'outdoor cinema', 23);"


                );

        try (Connection conn = con) {
            conn.setAutoCommit(false); // Begin transaction

            for (String insertStatement : insertStatements) {
                PreparedStatement stmt = conn.prepareStatement(insertStatement);
                stmt.executeUpdate();
                stmt.close();
            }

            conn.commit(); // Commit transaction
            System.out.println("Event data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/

    /*
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
    }*/

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

