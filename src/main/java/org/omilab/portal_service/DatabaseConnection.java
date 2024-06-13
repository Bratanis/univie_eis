package org.omilab.portal_service;

import java.sql.*;

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

    public void showTables2(Connection con) {
        String sql = "SHOW TABLES";

        try {
            // Set the desired database context
            String useDatabaseSQL = "USE mysql";
            Statement useDatabaseStatement = con.createStatement();
            useDatabaseStatement.execute(useDatabaseSQL);

            // Create a Statement object with the SQL statement to show tables
            Statement statement = con.createStatement();

            // Execute the statement to retrieve the tables
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("mysql: ");
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

    public void createTables(Connection con) {
        String createDistrictTable = "CREATE TABLE IF NOT EXISTS District (" +
                "    Number INT PRIMARY KEY," +
                "    Name VARCHAR(100)" +
                ");";

        String createAttractionTable = "CREATE TABLE IF NOT EXISTS Attraction (" +
                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
                "    Name VARCHAR(255)," +
                "    Address VARCHAR(255)," +
                "    DistrictNr INT," +
                "    FOREIGN KEY (DistrictNr) REFERENCES District(Number)" +
                ");";

        String createVisitorTable = "CREATE TABLE IF NOT EXISTS Visitor (" +
                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
                "    Age INT CHECK (Age >= 0)," +
                "    Gender VARCHAR(50)," +
                "    Nationality VARCHAR(100)" +
                ");";

        String createEventTable = "CREATE TABLE IF NOT EXISTS Event (" +
                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
                "    Name VARCHAR(255)," +
                "    Date DATE," +
                "    Address VARCHAR(255)," +
                "    Type VARCHAR(100)," +
                "    DistrictNr INT," +
                "    FOREIGN KEY (DistrictNr) REFERENCES District(Number)" +
                ");";

        String createVisitRecordTable = "CREATE TABLE IF NOT EXISTS VisitRecord (" +
                "    ID INT AUTO_INCREMENT PRIMARY KEY," +
                "    AttractionID INT," +
                "    VisitorID INT," +
                "    VisitTime TIMESTAMP," +
                "    MoneySpent DECIMAL(10, 2)," +
                "    TimeSpent DECIMAL(10, 2) CHECK (TimeSpent >= 0 AND TimeSpent <= 24)," +
                "    Rating DECIMAL(3, 1) CHECK (Rating >= 0 AND Rating <= 100)," +
                "    FOREIGN KEY (AttractionID) REFERENCES Attraction(ID)," +
                "    FOREIGN KEY (VisitorID) REFERENCES Visitor(ID)" +
                ");";

        try {
            // Execute each table creation statement
            executeUpdate(con, createAttractionTable);
            executeUpdate(con, createVisitorTable);
            executeUpdate(con, createEventTable);
            executeUpdate(con, createVisitRecordTable);

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(Connection con, String sql) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }
    public void createDistricts(Connection con) {
        String sql = "CREATE TABLE District (" +
                "    Number INT PRIMARY KEY," +
                "    Name VARCHAR(100)" +
                ");";

        try {
            // Create a PreparedStatement object with the SQL statement
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Execute the PreparedStatement to create the table
            preparedStatement.executeUpdate();

            // Close the PreparedStatement
            preparedStatement.close();

            System.out.println("Table 'District' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential SQLException
        }
    }

}

